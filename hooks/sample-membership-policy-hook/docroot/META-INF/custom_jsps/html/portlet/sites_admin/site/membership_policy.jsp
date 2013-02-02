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

<%@ include file="/html/portlet/sites_admin/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portlet.expando.model.ExpandoColumn" %>
<%@ page import="com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil" %>

<%
Group liveGroup = (Group)request.getAttribute("site.liveGroup");

long groupId = scopeGroupId;

UnicodeProperties groupTypeSettings = null;

if (liveGroup != null) {
	groupId = liveGroup.getGroupId();

	groupTypeSettings = liveGroup.getTypeSettingsProperties();
}
else {
	groupTypeSettings = new UnicodeProperties();
}

String forbiddenUserSecurityLevel = groupTypeSettings.getProperty("forbiddenUserSecurityLevel", StringPool.BLANK);
String mandatoryUserSecurityLevel = groupTypeSettings.getProperty("mandatoryUserSecurityLevel", StringPool.BLANK);
boolean parentSiteMembershipRequired = GetterUtil.getBoolean(groupTypeSettings.getProperty("parentSiteMembershipRequired"), false);

List<Role> forbiddenSiteRoles = new ArrayList();

long[] forbiddenSiteRoleIds = StringUtil.split(groupTypeSettings.getProperty("forbiddenSiteRoleIds"), 0L);

for (long forbiddenSiteRoleId : forbiddenSiteRoleIds) {
	if (forbiddenSiteRoleId == 0) {
		continue;
	}

	Role role = RoleLocalServiceUtil.getRole(forbiddenSiteRoleId);

	forbiddenSiteRoles.add(role);
}

List<Role> mandatorySiteRoles = new ArrayList();

long[] mandatorySiteRoleIds = StringUtil.split(groupTypeSettings.getProperty("mandatorySiteRoleIds"), 0L);

for (long mandatorySiteRoleId : mandatorySiteRoleIds) {
	if (mandatorySiteRoleId == 0) {
		continue;
	}

	Role role = RoleLocalServiceUtil.getRole(mandatorySiteRoleId);

	mandatorySiteRoles.add(role);
}

ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.getDefaultTableColumn(liveGroup.getCompanyId(), User.class.getName(), "user-security-level");

String[] userSecurityLevels = (String[])expandoColumn.getDefaultValue();
%>

<h3><liferay-ui:message key="site-membership" /></h3>

<c:choose>
	<c:when test="<%= liveGroup.isRoot() %>">
		<aui:select helpMessage="forbidden-user-security-level-help" label="forbidden-user-security-level" name="TypeSettingsProperties--forbiddenUserSecurityLevel--">
			<aui:option selected="<%= forbiddenUserSecurityLevel.equals(StringPool.BLANK) %>" value="<%= StringPool.BLANK %>" />

			<%
			for (String userSecurityLevel : userSecurityLevels) {
			%>

				<aui:option label="<%= HtmlUtil.escape(userSecurityLevel) %>" selected="<%= forbiddenUserSecurityLevel.equals(userSecurityLevel) %>" value="<%= HtmlUtil.escape(userSecurityLevel) %>" />

			<%
			}
			%>

		</aui:select>
		<aui:select helpMessage="mandatory-user-security-level-help" label="mandatory-user-security-level" name="TypeSettingsProperties--mandatoryUserSecurityLevel--">
			<aui:option selected="<%= mandatoryUserSecurityLevel.equals(StringPool.BLANK) %>" value="<%= StringPool.BLANK %>" />

			<%
			for (String userSecurityLevel : userSecurityLevels) {
			%>

				<aui:option label="<%= HtmlUtil.escape(userSecurityLevel) %>" selected="<%= mandatoryUserSecurityLevel.equals(userSecurityLevel) %>" value="<%= HtmlUtil.escape(userSecurityLevel) %>" />

			<%
			}
			%>

		</aui:select>
	</c:when>
	<c:otherwise>
		<aui:input helpMessage="parent-site-membership-required-help" label="parent-site-membership-required" name="TypeSettingsProperties--parentSiteMembershipRequired--" type="checkbox" value="<%= parentSiteMembershipRequired %>" />
	</c:otherwise>
</c:choose>

<br />

<liferay-util:buffer var="removeRoleIcon">
	<liferay-ui:icon
		image="unlink"
		label="<%= true %>"
		message="remove"
	/>
</liferay-util:buffer>

<aui:input name="TypeSettingsProperties--forbiddenSiteRoleIds--" type="hidden" value="<%= ListUtil.toString(forbiddenSiteRoles, Role.ROLE_ID_ACCESSOR) %>" />
<aui:input name="TypeSettingsProperties--mandatorySiteRoleIds--" type="hidden" value="<%= ListUtil.toString(mandatorySiteRoles, Role.ROLE_ID_ACCESSOR) %>" />

<h3><liferay-ui:message key="forbidden-site-roles" /> <liferay-ui:icon-help message="forbidden-site-roles-help" /></h3>

<liferay-ui:search-container
	headerNames="title,null"
	id="forbiddenSiteRolesSearchContainer"
>
	<liferay-ui:search-container-results
		results="<%= forbiddenSiteRoles %>"
		total="<%= forbiddenSiteRoles.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Role"
		keyProperty="roleId"
		modelVar="role"
	>

		<liferay-ui:search-container-column-text
			name="title"
			value="<%= HtmlUtil.escape(role.getTitle(locale)) %>"
		/>

		<liferay-ui:search-container-column-text>
			<a class="modify-link" data-rowId="<%= role.getRoleId() %>" href="javascript:;"><%= removeRoleIcon %></a>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>

<liferay-ui:icon
	cssClass="modify-link"
	image="add"
	label="<%= true %>"
	message="select"
	url='<%= "javascript:" + renderResponse.getNamespace() + "openForbiddenSiteRoleSelector();" %>'
/>

<br /><br />

<h3><liferay-ui:message key="mandatory-site-roles" /> <liferay-ui:icon-help message="mandatory-site-roles-help" /></h3>

<liferay-ui:search-container
	headerNames="title,null"
	id="mandatorySiteRolesSearchContainer"
>
	<liferay-ui:search-container-results
		results="<%= mandatorySiteRoles %>"
		total="<%= mandatorySiteRoles.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Role"
		keyProperty="roleId"
		modelVar="role"
	>

		<liferay-ui:search-container-column-text
			name="title"
			value="<%= HtmlUtil.escape(role.getTitle(locale)) %>"
		/>

		<liferay-ui:search-container-column-text>
			<a class="modify-link" data-rowId="<%= role.getRoleId() %>" href="javascript:;"><%= removeRoleIcon %></a>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>

<liferay-ui:icon
	cssClass="modify-link"
	image="add"
	label="<%= true %>"
	message="select"
	url='<%= "javascript:" + renderResponse.getNamespace() + "openMandatorySiteRoleSelector();" %>'
/>

<aui:script use="liferay-search-container">
	var forbiddenSiteRolesSearchContainer = Liferay.SearchContainer.get('<portlet:namespace />forbiddenSiteRolesSearchContainer');
	var mandatorySiteRolesSearchContainer = Liferay.SearchContainer.get('<portlet:namespace />mandatorySiteRolesSearchContainer');

	forbiddenSiteRolesSearchContainer.get('contentBox').delegate(
		'click',
		function(event) {
			var link = event.currentTarget;
			var tr = link.ancestor('tr');

			var rowId = link.getAttribute('data-rowId');

			forbiddenSiteRolesSearchContainer.deleteRow(tr, rowId);

			<portlet:namespace />deleteForbiddenRole(rowId);
		},
		'.modify-link'
	);

	mandatorySiteRolesSearchContainer.get('contentBox').delegate(
		'click',
		function(event) {
			var link = event.currentTarget;
			var tr = link.ancestor('tr');

			var rowId = link.getAttribute('data-rowId');

			mandatorySiteRolesSearchContainer.deleteRow(tr, rowId);

			<portlet:namespace />deleteMandatoryRole(rowId);
		},
		'.modify-link'
	);
</aui:script>

<aui:script>
	var <portlet:namespace />forbiddenSiteRoleIds = ['<%= ListUtil.toString(forbiddenSiteRoles, Role.ROLE_ID_ACCESSOR, "', '") %>'];
	var <portlet:namespace />mandatorySiteRoleIds = ['<%= ListUtil.toString(mandatorySiteRoles, Role.ROLE_ID_ACCESSOR, "', '") %>'];

	function <portlet:namespace />deleteForbiddenRole(roleId) {
		for (var i = 0; i < <portlet:namespace />forbiddenSiteRoleIds.length; i++) {
			if (<portlet:namespace />forbiddenSiteRoleIds[i] == roleId) {
				<portlet:namespace />forbiddenSiteRoleIds.splice(i, 1);

				break;
			}
		}

		document.<portlet:namespace />fm.<portlet:namespace />forbiddenSiteRoleIds.value = <portlet:namespace />forbiddenSiteRoleIds.join(',');
	}

	function <portlet:namespace />deleteMandatoryRole(roleId) {
		for (var i = 0; i < <portlet:namespace />mandatorySiteRoleIds.length; i++) {
			if (<portlet:namespace />mandatorySiteRoleIds[i] == roleId) {
				<portlet:namespace />mandatorySiteRoleIds.splice(i, 1);

				break;
			}
		}

		document.<portlet:namespace />fm.<portlet:namespace />mandatorySiteRoleIds.value = <portlet:namespace />mandatorySiteRoleIds.join(',');
	}

	function <portlet:namespace />openForbiddenSiteRoleSelector() {
		var url = '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/sites_admin/select_site_role" /><portlet:param name="step" value="2" /><portlet:param name="callback" value="selectForbiddenRole" /><portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" /></portlet:renderURL>';

		var roleWindow = window.open(url, 'role', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');

		roleWindow.focus();
	}

	function <portlet:namespace />openMandatorySiteRoleSelector() {
		var url = '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/sites_admin/select_site_role" /><portlet:param name="step" value="2" /><portlet:param name="callback" value="selectMandatoryRole" /><portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" /></portlet:renderURL>';

		var roleWindow = window.open(url, 'role', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');

		roleWindow.focus();
	}

	Liferay.provide(
		window,
		'<portlet:namespace />selectForbiddenRole',
		function(roleId, name, searchContainer, groupName, groupId) {
			var A = AUI();

			searchContainer = Liferay.SearchContainer.get('<portlet:namespace />forbiddenSiteRolesSearchContainer');

			var rowColumns = [];

			rowColumns.push(name);

			if (groupId) {
				rowColumns.push('<a class="modify-link" data-rowId="' + roleId + '" href="javascript:;"><%= UnicodeFormatter.toString(removeRoleIcon) %></a>');

				<portlet:namespace />forbiddenSiteRoleIds.push(roleId);

				document.<portlet:namespace />fm.<portlet:namespace />forbiddenSiteRoleIds.value = <portlet:namespace />forbiddenSiteRoleIds.join(',');
			}
			else {
				rowColumns.push('<a class="modify-link" data-rowId="' + roleId + '" href="javascript:;"><%= UnicodeFormatter.toString(removeRoleIcon) %></a>');
			}

			searchContainer.addRow(rowColumns, roleId);
			searchContainer.updateDataStore();
		},
		['liferay-search-container']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />selectMandatoryRole',
		function(roleId, name, searchContainer, groupName, groupId) {
			var A = AUI();

			searchContainer = Liferay.SearchContainer.get('<portlet:namespace />mandatorySiteRolesSearchContainer');

			var rowColumns = [];

			rowColumns.push(name);

			if (groupId) {
				rowColumns.push('<a class="modify-link" data-rowId="' + roleId + '" href="javascript:;"><%= UnicodeFormatter.toString(removeRoleIcon) %></a>');

				<portlet:namespace />mandatorySiteRoleIds.push(roleId);

				document.<portlet:namespace />fm.<portlet:namespace />mandatorySiteRoleIds.value = <portlet:namespace />mandatorySiteRoleIds.join(',');
			}
			else {
				rowColumns.push('<a class="modify-link" data-rowId="' + roleId + '" href="javascript:;"><%= UnicodeFormatter.toString(removeRoleIcon) %></a>');
			}

			searchContainer.addRow(rowColumns, roleId);
			searchContainer.updateDataStore();
		},
		['liferay-search-container']
	);
</aui:script>