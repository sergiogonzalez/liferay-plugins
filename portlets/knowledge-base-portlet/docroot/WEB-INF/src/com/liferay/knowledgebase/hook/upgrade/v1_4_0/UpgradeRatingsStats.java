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

package com.liferay.knowledgebase.hook.upgrade.v1_4_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Roberto Díaz
 */
public class UpgradeRatingsStats
	extends com.liferay.knowledgebase.hook.upgrade.v1_0_0.UpgradeRatingsStats {

	@Override
	protected void updateRatingsStats() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			long classNameId = getClassNameId(KB_ARTICLE_CLASS_NAME);

			ps = con.prepareStatement(
				"select statsId, totalScore, averageScore from RatingsStats " +
					"where classNameId = " + classNameId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long statsId = rs.getLong("statsId");
				double totalScore = rs.getDouble("totalScore");
				double averageScore = rs.getDouble("averageScore");

				StringBundler sb = new StringBundler(6);

				sb.append("update RatingsStats set totalScore = ");
				sb.append(totalScore / 2);
				sb.append(", averageScore = ");
				sb.append(averageScore / 2);
				sb.append(" where statsId = ");
				sb.append(statsId);

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static final String KB_ARTICLE_CLASS_NAME =
		"com.liferay.knowledgebase.model.KBArticle";

}