/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for KBFolder. This utility wraps
 * {@link com.liferay.knowledgebase.service.impl.KBFolderServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see KBFolderService
 * @see com.liferay.knowledgebase.service.base.KBFolderServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBFolderServiceImpl
 * @generated
 */
@ProviderType
public class KBFolderServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBFolderServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.knowledgebase.model.KBFolder addKBFolder(
		long groupId, long parentResourceClassNameId,
		long parentResourcePrimKey, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKBFolder(groupId, parentResourceClassNameId,
			parentResourcePrimKey, name, description, serviceContext);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static com.liferay.knowledgebase.model.KBFolder getFolder(
		long kbFolderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolder(kbFolderId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFolder> getFolders(
		long groupId, long parentKBFolderId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolders(groupId, parentKBFolderId, start, end);
	}

	public static int getFoldersCount(long groupId, long parentKBFolderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoldersCount(groupId, parentKBFolderId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.knowledgebase.model.KBFolder updateKBFolder(
		long parentResourceClassNameId, long parentResourcePrimKey,
		long kbFolderId, java.lang.String name, java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKBFolder(parentResourceClassNameId,
			parentResourcePrimKey, kbFolderId, name, description);
	}

	public static void clearService() {
		_service = null;
	}

	public static KBFolderService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KBFolderService.class.getName());

			if (invokableService instanceof KBFolderService) {
				_service = (KBFolderService)invokableService;
			}
			else {
				_service = new KBFolderServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(KBFolderServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(KBFolderService service) {
	}

	private static KBFolderService _service;
}