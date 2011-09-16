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

package com.liferay.privatemessaging.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.privatemessaging.model.UserThread;
import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;

/**
 * The extended model base implementation for the UserThread service. Represents a row in the &quot;PM_UserThread&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserThreadImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadImpl
 * @see com.liferay.privatemessaging.model.UserThread
 * @generated
 */
public abstract class UserThreadBaseImpl extends UserThreadModelImpl
	implements UserThread {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user thread model instance should use the {@link UserThread} interface instead.
	 */
	public void persist() throws SystemException {
		UserThreadLocalServiceUtil.updateUserThread(this);
	}
}