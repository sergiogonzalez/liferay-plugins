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

package com.liferay.vldap.server.directory;

import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.Collections;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class CommunitiesDirectory extends BaseDirectory {

	public CommunitiesDirectory(
			Directory parentDirectory, Directory usersDirectory)
		throws Exception {

		super("ou=Communities", parentDirectory);

		_usersDirectory = usersDirectory;

		initAttributes();
	}

	@Override
	protected void initAttributes() {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", "Communities");
	}

	@Override
	protected List<Directory> initDirectories() throws Exception {
		List<Group> groups = Collections.emptyList();

		if (_company != null) {
			groups = GroupLocalServiceUtil.search(
				_company.getCompanyId(), null, null, null, 0,
				PortletPropsValues.SEARCH_MAX_SIZE);
		}

		for (Group group : groups) {
			Directory communityDirectory = new CommunityDirectory(
				group, this, _usersDirectory);

			_directories.add(communityDirectory);
		}

		return _directories;
	}

	protected void setCompany(Company company) {
		_company = company;
	}

	private Company _company;
	private Directory _usersDirectory;

}