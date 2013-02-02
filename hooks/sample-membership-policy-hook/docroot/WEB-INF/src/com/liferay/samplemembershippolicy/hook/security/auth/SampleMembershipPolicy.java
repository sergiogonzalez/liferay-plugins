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

package com.liferay.samplemembershippolicy.hook.security.auth;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.MembershipPolicy;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.persistence.GroupUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eduardo Garcia
 */
public class SampleMembershipPolicy implements MembershipPolicy {

	public List<Role> getForbiddenRoles(Group group, User user) {
		UnicodeProperties groupTypeSettings = group.getTypeSettingsProperties();

		long[] forbiddenSiteRoleIds = StringUtil.split(
			groupTypeSettings.getProperty("forbiddenSiteRoleIds"), 0L);

		List<Role> forbiddenSiteRoles = new ArrayList();

		for (long forbiddenSiteRoleId : forbiddenSiteRoleIds) {
			try {
				Role role = RoleLocalServiceUtil.getRole(forbiddenSiteRoleId);

				forbiddenSiteRoles.add(role);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}
			}
		}

		return forbiddenSiteRoles;
	}

	public List<Group> getMandatoryGroups(User user) {
		List<Group> mandatoryGroups = new ArrayList<Group>();

		String userSecurityLevel = getUserSecurityLevel(user);

		try {
			List<Group> groups = GroupUtil.findAll();

			for (Group group : groups) {
				UnicodeProperties groupTypeSettings =
					group.getTypeSettingsProperties();

				String mandatoryUserType = groupTypeSettings.getProperty(
					"mandatoryUserType", StringPool.BLANK);

				if (userSecurityLevel.equals(mandatoryUserType)) {
					mandatoryGroups.add(group);
				}
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return mandatoryGroups;
	}

	public List<Role> getMandatoryRoles(Group group, User user) {
		UnicodeProperties groupTypeSettings = group.getTypeSettingsProperties();

		long[] mandatorySiteRoleIds = StringUtil.split(
			groupTypeSettings.getProperty("mandatorySiteRoleIds"), 0L);

		List<Role> mandatorySiteRoles = new ArrayList();

		for (long mandatorySiteRoleId : mandatorySiteRoleIds) {
			try {
				Role role = RoleLocalServiceUtil.getRole(mandatorySiteRoleId);

				mandatorySiteRoles.add(role);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}
			}
		}

		return mandatorySiteRoles;
	}

	public boolean isApplicableUser(User user) {
		String userSecurityLevel = getUserSecurityLevel(user);

		if (Validator.isNotNull(userSecurityLevel)) {
			return true;
		}

		return false;
	}

	public boolean isMembershipAllowed(Group group, User user) {
		UnicodeProperties groupTypeSettings = group.getTypeSettingsProperties();

		boolean parentSiteMembershipRequired = GetterUtil.getBoolean(
			groupTypeSettings.getProperty("parentSiteMembershipRequired"),
			false);

		if (!group.isRoot() && parentSiteMembershipRequired) {
			try {
				List<Group> ancestorGroups = group.getAncestors();
				List<Group> userGroups = user.getGroups();

				for (Group ancestorGroup : ancestorGroups) {
					if (userGroups.contains(ancestorGroup)) {
						return true;
					}
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}
			}
		}

		return false;
	}

	protected String getUserSecurityLevel(User user) {
		String userType = StringPool.BLANK;

		try {
			userType = ExpandoValueLocalServiceUtil.getData(
				user.getCompanyId(), User.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME, "user-security-level",
				user.getUserId(), StringPool.BLANK);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return userType;
	}

	private static Log _log = LogFactoryUtil.getLog(
		SampleMembershipPolicy.class);

}