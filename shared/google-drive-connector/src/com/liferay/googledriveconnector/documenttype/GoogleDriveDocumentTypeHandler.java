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

package com.liferay.googledriveconnector.documenttype;

import com.liferay.googledriveconnector.util.OSGiUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.documenttype.BaseDocumentTypeHandler;
import com.liferay.portlet.documentlibrary.documenttype.DocumentTypeHandler;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntryType;
import com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

// TODO: create a plugin to make Google API available to all portal
// TODO: compose picker button HTML with some template engine
// TODO: add icon and name of document to the right of the upload button
// TODO: move Javascript code to local scope

/**
 * @author Iván Zaera
 */
@Component(service = DocumentTypeHandler.class)
public class GoogleDriveDocumentTypeHandler extends BaseDocumentTypeHandler {

	public static final UUID ID = UUID.fromString(
		"6E4493C1-268E-49A7-8AD1-A6A060F163A6");

	public GoogleDriveDocumentTypeHandler() {
		super(ID);
	}

	@Activate
	public void activate() {
		_addGoogleMetadataSet();
	}

	private void _addGoogleMetadataSet() {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			_addGoogleMetadataSet(company);
		}
	}

	private void _addGoogleMetadataSet(Company company) {

		// TODO implement this

		_googleMetadataSetId = "11901";
	}

	@Override
	public boolean handles(DLFileEntryType dlFileEntryType) {
		List<DDMStructure> ddmStructures = dlFileEntryType.getDDMStructures();

		for (DDMStructure ddmStructure : ddmStructures) {
			String structureKey = ddmStructure.getStructureKey();

			if (structureKey.equals(_googleMetadataSetId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public List<DDMStructure> getVisibleDDMStructures(
		DLFileEntryType dlFileEntryType) {

		List<DDMStructure> visibleDDMStructures = new ArrayList<DDMStructure>();

		for (DDMStructure ddmStructure : dlFileEntryType.getDDMStructures() ) {
			String structureKey = ddmStructure.getStructureKey();

			if (!structureKey.equals(_googleMetadataSetId)) {
				visibleDDMStructures.add(ddmStructure);
			}
		}

		return visibleDDMStructures;
	}

	@Override
	public boolean isDownloadable(DLFileEntryType dlFileEntryType) {
		return false;
	}

	@Override
	public String getSelectFileButtonHTML(
		DLFileEntryType dlFileEntryType, String callbackFunction) {

		String html = OSGiUtil.get(
			getClass(),
			"com/liferay/googledriveconnector/documenttype/FilePicker.html");

		return html.replaceAll(
			Pattern.quote("[$$CALLBACK_FUNCTION$$]"), callbackFunction);
	}

	@Override
	public void processUpload(
		boolean newFileEntry, DLFileEntry dlFileEntry, String jsonPayload) {

		// TODO: make deserialization work

		Object jsonObject = JSONFactoryUtil.deserialize(jsonPayload);
		System.out.println("====> " + jsonObject);

		DDMStructure ddmStructure = fetchGoogleDDMStructure(dlFileEntry);

		// TODO: dump google fields to metadata fields in file entry

	}

	private DDMStructure fetchGoogleDDMStructure(DLFileEntry dlFileEntry) {
		DLFileEntryType dlFileEntryType =
			DLFileEntryTypeLocalServiceUtil.fetchFileEntryType(
				dlFileEntry.getFileEntryTypeId());

		if (dlFileEntryType != null) {
			List<DDMStructure> ddmStructures =
				dlFileEntryType.getDDMStructures();

			for (DDMStructure ddmStructure : ddmStructures) {
				String structureKey = ddmStructure.getStructureKey();

				if (structureKey.equals(_googleMetadataSetId)) {
					return ddmStructure;
				}
			}
		}

		return null;
	}

	private String _googleMetadataSetId;

}