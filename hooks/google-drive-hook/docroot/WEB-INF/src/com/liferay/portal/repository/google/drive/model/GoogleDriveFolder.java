/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.repository.google.drive.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Sergio González
 */
public class GoogleDriveFolder implements Folder {

	@Override
	public Object clone() {
		return this;
	}

	@Override
	public boolean containsPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException, SystemException {

		return false;
	}

	@Override
	public List<Long> getAncestorFolderIds()
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public List<Folder> getAncestors() throws PortalException, SystemException {
		return null;
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		return null;
	}

	@Override
	public long getCompanyId() {
		return 0;
	}

	@Override
	public Date getCreateDate() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return null;
	}

	@Override
	public long getFolderId() {
		return 0;
	}

	@Override
	public long getGroupId() {
		return 0;
	}

	@Override
	public Date getLastPostDate() {
		return null;
	}

	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public Class<?> getModelClass() {
		return null;
	}

	@Override
	public String getModelClassName() {
		return null;
	}

	@Override
	public Date getModifiedDate() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Folder getParentFolder() throws PortalException, SystemException {
		return null;
	}

	@Override
	public long getParentFolderId() {
		return 0;
	}

	@Override
	public long getPrimaryKey() {
		return 0;
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return null;
	}

	@Override
	public long getRepositoryId() {
		return 0;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return null;
	}

	@Override
	public long getUserId() {
		return 0;
	}

	@Override
	public String getUserName() {
		return null;
	}

	@Override
	public String getUserUuid() throws SystemException {
		return null;
	}

	@Override
	public String getUuid() {
		return null;
	}

	@Override
	public boolean hasInheritableLock() {
		return false;
	}

	@Override
	public boolean hasLock() {
		return false;
	}

	@Override
	public boolean isDefaultRepository() {
		return false;
	}

	@Override
	public boolean isEscapedModel() {
		return false;
	}

	@Override
	public boolean isLocked() {
		return false;
	}

	@Override
	public boolean isMountPoint() {
		return false;
	}

	@Override
	public boolean isRoot() {
		return false;
	}

	@Override
	public boolean isSupportsLocking() {
		return false;
	}

	@Override
	public boolean isSupportsMetadata() {
		return false;
	}

	@Override
	public boolean isSupportsMultipleUpload() {
		return false;
	}

	@Override
	public boolean isSupportsShortcuts() {
		return false;
	}

	@Override
	public boolean isSupportsSocial() {
		return false;
	}

	@Override
	public boolean isSupportsSubscribing() {
		return false;
	}

	@Override
	public void setCompanyId(long companyId) {
	}

	@Override
	public void setCreateDate(Date date) {
	}

	@Override
	public void setGroupId(long groupId) {
	}

	@Override
	public void setModifiedDate(Date date) {
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
	}

	@Override
	public void setUserId(long userId) {
	}

	@Override
	public void setUserName(String userName) {
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public void setUuid(String uuid) {
	}

	@Override
	public Folder toEscapedModel() {
		return null;
	}

	@Override
	public Folder toUnescapedModel() {
		return null;
	}

}