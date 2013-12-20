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

/**
 * @author Sergio González
 */
public class GoogleDriveVersionLabel {

	public GoogleDriveVersionLabel(
		String fileId, String revisionId, String versionLabel, long versionId,
		String versionUuid) {

		_fileId = fileId;
		_revisionId = revisionId;
		_versionLabel = versionLabel;
		_versionId = versionId;
		_versionUuid = versionUuid;
	}

	public String getFileId() {
		return _fileId;
	}

	public String getRevisionId() {
		return _revisionId;
	}

	public long getVersionId() {
		return _versionId;
	}

	public String getVersionLabel() {
		return _versionLabel;
	}

	public String getVersionUuid() {
		return _versionUuid;
	}

	private String _fileId;
	private String _revisionId;
	private long _versionId;
	private String _versionLabel;
	private String _versionUuid;

}