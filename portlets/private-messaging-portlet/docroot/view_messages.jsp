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

<aui:layout cssClass="controls">
	<aui:column columnWidth="15" cssClass="col-1">
		<liferay-ui:message key="select" />:

		<span class="select-all"><a href="javascript:;"><liferay-ui:message key="all" /></a></span>

		<span class="select-none"><a href="javascript:;"><liferay-ui:message key="none" /></a></span>
	</aui:column>

	<aui:column columnWidth="35">
		<aui:button cssClass="mark-messages-as-unread" name="markAsUnread" value="mark-as-unread" />

		<aui:button cssClass="delete-messages" name="deleteMessage" value="delete" />
	</aui:column>

	<aui:column columnWidth="50" cssClass="col-2">
		<aui:button cssClass="new-message" name="newMessage" value="new-message" />
	</aui:column>
</aui:layout>

<liferay-ui:search-container delta="25" emptyResultsMessage="no-messages-found">
	<liferay-ui:search-container-results>

		<%
		results = UserThreadLocalServiceUtil.getUserUserThreads(themeDisplay.getUserId(), false, searchContainer.getStart(), searchContainer.getEnd());
		total = UserThreadLocalServiceUtil.getUserUserThreadCount(themeDisplay.getUserId(), false);

		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.privatemessaging.model.UserThread"
		modelVar="userThread"
	>

		<%
		if (!userThread.isRead()) {
			row.setClassName("unread");
		}
		%>

		<liferay-ui:search-container-column-text align="center">
			<aui:input label="" name="mbThread" type="checkbox" data-mbThreadId="<%= userThread.getMbThreadId() %>" />
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text valign="top">

			<%
			long userId = PrivateMessagingUtil.getThreadRepresentativeUserId(user.getUserId(), userThread.getMbThreadId());
			%>

			<liferay-ui:user-display
				userId="<%= userId %>"
				displayStyle="<%= 2 %>"
			/>

			<div class="last-thread">

				<%
				List<User> users = PrivateMessagingUtil.getThreadUsers(user.getUserId(), userThread.getMbThreadId());

				if (users.isEmpty()) {
					users.add(user);
				}

				MBMessage lastMBMessage = PrivateMessagingUtil.getLastThreadMessage(user.getUserId(), userThread.getMbThreadId());

				PortletURL viewThreadURL = renderResponse.createRenderURL();

				viewThreadURL.setParameter("mbThreadId", String.valueOf(userThread.getMbThreadId()));

				for (int i = 0; i < users.size(); i++) {
					User curUser = users.get(i);
				%>

					<c:choose>
						<c:when test="<%= LAYOUT_USER_PUBLIC_LAYOUTS_ENABLED %>">
							<liferay-portlet:actionURL var="publicPagesURL" portletName="<%= PortletKeys.MY_SITES %>">
								<portlet:param name="struts_action" value="/my_sites/view" />
								<portlet:param name="groupId" value="<%= String.valueOf(curUser.getGroup().getGroupId()) %>" />
								<portlet:param name="privateLayout" value="<%= Boolean.FALSE.toString() %>" />
							</liferay-portlet:actionURL>

							<a class="profile-link" href="<%= publicPagesURL %>"><%= HtmlUtil.escape(curUser.getFullName()) %></a>
						</c:when>
						<c:otherwise>
							<span class="profile-link"><%= HtmlUtil.escape(curUser.getFullName()) %></span>
						</c:otherwise>
					</c:choose>

					<c:if test="<%= i != users.size() - 1 %>">
						,
					</c:if>

				<%
				}
				%>

				<span class="date">
					<%= dateFormatDateTime.format(lastMBMessage.getCreateDate()) %>
				</span>

				<div class="subject">
					<a href="<%= viewThreadURL.toString() %>"><%= HtmlUtil.escape(StringUtil.shorten(lastMBMessage.getSubject(), 50)) %></a>
				</div>

				<div class="body">
					<c:if test="<%= user.getUserId() == lastMBMessage.getUserId() %>">
						<liferay-ui:icon
							src="/html/themes/classic/images/mail/replied.png"
						/>
					</c:if>

					<%= HtmlUtil.escape(StringUtil.shorten(lastMBMessage.getBody(), 100)) %>
				</div>
			</div>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>