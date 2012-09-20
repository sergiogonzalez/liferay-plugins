/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.resourcesimporter.util;

import com.liferay.portal.kernel.util.StringPool;

import java.net.URL;
import java.net.URLConnection;

import java.util.Set;

/**
 * @author Raymond Augé
 * @author Ryan Park
 */
public class ResourceImporter extends FileSystemImporter {

	@Override
	public void importResources() throws Exception {
		doImportResources();
	}

	@Override
	protected void addDLFileEntries(String fileEntriesDirName)
		throws Exception {

		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcesDir.concat(fileEntriesDirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			URL resourceURL = servletContext.getResource(resourcePath);

			String path = resourceURL.getPath();

			if (path.endsWith(StringPool.SLASH)) {
				continue;
			}

			URLConnection urlConnection = resourceURL.openConnection();

			String name = resourceURL.getFile();

			int pos = name.lastIndexOf(StringPool.SLASH);

			if (pos != -1) {
				name = name.substring(pos + 1);
			}

			doAddDLFileEntries(
				name, urlConnection.getInputStream(),
				urlConnection.getContentLength());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void addJournalArticles(
			String journalStructureId, String journalTemplateId,
			String articlesDirName)
		throws Exception {

		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcesDir.concat(articlesDirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			URL resourceURL = servletContext.getResource(resourcePath);

			String path = resourceURL.getPath();

			if (path.endsWith(StringPool.SLASH)) {
				continue;
			}

			URLConnection urlConnection = resourceURL.openConnection();

			String name = resourceURL.getFile();

			int pos = name.lastIndexOf(StringPool.SLASH);

			if (pos != -1) {
				name = name.substring(pos + 1);
			}

			doAddJournalArticles(
				journalStructureId, journalTemplateId, name,
				urlConnection.getInputStream());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void addJournalStructures(String structuresDirName)
		throws Exception {

		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcesDir.concat(structuresDirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			URL resourceURL = servletContext.getResource(resourcePath);

			String path = resourceURL.getPath();

			if (path.endsWith(StringPool.SLASH)) {
				continue;
			}

			URLConnection urlConnection = resourceURL.openConnection();

			String name = resourceURL.getFile();

			int pos = name.lastIndexOf(StringPool.SLASH);

			if (pos != -1) {
				name = name.substring(pos + 1);
			}

			doAddJournalStructures(name, urlConnection.getInputStream());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void addJournalTemplates(
			String journalStructureId, String templatesDirName)
		throws Exception {

		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcesDir.concat(templatesDirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			URL resourceURL = servletContext.getResource(resourcePath);

			String path = resourceURL.getPath();

			if (path.endsWith(StringPool.SLASH)) {
				continue;
			}

			URLConnection urlConnection = resourceURL.openConnection();

			String name = resourceURL.getFile();

			int pos = name.lastIndexOf(StringPool.SLASH);

			if (pos != -1) {
				name = name.substring(pos + 1);
			}

			doAddJournalTemplates(
				journalStructureId, name, urlConnection.getInputStream());
		}
	}

	@Override
	protected void setupSettings(String settingsName) throws Exception {
		URL settingsJSONURL = servletContext.getResource(
			resourcesDir.concat(settingsName));

		if (settingsJSONURL == null) {
			return;
		}

		URLConnection urlConnection = settingsJSONURL.openConnection();

		setupSettings(urlConnection.getInputStream());
	}

	@Override
	protected void setupSitemap(String sitemapName) throws Exception {
		URL sitemapJSONURL = servletContext.getResource(
			resourcesDir.concat(sitemapName));

		if (sitemapJSONURL == null) {
			return;
		}

		URLConnection urlConnection = sitemapJSONURL.openConnection();

		setupSitemap(urlConnection.getInputStream());
	}

}