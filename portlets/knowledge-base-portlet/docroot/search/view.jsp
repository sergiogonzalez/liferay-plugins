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

<%@ include file="/search/init.jsp" %>

<liferay-portlet:renderURL varImpl="searchURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/search/search.jsp" />
</liferay-portlet:renderURL>

<aui:nav-bar>
	<aui:nav-bar-search cssClass="pull-right yui3-skin-sam">
		<div class="form-search">
			<aui:form action="<%= searchURL %>" method="get" name="searchFm">
				<liferay-portlet:renderURLParams varImpl="searchURL" />

				<liferay-ui:input-search id="kbSearchButton" />
			</aui:form>
		</div>
	</aui:nav-bar-search>
</aui:nav-bar>

<aui:script use="autocomplete,autocomplete-highlighters">
	var node = A.one('#<portlet:namespace />kbSearchButton');

	node.plug(
		A.Plugin.AutoComplete,
		{
			requestTemplate: '&<portlet:namespace />query={query}',
			resultHighlighter: 'phraseMatch',
			resultTextLocator: 'title',
			source: '<portlet:resourceURL id="incrementalSearchResults" />'
		}
	);

	node.ac.on(
		'select',
		function(e) {
			var kbArticle = e.result.raw;
			var classPK = kbArticle.id;

			var baseURL = '<portlet:renderURL />';
			var renderURL = Liferay.PortletURL.createURL(
				baseURL,
				{
					mvcPath: '/search/view_article.jsp',
					resourcePrimKey: classPK
				}
			);

			document.location = renderURL.toString();
		}
	);
</aui:script>