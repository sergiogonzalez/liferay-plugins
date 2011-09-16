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
 * The base model interface for the HRTerminationType service. Represents a row in the &quot;HRTerminationType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.hr.model.impl.HRTerminationTypeModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.hr.model.impl.HRTerminationTypeImpl}.
 * </p>
 *
 * @author Wesley Gong
 * @see HRTerminationType
 * @see com.liferay.hr.model.impl.HRTerminationTypeImpl
 * @see com.liferay.hr.model.impl.HRTerminationTypeModelImpl
 * @generated
 */
public interface HRTerminationTypeModel extends BaseModel<HRTerminationType>,
	GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a h r termination type model instance should use the {@link HRTerminationType} interface instead.
	 */

	/**
	 * Returns the primary key of this h r termination type.
	 *
	 * @return the primary key of this h r termination type
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this h r termination type.
	 *
	 * @param primaryKey the primary key of this h r termination type
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the hr termination type ID of this h r termination type.
	 *
	 * @return the hr termination type ID of this h r termination type
	 */
	public long getHrTerminationTypeId();

	/**
	 * Sets the hr termination type ID of this h r termination type.
	 *
	 * @param hrTerminationTypeId the hr termination type ID of this h r termination type
	 */
	public void setHrTerminationTypeId(long hrTerminationTypeId);

	/**
	 * Returns the group ID of this h r termination type.
	 *
	 * @return the group ID of this h r termination type
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this h r termination type.
	 *
	 * @param groupId the group ID of this h r termination type
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this h r termination type.
	 *
	 * @return the company ID of this h r termination type
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this h r termination type.
	 *
	 * @param companyId the company ID of this h r termination type
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this h r termination type.
	 *
	 * @return the user ID of this h r termination type
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this h r termination type.
	 *
	 * @param userId the user ID of this h r termination type
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this h r termination type.
	 *
	 * @return the user uuid of this h r termination type
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this h r termination type.
	 *
	 * @param userUuid the user uuid of this h r termination type
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this h r termination type.
	 *
	 * @return the user name of this h r termination type
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this h r termination type.
	 *
	 * @param userName the user name of this h r termination type
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this h r termination type.
	 *
	 * @return the create date of this h r termination type
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this h r termination type.
	 *
	 * @param createDate the create date of this h r termination type
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this h r termination type.
	 *
	 * @return the modified date of this h r termination type
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this h r termination type.
	 *
	 * @param modifiedDate the modified date of this h r termination type
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the code of this h r termination type.
	 *
	 * @return the code of this h r termination type
	 */
	@AutoEscape
	public String getCode();

	/**
	 * Sets the code of this h r termination type.
	 *
	 * @param code the code of this h r termination type
	 */
	public void setCode(String code);

	/**
	 * Returns the name of this h r termination type.
	 *
	 * @return the name of this h r termination type
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this h r termination type.
	 *
	 * @param name the name of this h r termination type
	 */
	public void setName(String name);

	/**
	 * Returns the description of this h r termination type.
	 *
	 * @return the description of this h r termination type
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this h r termination type.
	 *
	 * @param description the description of this h r termination type
	 */
	public void setDescription(String description);

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

	public int compareTo(HRTerminationType hrTerminationType);

	public int hashCode();

	public CacheModel<HRTerminationType> toCacheModel();

	public HRTerminationType toEscapedModel();

	public String toString();

	public String toXmlString();
}