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

package com.liferay.knowledgebase.hook.upgrade.v1_4_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

/**
 * @author Adolfo Pérez
 */
public class UpgradePortletPreferences extends BaseUpgradePortletPreferences {

	@Override
	protected String[] getPortletIds() {
		return _PORTLET_IDS;
	}

	protected void renamePreference(
			PortletPreferences portletPreferences, String oldName,
			String newName)
		throws ReadOnlyException {

		boolean value = GetterUtil.getBoolean(
			portletPreferences.getValue(oldName, null));

		portletPreferences.setValue(newName, String.valueOf(value));

		portletPreferences.reset(oldName);
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		renamePreference(
			portletPreferences, "enableKBArticleKBComments",
			"enableKBArticleFeedback");

		renamePreference(
			portletPreferences, "showKBArticleKBComments",
			"showKBArticleFeedback");

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

	private static final String[] _PORTLET_IDS = new String[] {
		"2_WAR_knowledgebaseportlet", "3_WAR_knowledgebaseportlet_INSTANCE_%",
		"4_WAR_knowledgebaseportlet_INSTANCE_%", "5_WAR_knowledgebaseportlet"
	};

}