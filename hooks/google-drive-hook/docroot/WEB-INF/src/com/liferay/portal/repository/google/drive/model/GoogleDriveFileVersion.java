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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.repository.google.drive.GoogleDriveRepository;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;
import java.util.Map;

/**
 * @author Sergio González
 */
public class GoogleDriveFileVersion
	extends GoogleDriveModel implements FileVersion {

	public GoogleDriveFileVersion(
		GoogleDriveRepository googleDriveRepository, FileEntry fileEntry,
		String version, long size, String fileId, String revisionId) {

		_googleDriveRepository = googleDriveRepository;
		_fileEntry = fileEntry;
		_version = version;
		_size = size;
		_fileId = fileId;
		_revisionId = revisionId;
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		return _fileEntry.getAttributes();
	}

	@Override
	public String getChangeLog() {
		return StringPool.BLANK;
	}

	@Override
	public long getCompanyId() {
		return _fileEntry.getCompanyId();
	}

	@Override
	public InputStream getContentStream(boolean incrementCounter)
		throws PortalException, SystemException {

		InputStream inputStream = _googleDriveRepository.getContentStream(
			_fileId, _revisionId);

		try {
			DLAppHelperLocalServiceUtil.getFileAsStream(
				PrincipalThreadLocal.getUserId(), getFileEntry(),
				incrementCounter);
		}
		catch (Exception e) {
			_log.error(e);
		}

		return inputStream;
	}

	@Override
	public Date getCreateDate() {
		return _fileEntry.getCreateDate();
	}

	@Override
	public String getDescription() {
		return _fileEntry.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return null;
	}

	@Override
	public String getExtension() {
		return FileUtil.getExtension(getTitle());
	}

	@Override
	public String getExtraSettings() {
		return null;
	}

	@Override
	public FileEntry getFileEntry() {
		return _fileEntry;
	}

	@Override
	public long getFileEntryId() {
		try {
			return getFileEntry().getFileEntryId();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return 0;
	}

	@Override
	public long getFileVersionId() {
		return _fileEntry.getFileEntryId();
	}

	@Override
	public long getGroupId() {
		return _fileEntry.getGroupId();
	}

	@Override
	public String getIcon() {
		return _fileEntry.getIcon();
	}

	@Override
	public String getMimeType() {
		return _fileEntry.getMimeType();
	}

	@Override
	public Object getModel() {
		return _fileEntry.getModel();
	}

	@Override
	public Class<?> getModelClass() {
		return DLFileVersion.class;
	}

	@Override
	public Date getModifiedDate() {
		return _fileEntry.getModifiedDate();
	}

	@Override
	public long getPrimaryKey() {
		return _fileEntry.getPrimaryKey();
	}

	@Override
	public long getRepositoryId() {
		return _fileEntry.getRepositoryId();
	}

	@Override
	public long getSize() {
		return _size;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(FileVersion.class);
	}

	@Override
	public int getStatus() {
		return 0;
	}

	@Override
	public long getStatusByUserId() {
		return _fileEntry.getVersionUserId();
	}

	@Override
	public String getStatusByUserName() {
		return _fileEntry.getVersionUserName();
	}

	@Override
	public String getStatusByUserUuid() throws SystemException {
		return _fileEntry.getVersionUserUuid();
	}

	@Override
	public Date getStatusDate() {
		return _fileEntry.getModifiedDate();
	}

	@Override
	public String getTitle() {
		return _fileEntry.getTitle();
	}

	@Override
	public long getUserId() {
		return _fileEntry.getUserId();
	}

	@Override
	public String getUserName() {
		return _fileEntry.getUserName();
	}

	@Override
	public String getUserUuid() throws SystemException {
		return _fileEntry.getUserUuid();
	}

	@Override
	public String getUuid() {
		return _fileEntry.getUuid();
	}

	@Override
	public String getVersion() {
		return _version;
	}

	@Override
	public boolean isApproved() {
		return false;
	}

	@Override
	public boolean isDefaultRepository() {
		return false;
	}

	@Override
	public boolean isDraft() {
		return false;
	}

	@Override
	public boolean isEscapedModel() {
		return false;
	}

	@Override
	public boolean isExpired() {
		return false;
	}

	@Override
	public boolean isPending() {
		return false;
	}

	@Override
	public FileVersion toEscapedModel() {
		return this;
	}

	@Override
	public FileVersion toUnescapedModel() {
		return this;
	}

	private static Log _log = LogFactoryUtil.getLog(
		GoogleDriveFileVersion.class);

	private FileEntry _fileEntry;
	private String _fileId;
	private GoogleDriveRepository _googleDriveRepository;
	private String _revisionId;
	private long _size;
	private String _version;

}