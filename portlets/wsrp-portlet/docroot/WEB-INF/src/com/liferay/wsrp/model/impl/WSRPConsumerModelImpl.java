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

package com.liferay.wsrp.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerModel;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.Date;

/**
 * The base model implementation for the WSRPConsumer service. Represents a row in the &quot;WSRP_WSRPConsumer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.wsrp.model.WSRPConsumerModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link WSRPConsumerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerImpl
 * @see com.liferay.wsrp.model.WSRPConsumer
 * @see com.liferay.wsrp.model.WSRPConsumerModel
 * @generated
 */
public class WSRPConsumerModelImpl extends BaseModelImpl<WSRPConsumer>
	implements WSRPConsumerModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a w s r p consumer model instance should use the {@link com.liferay.wsrp.model.WSRPConsumer} interface instead.
	 */
	public static final String TABLE_NAME = "WSRP_WSRPConsumer";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "wsrpConsumerId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "url", Types.VARCHAR },
			{ "wsdl", Types.CLOB },
			{ "registrationContextString", Types.CLOB },
			{ "registrationPropertiesString", Types.VARCHAR },
			{ "forwardCookies", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table WSRP_WSRPConsumer (uuid_ VARCHAR(75) null,wsrpConsumerId LONG not null primary key,companyId LONG,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,url STRING null,wsdl TEXT null,registrationContextString TEXT null,registrationPropertiesString STRING null,forwardCookies VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table WSRP_WSRPConsumer";
	public static final String ORDER_BY_JPQL = " ORDER BY wsrpConsumer.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY WSRP_WSRPConsumer.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.wsrp.model.WSRPConsumer"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.wsrp.model.WSRPConsumer"),
			true);

	public Class<?> getModelClass() {
		return WSRPConsumer.class;
	}

	public String getModelClassName() {
		return WSRPConsumer.class.getName();
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.wsrp.model.WSRPConsumer"));

	public WSRPConsumerModelImpl() {
	}

	public long getPrimaryKey() {
		return _wsrpConsumerId;
	}

	public void setPrimaryKey(long primaryKey) {
		setWsrpConsumerId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_wsrpConsumerId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getWsrpConsumerId() {
		return _wsrpConsumerId;
	}

	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumerId = wsrpConsumerId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrl() {
		if (_url == null) {
			return StringPool.BLANK;
		}
		else {
			return _url;
		}
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getWsdl() {
		if (_wsdl == null) {
			return StringPool.BLANK;
		}
		else {
			return _wsdl;
		}
	}

	public void setWsdl(String wsdl) {
		_wsdl = wsdl;
	}

	public String getRegistrationContextString() {
		if (_registrationContextString == null) {
			return StringPool.BLANK;
		}
		else {
			return _registrationContextString;
		}
	}

	public void setRegistrationContextString(String registrationContextString) {
		_registrationContextString = registrationContextString;
	}

	public String getRegistrationPropertiesString() {
		if (_registrationPropertiesString == null) {
			return StringPool.BLANK;
		}
		else {
			return _registrationPropertiesString;
		}
	}

	public void setRegistrationPropertiesString(
		String registrationPropertiesString) {
		_registrationPropertiesString = registrationPropertiesString;
	}

	public String getForwardCookies() {
		if (_forwardCookies == null) {
			return StringPool.BLANK;
		}
		else {
			return _forwardCookies;
		}
	}

	public void setForwardCookies(String forwardCookies) {
		_forwardCookies = forwardCookies;
	}

	@Override
	public WSRPConsumer toEscapedModel() {
		if (isEscapedModel()) {
			return (WSRPConsumer)this;
		}
		else {
			if (_escapedModelProxy == null) {
				_escapedModelProxy = (WSRPConsumer)Proxy.newProxyInstance(_classLoader,
						_escapedModelProxyInterfaces,
						new AutoEscapeBeanHandler(this));
			}

			return _escapedModelProxy;
		}
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					WSRPConsumer.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		WSRPConsumerImpl wsrpConsumerImpl = new WSRPConsumerImpl();

		wsrpConsumerImpl.setUuid(getUuid());
		wsrpConsumerImpl.setWsrpConsumerId(getWsrpConsumerId());
		wsrpConsumerImpl.setCompanyId(getCompanyId());
		wsrpConsumerImpl.setCreateDate(getCreateDate());
		wsrpConsumerImpl.setModifiedDate(getModifiedDate());
		wsrpConsumerImpl.setName(getName());
		wsrpConsumerImpl.setUrl(getUrl());
		wsrpConsumerImpl.setWsdl(getWsdl());
		wsrpConsumerImpl.setRegistrationContextString(getRegistrationContextString());
		wsrpConsumerImpl.setRegistrationPropertiesString(getRegistrationPropertiesString());
		wsrpConsumerImpl.setForwardCookies(getForwardCookies());

		wsrpConsumerImpl.resetOriginalValues();

		return wsrpConsumerImpl;
	}

	public int compareTo(WSRPConsumer wsrpConsumer) {
		int value = 0;

		value = getName().compareTo(wsrpConsumer.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		WSRPConsumer wsrpConsumer = null;

		try {
			wsrpConsumer = (WSRPConsumer)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = wsrpConsumer.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<WSRPConsumer> toCacheModel() {
		WSRPConsumerCacheModel wsrpConsumerCacheModel = new WSRPConsumerCacheModel();

		wsrpConsumerCacheModel.uuid = getUuid();

		String uuid = wsrpConsumerCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			wsrpConsumerCacheModel.uuid = null;
		}

		wsrpConsumerCacheModel.wsrpConsumerId = getWsrpConsumerId();

		wsrpConsumerCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			wsrpConsumerCacheModel.createDate = createDate.getTime();
		}
		else {
			wsrpConsumerCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			wsrpConsumerCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			wsrpConsumerCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		wsrpConsumerCacheModel.name = getName();

		String name = wsrpConsumerCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			wsrpConsumerCacheModel.name = null;
		}

		wsrpConsumerCacheModel.url = getUrl();

		String url = wsrpConsumerCacheModel.url;

		if ((url != null) && (url.length() == 0)) {
			wsrpConsumerCacheModel.url = null;
		}

		wsrpConsumerCacheModel.wsdl = getWsdl();

		String wsdl = wsrpConsumerCacheModel.wsdl;

		if ((wsdl != null) && (wsdl.length() == 0)) {
			wsrpConsumerCacheModel.wsdl = null;
		}

		wsrpConsumerCacheModel.registrationContextString = getRegistrationContextString();

		String registrationContextString = wsrpConsumerCacheModel.registrationContextString;

		if ((registrationContextString != null) &&
				(registrationContextString.length() == 0)) {
			wsrpConsumerCacheModel.registrationContextString = null;
		}

		wsrpConsumerCacheModel.registrationPropertiesString = getRegistrationPropertiesString();

		String registrationPropertiesString = wsrpConsumerCacheModel.registrationPropertiesString;

		if ((registrationPropertiesString != null) &&
				(registrationPropertiesString.length() == 0)) {
			wsrpConsumerCacheModel.registrationPropertiesString = null;
		}

		wsrpConsumerCacheModel.forwardCookies = getForwardCookies();

		String forwardCookies = wsrpConsumerCacheModel.forwardCookies;

		if ((forwardCookies != null) && (forwardCookies.length() == 0)) {
			wsrpConsumerCacheModel.forwardCookies = null;
		}

		return wsrpConsumerCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", wsrpConsumerId=");
		sb.append(getWsrpConsumerId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", wsdl=");
		sb.append(getWsdl());
		sb.append(", registrationContextString=");
		sb.append(getRegistrationContextString());
		sb.append(", registrationPropertiesString=");
		sb.append(getRegistrationPropertiesString());
		sb.append(", forwardCookies=");
		sb.append(getForwardCookies());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.wsrp.model.WSRPConsumer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wsrpConsumerId</column-name><column-value><![CDATA[");
		sb.append(getWsrpConsumerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wsdl</column-name><column-value><![CDATA[");
		sb.append(getWsdl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationContextString</column-name><column-value><![CDATA[");
		sb.append(getRegistrationContextString());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationPropertiesString</column-name><column-value><![CDATA[");
		sb.append(getRegistrationPropertiesString());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>forwardCookies</column-name><column-value><![CDATA[");
		sb.append(getForwardCookies());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = WSRPConsumer.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			WSRPConsumer.class
		};
	private String _uuid;
	private long _wsrpConsumerId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _url;
	private String _wsdl;
	private String _registrationContextString;
	private String _registrationPropertiesString;
	private String _forwardCookies;
	private transient ExpandoBridge _expandoBridge;
	private WSRPConsumer _escapedModelProxy;
}