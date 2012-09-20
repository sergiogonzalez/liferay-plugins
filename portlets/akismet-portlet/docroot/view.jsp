<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL name="updateConfiguration" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<liferay-ui:error key="apiKeyError" message="unable-to-validate-akismet-api-key" />

	<aui:fieldset>
		<aui:input label="enabled" name="enabled" type="checkbox" value="<%= AkismetUtil.isEnabled(company.getCompanyId()) %>" />

		<aui:input label="api-key" name="apiKey" type="text" value="<%= PrefsPortletPropsUtil.getString(company.getCompanyId(), PortletPropsKeys.AKISMET_API_KEY) %>" />

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>