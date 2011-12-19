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

<%@ include file="/init.jsp" %>

<div class="loading-animation">
	<iframe class="aui-helper-hidden-accessible" id="<portlet:namespace />frame" scrolling="no" src="javascript:;"></iframe>
</div>

<div class="aui-helper-hidden time-out-message portlet-msg-error">
	<liferay-ui:message key="could-not-connect-to-the-liferay-marketplace" />
</div>

<aui:script use="aui-base,aui-io,aui-messaging">
	var frame = A.one('#<portlet:namespace />frame');

	var timeout = setTimeout(
		function() {
			frame.ancestor().removeClass('loading-animation');
			A.one('.time-out-message').show();
		},
		30000
	);

	var processResponse = function(event) {
		clearTimeout(timeout);

		var response = event.responseData;

		var cmd = response.cmd;

		if (cmd) {
			A.io.request(
				'<portlet:actionURL />',
				{
					data: response,
					dataType: 'JSON',
					method: 'POST',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							A.postMessage(response, '<%= iFrameURL %>', frame);
						}
					}
				}
			);
		}

		var height = response.height;

		if (height) {
			frame.removeClass('aui-helper-hidden-accessible');
			frame.ancestor().removeClass('loading-animation');

			frame.height(height + 50);
		}
	}

	A.receiveMessage(processResponse, A.Lang.emptyFnTrue);

	frame.attr('src', '<%= iFrameURL %>');
</aui:script>