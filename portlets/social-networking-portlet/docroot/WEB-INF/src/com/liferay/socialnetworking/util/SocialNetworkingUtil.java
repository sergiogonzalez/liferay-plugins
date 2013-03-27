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

package com.liferay.socialnetworking.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.User;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Peter Borkuti
 */
public class SocialNetworkingUtil {

	public static String getUserProfileURL(
			long userId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		String urlPattern = PortletPropsValues.USER_PROFILE_URL;

		if (Validator.isNull(urlPattern)) {
			urlPattern = serviceContext.getPathFriendlyURLPublic();
			urlPattern = urlPattern.concat("/${liferay:userScreenName}/home");
		}

		User user = UserLocalServiceUtil.getUserById(userId);

		StringBundler sb = new StringBundler(3);

		sb.append(serviceContext.getPortalURL());
		sb.append(PortalUtil.getPathContext());
		sb.append(replaceVariables(user, urlPattern));

		return sb.toString();
	}

	public static String getWallLayoutFriendlyURL(User user)
		throws PortalException, SystemException {

		String wallLayoutFriendlyURL =
			PortletPropsValues.WALL_LAYOUT_FRIENDLY_URL;

		if (Validator.isNull(wallLayoutFriendlyURL)) {
			Group group = user.getGroup();

			long plid = LayoutLocalServiceUtil.getDefaultPlid(
				group.getGroupId(), false, PortletKeys.WALL);

			if (plid != 0) {
				Layout layout = LayoutLocalServiceUtil.getLayout(plid);

				wallLayoutFriendlyURL = layout.getFriendlyURL();
			}
		}

		return wallLayoutFriendlyURL;
	}

	private static String replaceVariables(User user, String urlPattern) {
		Map<String, String> variables = new HashMap<String, String>();

		variables.put("liferay:userId", String.valueOf(user.getUserId()));
		variables.put(
			"liferay:userScreenName", HtmlUtil.escapeURL(user.getScreenName()));

		return StringUtil.replace(
			urlPattern, StringPool.DOLLAR_AND_OPEN_CURLY_BRACE,
			StringPool.CLOSE_CURLY_BRACE, variables);
	}

}