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
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.util.Date;

/**
 * @author Sergio González
 */
public abstract class GoogleDriveModel {

	@Override
	public Object clone() {
		return this;
	}

	public abstract long getCompanyId();

	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), getModelClassName(), getPrimaryKey());
	}

	public abstract Class<?> getModelClass();

	public String getModelClassName() {
		Class<?> clazz = getModelClass();

		return clazz.getName();
	}

	public long getParentFolderId() {
		return _parentFolderId;
	}

	public abstract long getPrimaryKey();

	public Serializable getPrimaryKeyObj() {
		return getPrimaryKey();
	}

	public void setCompanyId(long companyId) {
	}

	public void setCreateDate(Date date) {
	}

	public void setGroupId(long groupId) {
	}

	public void setModifiedDate(Date modifiedDate) {
	}

	public void setParentFolder(Folder parentFolder) {
		_parentFolder = parentFolder;
	}

	public void setParentFolderId(long parentFolderId) {
		_parentFolderId = parentFolderId;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
	}

	public void setUserId(long userId) {
	}

	public void setUserName(String userName) {
	}

	public void setUserUuid(String userUuid) {
	}

	public void setUuid(String uuid) {
	}

	@SuppressWarnings("unused")
	protected Folder getParentFolder() throws PortalException, SystemException {
		return _parentFolder;
	}

	private Folder _parentFolder;
	private long _parentFolderId;

}