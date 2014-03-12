/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mentions.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.usersadmin.util.UsersAdminUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public class MentionsPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			if (!_isMentionsEnabled(themeDisplay.getSiteGroupId())) {
				return;
			}

			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				resourceRequest);
			HttpServletResponse response = PortalUtil.getHttpServletResponse(
				resourceResponse);

			JSONArray jsonArray = getJSONArray(request);

			response.setContentType(ContentTypes.APPLICATION_JSON);

			ServletResponseUtil.write(response, jsonArray.toString());
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return;
	}

	protected JSONArray getJSONArray(HttpServletRequest request)
		throws PortalException, SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String query = ParamUtil.getString(request, "query") + StringPool.STAR;

		Hits hits = UserLocalServiceUtil.search(
			themeDisplay.getCompanyId(), query, query, query, query,
			StringPool.BLANK, WorkflowConstants.STATUS_APPROVED, null, false, 0,
			100, (Sort)null);

		List<User> users = UsersAdminUtil.getUsers(hits);

		for (User user : users) {
			if (user.isDefaultUser() ||
				(themeDisplay.getUserId() == user.getUserId())) {

				continue;
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("fullName", user.getFullName());
			jsonObject.put("portraitURL", user.getPortraitURL(themeDisplay));
			jsonObject.put("screenName", user.getScreenName());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private boolean _isMentionsEnabled(long siteGroupId)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

		PortletPreferences companyPortletPreferences =
			PrefsPropsUtil.getPreferences(group.getCompanyId(), true);

		boolean companyMentionsEnabled = GetterUtil.getBoolean(
			companyPortletPreferences.getValue("mentionsEnabled", null), true);

		if (!companyMentionsEnabled) {
			return false;
		}

		return GetterUtil.getBoolean(
			group.getLiveParentTypeSettingsProperty("mentionsEnabled"), true);
	}

	private static Log _log = LogFactoryUtil.getLog(MentionsPortlet.class);

}