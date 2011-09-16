<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%
String topLink = ParamUtil.getString(request, "topLink", "contacts-home");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("jspPage", "/contacts_center/view.jsp");
portletURL.setParameter("topLink", topLink);

String keywords = ParamUtil.getString(request, "keywords");
%>

<liferay-ui:header title="find-people" />

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="jspPage" value="/contacts_center/view.jsp" />
	<portlet:param name="topLink" value="<%= topLink %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="no-users-were-found"
	iteratorURL="<%= portletURL %>"
>
	<liferay-ui:search-container-results
		results="<%= UserLocalServiceUtil.search(company.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, null, searchContainer.getStart(), searchContainer.getEnd(), new UserLastNameComparator(true)) %>"
		total="<%= UserLocalServiceUtil.searchCount(company.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, null) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.User"
		escapedModel="<%= true %>"
		keyProperty="userId"
		modelVar="user2"
	>
		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="jspPage" value="/contacts_center/view_user.jsp" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" />
		</liferay-portlet:renderURL>

		<%@ include file="/contacts_center/user_columns.jspf" %>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>