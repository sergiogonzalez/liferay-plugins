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

KBComment kbComment = (KBComment)request.getAttribute("article_comment.jsp-kb_comment");
%>

<div class="kb-article-comment">
	<table class="lfr-table" width="100%">
	<tr>
		<td align="center" valign="top">
			<liferay-ui:user-display
				displayStyle="2"
				userId="<%= kbComment.getUserId() %>"
				userName="<%= kbComment.getUserName() %>"
			/>
		</td>
		<td valign="top" width="90%">
			<div>
				<strong class="kb-question"><liferay-ui:message key="was-this-information-helpful" /></strong>

				<c:choose>
					<c:when test="<%= kbComment.getHelpful() %>">
						<strong class="kb-yes"><liferay-ui:message key="yes" /></strong>
					</c:when>
					<c:otherwise>
						<strong class="kb-no"><liferay-ui:message key="no" /></strong>
					</c:otherwise>
				</c:choose>
			</div>

			<br />

			<div>
				<%= HtmlUtil.escape(kbComment.getContent()) %>
			</div>

			<br />

			<div>
				<%= LanguageUtil.format(request, "posted-on-x", dateFormatDateTime.format(kbComment.getModifiedDate()), false) %>
			</div>

			<%
			int feedbackStatus = kbComment.getStatus();
			int prevStatus = KnowledgeBaseUtil.getPrevStatus(feedbackStatus);
			int nextStatus = KnowledgeBaseUtil.getNextStatus(feedbackStatus);
			%>

			<div class="kb-feedback-actions">
				<c:if test="<%= prevStatus != KBCommentConstants.STATUS_NONE %>">
					<liferay-portlet:actionURL name="updateKBCommentStatus" var="prevStatusURL">
						<portlet:param name="targetStatus" value="<%= Integer.toString(prevStatus) %>" />
						<portlet:param name="kbCommentId" value="<%= Long.toString(kbComment.getKbCommentId()) %>" />
						<portlet:param name="redirect" value="<%= redirect %>" />
					</liferay-portlet:actionURL>

					<aui:button href="<%= prevStatusURL %>" id="prevStatus" value="<%= KnowledgeBaseUtil.getStatusTransitionLabel(prevStatus) %>" />
				</c:if>

				<c:if test="<%= nextStatus != KBCommentConstants.STATUS_NONE %>">
					<liferay-portlet:actionURL name="updateKBCommentStatus" var="nextStatusURL">
						<portlet:param name="targetStatus" value="<%= Integer.toString(nextStatus) %>" />
						<portlet:param name="kbCommentId" value="<%= Long.toString(kbComment.getKbCommentId()) %>" />
						<portlet:param name="redirect" value="<%= redirect %>" />
					</liferay-portlet:actionURL>

					<aui:button href="<%= nextStatusURL %>" id="nextStatus" value="<%= KnowledgeBaseUtil.getStatusTransitionLabel(nextStatus) %>" />
				</c:if>

				<c:if test="<%= feedbackStatus == KBCommentConstants.STATUS_RESOLVED && KBCommentPermission.contains(permissionChecker, kbComment, ActionKeys.DELETE) %>">
					<liferay-portlet:actionURL name="deleteKBComment" var="deleteURL">
						<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
						<portlet:param name="redirect" value="<%= redirect %>" />
					</liferay-portlet:actionURL>

					<aui:button cssClass="kb-feedback-delete" href="<%= deleteURL %>" value="delete" />
				</c:if>
			</div>
		</td>
	</tr>
	</table>

	<div class="separator"><!-- --></div>
</div>