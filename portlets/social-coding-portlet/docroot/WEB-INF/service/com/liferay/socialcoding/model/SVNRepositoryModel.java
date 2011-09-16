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

package com.liferay.socialcoding.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the SVNRepository service. Represents a row in the &quot;SC_SVNRepository&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.socialcoding.model.impl.SVNRepositoryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.socialcoding.model.impl.SVNRepositoryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SVNRepository
 * @see com.liferay.socialcoding.model.impl.SVNRepositoryImpl
 * @see com.liferay.socialcoding.model.impl.SVNRepositoryModelImpl
 * @generated
 */
public interface SVNRepositoryModel extends BaseModel<SVNRepository> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a s v n repository model instance should use the {@link SVNRepository} interface instead.
	 */

	/**
	 * Returns the primary key of this s v n repository.
	 *
	 * @return the primary key of this s v n repository
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this s v n repository.
	 *
	 * @param primaryKey the primary key of this s v n repository
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the svn repository ID of this s v n repository.
	 *
	 * @return the svn repository ID of this s v n repository
	 */
	public long getSvnRepositoryId();

	/**
	 * Sets the svn repository ID of this s v n repository.
	 *
	 * @param svnRepositoryId the svn repository ID of this s v n repository
	 */
	public void setSvnRepositoryId(long svnRepositoryId);

	/**
	 * Returns the url of this s v n repository.
	 *
	 * @return the url of this s v n repository
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this s v n repository.
	 *
	 * @param url the url of this s v n repository
	 */
	public void setUrl(String url);

	/**
	 * Returns the revision number of this s v n repository.
	 *
	 * @return the revision number of this s v n repository
	 */
	public long getRevisionNumber();

	/**
	 * Sets the revision number of this s v n repository.
	 *
	 * @param revisionNumber the revision number of this s v n repository
	 */
	public void setRevisionNumber(long revisionNumber);

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

	public int compareTo(SVNRepository svnRepository);

	public int hashCode();

	public CacheModel<SVNRepository> toCacheModel();

	public SVNRepository toEscapedModel();

	public String toString();

	public String toXmlString();
}