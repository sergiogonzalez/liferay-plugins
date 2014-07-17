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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class KBCommentFinderUtil {
	public static java.util.List<com.liferay.knowledgebase.model.KBComment> filterFindByG_S(
		long groupId, int state, int start, int end) {
		return getFinder().filterFindByG_S(groupId, state, start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBComment> findByG_S(
		long groupId, int state, int start, int end) {
		return getFinder().findByG_S(groupId, state, start, end);
	}

	public static int countByG_S(long groupId, int state) {
		return getFinder().countByG_S(groupId, state);
	}

	public static int filterCountByG_S(long groupId, int state) {
		return getFinder().filterCountByG_S(groupId, state);
	}

	public static KBCommentFinder getFinder() {
		if (_finder == null) {
			_finder = (KBCommentFinder)PortletBeanLocatorUtil.locate(com.liferay.knowledgebase.service.ClpSerializer.getServletContextName(),
					KBCommentFinder.class.getName());

			ReferenceRegistry.registerReference(KBCommentFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(KBCommentFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(KBCommentFinderUtil.class, "_finder");
	}

	private static KBCommentFinder _finder;
}