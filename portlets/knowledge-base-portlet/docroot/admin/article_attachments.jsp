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

<%@ include file="/admin/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

String[] fileNames = kbArticle.getAttachmentsFileNames();

List<FileEntry> fileEntries = new ArrayList<FileEntry>();

if (kbArticle != null) {
	fileEntries = kbArticle.getAttachmentsFileEntries();
}
%>

<c:if test="<%= fileNames.length > 0 %>">
	<div class="kb-article-attachments">

		<%
		for (FileEntry fileEntry : fileEntries) {
		%>

			<div>
				<liferay-portlet:resourceURL id="attachment" var="clipURL">
					<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
				</liferay-portlet:resourceURL>

				<liferay-ui:icon
					image="clip"
					label="<%= true %>"
					message='<%= fileEntry.getTitle() + " (" + TextFormatter.formatKB(fileEntry.getSize(), locale) + "k)" %>'
					method="get"
					url="<%= clipURL %>"
				/>
			</div>

		<%
		}
		%>

	</div>
</c:if>