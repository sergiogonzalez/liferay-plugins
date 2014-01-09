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

package com.liferay.portal.repository.google.drive;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;
import com.google.api.services.drive.model.Revision;
import com.google.api.services.drive.model.RevisionList;

import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.BaseRepositoryImpl;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.model.User;
import com.liferay.portal.repository.google.drive.model.GoogleDriveFileEntry;
import com.liferay.portal.repository.google.drive.model.GoogleDriveFileVersion;
import com.liferay.portal.repository.google.drive.model.GoogleDriveFolder;
import com.liferay.portal.repository.google.drive.model.GoogleDriveVersionLabel;
import com.liferay.portal.repository.proxy.FileEntryProxyBean;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.RepositoryEntryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.bookmarks.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * @author Sergio González
 */
public class GoogleDriveRepository extends BaseRepositoryImpl {

	@Override
	public FileEntry addFileEntry(
			long folderId, String sourceFileName, String mimeType, String title,
			String description, String changeLog, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		File file = addFile(folderId, mimeType, title, description, is);

		return toFileEntry(file);
	}

	@Override
	public Folder addFolder(
			long parentFolderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		File file = addFile(
			parentFolderId, _FOLDER_MIME_TYPE, title, description, null);

		return toFolder(file);
	}

	@Override
	public FileVersion cancelCheckOut(long fileEntryId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void checkInFileEntry(
			long fileEntryId, boolean major, String changeLog,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void checkInFileEntry(
			long fileEntryId, String lockUuid, ServiceContext serviceContext)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, String owner, long expirationTime,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry copyFileEntry(
			long groupId, long fileEntryId, long destFolderId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public void deleteFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		deleteFile(fileEntryId);
	}

	@Override
	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		deleteFile(folderId);
	}

	public InputStream getContentStream(long fileEntryId)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		File file = toFileObject(fileEntryId);

		String downloadURL = file.getDownloadUrl();

		if (Validator.isNull(downloadURL)) {
			return null;
		}

		try {
			HttpResponse response = drive.getRequestFactory().buildGetRequest(
				new GenericUrl(downloadURL)).execute();

			return response.getContent();
		}
		catch (IOException e) {
			e.printStackTrace();

			return null;
		}
	}

	public InputStream getContentStream(String fileId, String revisionId)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		try {
			Revision revision =
				drive.revisions().get(fileId, revisionId).execute();

			String downloadURL = revision.getDownloadUrl();

			if (Validator.isNull(downloadURL)) {
				return null;
			}

			HttpResponse response =
				drive.getRequestFactory().buildGetRequest(
					new GenericUrl(downloadURL)).execute();

			return response.getContent();
		}
		catch (IOException e) {
			e.printStackTrace();

			return null;
		}
	}

	public Drive getDrive() throws PortalException, SystemException {
		HttpSession httpSession = PortalSessionThreadLocal.getHttpSession();

		Drive drive = null;

		if (httpSession != null) {
			TransientValue<Drive> transientValue =
				(TransientValue<Drive>)httpSession.getAttribute(_SESSION_KEY);

			if (transientValue != null) {
				drive = transientValue.getValue();
			}
		}
		else {
			drive = _driveThreadLocal.get();
		}

		if (drive != null) {
			return drive;
		}

		return createDrive();
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		return getFileEntries(folderId, null, start, end, obc);
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, long fileEntryTypeId, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		return new ArrayList<FileEntry>();
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		String fileId = toFileObjectId(folderId);

		try {
			StringBundler sb = new StringBundler();

			if (fileId != null) {
				sb.append("'");
				sb.append(fileId);
				sb.append("' in parents and ");
			}

			sb.append("mimeType != '");
			sb.append(_FOLDER_MIME_TYPE);
			sb.append("' and trashed = false");

			FileList fileList =
				drive.files().list().setQ(sb.toString()).execute();

			List<File> files = fileList.getItems();

			List<FileEntry> fileEntries = new ArrayList<FileEntry>();

			for (File file : files) {
				fileEntries.add(toFileEntry(file));
			}

			return ListUtil.subList(fileEntries, start, end);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new PortalException(ioe);
		}
	}

	@Override
	public int getFileEntriesCount(long folderId)
		throws PortalException, SystemException {

		return getFileEntriesCount(folderId, null);
	}

	@Override
	public int getFileEntriesCount(long folderId, long fileEntryTypeId)
		throws PortalException, SystemException {

		return 0;
	}

	@Override
	public int getFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		List<FileEntry> fileEntries = getFileEntries(
			folderId, mimeTypes, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		return fileEntries.size();
	}

	@Override
	public FileEntry getFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		try {
			File file = toFileObject(fileEntryId);

			return toFileEntry(file);
		}
		catch (Exception e) {
			throw new RepositoryException();
		}
	}

	@Override
	public FileEntry getFileEntry(long folderId, String title)
		throws PortalException, SystemException {

		File file = toFileObject(folderId, title, true);

		if (file == null) {
			throw new NoSuchFileEntryException();
		}

		return toFileEntry(file);
	}

	@Override
	public FileEntry getFileEntryByUuid(String uuid)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByUUID_G(
			uuid, getGroupId());

		if (repositoryEntry == null) {
			throw new NoSuchFileEntryException(
				"No Google Drive file entry with {uuid=" + uuid + "}");
		}

		return getFileEntry(repositoryEntry.getRepositoryEntryId());
	}

	public FileVersion getFileVersion(
			GoogleDriveVersionLabel googleDriveVersionLabel)
		throws PortalException, SystemException {

		FileEntry fileEntry = getFileEntry(
			googleDriveVersionLabel.getVersionId());

		String fileId = googleDriveVersionLabel.getFileId();
		String revisionId = googleDriveVersionLabel.getRevisionId();

		Drive drive = getDrive();

		long size = 0;

		try {
			Revision revision =
				drive.revisions().get(fileId, revisionId).execute();

			size = GetterUtil.getLong(revision.getFileSize());
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return new GoogleDriveFileVersion(
			this, fileEntry, googleDriveVersionLabel.getVersionLabel(), size,
			fileId, revisionId);
	}

	@Override
	public FileVersion getFileVersion(long fileVersionId)
		throws PortalException, SystemException {

		FileEntry fileEntry = getFileEntry(fileVersionId);

		return fileEntry.getFileVersion();
	}

	@Override
	public Folder getFolder(long folderId)
		throws PortalException, SystemException {

		Map<Long, Folder> foldersCache = _folderCache.get();

		Folder folder = foldersCache.get(folderId);

		if (folder != null) {
			return folder;
		}

		File file = toFileObject(folderId);

		folder = toFolder(file);

		foldersCache.put(folderId, folder);

		return folder;
	}

	@Override
	public Folder getFolder(long parentFolderId, String title)
		throws PortalException, SystemException {

		File file = toFileObject(parentFolderId, title, false);

		if (file == null) {
			throw new NoSuchFolderException();
		}

		return toFolder(file);
	}

	@Override
	public List<Folder> getFolders(
			long parentFolderId, boolean includeMountFolders, int start,
			int end, OrderByComparator obc)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		String fileId = toFileObjectId(parentFolderId);

		try {
			StringBundler sb = new StringBundler();

			if (fileId != null) {
				sb.append("'");
				sb.append(fileId);
				sb.append("' in parents and ");
			}

			sb.append("mimeType = '");
			sb.append(_FOLDER_MIME_TYPE);
			sb.append("' and trashed = false");

			FileList fileList =
				drive.files().list().setQ(sb.toString()).execute();

			List<File> files = fileList.getItems();

			List<Folder> folders = new ArrayList<Folder>();

			for (File file : files) {
				folders.add(toFolder(file));
			}

			return ListUtil.subList(folders, start, end);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new SystemException(ioe);
		}
	}

	@Override
	public List<Object> getFoldersAndFileEntries(
			long folderId, int start, int end, OrderByComparator obc)
		throws SystemException {

		try {
			return getFoldersAndFileEntries(folderId, null, start, end, obc);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public List<Object> getFoldersAndFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		String fileId = toFileObjectId(folderId);

		try {
			FileList fileList = null;

			if (fileId != null) {
				fileList = drive.files().list().setQ(
					"'" + fileId + "' in parents and trashed = false").execute();
			}
			else {
				fileList =
					drive.files().list().setQ("trashed = false").execute();
			}

			List<File> files = fileList.getItems();

			List<Object> foldersAndFileEntries = new ArrayList<Object>();

			for (File file : files) {
				String mimeType = file.getMimeType();

				if (mimeType.equals("application/vnd.google-apps.folder")) {
					foldersAndFileEntries.add(toFolder(file));
				}
				else {
					foldersAndFileEntries.add(toFileEntry(file));
				}
			}

			return ListUtil.subList(foldersAndFileEntries, start, end);
		}
		catch (GoogleJsonResponseException gjre) {
			if (gjre.getStatusCode() == 401) {
				throw new PrincipalException(gjre);
			}
			else {
				throw new PortalException(gjre);
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new PortalException(ioe);
		}
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId)
		throws SystemException {

		try {
			return getFoldersAndFileEntriesCount(folderId, null);
		}
		catch (PortalException pe) {
			pe.printStackTrace();

			throw new SystemException(pe);
		}
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		return 0;
	}

	@Override
	public int getFoldersCount(long parentFolderId, boolean includeMountfolders)
		throws PortalException, SystemException {

		List<Folder> folders = getFolders(
			parentFolderId, includeMountfolders, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		return folders.size();
	}

	@Override
	public int getFoldersFileEntriesCount(List<Long> folderIds, int status)
		throws PortalException, SystemException {

		int count = 0;

		for (long folderId : folderIds) {
			count += getFoldersAndFileEntriesCount(folderId);
		}

		return count;
	}

	@Override
	public List<Folder> getMountFolders(
			long parentFolderId, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		return new ArrayList<Folder>();
	}

	@Override
	public int getMountFoldersCount(long parentFolderId)
		throws PortalException, SystemException {

		return 0;
	}

	@Override
	public void getSubfolderIds(List<Long> folderIds, long folderId)
		throws PortalException, SystemException {

		List<Folder> subfolders = getFolders(
			folderId, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		getSubfolderIds(folderIds, subfolders, true);
	}

	@Override
	public List<Long> getSubfolderIds(long folderId, boolean recurse)
		throws PortalException, SystemException {

		List<Long> subfolderIds = new ArrayList<Long>();

		List<Folder> subfolders = getFolders(
			folderId, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		getSubfolderIds(subfolderIds, subfolders, recurse);

		return subfolderIds;
	}

	@Override
	public String[] getSupportedConfigurations() {
		return new String[0];
	}

	@Override
	public String[][] getSupportedParameters() {
		return new String[0][];
	}

	@Override
	public void initRepository() throws PortalException, SystemException {
		/* TODO */
	}

	@Override
	public Lock lockFolder(long folderId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public Lock lockFolder(
			long folderId, String owner, boolean inheritable,
			long expirationTime)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		File file = moveFile(fileEntryId, newFolderId);

		return toFileEntry(file);
	}

	@Override
	public Folder moveFolder(
			long folderId, long newParentFolderId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		File file = moveFile(folderId, newParentFolderId);

		return toFolder(file);
	}

	@Override
	public Lock refreshFileEntryLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public Lock refreshFolderLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void revertFileEntry(
			long fileEntryId, String version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		/* TODO */

		throw new UnsupportedOperationException();
	}

	@Override
	public Hits search(long creatorUserId, int status, int start, int end)
		throws PortalException, SystemException {

		/* TODO */

		return null;
	}

	@Override
	public Hits search(
			long creatorUserId, long folderId, String[] mimeTypes, int status,
			int start, int end)
		throws PortalException, SystemException {

		/* TODO */

		return null;
	}

	@Override
	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		/* TODO */

		return null;
	}

	@Override
	public void unlockFolder(long folderId, String lockUuid)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		File file = toFileObject(fileEntryId);

		file.setTitle(title);
		file.setDescription(description);

		try {
			if (is != null) {
				InputStreamContent inputStreamContent = new InputStreamContent(
					mimeType, is);

				file = drive.files().update(
					file.getId(), file, inputStreamContent).execute();
			}
			else {
				file = drive.files().update(file.getId(), file).execute();
			}

			return toFileEntry(file);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new PortalException(ioe);
		}
	}

	@Override
	public Folder updateFolder(
			long folderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		File file = toFileObject(folderId);

		file.setTitle(title);
		file.setDescription(description);

		try {
			file = drive.files().update(file.getId(), file).execute();

			return toFolder(file);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new PortalException(ioe);
		}
	}

	@Override
	public boolean verifyFileEntryCheckOut(long fileEntryId, String lockUuid)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean verifyInheritableLock(long folderId, String lockUuid)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	protected File addFile(
			long folderId, String mimeType, String title, String description,
			InputStream is)
		throws PortalException, SystemException {

		File body = new File();

		body.setTitle(title);
		body.setDescription(description);
		body.setMimeType(mimeType);

		if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			Folder folder = DLAppLocalServiceUtil.getFolder(folderId);

			if (!folder.isRoot()) {
				File file = toFileObject(folderId);

				body.setParents(
					Arrays.asList(new ParentReference().setId(file.getId())));
			}
		}

		com.google.api.services.drive.model.File file = null;

		Drive drive = getDrive();

		try {
			if (is != null) {
				InputStreamContent isContent = new InputStreamContent(
					mimeType, is);

				file = drive.files().insert(body, isContent).execute();
			}
			else {
				file = drive.files().insert(body).execute();
			}

			return file;
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new PortalException(ioe);
		}
	}

	protected Drive createDrive() throws PortalException, SystemException {
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();

		long userId = PrincipalThreadLocal.getUserId();

		try {
			User user = UserLocalServiceUtil.getUser(userId);

			if (!user.isDefaultUser()) {
				String googleClientId = PrefsPropsUtil.getString(
					user.getCompanyId(), "google.client.id");
				String googleClientSecret = PrefsPropsUtil.getString(
					user.getCompanyId(), "google.client.secret");

				GoogleCredential googleCredential = null;

				if (Validator.isNull(googleClientId) ||
					Validator.isNull(googleClientSecret)) {

					InputStream is =
						GoogleDriveRepository.class.getResourceAsStream(
							_CLIENT_SECRETS_LOCATION);

					try {
						GoogleClientSecrets clientSecrets =
							GoogleClientSecrets.load(
								jsonFactory, new InputStreamReader(is));

						googleCredential = new GoogleCredential.Builder().
							setTransport(httpTransport).
							setJsonFactory(jsonFactory).
							setClientSecrets(clientSecrets).build();
					}
					catch (IOException ioe) {
						throw new SystemException(ioe);
					}
				}
				else {
					googleCredential = new GoogleCredential.Builder().
						setTransport(httpTransport).setJsonFactory(jsonFactory).
						setClientSecrets(googleClientId, googleClientSecret).
						build();
				}

				ExpandoBridge expandoBridge = user.getExpandoBridge();

				String googleAccessToken = GetterUtil.getString(
					expandoBridge.getAttribute("googleAccessToken", false));
				String googleRefreshToken = GetterUtil.getString(
					expandoBridge.getAttribute("googleRefreshToken", false));

				googleCredential.setAccessToken(googleAccessToken);
				googleCredential.setRefreshToken(googleRefreshToken);

				Drive drive = new Drive.Builder(
					httpTransport, jsonFactory, googleCredential).build();

				HttpSession httpSession =
					PortalSessionThreadLocal.getHttpSession();

				if (httpSession != null) {
					httpSession.setAttribute(
						_SESSION_KEY, new TransientValue<Drive>(drive));
				}
				else {
					_driveThreadLocal.set(drive);
				}

				return drive;
			}
			else {
				throw new PrincipalException();
			}
		}
		catch (Exception e) {
			throw new PrincipalException(e);
		}
	}

	protected void deleteFile(long entryId)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		File file = toFileObject(entryId);

		try {
			drive.files().delete(file.getId()).execute();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new PortalException(ioe);
		}
	}

	protected String getNextVersion(String version, boolean majorVersion) {
		int[] versionParts = StringUtil.split(version, StringPool.PERIOD, 0);

		if (majorVersion) {
			versionParts[0]++;
			versionParts[1] = 0;
		}
		else {
			versionParts[1]++;
		}

		return versionParts[0] + StringPool.PERIOD + versionParts[1];
	}

	protected void getSubfolderIds(
			List<Long> subfolderIds, List<Folder> subfolders, boolean recurse)
		throws PortalException, SystemException {

		for (Folder subfolder : subfolders) {
			long subfolderId = subfolder.getFolderId();

			subfolderIds.add(subfolderId);

			if (recurse) {
				List<Folder> subSubFolders = getFolders(
					subfolderId, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null);

				getSubfolderIds(subfolderIds, subSubFolders, recurse);
			}
		}
	}

	protected File moveFile(long entryId, long newFolderId)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		File file = toFileObject(entryId);

		File newFile = toFileObject(newFolderId);

		List<ParentReference> parentReferences = file.getParents();

		try {
			for (ParentReference parentReference : parentReferences) {
				drive.parents().delete(file.getId(), parentReference.getId());
			}

			ParentReference newParentReference = new ParentReference();

			newParentReference.setId(newFile.getId());

			drive.parents().insert(file.getId(), newParentReference).execute();

			return file;
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new PortalException(ioe);
		}
	}

	protected FileEntry toFileEntry(File file)
		throws PortalException, SystemException {

		Map<String, FileEntry> toFileEntryCache = _toFileEntryCache.get();

		FileEntry fileEntry = toFileEntryCache.get(file.getId());

		if (fileEntry != null) {
			return fileEntry;
		}

		Object[] ids = getRepositoryEntryIds(file.getId());

		long fileEntryId = (Long)ids[0];
		String uuid = (String)ids[1];

		long parentFolderId = 0;

		List<ParentReference> parentReferences = file.getParents();

		if (!parentReferences.isEmpty()) {
			String folderId = parentReferences.get(0).getId();

			Object[] parentIds = getRepositoryEntryIds(folderId);

			parentFolderId = (Long)parentIds[0];
		}

		List<GoogleDriveVersionLabel> googleDriveVersionLabels =
			new ArrayList<GoogleDriveVersionLabel>();

		Drive drive = getDrive();

		String version = DLFileEntryConstants.VERSION_DEFAULT;

		try {
			RevisionList revisionList =
				drive.revisions().list(file.getId()).execute();

			List<Revision> revisions = revisionList.getItems();

			for (int i = 0; i < revisions.size(); i++) {
				Revision revision = revisions.get(i);

				if (i != 0) {
					version = getNextVersion(version, false);
				}

				Object[] versionIds = getRepositoryEntryIds(file.getId());

				long versionId = (Long)versionIds[0];
				String versionUuid = (String)versionIds[1];

				GoogleDriveVersionLabel googleDriveVersionLabel =
					new GoogleDriveVersionLabel(
						file.getId(), revision.getId(), version, versionId,
						versionUuid);

				googleDriveVersionLabels.add(googleDriveVersionLabel);
			}
		}
		catch (IOException ioe) {
			System.out.println("An error occurred: " + ioe);
		}

		GoogleDriveFileEntry googleDriveFileEntry = new GoogleDriveFileEntry(
			this, uuid, fileEntryId, file, googleDriveVersionLabels, version);

		googleDriveFileEntry.setParentFolderId(parentFolderId);

		boolean updateAssetEnabled = _updateAssetEnabledThreadLocal.get();

		toFileEntryCache.put(file.getId(), googleDriveFileEntry);

		if (!updateAssetEnabled) {
			return googleDriveFileEntry;
		}

		try {
			AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
				DLFileEntryConstants.getClassName(), fileEntryId);

			if (assetEntry != null) {
				return googleDriveFileEntry;
			}

			Thread currentThread = Thread.currentThread();

			ClassLoader contextClassLoader =
				currentThread.getContextClassLoader();

			FileEntry fileEntryProxy =
				(FileEntry)ProxyUtil.newProxyInstance(
					contextClassLoader, new Class[]{FileEntry.class},
					new ClassLoaderBeanHandler(
						googleDriveFileEntry, contextClassLoader));

			FileEntry fileEntryProxyBean = new FileEntryProxyBean(
				fileEntryProxy, contextClassLoader);

			FileVersion fileVersion = fileEntryProxyBean.getFileVersion();

			_updateAssetEnabledThreadLocal.set(false);

			dlAppHelperLocalService.addFileEntry(
				PrincipalThreadLocal.getUserId(), fileEntryProxyBean,
				fileVersion, new ServiceContext());
		}
		catch (Exception e) {
			_log.error("Unable to update asset", e);
		}
		finally {
			_updateAssetEnabledThreadLocal.set(updateAssetEnabled);
		}

		return googleDriveFileEntry;
	}

	protected File toFileObject(long entryId)
		throws PortalException, SystemException {

		Map<Long, File> toFileObjectEntryCache = _toFileObjectEntryCache.get();

		File file = toFileObjectEntryCache.get(entryId);

		if (file != null) {
			return file;
		}

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
			entryId);

		if (repositoryEntry == null) {
			throw new NoSuchFileEntryException();
		}

		Drive drive = getDrive();

		try {
			file = drive.files().get(repositoryEntry.getMappedId()).execute();

			toFileObjectEntryCache.put(entryId, file);

			return file;
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new PortalException(ioe);
		}
	}

	protected File toFileObject(long folderId, String name, boolean isFolder)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		File file = toFileObject(folderId);

		try {
			StringBundler sb = new StringBundler();

			sb.append("'");
			sb.append(file.getId());
			sb.append("' in parents and title contains '");
			sb.append(name);
			sb.append(" and mimeType ");

			if (isFolder) {
				sb.append("= ");
			}
			else {
				sb.append("!= ");
			}

			sb.append(_FOLDER_MIME_TYPE);

			FileList fileList =
				drive.files().list().setQ(sb.toString()).execute();

			List<File> files = fileList.getItems();

			if (files.isEmpty()) {
				return null;
			}

			return files.get(0);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new PortalException(ioe);
		}
	}

	protected String toFileObjectId(long entryId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
			entryId);

		if (repositoryEntry == null) {
			return null;
		}

		return repositoryEntry.getMappedId();
	}

	protected Folder toFolder(File file) throws SystemException {
		try {
			Object[] ids = getRepositoryEntryIds(file.getId());

			long folderId = (Long)ids[0];
			String uuid = (String)ids[1];

			long parentFolderId = 0;

			if (file.getParents().isEmpty()) {
				Folder folder = DLAppLocalServiceUtil.getMountFolder(
					getRepositoryId());

				parentFolderId = folder.getParentFolderId();
			}
			else {
				Object[] parentIds = getRepositoryEntryIds(
					file.getParents().get(0).getId());

				parentFolderId = (Long)parentIds[0];
			}

			GoogleDriveFolder googleDriveFolder = new GoogleDriveFolder(
				this, uuid, folderId, file);

			googleDriveFolder.setParentFolderId(parentFolderId);

			return googleDriveFolder;
		}
		catch (PortalException pe) {
			throw new RepositoryException(pe);
		}
	}

	private static final String _CLIENT_SECRETS_LOCATION =
		"client_secrets.json";

	private static final String _FOLDER_MIME_TYPE =
		"application/vnd.google-apps.folder";

	private static final String _SESSION_KEY =
		GoogleDriveRepository.class.getName() + ".drive";

	private static Log _log = LogFactoryUtil.getLog(
		GoogleDriveRepository.class);

	private static ThreadLocal<Map<Long, Folder>> _folderCache =
		new AutoResetThreadLocal<Map<Long, Folder>>(
			GoogleDriveRepository.class + "._folderCache",
			new HashMap<Long, Folder>());
	private static ThreadLocal<Map<String, FileEntry>> _toFileEntryCache =
		new AutoResetThreadLocal<Map<String, FileEntry>>(
			GoogleDriveRepository.class + "._toFileEntryCache",
			new HashMap<String, FileEntry>());
	private static ThreadLocal<Map<Long, File>> _toFileObjectEntryCache =
		new AutoResetThreadLocal<Map<Long, File>>(
			GoogleDriveRepository.class + "._toFileObjectEntryCache",
			new HashMap<Long, File>());

	private AutoResetThreadLocal<Drive> _driveThreadLocal =
		new AutoResetThreadLocal<Drive>(Drive.class.getName());
	private AutoResetThreadLocal<Boolean> _updateAssetEnabledThreadLocal =
		new AutoResetThreadLocal<Boolean>(
			GoogleDriveRepository.class + "_updateAssetEnabledThreadLocal",
			Boolean.TRUE);

}