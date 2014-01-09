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
import com.google.api.services.drive.model.Permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Lock;
import com.liferay.portal.repository.google.drive.GoogleDriveRepository;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileVersionException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;

import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Sergio González
 */
public class GoogleDriveFileEntry
	extends GoogleDriveModel implements FileEntry {

	public GoogleDriveFileEntry(
			GoogleDriveRepository googleDriveRepository, String uuid,
			long fileEntryId, File file,
			List<GoogleDriveVersionLabel> googleDriveVersionLabel,
			String version)
		throws PortalException {

		_googleDriveRepository = googleDriveRepository;
		_uuid = uuid;
		_fileEntryId = fileEntryId;
		_googleDriveVersionLabels = googleDriveVersionLabel;

		DateTime createDateTime = file.getCreatedDate();

		_createDate = new Date(createDateTime.getValue());

		DateTime modifiedDateTime = file.getModifiedDate();

		_modifiedDate = new Date(modifiedDateTime.getValue());

		_title = GetterUtil.getString(file.getTitle());

		_description = GetterUtil.getString(file.getDescription());

		_extension = file.getFileExtension();

		_mimeType = file.getMimeType();

		_size = GetterUtil.getLong(file.getFileSize());

		_version = version;

		_fileId = file.getId();

		_revisionId = getVersionLabel(version).getRevisionId();

		_permission = file.getUserPermission();
	}

	@Override
	public boolean containsPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException, SystemException {

		return containsPermission(_permission.getRole(), actionId);
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
	public InputStream getContentStream()
		throws PortalException, SystemException {

		InputStream inputStream = _googleDriveRepository.getContentStream(
			_fileEntryId);

		try {
			DLAppHelperLocalServiceUtil.getFileAsStream(
				PrincipalThreadLocal.getUserId(), this, true);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return inputStream;
	}

	@Override
	public InputStream getContentStream(String version)
		throws PortalException, SystemException {

		if (version.equals(getVersion())) {
			return getContentStream();
		}

		if (Validator.isNull(version)) {
			return getContentStream();
		}

		GoogleDriveVersionLabel googleDriveVersionLabel = getVersionLabel(
			version);

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			googleDriveVersionLabel.getVersionId());

		return fileEntry.getContentStream();
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
	public String getExtension() {
		return _extension;
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public FileVersion getFileVersion()
		throws PortalException, SystemException {

		return new GoogleDriveFileVersion(
			_googleDriveRepository, this, getVersion(), getSize(), getFileId(),
			getRevisionId());
	}

	@Override
	public FileVersion getFileVersion(String version)
		throws PortalException, SystemException {

		GoogleDriveVersionLabel googleDriveVersionLabel = getVersionLabel(
			version);

		return _googleDriveRepository.getFileVersion(googleDriveVersionLabel);
	}

	@Override
	public List<FileVersion> getFileVersions(int status)
		throws SystemException {

		List<FileVersion> fileVersions = new ArrayList<FileVersion>();

		for (GoogleDriveVersionLabel googleDriveVersionLabel :
				_googleDriveVersionLabels) {

			try {
				FileVersion fileVersion =
					_googleDriveRepository.getFileVersion(
						googleDriveVersionLabel);

				fileVersions.add(fileVersion);
			}
			catch (PortalException pe) {
				throw new RepositoryException();
			}
		}

		return fileVersions;
	}

	@Override
	public Folder getFolder() {
		Folder parentFolder = null;

		try {
			parentFolder = super.getParentFolder();

			if (parentFolder != null) {
				return parentFolder;
			}

			parentFolder = DLAppLocalServiceUtil.getFolder(getParentFolderId());
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		setParentFolder(parentFolder);

		return parentFolder;
	}

	@Override
	public long getFolderId() {
		return getParentFolderId();
	}

	@Override
	public long getGroupId() {
		return _googleDriveRepository.getGroupId();
	}

	@Override
	public String getIcon() {
		return DLUtil.getFileIcon(getExtension());
	}

	@Override
	public FileVersion getLatestFileVersion()
		throws PortalException, SystemException {

		return getLatestFileVersion(false);
	}

	@Override
	public FileVersion getLatestFileVersion(boolean trusted)
		throws PortalException, SystemException {

		GoogleDriveVersionLabel googleDriveVersionLabel =
			_googleDriveVersionLabels.get(0);

		return getFileVersion(googleDriveVersionLabel.getVersionLabel());
	}

	@Override
	public Lock getLock() {
		return null;
	}

	@Override
	public String getMimeType() {
		return _mimeType;
	}

	@Override
	public String getMimeType(String version) {
		try {
			GoogleDriveVersionLabel googleDriveVersionLabel = getVersionLabel(
				version);

			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
				googleDriveVersionLabel.getVersionId());

			return fileEntry.getMimeType();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return StringPool.BLANK;
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
	public long getPrimaryKey() {
		return _fileEntryId;
	}

	@Override
	public int getReadCount() {
		return 0;
	}

	@Override
	public long getRepositoryId() {
		return _googleDriveRepository.getRepositoryId();
	}

	@Override
	public long getSize() {
		return _size;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(FileEntry.class);
	}

	@Override
	public String getTitle() {
		return _title;
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
	public String getVersion() {
		return _version;
	}

	@Override
	public long getVersionUserId() {
		return getUserId();
	}

	@Override
	public String getVersionUserName() {
		return StringPool.BLANK;
	}

	@Override
	public String getVersionUserUuid() throws SystemException {
		return StringPool.BLANK;
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
		return this;
	}

	@Override
	public FileEntry toUnescapedModel() {
		return this;
	}

	protected String getFileId() {
		return _fileId;
	}

	protected String getRevisionId() {
		return _revisionId;
	}

	protected GoogleDriveVersionLabel getVersionLabel(String version)
		throws PortalException {

		for (GoogleDriveVersionLabel googleDriveVersionLabel :
				_googleDriveVersionLabels) {

			if (version.equals(googleDriveVersionLabel.getVersionLabel())) {
				return googleDriveVersionLabel;
			}
		}

		throw new NoSuchFileVersionException();
	}

	private static Log _log = LogFactoryUtil.getLog(GoogleDriveFileEntry.class);

	private Date _createDate;
	private String _description;
	private String _extension;
	private long _fileEntryId;
	private String _fileId;
	private GoogleDriveRepository _googleDriveRepository;
	private List<GoogleDriveVersionLabel> _googleDriveVersionLabels;
	private String _mimeType;
	private Date _modifiedDate;
	private Permission _permission;
	private String _revisionId;
	private long _size;
	private String _title;
	private String _uuid;
	private String _version;

}