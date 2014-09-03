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

if (enableKBArticleViewCountIncrement && !kbArticle.isDraft()) {
	KBArticle latestKBArticle = KBArticleLocalServiceUtil.getLatestKBArticle(kbArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED);

	KBArticleLocalServiceUtil.updateViewCount(themeDisplay.getUserId(), kbArticle.getResourcePrimKey(), latestKBArticle.getViewCount() + 1);

	AssetEntryServiceUtil.incrementViewCounter(KBArticle.class.getName(), latestKBArticle.getClassPK());
}
%>

<div>
	<div class="float-container kb-entity-header">
		<div>
			<c:if test="<%= KnowledgeBaseUtil.isGitHubKBArticle(kbArticle) %>">
				<a class="kb-article-ribbon" href="<%= kbArticle.getSourceLocation() %>">
					<img alt='<liferay-ui:message key="fork-me-on-github" />' class="github-ribbon" src="<%= PortalUtil.getPathContext(request) %>/images/blue-white-ribbon.png" />
				</a>
			</c:if>

			<h1 class="kb-title">
				<%= kbArticle.getTitle() %>
			</h1>
		</div>

		<c:if test="<%= !kbArticle.isApproved() %>">
			<div class="kb-article-status">
				<aui:model-context bean="<%= kbArticle %>" model="<%= KBArticle.class %>" />

				<aui:workflow-status status="<%= kbArticle.getStatus() %>" />
			</div>
		</c:if>

		<div class="kb-tools">
			<liferay-util:include page="/admin/article_tools.jsp" servletContext="<%= application %>" />
		</div>
	</div>

	<%
	request.setAttribute("article_icons.jsp-kb_article", kbArticle);
	%>

	<liferay-util:include page="/admin/article_icons.jsp" servletContext="<%= application %>" />

	<div class="kb-entity-body">
		<div class="kb-article-body" id="<portlet:namespace /><%= kbArticle.getResourcePrimKey() %>">
			<%= kbArticle.getContent() %>
		</div>

		<liferay-util:include page="/admin/article_child.jsp" servletContext="<%= application %>" />

		<liferay-util:include page="/admin/article_siblings.jsp" servletContext="<%= application %>" />

		<liferay-util:include page="/admin/article_assets.jsp" servletContext="<%= application %>" />

		<liferay-util:include page="/admin/article_asset_entries.jsp" servletContext="<%= application %>" />

		<liferay-util:include page="/admin/article_asset_links.jsp" servletContext="<%= application %>" />

		<liferay-util:include page="/admin/article_ratings.jsp" servletContext="<%= application %>" />

		<liferay-util:include page="/admin/article_social_bookmarks.jsp" servletContext="<%= application %>" />
	</div>
</div>