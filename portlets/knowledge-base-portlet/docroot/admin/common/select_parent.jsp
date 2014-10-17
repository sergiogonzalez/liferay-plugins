<%--
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
--%>

<%@ include file="/admin/init.jsp" %>

<%
int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

long kbFolderClassNameId = PortalUtil.getClassNameId(KBFolderConstants.getClassName());

long resourceClassNameId = ParamUtil.getLong(request, "resourceClassNameId");
long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
long parentResourceClassNameId = ParamUtil.getLong(request, "parentResourceClassNameId", kbFolderClassNameId);
long parentResourcePrimKey = ParamUtil.getLong(request, "parentResourcePrimKey", KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);
long oldParentResourceClassNameId = ParamUtil.getLong(request, "oldParentResourceClassNameId");
long oldParentResourcePrimKey = ParamUtil.getLong(request, "oldParentResourcePrimKey");

String orderByCol = ParamUtil.getString(request, "orderByCol", "priority");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");
%>

<aui:form method="post" name="fm">
	<aui:fieldset>
		<c:if test="<%= oldParentResourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID %>">
			<aui:button-row cssClass="input-append">
				<c:choose>
					<c:when test="<%= oldParentResourceClassNameId == kbFolderClassNameId %>">

						<%
						KBFolder oldParentKBFolder = KBFolderServiceUtil.getKBFolder(oldParentResourcePrimKey);
						%>

						<liferay-ui:input-resource url="<%= oldParentKBFolder.getName() %>" />
					</c:when>
					<c:otherwise>
						<liferay-ui:input-resource url='<%= BeanPropertiesUtil.getString(KBArticleServiceUtil.getLatestKBArticle(oldParentResourcePrimKey, status), "title") %>' />
					</c:otherwise>
				</c:choose>

				<aui:button
					cssClass="selector-button"
					data-priority="<%= KBArticleConstants.DEFAULT_PRIORITY %>"
					data-resourceClassNameId="<%= kbFolderClassNameId %>"
					data-resourcePrimKey="<%= KBFolderConstants.DEFAULT_PARENT_FOLDER_ID %>"
					data-title=""
					value="remove" />
			</aui:button-row>

			<div class="separator"><!-- --></div>
		</c:if>

		<%
		KnowledgeBaseUtil.addPortletBreadcrumbEntries(oldParentResourceClassNameId, oldParentResourcePrimKey, parentResourceClassNameId, parentResourcePrimKey, "/admin/common/select_parent.jsp", request, renderResponse);
		%>

		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= false %>"
			showParentGroups="<%= false %>"
			showPortletBreadcrumb="<%= false %>"
		/>

		<liferay-portlet:renderURL varImpl="iteratorURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value='<%= templatePath + "select_parent.jsp" %>' />
			<portlet:param name="resourceClassNameId" value="<%= String.valueOf(resourceClassNameId) %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
			<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(parentResourceClassNameId) %>" />
			<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
			<portlet:param name="oldParentResourceClassNameId" value="<%= String.valueOf(oldParentResourceClassNameId) %>" />
			<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
			<portlet:param name="status" value="<%= String.valueOf(status) %>" />
		</liferay-portlet:renderURL>

		<c:if test="<%= parentResourceClassNameId == kbFolderClassNameId %>">
			<liferay-ui:search-container
				curParam="cur1"
				id="kbFoldersAdminSearchContainer"
				iteratorURL="<%= iteratorURL %>"
				total="<%= KBFolderServiceUtil.getKBFoldersCount(scopeGroupId, parentResourcePrimKey) %>"
			>
				<liferay-ui:search-container-results
					results="<%= KBFolderServiceUtil.getKBFolders(scopeGroupId, parentResourcePrimKey, searchContainer.getStart(), searchContainer.getEnd()) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.knowledgebase.model.KBFolder"
					escapedModel="<%= true %>"
					keyProperty="kbFolderId"
					modelVar="kbFolder"
				>

					<liferay-portlet:renderURL var="rowURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="mvcPath" value='<%= templatePath + "select_parent.jsp" %>' />
						<portlet:param name="resourceClassNameId" value="<%= String.valueOf(resourceClassNameId) %>" />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
						<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(kbFolder.getClassNameId()) %>" />
						<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbFolder.getKbFolderId()) %>" />
						<portlet:param name="oldParentResourceClassNameId" value="<%= String.valueOf(oldParentResourceClassNameId) %>" />
						<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
						<portlet:param name="status" value="<%= String.valueOf(status) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:search-container-column-text
						name="folder"
					>
						<a class="icon-folder-open" href="<%= rowURL %>">
							<%= kbFolder.getName() %>
						</a>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						href="<%= rowURL %>"
						name="author"
						property="userName"
					/>

					<liferay-ui:search-container-column-date
						href="<%= rowURL %>"
						name="create-date"
						property="createDate"
					/>

					<liferay-ui:search-container-column-date
						href="<%= rowURL %>"
						name="modified-date"
						property="modifiedDate"
					/>

					<liferay-ui:search-container-column-text
						align="right"
					>
						<aui:button
							cssClass="selector-button"
							data-priority="<%= KBArticleConstants.DEFAULT_PRIORITY %>"
							data-resourceClassNameId="<%= kbFolder.getClassNameId() %>"
							data-resourcePrimKey="<%= kbFolder.getKbFolderId() %>"
							data-title="<%= HtmlUtil.escapeAttribute(kbFolder.getName()) %>"
							disabled="<%= (kbFolder.getKbFolderId() == resourcePrimKey) || (kbFolder.getKbFolderId() == oldParentResourcePrimKey) %>"
							value="choose" />
					</liferay-ui:search-container-column-text>

				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</c:if>

		<liferay-ui:search-container
			curParam="cur2"
			emptyResultsMessage="there-are-no-articles"
			iteratorURL="<%= iteratorURL %>"
			orderByCol="<%= orderByCol %>"
			orderByComparator="<%= KnowledgeBaseUtil.getKBArticleOrderByComparator(orderByCol, orderByType) %>"
			orderByType="<%= orderByType %>"
			total="<%= KBArticleServiceUtil.getKBArticlesCount(scopeGroupId, parentResourcePrimKey, status) %>"
		>
			<liferay-ui:search-container-results
				results="<%= KBArticleServiceUtil.getKBArticles(scopeGroupId, parentResourcePrimKey, status, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBArticle"
				escapedModel="<%= true %>"
				keyProperty="resourcePrimKey"
				modelVar="curKBArticle"
			>
				<liferay-portlet:renderURL var="rowURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value='<%= templatePath + "select_parent.jsp" %>' />
					<portlet:param name="resourceClassNameId" value="<%= String.valueOf(resourceClassNameId) %>" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
					<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(curKBArticle.getClassNameId()) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(curKBArticle.getResourcePrimKey()) %>" />
					<portlet:param name="oldParentResourceClassNameId" value="<%= String.valueOf(oldParentResourceClassNameId) %>" />
					<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status) %>" />
				</liferay-portlet:renderURL>

				<%
				if ((curKBArticle.getResourcePrimKey() == resourcePrimKey) || (KBArticleServiceUtil.getKBArticlesCount(scopeGroupId, curKBArticle.getResourcePrimKey(), status) == 0)) {
					rowURL = null;
				}
				%>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="priority"
					orderable="<%= true %>"
					value="<%= BigDecimal.valueOf(curKBArticle.getPriority()).toPlainString() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					orderable="<%= true %>"
					property="title"
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="status"
					orderable="<%= true %>"
					value='<%= curKBArticle.getStatus() + " (" + LanguageUtil.get(request, WorkflowConstants.getStatusLabel(curKBArticle.getStatus())) + ")" %>'
				/>

				<liferay-ui:search-container-column-text
					align="right"
				>
					<aui:button
						cssClass="selector-button"
						data-priority="<%= curKBArticle.getPriority() %>"
						data-resourceClassNameId="<%= curKBArticle.getClassNameId() %>"
						data-resourcePrimKey="<%= curKBArticle.getResourcePrimKey() %>"
						data-title="<%= HtmlUtil.escapeAttribute(curKBArticle.getTitle()) %>"
						disabled="<%= (resourceClassNameId == kbFolderClassNameId) || (curKBArticle.getResourcePrimKey() == resourcePrimKey) || (curKBArticle.getResourcePrimKey() == oldParentResourcePrimKey) %>"
						value="choose" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script use="aui-base">
	var Util = Liferay.Util;

	A.one('#<portlet:namespace />fm').delegate(
		'click',
		function(event) {
			var result = Util.getAttributes(event.currentTarget, 'data-');

			Util.getOpener().Liferay.fire('<portlet:namespace />selectKBObject', result);

			Util.getWindow().destroy();
		},
		'.selector-button'
	);
</aui:script>