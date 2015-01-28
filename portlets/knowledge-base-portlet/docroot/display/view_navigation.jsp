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

<%@ include file="/display/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

KBNavigationDisplayContext kbNavigationDisplayContext = new KBNavigationDisplayContext(renderRequest, portalPreferences, portletPreferences, kbArticle);

List<Long> ancestorResourcePrimaryKeys = kbNavigationDisplayContext.getAncestorResourcePrimaryKeys();

long kbFolderClassNameId = PortalUtil.getClassNameId(KBFolderConstants.getClassName());

long rootResourcePrimKey = kbNavigationDisplayContext.getRootResourcePrimKey();

String currentKBFolderURLTitle = kbNavigationDisplayContext.getCurrentKBFolderURLTitle();

String pageTitle = kbNavigationDisplayContext.getPageTitle();

if (Validator.isNotNull(pageTitle)) {
	PortalUtil.setPageTitle(pageTitle, request);
}

KBArticleURLHelper kbArticleURLHelper = new KBArticleURLHelper(renderRequest, renderResponse, templatePath);
%>

<div class="kbarticle-navigation">
	<c:if test="<%= resourceClassNameId == kbFolderClassNameId %>">

		<%
		List<KBFolder> kbFolders = KnowledgeBaseUtil.getAlternateRootKBFolders(scopeGroupId, resourcePrimKey);
		%>

		<c:if test="<%= kbFolders.size() > 1 %>">
			<liferay-portlet:actionURL name="updateRootKBFolderId" var="updateRootKBFolderIdURL">
				<c:if test="<%= kbArticle != null %>">
					<portlet:param name="urlTitle" value="<%= kbArticle.getUrlTitle() %>" />
				</c:if>
			</liferay-portlet:actionURL>

			<div class="kb-field-wrapper kbarticle-root-selector">
				<aui:form action="<%= updateRootKBFolderIdURL %>" name="updateRootKBFolderIdFm">
					<aui:select label="" name="rootKBFolderId">

						<%
						for (KBFolder kbFolder : kbFolders) {
						%>

							<aui:option
								selected="<%= currentKBFolderURLTitle.equals(kbFolder.getUrlTitle()) %>"
								value="<%= kbFolder.getKbFolderId() %>"
							>
								<%= contentRootPrefix + " " + kbFolder.getName() %>
							</aui:option>

						<%
						}
						%>

					</aui:select>
				</aui:form>
			</div>

			<aui:script use="aui-base">
				var updateRootKBFolderIdFm = A.one('#<portlet:namespace />updateRootKBFolderIdFm');
				var rootKBFolderIdSelect = A.one('#<portlet:namespace />rootKBFolderId');

				rootKBFolderIdSelect.on(
					'change',
					function() {
						updateRootKBFolderIdFm.submit();
					}
				);
			</aui:script>
		</c:if>
	</c:if>

	<%
	List<KBArticle> kbArticles = KBArticleLocalServiceUtil.getKBArticles(themeDisplay.getScopeGroupId(), rootResourcePrimKey, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));

	for (KBArticle curKBArticle : kbArticles) {
		PortletURL viewURL = kbArticleURLHelper.createViewURL(curKBArticle);
	%>

		<ul>
			<li>

				<%
				boolean kbArticleExpanded = false;

				if ((ancestorResourcePrimaryKeys.size() > 0) && (curKBArticle.getResourcePrimKey() == ancestorResourcePrimaryKeys.get(0))) {
					kbArticleExpanded = true;
				}

				String kbArticleClass = StringPool.BLANK;

				if (curKBArticle.getResourcePrimKey() == kbArticle.getResourcePrimKey()) {
					kbArticleClass = "kbarticle-selected";
				}
				else if (kbArticleExpanded) {
					kbArticleClass = "kbarticle-expanded";
				}
				%>

				<a class="<%= kbArticleClass %>" href="<%= viewURL %>"><%= HtmlUtil.escape(curKBArticle.getTitle()) %></a>

				<c:if test="<%= kbArticleExpanded %>">

					<%
					List<KBArticle> childKBArticles = KBArticleLocalServiceUtil.getKBArticles(themeDisplay.getScopeGroupId(), curKBArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));

					for (KBArticle childKBArticle : childKBArticles) {
						PortletURL viewChildURL = kbArticleURLHelper.createViewURL(childKBArticle);
					%>

						<ul>
							<li>

								<%
								boolean childKBArticleExpanded = false;

								if ((ancestorResourcePrimaryKeys.size() > 1) && (childKBArticle.getResourcePrimKey() == ancestorResourcePrimaryKeys.get(1))) {
									childKBArticleExpanded = true;
								}

								String childKBArticleClass = StringPool.BLANK;

								if (childKBArticle.getResourcePrimKey() == kbArticle.getResourcePrimKey()) {
									childKBArticleClass = "kbarticle-selected";
								}
								else if (childKBArticleExpanded) {
									childKBArticleClass = "kbarticle-expanded";
								}
								%>

								<a class="<%= childKBArticleClass %>" href="<%= viewChildURL %>"><%= HtmlUtil.escape(childKBArticle.getTitle()) %></a>

								<c:if test="<%= childKBArticleExpanded %>">

									<%
									List<KBArticle> allDescendantKBArticles = KBArticleLocalServiceUtil.getAllDescendantKBArticles(childKBArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED, new KBArticlePriorityComparator(true));

									for (KBArticle descendantKBArticle : allDescendantKBArticles) {
										PortletURL viewCurKBArticleURL = kbArticleURLHelper.createViewURL(descendantKBArticle);
									%>

										<ul>
											<li>
												<a class="<%= descendantKBArticle.getResourcePrimKey() == kbArticle.getResourcePrimKey() ? "kbarticle-selected" : StringPool.BLANK %>" href="<%= viewCurKBArticleURL %>"><%= HtmlUtil.escape(descendantKBArticle.getTitle()) %></a>
											</li>
										</ul>

									<%
									}
									%>

								</c:if>
							</li>
						</ul>

					<%
					}
					%>

				</c:if>
			</li>
		</ul>

	<%
	}
	%>

</div>