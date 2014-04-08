<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/html/taglib/ui/search/init.jsp" %>

<%
Group group = layout.getGroup();
%>

<liferay-util:include page="/html/taglib/ui/search/start.jsp" useCustomPage="<%= false %>" />

<c:if test="<%= group.isUser() %>">
	<aui:script use="aui-base">
		var searchOptions = A.all('select[name=<%= namespace %>groupId] option');

		searchOptions.each(
			function(searchOption, index, collection) {
				if (searchOption.val() != 0) {
					searchOption.remove();
				}
			}
		);
	</aui:script>
</c:if>