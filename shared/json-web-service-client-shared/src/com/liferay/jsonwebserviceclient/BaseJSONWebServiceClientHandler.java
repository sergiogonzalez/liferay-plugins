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

package com.liferay.jsonwebserviceclient;

import flexjson.JSONDeserializer;

import java.io.IOException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.CredentialException;

/**
 * @author Igor Beslic
 */
public abstract class BaseJSONWebServiceClientHandler {

	public abstract JSONWebServiceClient getJSONWebServiceClient();

	protected String doGet(String url, String... parametersArray)
		throws CredentialException, IOException {

		Map<String, String> parameters = new HashMap<String, String>();

		for (int i = 0; i < parametersArray.length; i += 2) {
			parameters.put(parametersArray[i], parametersArray[i + 1]);
		}

		return getJSONWebServiceClient().doGet(url, parameters);
	}

	protected <T> List<T> doGetToList(
			Class<T> clazz, String url, String... parametersArray)
		throws Exception {

		String json = doGet(url, parametersArray);

		if ((json == null) || json.equals("{}") || json.equals("[]")) {
			return Collections.emptyList();
		}

		if (json.contains("exception\":\"")) {
			throw new Exception(getExceptionMessage(json));
		}

		JSONDeserializer<List<T>> jsonDeserializer =
			new JSONDeserializer<List<T>>();

		jsonDeserializer.use("values", clazz);

		return jsonDeserializer.deserialize(json);
	}

	protected <T> T doGetToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws Exception {

		String json = doGet(url, parametersArray);

		if ((json == null) || json.equals("")) {
			return null;
		}

		if (json.contains("exception\":\"")) {
			throw new Exception(getExceptionMessage(json));
		}

		JSONDeserializer<T> jsonDeserializer = new JSONDeserializer<T>();

		return jsonDeserializer.deserialize(json, clazz);
	}

	protected <T> T doGetToObject(String url, String... parametersArray)
		throws Exception {

		String json = doGet(url, parametersArray);

		if ((json == null) || json.equals("")) {
			return null;
		}

		if (json.contains("exception\":\"")) {
			throw new Exception(getExceptionMessage(json));
		}

		JSONDeserializer<T> jsonDeserializer = new JSONDeserializer<T>();

		return jsonDeserializer.deserialize(json);
	}

	protected void doPost(String url, String... parametersArray)
		throws CredentialException, IOException {

		Map<String, String> parameters = new HashMap<String, String>();

		for (int i = 0; i < parametersArray.length; i += 2) {
			parameters.put(parametersArray[i], parametersArray[i + 1]);
		}

		getJSONWebServiceClient().doPost(url, parameters);
	}

	protected String getExceptionMessage(String json) {
		int exceptionMessageStart = json.indexOf("exception\":\"") + 12;

		int exceptionMessageEnd = json.indexOf("\"", exceptionMessageStart);

		return json.substring(exceptionMessageStart, exceptionMessageEnd);
	}

}