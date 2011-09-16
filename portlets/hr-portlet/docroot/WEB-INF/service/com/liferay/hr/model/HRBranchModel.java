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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the HRBranch service. Represents a row in the &quot;HRBranch&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.hr.model.impl.HRBranchModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.hr.model.impl.HRBranchImpl}.
 * </p>
 *
 * @author Wesley Gong
 * @see HRBranch
 * @see com.liferay.hr.model.impl.HRBranchImpl
 * @see com.liferay.hr.model.impl.HRBranchModelImpl
 * @generated
 */
public interface HRBranchModel extends BaseModel<HRBranch>, GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a h r branch model instance should use the {@link HRBranch} interface instead.
	 */

	/**
	 * Returns the primary key of this h r branch.
	 *
	 * @return the primary key of this h r branch
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this h r branch.
	 *
	 * @param primaryKey the primary key of this h r branch
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the hr branch ID of this h r branch.
	 *
	 * @return the hr branch ID of this h r branch
	 */
	public long getHrBranchId();

	/**
	 * Sets the hr branch ID of this h r branch.
	 *
	 * @param hrBranchId the hr branch ID of this h r branch
	 */
	public void setHrBranchId(long hrBranchId);

	/**
	 * Returns the group ID of this h r branch.
	 *
	 * @return the group ID of this h r branch
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this h r branch.
	 *
	 * @param groupId the group ID of this h r branch
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this h r branch.
	 *
	 * @return the company ID of this h r branch
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this h r branch.
	 *
	 * @param companyId the company ID of this h r branch
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this h r branch.
	 *
	 * @return the user ID of this h r branch
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this h r branch.
	 *
	 * @param userId the user ID of this h r branch
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this h r branch.
	 *
	 * @return the user uuid of this h r branch
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this h r branch.
	 *
	 * @param userUuid the user uuid of this h r branch
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this h r branch.
	 *
	 * @return the user name of this h r branch
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this h r branch.
	 *
	 * @param userName the user name of this h r branch
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this h r branch.
	 *
	 * @return the create date of this h r branch
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this h r branch.
	 *
	 * @param createDate the create date of this h r branch
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this h r branch.
	 *
	 * @return the modified date of this h r branch
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this h r branch.
	 *
	 * @param modifiedDate the modified date of this h r branch
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the organization ID of this h r branch.
	 *
	 * @return the organization ID of this h r branch
	 */
	public long getOrganizationId();

	/**
	 * Sets the organization ID of this h r branch.
	 *
	 * @param organizationId the organization ID of this h r branch
	 */
	public void setOrganizationId(long organizationId);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(HRBranch hrBranch);

	public int hashCode();

	public CacheModel<HRBranch> toCacheModel();

	public HRBranch toEscapedModel();

	public String toString();

	public String toXmlString();
}