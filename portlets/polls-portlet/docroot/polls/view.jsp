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

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("jspPage", "/polls/view_question.jsp");

List<String> headerNames = new ArrayList<String>();

headerNames.add("question");
headerNames.add("num-of-votes");
headerNames.add("last-vote-date");
headerNames.add("expiration-date");
headerNames.add(StringPool.BLANK);
%>

<aui:form method="post" name="fm">
	<liferay-ui:search-container
		curParam="<%= SearchContainer.DEFAULT_CUR_PARAM %>"
		delta="<%= SearchContainer.DEFAULT_DELTA %>"
		deltaConfigurable="<%= false %>"
		headerNames="<%= StringUtil.merge(headerNames) %>"
		iteratorURL="<%= portletURL %>"
	>

		<liferay-ui:search-container-results
			results="<%= PollsQuestionLocalServiceUtil.getQuestions(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
			total="<%= PollsQuestionLocalServiceUtil.getQuestionsCount(scopeGroupId) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portlet.polls.model.PollsQuestion"
			escapedModel="<%= true %>"
			keyProperty="questionId"
			modelVar="question"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="jspPage" value="/polls/view_question.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="questionId" value="<%= String.valueOf(question.getQuestionId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="question"
				orderable="<%= false %>"
				value="<%= question.getTitle(locale) %>"
			/>

			<%
			int votesCount = PollsVoteLocalServiceUtil.getQuestionVotesCount(question.getQuestionId());
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="num-of-votes"
				orderable="<%= false %>"
				value="<%= String.valueOf(votesCount) %>"
			/>

			<%
			String lastVoteDate = StringPool.BLANK;

			if (question.getLastVoteDate() == null) {
				lastVoteDate = LanguageUtil.get(pageContext, "never");
			}
			else {
				lastVoteDate = dateFormatDateTime.format(question.getLastVoteDate());
			}
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="last-vote-date"
				orderable="<%= false %>"
				value="<%= lastVoteDate %>"
			/>

			<%
			String expirationDate = StringPool.BLANK;

			if (question.getExpirationDate() == null) {
				expirationDate = LanguageUtil.get(pageContext, "never");
			}
			else {
				expirationDate = dateFormatDateTime.format(question.getExpirationDate());
			}
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="expiration-date"
				orderable="<%= false %>"
				value="<%= expirationDate %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/polls/question_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>

	<%
	boolean showAddPollButton = PollsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_QUESTION);
	boolean showPermissionsButton = PollsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);
	%>

	<aui:fieldset>
		<c:if test="<%= showAddPollButton || showPermissionsButton %>">
			<aui:button-row>
				<c:if test="<%= showAddPollButton %>">
					<portlet:renderURL var="editQuestionURL">
						<portlet:param name="jspPage" value="/polls/edit_question.jsp" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</portlet:renderURL>

					<aui:button href="<%= editQuestionURL %>" value="add-question" />
				</c:if>

				<c:if test="<%= showPermissionsButton %>">
					<liferay-security:permissionsURL
						modelResource="com.liferay.portlet.polls"
						modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
						resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
						var="permissionsURL"
					/>

					<aui:button href="<%= permissionsURL %>" value="permissions" />
				</c:if>
			</aui:button-row>
		</c:if>
	</aui:fieldset>
</aui:form>