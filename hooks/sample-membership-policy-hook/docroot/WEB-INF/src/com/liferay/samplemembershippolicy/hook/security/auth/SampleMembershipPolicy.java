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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.DefaultMembershipPolicy;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * @author Eduardo Garcia
 */
public class SampleMembershipPolicy extends DefaultMembershipPolicy {

	@Override
	public Set<Group> getMandatoryGroups(User user) {
		if (!isApplicableUser(user)) {
			return super.getMandatoryGroups(user);
		}

		Set<Group> mandatoryGroups = new HashSet<Group>();

		String userSecurityLevel = getUserSecurityLevel(user);

		try {
			LinkedHashMap<String, Object> groupParams =
				new LinkedHashMap<String, Object>();

			groupParams.put("inherit", Boolean.FALSE);
			groupParams.put("site", Boolean.TRUE);

			List<Group> groups = GroupLocalServiceUtil.search(
				user.getCompanyId(), groupParams, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

			for (Group group : groups) {
				UnicodeProperties groupTypeSettings =
					group.getTypeSettingsProperties();

				String mandatoryUserSecurityLevel =
					groupTypeSettings.getProperty(
						"mandatoryUserSecurityLevel", StringPool.BLANK);

				if (userSecurityLevel.equals(mandatoryUserSecurityLevel)) {
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

	@Override
	public Set<Role> getMandatoryRoles(Group group, User user) {
		if (!isApplicableUser(user)) {
			return super.getMandatoryRoles(group, user);
		}

		UnicodeProperties groupTypeSettings = group.getTypeSettingsProperties();

		long[] mandatorySiteRoleIds = StringUtil.split(
			groupTypeSettings.getProperty("mandatorySiteRoleIds"), 0L);

		Set<Role> mandatorySiteRoles = new HashSet<Role>();

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

	@Override
	public boolean isApplicableUser(User user) {
		String userSecurityLevel = getUserSecurityLevel(user);

		if (Validator.isNotNull(userSecurityLevel)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isMembershipAllowed(Group group, Role role, User user) {
		if (!isApplicableUser(user)) {
			return super.isMembershipAllowed(group, role, user);
		}

		UnicodeProperties groupTypeSettings = group.getTypeSettingsProperties();

		long[] forbiddenSiteRoleIds = StringUtil.split(
			groupTypeSettings.getProperty("forbiddenSiteRoleIds"), 0L);

		if (!ArrayUtil.contains(forbiddenSiteRoleIds, role.getRoleId())) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isMembershipAllowed(Group group, User user) {
		if (!isApplicableUser(user)) {
			return super.isMembershipAllowed(group, user);
		}

		UnicodeProperties groupTypeSettings = group.getTypeSettingsProperties();

		if (group.isRoot()) {
			String forbiddenUserSecurityLevel =
				groupTypeSettings.getProperty(
					"forbiddenUserSecurityLevel", StringPool.BLANK);

			String userSecurityLevel = getUserSecurityLevel(user);

			if (!userSecurityLevel.equals(forbiddenUserSecurityLevel)) {
				return true;
			}

		}
		else {
			boolean parentSiteMembershipRequired = GetterUtil.getBoolean(
				groupTypeSettings.getProperty("parentSiteMembershipRequired"),
				false);

			if (parentSiteMembershipRequired) {
				try {
					if (UserLocalServiceUtil.hasGroupUser(
							group.getParentGroupId(), user.getUserId())) {
						return true;
					}
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(e, e);
					}
				}
			}
		}

		return false;
	}

	protected String getUserSecurityLevel(User user) {
		String userType = StringPool.BLANK;

		try {
			String[] value = ExpandoValueLocalServiceUtil.getData(
				user.getCompanyId(), User.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME, "user-security-level",
				user.getUserId(), new String[0]);

			if (value.length > 0) {
				userType = value[0];
			}
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