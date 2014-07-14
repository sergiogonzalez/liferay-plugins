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
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(kbArticle, request, "resourcePrimKey");

List<FileEntry> attachmentsFileEntries = new ArrayList<FileEntry>();

if (kbArticle != null) {
	attachmentsFileEntries = kbArticle.getAttachmentsFileEntries();
}
%>

<div class="kb-attachments">

	<%
	for (FileEntry fileEntry : attachmentsFileEntries) {
	%>

		<div>
			<liferay-portlet:resourceURL id="attachment" var="clipURL">
				<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
			</liferay-portlet:resourceURL>

			<liferay-ui:icon
				iconCssClass="icon-paper-clip"
				label="<%= true %>"
				message='<%= fileEntry.getTitle() + " (" + TextFormatter.formatKB(fileEntry.getSize(), locale) + "k)" %>'
				url="<%= clipURL %>"
			/>
		</div>

	<%
	}
	%>

	<div class="lfr-dynamic-uploader">
		<div class="lfr-upload-container" id="<portlet:namespace />fileUpload"></div>
	</div>

	<span id="<portlet:namespace />selectedFileNameContainer"></span>

	<div class="hide" id="<portlet:namespace />metadataExplanationContainer"></div>

	<div class="hide selected" id="<portlet:namespace />selectedFileNameMetadataContainer"></div>
</div>

<%
Date expirationDate = new Date(System.currentTimeMillis() + GetterUtil.getInteger(PropsUtil.get(PropsKeys.SESSION_TIMEOUT)) * Time.MINUTE);

Ticket ticket = TicketLocalServiceUtil.addTicket(user.getCompanyId(), User.class.getName(), user.getUserId(), TicketConstants.TYPE_IMPERSONATE, null, expirationDate, new ServiceContext());
%>

<aui:script use="liferay-upload">
	new Liferay.Upload(
		{
			boundingBox: '#<portlet:namespace />fileUpload',
			deleteFile: '<liferay-portlet:actionURL name="deleteTempAttachment" windowState="<%= LiferayWindowState.POP_UP.toString() %>" doAsUserId="<%= user.getUserId() %>"><portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" /></liferay-portlet:actionURL>&ticketKey=<%= ticket.getKey() %><liferay-ui:input-permissions-params modelName="<%= KBArticle.class.getName() %>" />',
			fileDescription: '<%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA)) %>',
			maxFileSize: '<%= PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) %> B',
			metadataContainer: '#<portlet:namespace />selectedFileNameMetadataContainer',
			metadataExplanationContainer: '#<portlet:namespace />metadataExplanationContainer',
			namespace: '<portlet:namespace />',
			tempFileURL: {
				method: Liferay.Service.bind('/knowledge-base-portlet.kbarticle/get-temp-attachment-names'),
				params: {
					groupId: <%= scopeGroupId %>,
					tempFolderName: 'com.liferay.knowledgebase.admin.portlet.AdminPortlet'
				}
			},
			uploadFile: '<liferay-portlet:actionURL name="addTempAttachment" windowState="<%= LiferayWindowState.POP_UP.toString() %>" doAsUserId="<%= user.getUserId() %>"><portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" /></liferay-portlet:actionURL>&ticketKey=<%= ticket.getKey() %><liferay-ui:input-permissions-params modelName="<%= KBArticle.class.getName() %>" />'
		}
	);
</aui:script>