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

package com.liferay.mysubscriptions.util;

import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntryType;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalFolder;
import com.liferay.portlet.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.service.WikiNodeLocalServiceUtil;

import java.util.Locale;

/**
 * @author Peter Shin
 * @author Jonathan Lee
 * @author Roberto Díaz
 */
public class MySubscriptionsUtil {

	public static AssetRenderer getAssetRenderer(
		String className, long classPK) {

		try {
			return doGetAssetRenderer(className, classPK);
		}
		catch (Exception e) {
		}

		return null;
	}

	public static String getAssetURLViewInContext(
			ThemeDisplay themeDisplay, String className, long classPK)
		throws PortalException, SystemException {

		if (className.equals(BlogsEntry.class.getName())) {
			return PortalUtil.getLayoutFullURL(classPK, PortletKeys.BLOGS);
		}

		if (className.equals(_KNOWLEDGE_BASE_MODEL_CLASSNAME)) {
			return PortalUtil.getLayoutFullURL(
				classPK, PortletKeys.KNOWLEDGE_BASE_DISPLAY);
		}

		if (className.equals(Layout.class.getName())) {
			return PortalUtil.getLayoutFullURL(
				LayoutLocalServiceUtil.getLayout(classPK), themeDisplay);
		}

		if (className.equals(MBCategory.class.getName())) {
			return PortalUtil.getLayoutFullURL(
				classPK, PortletKeys.MESSAGE_BOARDS);
		}

		if (className.equals(WikiNode.class.getName())) {
			long plid = PortalUtil.getPlidFromPortletId(
				themeDisplay.getScopeGroupId(), PortletKeys.WIKI);

			if (plid == 0) {
				return null;
			}

			StringBundler sb = new StringBundler(5);

			Layout layout = LayoutLocalServiceUtil.getLayout(plid);

			String layoutFullURL = PortalUtil.getLayoutFullURL(
				layout, themeDisplay);

			sb.append(layoutFullURL);

			sb.append(Portal.FRIENDLY_URL_SEPARATOR);
			sb.append("wiki/");
			sb.append(classPK);
			sb.append("/all_pages");

			return sb.toString();
		}

		return null;
	}

	public static String getModelResource(
			Locale locale, String className, long classPK)
		throws SystemException {

		Group group = GroupLocalServiceUtil.fetchGroup(classPK);

		if (group == null) {
			if (className.equals(BlogsEntry.class.getName())) {
				return LanguageUtil.get(locale, "blogs-entry");
			}
			else if (className.equals(_KNOWLEDGE_BASE_MODEL_CLASSNAME)) {
				return LanguageUtil.get(locale, "knowledge-base-article");
			}
		}

		return ResourceActionsUtil.getModelResource(locale, className);
	}

	public static String getTitleText(
			Locale locale, String className, long classPK, String title)
		throws PortalException, SystemException {

		if (Validator.isNotNull(title)) {
			return title;
		}

		Group group = GroupLocalServiceUtil.fetchGroup(classPK);

		if (className.equals(BlogsEntry.class.getName())) {
			return LanguageUtil.format(
				locale, "x-at-x",
				new String[] {
					LanguageUtil.get(locale, "blog"),
					group.getDescriptiveName(locale)},
				false);
		}
		else if (className.equals(BookmarksFolder.class.getName())) {
			if (group != null) {
				return LanguageUtil.format(
					locale, "x-at-x",
					new String[] {
						LanguageUtil.get(locale, "home"),
						group.getDescriptiveName(locale)},
					false);
			}

			BookmarksFolder bookmarksFolder =
				BookmarksFolderLocalServiceUtil.getBookmarksFolder(classPK);

			return bookmarksFolder.getName();
		}
		else if (className.equals(DDMStructure.class.getName())) {
			if (group != null) {
				return LanguageUtil.format(
					locale, "x-at-x",
					new String[] {
						LanguageUtil.get(locale, "basic-web-content"),
						group.getDescriptiveName(locale)},
					false);
			}

			DDMStructure ddmStructure =
				DDMStructureLocalServiceUtil.getStructure(classPK);

			return ddmStructure.getName(locale);
		}
		else if (className.equals(DLFileEntryType.class.getName())) {
			DLFileEntryType dlFileEntryType =
				DLFileEntryTypeLocalServiceUtil.getDLFileEntryType(classPK);

			return dlFileEntryType.getName(locale);
		}
		else if (className.equals(JournalFolder.class.getName())) {
			if (group != null) {
				return LanguageUtil.format(
					locale, "x-at-x",
					new String[] {
						LanguageUtil.get(locale, "home"),
						group.getDescriptiveName(locale)},
					false);
			}

			JournalFolder journalFolder =
				JournalFolderLocalServiceUtil.getFolder(classPK);

			return journalFolder.getName();
		}
		else if (className.equals(_KNOWLEDGE_BASE_MODEL_CLASSNAME)) {
			if (group != null) {
				return LanguageUtil.format(
					locale, "x-at-x",
					new String[] {
						LanguageUtil.get(locale, "knowledge-base"),
						group.getDescriptiveName(locale)},
					false);
			}

			return LanguageUtil.get(locale, "knowledge-base-article");
		}
		else if (className.equals(Layout.class.getName())) {
			Layout layout = LayoutLocalServiceUtil.getLayout(classPK);

			return layout.getName(locale);
		}
		else if (className.equals(MBCategory.class.getName())) {
			if (group != null) {
				return LanguageUtil.format(
					locale, "x-at-x",
					new String[] {
						LanguageUtil.get(locale, "message-boards"),
						group.getDescriptiveName(locale)},
					false);
			}

			return LanguageUtil.get(locale, "message-boards");
		}
		else if (className.equals(PortletPreferences.class.getName())) {
			PortletPreferences portletPreferences =
				PortletPreferencesLocalServiceUtil.fetchPortletPreferences(
					classPK);

			if (portletPreferences == null) {
				return String.valueOf(classPK);
			}

			Layout layout = LayoutLocalServiceUtil.getLayout(
				portletPreferences.getPlid());

			javax.portlet.PortletPreferences preferences =
				PortletPreferencesFactoryUtil.getLayoutPortletSetup(
					layout, portletPreferences.getPortletId());

			group = layout.getGroup();

			return LanguageUtil.format(
				locale, "x-in-x-at-x",
				new String[] {
					preferences.getValue(
						"portletSetupTitle_" + locale.toString(),
						StringPool.BLANK),
					layout.getName(locale), group.getDescriptiveName(locale)},
				false);
		}
		else if (className.equals(WikiNode.class.getName())) {
			WikiNode wikiNode = WikiNodeLocalServiceUtil.getWikiNode(classPK);

			return wikiNode.getName();
		}
		else if (className.equals(Folder.class.getName())) {
			if (group != null) {
				return LanguageUtil.format(
					locale, "x-at-x",
					new String[] {
						LanguageUtil.get(locale, "home"),
						group.getDescriptiveName(locale)},
					false);
			}

			Folder folder = DLAppLocalServiceUtil.getFolder(classPK);

			return folder.getName();
		}

		return String.valueOf(classPK);
	}

	protected static AssetRenderer doGetAssetRenderer(
			String className, long classPK)
		throws Exception {

		if (className.equals(MBThread.class.getName())) {
			className = MBMessage.class.getName();

			MBThread mbThread = MBThreadLocalServiceUtil.getThread(classPK);

			classPK = mbThread.getRootMessageId();
		}

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		return assetRendererFactory.getAssetRenderer(classPK);
	}

	private static final String _KNOWLEDGE_BASE_MODEL_CLASSNAME =
		"com.liferay.knowledgebase.model.KBArticle";

}