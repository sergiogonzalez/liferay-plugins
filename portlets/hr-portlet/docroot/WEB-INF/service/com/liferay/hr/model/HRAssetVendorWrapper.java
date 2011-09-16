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

package com.liferay.hr.model;

/**
 * <p>
 * This class is a wrapper for {@link HRAssetVendor}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRAssetVendor
 * @generated
 */
public class HRAssetVendorWrapper implements HRAssetVendor {
	public HRAssetVendorWrapper(HRAssetVendor hrAssetVendor) {
		_hrAssetVendor = hrAssetVendor;
	}

	public Class<?> getModelClass() {
		return HRAssetVendor.class;
	}

	public String getModelClassName() {
		return HRAssetVendor.class.getName();
	}

	/**
	* Returns the primary key of this h r asset vendor.
	*
	* @return the primary key of this h r asset vendor
	*/
	public long getPrimaryKey() {
		return _hrAssetVendor.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r asset vendor.
	*
	* @param primaryKey the primary key of this h r asset vendor
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrAssetVendor.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr asset vendor ID of this h r asset vendor.
	*
	* @return the hr asset vendor ID of this h r asset vendor
	*/
	public long getHrAssetVendorId() {
		return _hrAssetVendor.getHrAssetVendorId();
	}

	/**
	* Sets the hr asset vendor ID of this h r asset vendor.
	*
	* @param hrAssetVendorId the hr asset vendor ID of this h r asset vendor
	*/
	public void setHrAssetVendorId(long hrAssetVendorId) {
		_hrAssetVendor.setHrAssetVendorId(hrAssetVendorId);
	}

	/**
	* Returns the group ID of this h r asset vendor.
	*
	* @return the group ID of this h r asset vendor
	*/
	public long getGroupId() {
		return _hrAssetVendor.getGroupId();
	}

	/**
	* Sets the group ID of this h r asset vendor.
	*
	* @param groupId the group ID of this h r asset vendor
	*/
	public void setGroupId(long groupId) {
		_hrAssetVendor.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r asset vendor.
	*
	* @return the company ID of this h r asset vendor
	*/
	public long getCompanyId() {
		return _hrAssetVendor.getCompanyId();
	}

	/**
	* Sets the company ID of this h r asset vendor.
	*
	* @param companyId the company ID of this h r asset vendor
	*/
	public void setCompanyId(long companyId) {
		_hrAssetVendor.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r asset vendor.
	*
	* @return the user ID of this h r asset vendor
	*/
	public long getUserId() {
		return _hrAssetVendor.getUserId();
	}

	/**
	* Sets the user ID of this h r asset vendor.
	*
	* @param userId the user ID of this h r asset vendor
	*/
	public void setUserId(long userId) {
		_hrAssetVendor.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r asset vendor.
	*
	* @return the user uuid of this h r asset vendor
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrAssetVendor.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r asset vendor.
	*
	* @param userUuid the user uuid of this h r asset vendor
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrAssetVendor.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r asset vendor.
	*
	* @return the user name of this h r asset vendor
	*/
	public java.lang.String getUserName() {
		return _hrAssetVendor.getUserName();
	}

	/**
	* Sets the user name of this h r asset vendor.
	*
	* @param userName the user name of this h r asset vendor
	*/
	public void setUserName(java.lang.String userName) {
		_hrAssetVendor.setUserName(userName);
	}

	/**
	* Returns the create date of this h r asset vendor.
	*
	* @return the create date of this h r asset vendor
	*/
	public java.util.Date getCreateDate() {
		return _hrAssetVendor.getCreateDate();
	}

	/**
	* Sets the create date of this h r asset vendor.
	*
	* @param createDate the create date of this h r asset vendor
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrAssetVendor.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r asset vendor.
	*
	* @return the modified date of this h r asset vendor
	*/
	public java.util.Date getModifiedDate() {
		return _hrAssetVendor.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r asset vendor.
	*
	* @param modifiedDate the modified date of this h r asset vendor
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrAssetVendor.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this h r asset vendor.
	*
	* @return the name of this h r asset vendor
	*/
	public java.lang.String getName() {
		return _hrAssetVendor.getName();
	}

	/**
	* Sets the name of this h r asset vendor.
	*
	* @param name the name of this h r asset vendor
	*/
	public void setName(java.lang.String name) {
		_hrAssetVendor.setName(name);
	}

	/**
	* Returns the description of this h r asset vendor.
	*
	* @return the description of this h r asset vendor
	*/
	public java.lang.String getDescription() {
		return _hrAssetVendor.getDescription();
	}

	/**
	* Sets the description of this h r asset vendor.
	*
	* @param description the description of this h r asset vendor
	*/
	public void setDescription(java.lang.String description) {
		_hrAssetVendor.setDescription(description);
	}

	public boolean isNew() {
		return _hrAssetVendor.isNew();
	}

	public void setNew(boolean n) {
		_hrAssetVendor.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrAssetVendor.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrAssetVendor.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrAssetVendor.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrAssetVendor.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrAssetVendor.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrAssetVendor.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrAssetVendor.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrAssetVendor.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRAssetVendorWrapper((HRAssetVendor)_hrAssetVendor.clone());
	}

	public int compareTo(com.liferay.hr.model.HRAssetVendor hrAssetVendor) {
		return _hrAssetVendor.compareTo(hrAssetVendor);
	}

	@Override
	public int hashCode() {
		return _hrAssetVendor.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.hr.model.HRAssetVendor> toCacheModel() {
		return _hrAssetVendor.toCacheModel();
	}

	public com.liferay.hr.model.HRAssetVendor toEscapedModel() {
		return new HRAssetVendorWrapper(_hrAssetVendor.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hrAssetVendor.toString();
	}

	public java.lang.String toXmlString() {
		return _hrAssetVendor.toXmlString();
	}

	public HRAssetVendor getWrappedHRAssetVendor() {
		return _hrAssetVendor;
	}

	public void resetOriginalValues() {
		_hrAssetVendor.resetOriginalValues();
	}

	private HRAssetVendor _hrAssetVendor;
}