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

<div class="lfr-button-column">
	<div class="lfr-button-column-content">
		<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "userToolbar" %>' />
	</div>
</div>

<aui:script use="aui-dialog,aui-dialog-iframe">
	var buttonRow = A.one('#<portlet:namespace />userToolbar');

	var contactsToolbarChildren = [];

	<%
	long userId = ParamUtil.getLong(request, "userId");

	User user2 = null;

	if (userId > 0) {
		user2 = UserLocalServiceUtil.getUser(userId);
	}

	boolean viewRelationActions = true;

	if (user2 != null) {
		if (SocialRelationLocalServiceUtil.hasRelation(user2.getUserId(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY)) {
			viewRelationActions = false;
		}
		else if (SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY)) {
			viewRelationActions = false;
		}
	}

	boolean showAddAsConnectionButton = user2 != null && viewRelationActions && !((user2 != null) && SocialRequestLocalServiceUtil.hasRequest(themeDisplay.getUserId(), User.class.getName(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION, user2.getUserId(), SocialRequestConstants.STATUS_PENDING)) && SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION);
	%>

	contactsToolbarChildren.push(
		{
			cssClass: '<%= ((user2 == null) || !showAddAsConnectionButton) ? "aui-helper-hidden" : "" %>',
			handler: function(event) {
				<portlet:namespace />relationAction(event, '<portlet:actionURL name="requestSocialRelation"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_CONNECTION) %>" /></portlet:actionURL>');
			},
			icon: 'add-coworker',
			id: '<portlet:namespace />addConnectionButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "add-as-connection") %>'
		}
	);

	<%
	boolean showRemoveAsConnectionButton = user2 != null && viewRelationActions && SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION);
	%>

	contactsToolbarChildren.push(
		{
			cssClass: '<%= ((user2 == null) || !showRemoveAsConnectionButton) ? "aui-helper-hidden" : "" %>',
			handler: function(event) {
				<portlet:namespace />relationAction(event, '<portlet:actionURL name="deleteSocialRelation"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_BI_CONNECTION) %>" /></portlet:actionURL>');
			},
			icon: 'remove-coworker',
			id: '<portlet:namespace />removeConnectionButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "remove-as-connection") %>'
		}
	);

	<%
	boolean showFollowButton = user2 != null && viewRelationActions && SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
	%>

	contactsToolbarChildren.push(
		{
			cssClass: '<%= ((user2 == null) || !showFollowButton) ? "aui-helper-hidden" : "" %>',
			handler: function(event) {
				<portlet:namespace />relationAction(event, '<portlet:actionURL name="addSocialRelation"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" /></portlet:actionURL>');
			},
			icon: 'follow',
			id: '<portlet:namespace />followButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "follow") %>'
		}
	);

	<%
	boolean showUnFollowButton = user2 != null && viewRelationActions && SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
	%>

	contactsToolbarChildren.push(
		{
			cssClass: '<%= ((user2 == null) || !showUnFollowButton) ? "aui-helper-hidden" : "" %>',
			handler: function(event) {
				<portlet:namespace />relationAction(event, '<portlet:actionURL name="deleteSocialRelation"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_FOLLOWER) %>" /></portlet:actionURL>');
			},
			icon: 'unfollow',
			id: '<portlet:namespace />unfollowButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "unfollow") %>'
		}
	);

	<%
	boolean showBlockButton = user2 != null && SocialRelationLocalServiceUtil.isRelatable(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY);
	%>

	contactsToolbarChildren.push(
		{
			cssClass: '<%= ((user2 == null) || !showBlockButton) ? "aui-helper-hidden" : "" %>',
			handler: function(event) {
				<portlet:namespace />relationAction(event, '<portlet:actionURL name="addSocialRelation"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_ENEMY) %>" /></portlet:actionURL>');
			},
			icon: 'block',
			id: '<portlet:namespace />blockButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "block") %>'
		}
	);

	<%
	boolean showUnBlockButton = user2 != null && SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY);
	%>

	contactsToolbarChildren.push(
		{
			cssClass: '<%= ((user2 == null) || !showUnBlockButton) ? "aui-helper-hidden" : "" %>',
			handler: function(event) {
				<portlet:namespace />relationAction(event, '<portlet:actionURL name="deleteSocialRelation"><portlet:param name="type" value="<%= String.valueOf(SocialRelationConstants.TYPE_UNI_ENEMY) %>" /></portlet:actionURL>');
			},
			icon: 'unblock',
			id: '<portlet:namespace />unblockButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "unblock") %>'
		}
	);

	contactsToolbarChildren.push(
		{
			cssClass: '<%= user2 == null ? "aui-helper-hidden" : "" %>',
			handler: function(event) {
				<c:choose>
					<c:when test="<%= (user2 == null) %>">
						<portlet:namespace />relationAction(event, '<liferay-portlet:resourceURL id="exportVCards" />');
					</c:when>
					<c:otherwise>
						location.href = '<liferay-portlet:resourceURL id="exportVCard"><portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" /></liferay-portlet:resourceURL>';
					</c:otherwise>
				</c:choose>
			},
			icon: 'export',
			id: '<portlet:namespace />exportButton',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "export-vcard") %>'
		}
	);

	<%
	String userDisplayURL = null;

	if (user2 != null) {
		userDisplayURL = user2.getDisplayURL(themeDisplay);
	}
	%>

	<c:if test="<%= Validator.isNotNull(userDisplayURL) %>">
		contactsToolbarChildren.push(
			{
				handler: function(event) {
					location.href= '<%= userDisplayURL %>';
				},
				icon: 'user',
				id: '<portlet:namespace />gotoProfileButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "go-to-profile") %>'
			}
		);
	</c:if>

	var contactsToolbar = new A.Toolbar(
		{
			activeState: false,
			boundingBox: buttonRow,
			children: contactsToolbarChildren
		}
	).render();

	function <portlet:namespace />relationAction(event, uri) {
		var contactFilterSelect = A.one('.contacts-portlet .contact-group-filter select[name=<portlet:namespace />socialRelationType]');

		var searchInput = A.one('.contacts-portlet #<portlet:namespace />name');

		var start = 0;
		var end = <%= maxResultCount %>;

		var lastNameAnchor = '';

		var node = A.one('.more-results a');

		if (node) {
			start = A.DataType.Number.parse(node.getAttribute('data-end'));
			end = start + <%= maxResultCount %>;

			lastNameAnchor = node.getAttribute('data-lastNameAnchor');
		}

		var userIds = [];

		<c:choose>
			<c:when test="<%= user2 != null %>">
				userIds = [<%= user2.getUserId() %>];
			</c:when>
			<c:otherwise>
				var selectedUsersNodes = A.all('.lfr-contact-grid-item input');

				if (selectedUsersNodes.size() > 0) {
					userIds = selectedUsersNodes.val();
				}
			</c:otherwise>
		</c:choose>

		A.io.request(
			uri,
			{
				after: {
					failure: function(event, id, obj) {
						var saveMessages = A.one('#<portlet:namespace/>saveMessages');

						if (saveMessages) {
							saveMessages.html('<span class="portlet-msg-error">' + Liferay.Language.get('an-error-occurred-while-retrieving-the-users-information') + '</span>');
						}
					},
					success: function(event, id, obj) {
						<c:choose>
							<c:when test="<%= user2 != null %>">
								Liferay.ContactsCenter.renderSelectContact(this.get('responseData'));
							</c:when>
							<c:otherwise>
								Liferay.ContactsCenter.renderMultiSelectContacts(this.get('responseData'), lastNameAnchor);
							</c:otherwise>
						</c:choose>
					}
				},
				data: {
					relationAction: true,
					end: end,
					keywords: searchInput.get('value'),
					socialRelationType: contactFilterSelect.get('value') || 'all',
					start: start,
					relationAction: true,
					userIds: <%= user2.getUserId() %>
				}
			}
		);
	}
</aui:script>