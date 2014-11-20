/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.util;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBFolderServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortalPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Adolfo Pérez
 */
public class KBNavigationDisplayContext {

	public KBNavigationDisplayContext(
		HttpServletRequest request, PortletRequest portletRequest,
		PortalPreferences portalPreferences,
		PortletPreferences portletPreferences, KBArticle kbArticle) {

		_kbArticle = kbArticle;
		_portalPreferences = portalPreferences;
		_portletPreferences = portletPreferences;
		_portletRequest = portletRequest;
		_request = request;
	}

	public List<Long> getAncestorResourcePrimaryKeys() throws PortalException {
		List<Long> ancestorResourcePrimaryKeys = new ArrayList<Long>();

		if (_kbArticle != null) {
			KBArticle latestKBArticle =
				KBArticleLocalServiceUtil.getLatestKBArticle(
					_kbArticle.getResourcePrimKey(),
					WorkflowConstants.STATUS_APPROVED);

			ancestorResourcePrimaryKeys =
				latestKBArticle.getAncestorResourcePrimaryKeys();

			Collections.reverse(ancestorResourcePrimaryKeys);
		}
		else {
			ancestorResourcePrimaryKeys.add(
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		}

		return ancestorResourcePrimaryKeys;
	}

	public String getCurrentKBFolderURLTitle() throws PortalException {
		String currentKBFolderUrlTitle = _portalPreferences.getValue(
			PortletKeys.KNOWLEDGE_BASE_DISPLAY, "preferredKBFolderUrlTitle");

		long rootResourcePrimKey = getRootResourcePrimKey();

		if (rootResourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(
				rootResourcePrimKey);

			String pageTitle =
				getContentRootPrefix() + " " + kbFolder.getName();

			if (_kbArticle != null) {
				pageTitle = _kbArticle.getTitle() + " - " + pageTitle;
			}

			PortalUtil.setPageTitle(pageTitle, _request);

			currentKBFolderUrlTitle = kbFolder.getUrlTitle();
		}

		return currentKBFolderUrlTitle;
	}

	public long getRootResourcePrimKey() throws PortalException {
		if (_rootResourcePrimKey == null) {
			_rootResourcePrimKey = KBFolderConstants.DEFAULT_PARENT_FOLDER_ID;

			if (_kbArticle != null) {
				_rootResourcePrimKey = KnowledgeBaseUtil.getKBFolderId(
					_kbArticle.getParentResourceClassNameId(),
					_kbArticle.getParentResourcePrimKey());
			}

			if (_rootResourcePrimKey ==
					KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

				_rootResourcePrimKey = KnowledgeBaseUtil.getRootResourcePrimKey(
					_portletRequest,
					PortalUtil.getScopeGroupId(_portletRequest),
					getResourceClassNameId(), getResourcePrimKey());
			}
		}

		return _rootResourcePrimKey;
	}

	public boolean isShowNavigation() throws PortalException {
		boolean showNavigation = true;

		long scopeGroupId = PortalUtil.getScopeGroupId(_portletRequest);

		long rootResourcePrimKey = getRootResourcePrimKey();

		int kbArticleCount = KBArticleLocalServiceUtil.getKBArticlesCount(
			scopeGroupId, rootResourcePrimKey,
			WorkflowConstants.STATUS_APPROVED);

		if (kbArticleCount == 0) {
			showNavigation = false;
		}
		else if (kbArticleCount == 1) {
			List<KBArticle> kbArticles =
				KBArticleLocalServiceUtil.getKBArticles(
					scopeGroupId, rootResourcePrimKey,
					WorkflowConstants.STATUS_APPROVED, 0, 1, null);

			KBArticle navigationKBArticle = kbArticles.get(0);

			int navigationKBArticleChildCount =
				KBArticleLocalServiceUtil.getKBArticlesCount(
					scopeGroupId, navigationKBArticle.getResourcePrimKey(),
					WorkflowConstants.STATUS_APPROVED);

			if (navigationKBArticleChildCount == 0) {
				showNavigation = false;
			}
		}

		return showNavigation;
	}

	protected String getContentRootPrefix() {
		return GetterUtil.getString(
			_portletPreferences.getValue("contentRootPrefix", null));
	}

	protected long getResourceClassNameId() {
		if (_resourceClassNameId == null) {
			_resourceClassNameId = GetterUtil.getLong(
				_portletPreferences.getValue("resourceClassNameId", null),
				PortalUtil.getClassNameId(KBFolderConstants.getClassName()));
		}

		return _resourceClassNameId;
	}

	protected long getResourcePrimKey() {
		if (_resourcePrimKey == null) {
			_resourcePrimKey = GetterUtil.getLong(
				_portletPreferences.getValue("resourcePrimKey", null));
		}

		return _resourcePrimKey;
	}

	private final KBArticle _kbArticle;
	private final PortalPreferences _portalPreferences;
	private final PortletPreferences _portletPreferences;
	private final PortletRequest _portletRequest;
	private final HttpServletRequest _request;
	private Long _resourceClassNameId;
	private Long _resourcePrimKey;
	private Long _rootResourcePrimKey;

}