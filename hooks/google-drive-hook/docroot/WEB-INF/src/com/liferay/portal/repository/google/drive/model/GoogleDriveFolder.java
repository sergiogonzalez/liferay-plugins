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

import com.google.api.client.util.DateTime;
import com.google.api.services.drive.model.File;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.repository.google.drive.GoogleDriveRepository;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Sergio González
 */
public class GoogleDriveFolder extends GoogleDriveModel implements Folder {

	public GoogleDriveFolder(
		GoogleDriveRepository googleDriveRepository, String uuid, long folderId,
		File file) {

		_googleDriveRepository = googleDriveRepository;
		_uuid = uuid;
		_folderId = folderId;

		DateTime createDateTime = file.getCreatedDate();

		_createDate = new Date(createDateTime.getValue());

		DateTime modifiedDateTime = file.getModifiedDate();

		_modifiedDate = new Date(modifiedDateTime.getValue());

		_name = GetterUtil.getString(file.getTitle());

		_description = GetterUtil.getString(file.getDescription());
	}

	@Override
	public boolean containsPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException, SystemException {

		return true;
	}

	@Override
	public List<Long> getAncestorFolderIds()
		throws PortalException, SystemException {

		List<Long> folderIds = new ArrayList<Long>();

		Folder folder = this;

		while (!folder.isRoot()) {
			folder = folder.getParentFolder();

			folderIds.add(folder.getFolderId());
		}

		return folderIds;
	}

	@Override
	public List<Folder> getAncestors() throws PortalException, SystemException {
		List<Folder> folders = new ArrayList<Folder>();

		Folder folder = this;

		while (!folder.isRoot()) {
			folder = folder.getParentFolder();

			folders.add(folder);
		}

		return folders;
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		return null;
	}

	@Override
	public long getCompanyId() {
		return _googleDriveRepository.getCompanyId();
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public long getFolderId() {
		return _folderId;
	}

	@Override
	public long getGroupId() {
		return _googleDriveRepository.getGroupId();
	}

	@Override
	public Date getLastPostDate() {
		return getModifiedDate();
	}

	@Override
	public Object getModel() {
		return this;
	}

	@Override
	public Class<?> getModelClass() {
		return DLFileEntry.class;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public String getName() {
		if (isRoot()) {
			try {
				Folder folder = DLAppLocalServiceUtil.getMountFolder(
					getRepositoryId());

				return folder.getName();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return _name;
	}

	@Override
	public Folder getParentFolder() throws PortalException, SystemException {
		Folder parentFolder = null;

		try {
			parentFolder = super.getParentFolder();

			if (parentFolder != null) {
				return parentFolder;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (getParentFolderId() == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return null;
		}

		parentFolder = DLAppLocalServiceUtil.getFolder(getParentFolderId());

		setParentFolder(parentFolder);

		return parentFolder;
	}

	@Override
	public long getPrimaryKey() {
		return _folderId;
	}

	@Override
	public long getRepositoryId() {
		return _googleDriveRepository.getRepositoryId();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(Folder.class);
	}

	@Override
	public long getUserId() {
		long userId = 0;

		try {
			userId = UserLocalServiceUtil.getDefaultUser(
				getCompanyId()).getUserId();
		}
		catch (Exception e) {
		}

		return userId;
	}

	@Override
	public String getUserName() {
		return StringPool.BLANK;
	}

	@Override
	public String getUserUuid() throws SystemException {
		return StringPool.BLANK;
	}

	@Override
	public String getUuid() {
		return _uuid;
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
		if (getParentFolderId() == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return true;
		}
		else {
			return false;
		}
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
	public Folder toEscapedModel() {
		return this;
	}

	@Override
	public Folder toUnescapedModel() {
		return this;
	}

	private static Log _log = LogFactoryUtil.getLog(GoogleDriveFolder.class);

	private Date _createDate;
	private String _description;
	private long _folderId;
	private GoogleDriveRepository _googleDriveRepository;
	private Date _modifiedDate;
	private String _name;
	private String _uuid;

}