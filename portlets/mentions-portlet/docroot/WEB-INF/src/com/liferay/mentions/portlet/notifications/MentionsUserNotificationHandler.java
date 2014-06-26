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

package com.liferay.mentions.portlet.notifications;

import com.liferay.mentions.util.PortletKeys;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.BaseModelUserNotificationHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

/**
 * @author Iván Zaera
 */
public class MentionsUserNotificationHandler
	extends BaseModelUserNotificationHandler {

	public MentionsUserNotificationHandler() {
		setPortletId(PortletKeys.MENTIONS);
	}

	@Override
	protected AssetRenderer getAssetRenderer(JSONObject jsonObject) {
		MBMessage mbMessage = MBMessageLocalServiceUtil.fetchMBMessage(
			jsonObject.getLong("classPK"));

		if (mbMessage != null) {
			return getAssetRenderer(
				mbMessage.getClassName(), mbMessage.getClassPK());
		}

		return null;
	}

	protected String getTitle(
		JSONObject jsonObject, AssetRenderer assetRenderer,
		ServiceContext serviceContext) {

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				assetRenderer.getClassName());

		String typeName = assetRendererFactory.getTypeName(
			serviceContext.getLocale());

		return serviceContext.translate(
			"x-mentioned-you-in-a-comment-in-a-x",
			HtmlUtil.escape(assetRenderer.getUserName()),
			StringUtil.toLowerCase(HtmlUtil.escape(typeName)));
	}

}