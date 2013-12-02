<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<liferay-util:buffer var="messageRowHtml">
	<liferay-util:include page="/html/portlet/message_boards/view_message_content.portal.jsp" />
</liferay-util:buffer>

<c:if test="<%= MBPermission.contains(permissionChecker, scopeGroupId, ActionKeys.BAN_USER) %>">

	<%
	int messageContainerPosition = 0;

	while ((messageContainerPosition = messageRowHtml.indexOf("<div class=\"message-container", messageContainerPosition)) > -1) {
		int ulTagPosition = messageRowHtml.indexOf("<ul class=\"edit-controls", messageContainerPosition);
		int ulCloseTagPosition = messageRowHtml.indexOf("</ul>", ulTagPosition);

		String messageIdHtmlAttribute = renderResponse.getNamespace() + "messageId=";

		int messageRowMessageIdPosition = messageRowHtml.indexOf(messageIdHtmlAttribute, ulTagPosition);

		if ((ulTagPosition > 0) && (ulCloseTagPosition > 0) && (messageRowMessageIdPosition > 0)) {
			String messageId = messageRowHtml.substring(messageRowMessageIdPosition + messageIdHtmlAttribute.length(), messageRowHtml.indexOf("\"", messageRowMessageIdPosition));

			MBMessage messageRowMessage = null;

			try {
				messageRowMessage = MBMessageLocalServiceUtil.getMessage(GetterUtil.getLong(messageId));
			}
			catch (Exception e) {
				messageContainerPosition++;

				continue;
			}

			boolean isSpam = messageRowMessage.getStatus() == WorkflowConstants.STATUS_DENIED;
	%>

			<liferay-util:buffer var="spamLiTag">
				<c:choose>
					<c:when test="<%= isSpam %>">
						<li>
							<portlet:actionURL var="notSpamURL">
								<portlet:param name="struts_action" value="/message_boards/edit_message" />
								<portlet:param name="<%= Constants.CMD %>" value="updateStatus" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="messageId" value="<%= messageId %>" />
								<portlet:param name="spam" value="<%= String.valueOf(Boolean.FALSE) %>" />
							</portlet:actionURL>

							<liferay-ui:icon
								image="../mail/compose"
								label="<%= true %>"
								message="not-spam"
								url="<%= notSpamURL %>"
							/>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<portlet:actionURL var="markAsSpamURL">
								<portlet:param name="struts_action" value="/message_boards/edit_message" />
								<portlet:param name="<%= Constants.CMD %>" value="updateStatus" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="messageId" value="<%= messageId %>" />
								<portlet:param name="spam" value="<%= String.valueOf(Boolean.TRUE) %>" />
							</portlet:actionURL>

							<liferay-ui:icon
								image="../mail/delete"
								label="<%= true %>"
								message="mark-as-spam"
								url="<%= markAsSpamURL %>"
							/>
						</li>
					</c:otherwise>
				</c:choose>
			</liferay-util:buffer>

	<%
			StringBundler sb = new StringBundler(5);

			if (isSpam) {
				sb.append(messageRowHtml.substring(0, messageContainerPosition + 4));
				sb.append(" style=\"background-color: #FDD; border: 1px solid red;\"");
				sb.append(messageRowHtml.substring(messageContainerPosition + 4, ulCloseTagPosition));
			}
			else {
				sb.append(messageRowHtml.substring(0, ulCloseTagPosition));
			}

			sb.append(spamLiTag);
			sb.append(messageRowHtml.substring(ulCloseTagPosition));

			messageRowHtml = sb.toString();

			messageContainerPosition = ulCloseTagPosition + spamLiTag.length();
		}
		else {
			messageContainerPosition++;
		}
	}
	%>

</c:if>

<%= messageRowHtml %>