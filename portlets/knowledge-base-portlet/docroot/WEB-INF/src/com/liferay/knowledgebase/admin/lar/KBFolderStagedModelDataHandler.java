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

package com.liferay.knowledgebase.admin.lar;

import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.service.KBFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;

import java.util.Map;

/**
 * @author Adolfo Pérez
 */
public class KBFolderStagedModelDataHandler
	extends BaseStagedModelDataHandler<KBFolder> {

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		KBFolder kbFolder =
			KBFolderLocalServiceUtil.fetchKBFolderByUuidAndGroupId(
				uuid, groupId);

		if (kbFolder != null) {
			KBFolderLocalServiceUtil.deleteKBFolder(kbFolder.getKbFolderId());
		}
	}

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, KBFolder kbFolder)
		throws Exception {

		if (kbFolder.getParentKBFolderId() !=
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			KBFolder parentKBFolder =
				KBFolderLocalServiceUtil.getKBFolder(
					kbFolder.getParentKBFolderId());

			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, kbFolder, parentKBFolder,
				PortletDataContext.REFERENCE_TYPE_PARENT);
		}

		Element kbFolderElement = portletDataContext.getExportDataElement(
			kbFolder);

		portletDataContext.addClassedModel(
			kbFolderElement, ExportImportPathUtil.getModelPath(kbFolder),
			kbFolder);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, KBFolder kbFolder)
		throws Exception {

		if (kbFolder.getParentKBFolderId() !=
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			StagedModelDataHandlerUtil.importReferenceStagedModels(
				portletDataContext, kbFolder, KBFolder.class);
		}

		long userId = portletDataContext.getUserId(kbFolder.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			kbFolder);

		KBFolder importedKBFolder = null;

		if (portletDataContext.isDataStrategyMirror()) {
			KBFolder existingKBFolder =
				KBFolderLocalServiceUtil.fetchKBFolderByUuidAndGroupId(
					kbFolder.getUuid(), portletDataContext.getScopeGroupId());

			if (existingKBFolder == null) {
				importedKBFolder = KBFolderLocalServiceUtil.addKBFolder(
					userId, portletDataContext.getScopeGroupId(),
					kbFolder.getClassNameId(), kbFolder.getParentKBFolderId(),
					kbFolder.getName(), kbFolder.getDescription(),
					serviceContext);
			}
			else {
				importedKBFolder = KBFolderLocalServiceUtil.updateKBFolder(
					kbFolder.getClassNameId(), kbFolder.getParentKBFolderId(),
					kbFolder.getKbFolderId(), kbFolder.getName(),
					kbFolder.getDescription());
			}
		}
		else {
			importedKBFolder = KBFolderLocalServiceUtil.addKBFolder(
				userId, portletDataContext.getScopeGroupId(),
				kbFolder.getClassNameId(), kbFolder.getParentKBFolderId(),
				kbFolder.getName(), kbFolder.getDescription(), serviceContext);
		}

		portletDataContext.importClassedModel(kbFolder, importedKBFolder);

		Map<Long, Long> kbFolderPrimaryKeysMap =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				KBFolder.class);

		kbFolderPrimaryKeysMap.put(
			kbFolder.getKbFolderId(), importedKBFolder.getKbFolderId());
	}

	private static final String[] _CLASS_NAMES = { KBFolder.class.getName() };

}