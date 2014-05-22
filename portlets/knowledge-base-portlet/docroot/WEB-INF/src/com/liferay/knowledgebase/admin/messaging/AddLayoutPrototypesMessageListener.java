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

package com.liferay.knowledgebase.admin.messaging;

import com.liferay.knowledgebase.util.KBConstants;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.layout.prototypes.LayoutConfigurator;
import com.liferay.layout.prototypes.LayoutPrototypesCreator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.service.CompanyLocalServiceUtil;

import java.util.List;

/**
 * @author Iván Zaera
 */
public class AddLayoutPrototypesMessageListener extends BaseMessageListener {

	protected void addKBPage(LayoutPrototypesCreator layoutPrototypesCreator)
		throws PortalException, SystemException {

		LayoutPrototype layoutPrototype =
			layoutPrototypesCreator.addLayoutPrototype(
				KBConstants.LAYOUT_PROTOTYPE_NAME,
				KBConstants.LAYOUT_PROTOTYPE_DESCRIPTION, "1_2_columns_i");

		if (layoutPrototype == null) {
			return;
		}

		Layout layout = layoutPrototype.getLayout();

		LayoutConfigurator layoutConfigurator = new LayoutConfigurator(layout);

		layoutConfigurator.addPortlet(
			"column-1", PortletKeys.KNOWLEDGE_BASE_SECTION);

		layoutConfigurator.addPortlet(
			"column-2", PortletKeys.KNOWLEDGE_BASE_NAVIGATION);

		layoutConfigurator.addPortlet(
			"column-3", PortletKeys.KNOWLEDGE_BASE_ARTICLE);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (command.equals("deploy") &&
			servletContextName.equals("knowledge-base-portlet")) {

			List<Company> companies = CompanyLocalServiceUtil.getCompanies();

			for (Company company : companies) {
				long companyId = company.getCompanyId();

				LayoutPrototypesCreator layoutPrototypesCreator =
					new LayoutPrototypesCreator(companyId);

				addKBPage(layoutPrototypesCreator);
			}
		}
	}

}