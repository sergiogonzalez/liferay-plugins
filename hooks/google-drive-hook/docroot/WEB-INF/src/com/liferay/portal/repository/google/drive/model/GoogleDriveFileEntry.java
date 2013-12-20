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
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.model.Lock;
import com.liferay.portal.security.permission.PermissionChecker;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Sergio González
 */
public class GoogleDriveFileEntry
	extends GoogleDriveModel implements FileEntry {

	@Override
	public boolean containsPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException, SystemException {

		return false;
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
	public InputStream getContentStream()
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public InputStream getContentStream(String version)
		throws PortalException, SystemException {

		return null;
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
	public String getExtension() {
		return null;
	}

	@Override
	public long getFileEntryId() {
		return 0;
	}

	@Override
	public FileVersion getFileVersion()
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public FileVersion getFileVersion(String version)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public List<FileVersion> getFileVersions(int status)
		throws SystemException {

		return null;
	}

	@Override
	public Folder getFolder() {
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
	public String getIcon() {
		return null;
	}

	@Override
	public FileVersion getLatestFileVersion()
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public FileVersion getLatestFileVersion(boolean trusted)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public Lock getLock() {
		return null;
	}

	@Override
	public String getMimeType() {
		return null;
	}

	@Override
	public String getMimeType(String version) {
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
	public Date getModifiedDate() {
		return null;
	}

	@Override
	public long getPrimaryKey() {
		return 0;
	}

	@Override
	public int getReadCount() {
		return 0;
	}

	@Override
	public long getRepositoryId() {
		return 0;
	}

	@Override
	public long getSize() {
		return 0;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return null;
	}

	@Override
	public String getTitle() {
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
	public String getVersion() {
		return null;
	}

	@Override
	public long getVersionUserId() {
		return 0;
	}

	@Override
	public String getVersionUserName() {
		return null;
	}

	@Override
	public String getVersionUserUuid() throws SystemException {
		return null;
	}

	@Override
	public boolean hasLock() {
		return false;
	}

	@Override
	public boolean isCheckedOut() {
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
	public boolean isInTrash() {
		return false;
	}

	@Override
	public boolean isInTrashContainer() {
		return false;
	}

	@Override
	public boolean isManualCheckInRequired() {
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
	public boolean isSupportsSocial() {
		return false;
	}

	@Override
	public FileEntry toEscapedModel() {
		return null;
	}

	@Override
	public FileEntry toUnescapedModel() {
		return null;
	}

}