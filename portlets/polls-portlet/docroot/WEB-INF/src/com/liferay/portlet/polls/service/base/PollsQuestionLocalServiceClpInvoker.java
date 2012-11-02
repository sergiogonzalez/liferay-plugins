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

package com.liferay.portlet.polls.service.base;

import com.liferay.portlet.polls.service.PollsQuestionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class PollsQuestionLocalServiceClpInvoker {
	public PollsQuestionLocalServiceClpInvoker() {
		_methodName0 = "addPollsQuestion";

		_methodParameterTypes0 = new String[] {
				"com.liferay.portlet.polls.model.PollsQuestion"
			};

		_methodName1 = "createPollsQuestion";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deletePollsQuestion";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deletePollsQuestion";

		_methodParameterTypes3 = new String[] {
				"com.liferay.portlet.polls.model.PollsQuestion"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "fetchPollsQuestion";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getPollsQuestion";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getPollsQuestionByUuidAndGroupId";

		_methodParameterTypes12 = new String[] { "java.lang.String", "long" };

		_methodName13 = "getPollsQuestions";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getPollsQuestionsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updatePollsQuestion";

		_methodParameterTypes15 = new String[] {
				"com.liferay.portlet.polls.model.PollsQuestion"
			};

		_methodName16 = "updatePollsQuestion";

		_methodParameterTypes16 = new String[] {
				"com.liferay.portlet.polls.model.PollsQuestion", "boolean"
			};

		_methodName49 = "getBeanIdentifier";

		_methodParameterTypes49 = new String[] {  };

		_methodName50 = "setBeanIdentifier";

		_methodParameterTypes50 = new String[] { "java.lang.String" };

		_methodName55 = "addQuestion";

		_methodParameterTypes55 = new String[] {
				"long", "java.util.Map", "java.util.Map", "int", "int", "int",
				"int", "int", "boolean", "java.util.List",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName56 = "addQuestionResources";

		_methodParameterTypes56 = new String[] { "long", "boolean", "boolean" };

		_methodName57 = "addQuestionResources";

		_methodParameterTypes57 = new String[] {
				"long", "java.lang.String[][]", "java.lang.String[][]"
			};

		_methodName58 = "addQuestionResources";

		_methodParameterTypes58 = new String[] {
				"com.liferay.portlet.polls.model.PollsQuestion", "boolean",
				"boolean"
			};

		_methodName59 = "addQuestionResources";

		_methodParameterTypes59 = new String[] {
				"com.liferay.portlet.polls.model.PollsQuestion",
				"java.lang.String[][]", "java.lang.String[][]"
			};

		_methodName60 = "deleteQuestion";

		_methodParameterTypes60 = new String[] { "long" };

		_methodName61 = "deleteQuestion";

		_methodParameterTypes61 = new String[] {
				"com.liferay.portlet.polls.model.PollsQuestion"
			};

		_methodName62 = "deleteQuestions";

		_methodParameterTypes62 = new String[] { "long" };

		_methodName63 = "getQuestion";

		_methodParameterTypes63 = new String[] { "long" };

		_methodName64 = "getQuestions";

		_methodParameterTypes64 = new String[] { "long" };

		_methodName65 = "getQuestions";

		_methodParameterTypes65 = new String[] { "long", "int", "int" };

		_methodName66 = "getQuestionsCount";

		_methodParameterTypes66 = new String[] { "long" };

		_methodName67 = "updateQuestion";

		_methodParameterTypes67 = new String[] {
				"long", "long", "java.util.Map", "java.util.Map", "int", "int",
				"int", "int", "int", "boolean", "java.util.List",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.addPollsQuestion((com.liferay.portlet.polls.model.PollsQuestion)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.createPollsQuestion(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.deletePollsQuestion(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.deletePollsQuestion((com.liferay.portlet.polls.model.PollsQuestion)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.fetchPollsQuestion(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.getPollsQuestion(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.getPollsQuestionByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.getPollsQuestions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.getPollsQuestionsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.updatePollsQuestion((com.liferay.portlet.polls.model.PollsQuestion)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.updatePollsQuestion((com.liferay.portlet.polls.model.PollsQuestion)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			PollsQuestionLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.addQuestion(((Long)arguments[0]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Boolean)arguments[8]).booleanValue(),
				(java.util.List<com.liferay.portlet.polls.model.PollsChoice>)arguments[9],
				(com.liferay.portal.service.ServiceContext)arguments[10]);
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			PollsQuestionLocalServiceUtil.addQuestionResources(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());

			return null;
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			PollsQuestionLocalServiceUtil.addQuestionResources(((Long)arguments[0]).longValue(),
				(java.lang.String[])arguments[1],
				(java.lang.String[])arguments[2]);

			return null;
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			PollsQuestionLocalServiceUtil.addQuestionResources((com.liferay.portlet.polls.model.PollsQuestion)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());

			return null;
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			PollsQuestionLocalServiceUtil.addQuestionResources((com.liferay.portlet.polls.model.PollsQuestion)arguments[0],
				(java.lang.String[])arguments[1],
				(java.lang.String[])arguments[2]);

			return null;
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			PollsQuestionLocalServiceUtil.deleteQuestion(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			PollsQuestionLocalServiceUtil.deleteQuestion((com.liferay.portlet.polls.model.PollsQuestion)arguments[0]);

			return null;
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			PollsQuestionLocalServiceUtil.deleteQuestions(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.getQuestion(((Long)arguments[0]).longValue());
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.getQuestions(((Long)arguments[0]).longValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.getQuestions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.getQuestionsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return PollsQuestionLocalServiceUtil.updateQuestion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Boolean)arguments[9]).booleanValue(),
				(java.util.List<com.liferay.portlet.polls.model.PollsChoice>)arguments[10],
				(com.liferay.portal.service.ServiceContext)arguments[11]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName49;
	private String[] _methodParameterTypes49;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName64;
	private String[] _methodParameterTypes64;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName66;
	private String[] _methodParameterTypes66;
	private String _methodName67;
	private String[] _methodParameterTypes67;
}