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

package com.liferay.bbb.service.persistence;

import com.liferay.bbb.model.BBBParticipant;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the b b b participant service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see BBBParticipantPersistenceImpl
 * @see BBBParticipantUtil
 * @generated
 */
public interface BBBParticipantPersistence extends BasePersistence<BBBParticipant> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BBBParticipantUtil} to access the b b b participant persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the b b b participants where bbbMeetingId = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @return the matching b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.BBBParticipant> findByBbbMeetingId(
		long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the b b b participants where bbbMeetingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param start the lower bound of the range of b b b participants
	* @param end the upper bound of the range of b b b participants (not inclusive)
	* @return the range of matching b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.BBBParticipant> findByBbbMeetingId(
		long bbbMeetingId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the b b b participants where bbbMeetingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param start the lower bound of the range of b b b participants
	* @param end the upper bound of the range of b b b participants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.BBBParticipant> findByBbbMeetingId(
		long bbbMeetingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first b b b participant in the ordered set where bbbMeetingId = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b participant
	* @throws com.liferay.bbb.NoSuchParticipantException if a matching b b b participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.BBBParticipant findByBbbMeetingId_First(
		long bbbMeetingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchParticipantException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first b b b participant in the ordered set where bbbMeetingId = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b participant, or <code>null</code> if a matching b b b participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.BBBParticipant fetchByBbbMeetingId_First(
		long bbbMeetingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last b b b participant in the ordered set where bbbMeetingId = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b participant
	* @throws com.liferay.bbb.NoSuchParticipantException if a matching b b b participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.BBBParticipant findByBbbMeetingId_Last(
		long bbbMeetingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchParticipantException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last b b b participant in the ordered set where bbbMeetingId = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b participant, or <code>null</code> if a matching b b b participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.BBBParticipant fetchByBbbMeetingId_Last(
		long bbbMeetingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the b b b participants before and after the current b b b participant in the ordered set where bbbMeetingId = &#63;.
	*
	* @param bbbParticipantId the primary key of the current b b b participant
	* @param bbbMeetingId the bbb meeting ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next b b b participant
	* @throws com.liferay.bbb.NoSuchParticipantException if a b b b participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.BBBParticipant[] findByBbbMeetingId_PrevAndNext(
		long bbbParticipantId, long bbbMeetingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchParticipantException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the b b b participants where bbbMeetingId = &#63; from the database.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByBbbMeetingId(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of b b b participants where bbbMeetingId = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @return the number of matching b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public int countByBbbMeetingId(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the b b b participant where bbbMeetingId = &#63; and emailAddress = &#63; or throws a {@link com.liferay.bbb.NoSuchParticipantException} if it could not be found.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param emailAddress the email address
	* @return the matching b b b participant
	* @throws com.liferay.bbb.NoSuchParticipantException if a matching b b b participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.BBBParticipant findByBMI_EA(
		long bbbMeetingId, java.lang.String emailAddress)
		throws com.liferay.bbb.NoSuchParticipantException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the b b b participant where bbbMeetingId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param emailAddress the email address
	* @return the matching b b b participant, or <code>null</code> if a matching b b b participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.BBBParticipant fetchByBMI_EA(
		long bbbMeetingId, java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the b b b participant where bbbMeetingId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param emailAddress the email address
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching b b b participant, or <code>null</code> if a matching b b b participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.BBBParticipant fetchByBMI_EA(
		long bbbMeetingId, java.lang.String emailAddress,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the b b b participant where bbbMeetingId = &#63; and emailAddress = &#63; from the database.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param emailAddress the email address
	* @return the b b b participant that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.BBBParticipant removeByBMI_EA(
		long bbbMeetingId, java.lang.String emailAddress)
		throws com.liferay.bbb.NoSuchParticipantException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of b b b participants where bbbMeetingId = &#63; and emailAddress = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param emailAddress the email address
	* @return the number of matching b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public int countByBMI_EA(long bbbMeetingId, java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the b b b participant in the entity cache if it is enabled.
	*
	* @param bbbParticipant the b b b participant
	*/
	public void cacheResult(com.liferay.bbb.model.BBBParticipant bbbParticipant);

	/**
	* Caches the b b b participants in the entity cache if it is enabled.
	*
	* @param bbbParticipants the b b b participants
	*/
	public void cacheResult(
		java.util.List<com.liferay.bbb.model.BBBParticipant> bbbParticipants);

	/**
	* Creates a new b b b participant with the primary key. Does not add the b b b participant to the database.
	*
	* @param bbbParticipantId the primary key for the new b b b participant
	* @return the new b b b participant
	*/
	public com.liferay.bbb.model.BBBParticipant create(long bbbParticipantId);

	/**
	* Removes the b b b participant with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbParticipantId the primary key of the b b b participant
	* @return the b b b participant that was removed
	* @throws com.liferay.bbb.NoSuchParticipantException if a b b b participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.BBBParticipant remove(long bbbParticipantId)
		throws com.liferay.bbb.NoSuchParticipantException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.bbb.model.BBBParticipant updateImpl(
		com.liferay.bbb.model.BBBParticipant bbbParticipant)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the b b b participant with the primary key or throws a {@link com.liferay.bbb.NoSuchParticipantException} if it could not be found.
	*
	* @param bbbParticipantId the primary key of the b b b participant
	* @return the b b b participant
	* @throws com.liferay.bbb.NoSuchParticipantException if a b b b participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.BBBParticipant findByPrimaryKey(
		long bbbParticipantId)
		throws com.liferay.bbb.NoSuchParticipantException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the b b b participant with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param bbbParticipantId the primary key of the b b b participant
	* @return the b b b participant, or <code>null</code> if a b b b participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.BBBParticipant fetchByPrimaryKey(
		long bbbParticipantId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the b b b participants.
	*
	* @return the b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.BBBParticipant> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the b b b participants.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of b b b participants
	* @param end the upper bound of the range of b b b participants (not inclusive)
	* @return the range of b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.BBBParticipant> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the b b b participants.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of b b b participants
	* @param end the upper bound of the range of b b b participants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.BBBParticipant> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the b b b participants from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of b b b participants.
	*
	* @return the number of b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}