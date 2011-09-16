/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.hr.service.persistence;

import com.liferay.hr.NoSuchTimeOffPolicyException;
import com.liferay.hr.model.HRTimeOffPolicy;
import com.liferay.hr.model.impl.HRTimeOffPolicyImpl;
import com.liferay.hr.model.impl.HRTimeOffPolicyModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the h r time off policy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTimeOffPolicyPersistence
 * @see HRTimeOffPolicyUtil
 * @generated
 */
public class HRTimeOffPolicyPersistenceImpl extends BasePersistenceImpl<HRTimeOffPolicy>
	implements HRTimeOffPolicyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRTimeOffPolicyUtil} to access the h r time off policy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRTimeOffPolicyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRTimeOffPolicyModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffPolicyModelImpl.FINDER_CACHE_ENABLED,
			HRTimeOffPolicyImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRTimeOffPolicyModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffPolicyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r time off policy in the entity cache if it is enabled.
	 *
	 * @param hrTimeOffPolicy the h r time off policy
	 */
	public void cacheResult(HRTimeOffPolicy hrTimeOffPolicy) {
		EntityCacheUtil.putResult(HRTimeOffPolicyModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffPolicyImpl.class, hrTimeOffPolicy.getPrimaryKey(),
			hrTimeOffPolicy);

		hrTimeOffPolicy.resetOriginalValues();
	}

	/**
	 * Caches the h r time off policies in the entity cache if it is enabled.
	 *
	 * @param hrTimeOffPolicies the h r time off policies
	 */
	public void cacheResult(List<HRTimeOffPolicy> hrTimeOffPolicies) {
		for (HRTimeOffPolicy hrTimeOffPolicy : hrTimeOffPolicies) {
			if (EntityCacheUtil.getResult(
						HRTimeOffPolicyModelImpl.ENTITY_CACHE_ENABLED,
						HRTimeOffPolicyImpl.class,
						hrTimeOffPolicy.getPrimaryKey(), this) == null) {
				cacheResult(hrTimeOffPolicy);
			}
		}
	}

	/**
	 * Clears the cache for all h r time off policies.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRTimeOffPolicyImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRTimeOffPolicyImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r time off policy.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRTimeOffPolicy hrTimeOffPolicy) {
		EntityCacheUtil.removeResult(HRTimeOffPolicyModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffPolicyImpl.class, hrTimeOffPolicy.getPrimaryKey());
	}

	/**
	 * Creates a new h r time off policy with the primary key. Does not add the h r time off policy to the database.
	 *
	 * @param hrTimeOffPolicyId the primary key for the new h r time off policy
	 * @return the new h r time off policy
	 */
	public HRTimeOffPolicy create(long hrTimeOffPolicyId) {
		HRTimeOffPolicy hrTimeOffPolicy = new HRTimeOffPolicyImpl();

		hrTimeOffPolicy.setNew(true);
		hrTimeOffPolicy.setPrimaryKey(hrTimeOffPolicyId);

		return hrTimeOffPolicy;
	}

	/**
	 * Removes the h r time off policy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r time off policy
	 * @return the h r time off policy that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r time off policy with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOffPolicy remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r time off policy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTimeOffPolicyId the primary key of the h r time off policy
	 * @return the h r time off policy that was removed
	 * @throws com.liferay.hr.NoSuchTimeOffPolicyException if a h r time off policy with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOffPolicy remove(long hrTimeOffPolicyId)
		throws NoSuchTimeOffPolicyException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRTimeOffPolicy hrTimeOffPolicy = (HRTimeOffPolicy)session.get(HRTimeOffPolicyImpl.class,
					Long.valueOf(hrTimeOffPolicyId));

			if (hrTimeOffPolicy == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrTimeOffPolicyId);
				}

				throw new NoSuchTimeOffPolicyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrTimeOffPolicyId);
			}

			return hrTimeOffPolicyPersistence.remove(hrTimeOffPolicy);
		}
		catch (NoSuchTimeOffPolicyException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the h r time off policy from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTimeOffPolicy the h r time off policy
	 * @return the h r time off policy that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOffPolicy remove(HRTimeOffPolicy hrTimeOffPolicy)
		throws SystemException {
		return super.remove(hrTimeOffPolicy);
	}

	@Override
	protected HRTimeOffPolicy removeImpl(HRTimeOffPolicy hrTimeOffPolicy)
		throws SystemException {
		hrTimeOffPolicy = toUnwrappedModel(hrTimeOffPolicy);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrTimeOffPolicy);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRTimeOffPolicyModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffPolicyImpl.class, hrTimeOffPolicy.getPrimaryKey());

		return hrTimeOffPolicy;
	}

	@Override
	public HRTimeOffPolicy updateImpl(
		com.liferay.hr.model.HRTimeOffPolicy hrTimeOffPolicy, boolean merge)
		throws SystemException {
		hrTimeOffPolicy = toUnwrappedModel(hrTimeOffPolicy);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrTimeOffPolicy, merge);

			hrTimeOffPolicy.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRTimeOffPolicyModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffPolicyImpl.class, hrTimeOffPolicy.getPrimaryKey(),
			hrTimeOffPolicy);

		return hrTimeOffPolicy;
	}

	protected HRTimeOffPolicy toUnwrappedModel(HRTimeOffPolicy hrTimeOffPolicy) {
		if (hrTimeOffPolicy instanceof HRTimeOffPolicyImpl) {
			return hrTimeOffPolicy;
		}

		HRTimeOffPolicyImpl hrTimeOffPolicyImpl = new HRTimeOffPolicyImpl();

		hrTimeOffPolicyImpl.setNew(hrTimeOffPolicy.isNew());
		hrTimeOffPolicyImpl.setPrimaryKey(hrTimeOffPolicy.getPrimaryKey());

		hrTimeOffPolicyImpl.setHrTimeOffPolicyId(hrTimeOffPolicy.getHrTimeOffPolicyId());
		hrTimeOffPolicyImpl.setGroupId(hrTimeOffPolicy.getGroupId());
		hrTimeOffPolicyImpl.setCompanyId(hrTimeOffPolicy.getCompanyId());
		hrTimeOffPolicyImpl.setUserId(hrTimeOffPolicy.getUserId());
		hrTimeOffPolicyImpl.setUserName(hrTimeOffPolicy.getUserName());
		hrTimeOffPolicyImpl.setCreateDate(hrTimeOffPolicy.getCreateDate());
		hrTimeOffPolicyImpl.setModifiedDate(hrTimeOffPolicy.getModifiedDate());
		hrTimeOffPolicyImpl.setHrTimeOffTypeId(hrTimeOffPolicy.getHrTimeOffTypeId());
		hrTimeOffPolicyImpl.setHrUserId(hrTimeOffPolicy.getHrUserId());
		hrTimeOffPolicyImpl.setAccrueHRTimeOffFrequencyTypeId(hrTimeOffPolicy.getAccrueHRTimeOffFrequencyTypeId());
		hrTimeOffPolicyImpl.setResetHRTimeOffFrequencyTypeId(hrTimeOffPolicy.getResetHRTimeOffFrequencyTypeId());
		hrTimeOffPolicyImpl.setEffectiveDate(hrTimeOffPolicy.getEffectiveDate());
		hrTimeOffPolicyImpl.setInactive(hrTimeOffPolicy.isInactive());
		hrTimeOffPolicyImpl.setHoursAllowed(hrTimeOffPolicy.getHoursAllowed());
		hrTimeOffPolicyImpl.setHoursBaseAmount(hrTimeOffPolicy.getHoursBaseAmount());
		hrTimeOffPolicyImpl.setHoursToAccrue(hrTimeOffPolicy.getHoursToAccrue());
		hrTimeOffPolicyImpl.setCarryOverHoursAllowed(hrTimeOffPolicy.getCarryOverHoursAllowed());

		return hrTimeOffPolicyImpl;
	}

	/**
	 * Returns the h r time off policy with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r time off policy
	 * @return the h r time off policy
	 * @throws com.liferay.portal.NoSuchModelException if a h r time off policy with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOffPolicy findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r time off policy with the primary key or throws a {@link com.liferay.hr.NoSuchTimeOffPolicyException} if it could not be found.
	 *
	 * @param hrTimeOffPolicyId the primary key of the h r time off policy
	 * @return the h r time off policy
	 * @throws com.liferay.hr.NoSuchTimeOffPolicyException if a h r time off policy with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOffPolicy findByPrimaryKey(long hrTimeOffPolicyId)
		throws NoSuchTimeOffPolicyException, SystemException {
		HRTimeOffPolicy hrTimeOffPolicy = fetchByPrimaryKey(hrTimeOffPolicyId);

		if (hrTimeOffPolicy == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrTimeOffPolicyId);
			}

			throw new NoSuchTimeOffPolicyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrTimeOffPolicyId);
		}

		return hrTimeOffPolicy;
	}

	/**
	 * Returns the h r time off policy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r time off policy
	 * @return the h r time off policy, or <code>null</code> if a h r time off policy with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOffPolicy fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r time off policy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrTimeOffPolicyId the primary key of the h r time off policy
	 * @return the h r time off policy, or <code>null</code> if a h r time off policy with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOffPolicy fetchByPrimaryKey(long hrTimeOffPolicyId)
		throws SystemException {
		HRTimeOffPolicy hrTimeOffPolicy = (HRTimeOffPolicy)EntityCacheUtil.getResult(HRTimeOffPolicyModelImpl.ENTITY_CACHE_ENABLED,
				HRTimeOffPolicyImpl.class, hrTimeOffPolicyId, this);

		if (hrTimeOffPolicy == _nullHRTimeOffPolicy) {
			return null;
		}

		if (hrTimeOffPolicy == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrTimeOffPolicy = (HRTimeOffPolicy)session.get(HRTimeOffPolicyImpl.class,
						Long.valueOf(hrTimeOffPolicyId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrTimeOffPolicy != null) {
					cacheResult(hrTimeOffPolicy);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRTimeOffPolicyModelImpl.ENTITY_CACHE_ENABLED,
						HRTimeOffPolicyImpl.class, hrTimeOffPolicyId,
						_nullHRTimeOffPolicy);
				}

				closeSession(session);
			}
		}

		return hrTimeOffPolicy;
	}

	/**
	 * Returns all the h r time off policies.
	 *
	 * @return the h r time off policies
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeOffPolicy> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r time off policies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r time off policies
	 * @param end the upper bound of the range of h r time off policies (not inclusive)
	 * @return the range of h r time off policies
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeOffPolicy> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r time off policies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r time off policies
	 * @param end the upper bound of the range of h r time off policies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r time off policies
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeOffPolicy> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRTimeOffPolicy> list = (List<HRTimeOffPolicy>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRTIMEOFFPOLICY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRTIMEOFFPOLICY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRTimeOffPolicy>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRTimeOffPolicy>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the h r time off policies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRTimeOffPolicy hrTimeOffPolicy : findAll()) {
			hrTimeOffPolicyPersistence.remove(hrTimeOffPolicy);
		}
	}

	/**
	 * Returns the number of h r time off policies.
	 *
	 * @return the number of h r time off policies
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HRTIMEOFFPOLICY);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the h r time off policy persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRTimeOffPolicy")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRTimeOffPolicy>> listenersList = new ArrayList<ModelListener<HRTimeOffPolicy>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRTimeOffPolicy>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(HRTimeOffPolicyImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = HRAssetPersistence.class)
	protected HRAssetPersistence hrAssetPersistence;
	@BeanReference(type = HRAssetCheckoutPersistence.class)
	protected HRAssetCheckoutPersistence hrAssetCheckoutPersistence;
	@BeanReference(type = HRAssetDefinitionPersistence.class)
	protected HRAssetDefinitionPersistence hrAssetDefinitionPersistence;
	@BeanReference(type = HRAssetProductPersistence.class)
	protected HRAssetProductPersistence hrAssetProductPersistence;
	@BeanReference(type = HRAssetTypePersistence.class)
	protected HRAssetTypePersistence hrAssetTypePersistence;
	@BeanReference(type = HRAssetVendorPersistence.class)
	protected HRAssetVendorPersistence hrAssetVendorPersistence;
	@BeanReference(type = HRBillabilityPersistence.class)
	protected HRBillabilityPersistence hrBillabilityPersistence;
	@BeanReference(type = HRBranchPersistence.class)
	protected HRBranchPersistence hrBranchPersistence;
	@BeanReference(type = HRClientPersistence.class)
	protected HRClientPersistence hrClientPersistence;
	@BeanReference(type = HREmploymentTypePersistence.class)
	protected HREmploymentTypePersistence hrEmploymentTypePersistence;
	@BeanReference(type = HRExpensePersistence.class)
	protected HRExpensePersistence hrExpensePersistence;
	@BeanReference(type = HRExpenseAccountPersistence.class)
	protected HRExpenseAccountPersistence hrExpenseAccountPersistence;
	@BeanReference(type = HRExpenseCurrencyPersistence.class)
	protected HRExpenseCurrencyPersistence hrExpenseCurrencyPersistence;
	@BeanReference(type = HRExpenseCurrencyConversionPersistence.class)
	protected HRExpenseCurrencyConversionPersistence hrExpenseCurrencyConversionPersistence;
	@BeanReference(type = HRExpenseTypePersistence.class)
	protected HRExpenseTypePersistence hrExpenseTypePersistence;
	@BeanReference(type = HRHolidayPersistence.class)
	protected HRHolidayPersistence hrHolidayPersistence;
	@BeanReference(type = HRJobTitlePersistence.class)
	protected HRJobTitlePersistence hrJobTitlePersistence;
	@BeanReference(type = HROfficePersistence.class)
	protected HROfficePersistence hrOfficePersistence;
	@BeanReference(type = HRProjectPersistence.class)
	protected HRProjectPersistence hrProjectPersistence;
	@BeanReference(type = HRProjectBillingRatePersistence.class)
	protected HRProjectBillingRatePersistence hrProjectBillingRatePersistence;
	@BeanReference(type = HRProjectRolePersistence.class)
	protected HRProjectRolePersistence hrProjectRolePersistence;
	@BeanReference(type = HRProjectStatusPersistence.class)
	protected HRProjectStatusPersistence hrProjectStatusPersistence;
	@BeanReference(type = HRTaskPersistence.class)
	protected HRTaskPersistence hrTaskPersistence;
	@BeanReference(type = HRTaskStatusPersistence.class)
	protected HRTaskStatusPersistence hrTaskStatusPersistence;
	@BeanReference(type = HRTerminationTypePersistence.class)
	protected HRTerminationTypePersistence hrTerminationTypePersistence;
	@BeanReference(type = HRTimeOffPersistence.class)
	protected HRTimeOffPersistence hrTimeOffPersistence;
	@BeanReference(type = HRTimeOffFrequencyTypePersistence.class)
	protected HRTimeOffFrequencyTypePersistence hrTimeOffFrequencyTypePersistence;
	@BeanReference(type = HRTimeOffPolicyPersistence.class)
	protected HRTimeOffPolicyPersistence hrTimeOffPolicyPersistence;
	@BeanReference(type = HRTimeOffTypePersistence.class)
	protected HRTimeOffTypePersistence hrTimeOffTypePersistence;
	@BeanReference(type = HRTimeSheetPersistence.class)
	protected HRTimeSheetPersistence hrTimeSheetPersistence;
	@BeanReference(type = HRTimeSheetDayPersistence.class)
	protected HRTimeSheetDayPersistence hrTimeSheetDayPersistence;
	@BeanReference(type = HRTimeSheetHoursPerDayPersistence.class)
	protected HRTimeSheetHoursPerDayPersistence hrTimeSheetHoursPerDayPersistence;
	@BeanReference(type = HRUserPersistence.class)
	protected HRUserPersistence hrUserPersistence;
	@BeanReference(type = HRUserHistoryPersistence.class)
	protected HRUserHistoryPersistence hrUserHistoryPersistence;
	@BeanReference(type = HRUserProjectPersistence.class)
	protected HRUserProjectPersistence hrUserProjectPersistence;
	@BeanReference(type = HRUserTaskPersistence.class)
	protected HRUserTaskPersistence hrUserTaskPersistence;
	@BeanReference(type = HRUserTimeOffPersistence.class)
	protected HRUserTimeOffPersistence hrUserTimeOffPersistence;
	@BeanReference(type = HRWageTypePersistence.class)
	protected HRWageTypePersistence hrWageTypePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_HRTIMEOFFPOLICY = "SELECT hrTimeOffPolicy FROM HRTimeOffPolicy hrTimeOffPolicy";
	private static final String _SQL_COUNT_HRTIMEOFFPOLICY = "SELECT COUNT(hrTimeOffPolicy) FROM HRTimeOffPolicy hrTimeOffPolicy";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrTimeOffPolicy.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRTimeOffPolicy exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRTimeOffPolicyPersistenceImpl.class);
	private static HRTimeOffPolicy _nullHRTimeOffPolicy = new HRTimeOffPolicyImpl() {
			public Object clone() {
				return this;
			}

			public CacheModel<HRTimeOffPolicy> toCacheModel() {
				return _nullHRTimeOffPolicyCacheModel;
			}
		};

	private static CacheModel<HRTimeOffPolicy> _nullHRTimeOffPolicyCacheModel = new CacheModel<HRTimeOffPolicy>() {
			public HRTimeOffPolicy toEntityModel() {
				return _nullHRTimeOffPolicy;
			}
		};
}