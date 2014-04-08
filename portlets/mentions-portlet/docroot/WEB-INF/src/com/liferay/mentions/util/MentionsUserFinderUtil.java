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

package com.liferay.mentions.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.List;

/**
 * @author Sergio González
 */
public class MentionsUserFinderUtil {

	public static MentionsUserFinder getMentionsUserFinder() {
		return _mentionsUserFinder;
	}

	public static List<User> getUsers(String query, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		return getMentionsUserFinder().getUsers(query, themeDisplay);
	}

	public void setMentionsUserFinder(MentionsUserFinder mentionsUserFinder) {
		_mentionsUserFinder = mentionsUserFinder;
	}

	private static MentionsUserFinder _mentionsUserFinder;

}