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

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Revision;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.BaseRepositoryImpl;
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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.model.User;
import com.liferay.portal.repository.google.drive.model.GoogleDriveFileVersion;
import com.liferay.portal.repository.google.drive.model.GoogleDriveVersionLabel;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.RepositoryEntryUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.util.portlet.PortletProps;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

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

		return null;
	}

	@Override
	public Folder addFolder(
			long parentFolderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public FileVersion cancelCheckOut(long fileEntryId)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public void checkInFileEntry(
			long fileEntryId, boolean major, String changeLog,
			ServiceContext serviceContext)
		throws PortalException, SystemException {
	}

	@Override
	public void checkInFileEntry(
			long fileEntryId, String lockUuid, ServiceContext serviceContext)
		throws PortalException, SystemException {
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, String owner, long expirationTime,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return null;
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
	}

	@Override
	public void deleteFolder(long folderId)
		throws PortalException, SystemException {
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

	public Drive getDrive() throws PortalException {
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

		return null;
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, long fileEntryTypeId, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public int getFileEntriesCount(long folderId)
		throws PortalException, SystemException {

		return 0;
	}

	@Override
	public int getFileEntriesCount(long folderId, long fileEntryTypeId)
		throws PortalException, SystemException {

		return 0;
	}

	@Override
	public int getFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		return 0;
	}

	@Override
	public FileEntry getFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public FileEntry getFileEntry(long folderId, String title)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public FileEntry getFileEntryByUuid(String uuid)
		throws PortalException, SystemException {

		return null;
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

		return null;
	}

	@Override
	public Folder getFolder(long folderId)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public Folder getFolder(long parentFolderId, String title)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public List<Folder> getFolders(
			long parentFolderId, boolean includeMountFolders, int start,
			int end, OrderByComparator obc)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public List<Object> getFoldersAndFileEntries(
			long folderId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return null;
	}

	@Override
	public List<Object> getFoldersAndFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId)
		throws SystemException {

		return 0;
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		return 0;
	}

	@Override
	public int getFoldersCount(long parentFolderId, boolean includeMountfolders)
		throws PortalException, SystemException {

		return 0;
	}

	@Override
	public int getFoldersFileEntriesCount(List<Long> folderIds, int status)
		throws PortalException, SystemException {

		return 0;
	}

	@Override
	public List<Folder> getMountFolders(
			long parentFolderId, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public int getMountFoldersCount(long parentFolderId)
		throws PortalException, SystemException {

		return 0;
	}

	@Override
	public void getSubfolderIds(List<Long> folderIds, long folderId)
		throws PortalException, SystemException {
	}

	@Override
	public List<Long> getSubfolderIds(long folderId, boolean recurse)
		throws PortalException, SystemException {

		return null;
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
	}

	@Override
	public Lock lockFolder(long folderId)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public Lock lockFolder(
			long folderId, String owner, boolean inheritable,
			long expirationTime)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public FileEntry moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public Folder moveFolder(
			long folderId, long newParentFolderId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public Lock refreshFileEntryLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public Lock refreshFolderLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public void revertFileEntry(
			long fileEntryId, String version, ServiceContext serviceContext)
		throws PortalException, SystemException {
	}

	@Override
	public Hits search(long creatorUserId, int status, int start, int end)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public Hits search(
			long creatorUserId, long folderId, String[] mimeTypes, int status,
			int start, int end)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		return null;
	}

	@Override
	public void unlockFolder(long folderId, String lockUuid)
		throws PortalException, SystemException {
	}

	@Override
	public FileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public Folder updateFolder(
			long folderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public boolean verifyFileEntryCheckOut(long fileEntryId, String lockUuid)
		throws PortalException, SystemException {

		return false;
	}

	@Override
	public boolean verifyInheritableLock(long folderId, String lockUuid)
		throws PortalException, SystemException {

		return false;
	}

	protected Drive createDrive() throws PortalException {
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();

		long userId = PrincipalThreadLocal.getUserId();

		try {
			User user = UserLocalServiceUtil.getUser(userId);

			if (!user.isDefaultUser()) {
				GoogleCredential googleCredential =
					new GoogleCredential.Builder().setTransport(httpTransport).
						setJsonFactory(jsonFactory).
						setClientSecrets(_CLIENT_ID, _CLIENT_SECRET).build();

				// Need to get the accessToken and refreshToken from expando.
				// This will be implemented in the upcoming commits.

				String accessToken = StringPool.BLANK;
				String refreshToken = StringPool.BLANK;

				googleCredential.setAccessToken(accessToken);
				googleCredential.setRefreshToken(refreshToken);

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

	protected File toFileObject(long entryId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
			entryId);

		if (repositoryEntry == null) {
			throw new NoSuchFileEntryException();
		}

		Drive drive = getDrive();

		try {
			return drive.files().get(repositoryEntry.getMappedId()).execute();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new PortalException(ioe);
		}
	}

	private static final String _CLIENT_ID = PortletProps.get(
		"google.client.id");

	private static final String _CLIENT_SECRET = PortletProps.get(
		"google.client.secret");

	private static final String _SESSION_KEY =
		GoogleDriveRepository.class.getName() + ".drive";

	private AutoResetThreadLocal<Drive> _driveThreadLocal =
		new AutoResetThreadLocal<Drive>(Drive.class.getName());

}