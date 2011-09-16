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

import com.liferay.hr.NoSuchAssetException;
import com.liferay.hr.model.HRAsset;
import com.liferay.hr.model.impl.HRAssetImpl;
import com.liferay.hr.model.impl.HRAssetModelImpl;

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
 * The persistence implementation for the h r asset service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetPersistence
 * @see HRAssetUtil
 * @generated
 */
public class HRAssetPersistenceImpl extends BasePersistenceImpl<HRAsset>
	implements HRAssetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRAssetUtil} to access the h r asset persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRAssetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRAssetModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetModelImpl.FINDER_CACHE_ENABLED, HRAssetImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRAssetModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r asset in the entity cache if it is enabled.
	 *
	 * @param hrAsset the h r asset
	 */
	public void cacheResult(HRAsset hrAsset) {
		EntityCacheUtil.putResult(HRAssetModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetImpl.class, hrAsset.getPrimaryKey(), hrAsset);

		hrAsset.resetOriginalValues();
	}

	/**
	 * Caches the h r assets in the entity cache if it is enabled.
	 *
	 * @param hrAssets the h r assets
	 */
	public void cacheResult(List<HRAsset> hrAssets) {
		for (HRAsset hrAsset : hrAssets) {
			if (EntityCacheUtil.getResult(
						HRAssetModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetImpl.class, hrAsset.getPrimaryKey(), this) == null) {
				cacheResult(hrAsset);
			}
		}
	}

	/**
	 * Clears the cache for all h r assets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRAssetImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRAssetImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r asset.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRAsset hrAsset) {
		EntityCacheUtil.removeResult(HRAssetModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetImpl.class, hrAsset.getPrimaryKey());
	}

	/**
	 * Creates a new h r asset with the primary key. Does not add the h r asset to the database.
	 *
	 * @param hrAssetId the primary key for the new h r asset
	 * @return the new h r asset
	 */
	public HRAsset create(long hrAssetId) {
		HRAsset hrAsset = new HRAssetImpl();

		hrAsset.setNew(true);
		hrAsset.setPrimaryKey(hrAssetId);

		return hrAsset;
	}

	/**
	 * Removes the h r asset with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r asset
	 * @return the h r asset that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAsset remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r asset with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetId the primary key of the h r asset
	 * @return the h r asset that was removed
	 * @throws com.liferay.hr.NoSuchAssetException if a h r asset with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAsset remove(long hrAssetId)
		throws NoSuchAssetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRAsset hrAsset = (HRAsset)session.get(HRAssetImpl.class,
					Long.valueOf(hrAssetId));

			if (hrAsset == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrAssetId);
				}

				throw new NoSuchAssetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrAssetId);
			}

			return hrAssetPersistence.remove(hrAsset);
		}
		catch (NoSuchAssetException nsee) {
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
	 * Removes the h r asset from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAsset the h r asset
	 * @return the h r asset that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAsset remove(HRAsset hrAsset) throws SystemException {
		return super.remove(hrAsset);
	}

	@Override
	protected HRAsset removeImpl(HRAsset hrAsset) throws SystemException {
		hrAsset = toUnwrappedModel(hrAsset);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrAsset);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRAssetModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetImpl.class, hrAsset.getPrimaryKey());

		return hrAsset;
	}

	@Override
	public HRAsset updateImpl(com.liferay.hr.model.HRAsset hrAsset,
		boolean merge) throws SystemException {
		hrAsset = toUnwrappedModel(hrAsset);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrAsset, merge);

			hrAsset.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRAssetModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetImpl.class, hrAsset.getPrimaryKey(), hrAsset);

		return hrAsset;
	}

	protected HRAsset toUnwrappedModel(HRAsset hrAsset) {
		if (hrAsset instanceof HRAssetImpl) {
			return hrAsset;
		}

		HRAssetImpl hrAssetImpl = new HRAssetImpl();

		hrAssetImpl.setNew(hrAsset.isNew());
		hrAssetImpl.setPrimaryKey(hrAsset.getPrimaryKey());

		hrAssetImpl.setHrAssetId(hrAsset.getHrAssetId());
		hrAssetImpl.setGroupId(hrAsset.getGroupId());
		hrAssetImpl.setCompanyId(hrAsset.getCompanyId());
		hrAssetImpl.setUserId(hrAsset.getUserId());
		hrAssetImpl.setUserName(hrAsset.getUserName());
		hrAssetImpl.setCreateDate(hrAsset.getCreateDate());
		hrAssetImpl.setModifiedDate(hrAsset.getModifiedDate());
		hrAssetImpl.setHrAssetDefinitionId(hrAsset.getHrAssetDefinitionId());
		hrAssetImpl.setHrAssetTypeId(hrAsset.getHrAssetTypeId());
		hrAssetImpl.setSerialNumber(hrAsset.getSerialNumber());
		hrAssetImpl.setInactiveDate(hrAsset.getInactiveDate());

		return hrAssetImpl;
	}

	/**
	 * Returns the h r asset with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset
	 * @return the h r asset
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAsset findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r asset with the primary key or throws a {@link com.liferay.hr.NoSuchAssetException} if it could not be found.
	 *
	 * @param hrAssetId the primary key of the h r asset
	 * @return the h r asset
	 * @throws com.liferay.hr.NoSuchAssetException if a h r asset with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAsset findByPrimaryKey(long hrAssetId)
		throws NoSuchAssetException, SystemException {
		HRAsset hrAsset = fetchByPrimaryKey(hrAssetId);

		if (hrAsset == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrAssetId);
			}

			throw new NoSuchAssetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrAssetId);
		}

		return hrAsset;
	}

	/**
	 * Returns the h r asset with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset
	 * @return the h r asset, or <code>null</code> if a h r asset with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAsset fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r asset with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrAssetId the primary key of the h r asset
	 * @return the h r asset, or <code>null</code> if a h r asset with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAsset fetchByPrimaryKey(long hrAssetId) throws SystemException {
		HRAsset hrAsset = (HRAsset)EntityCacheUtil.getResult(HRAssetModelImpl.ENTITY_CACHE_ENABLED,
				HRAssetImpl.class, hrAssetId, this);

		if (hrAsset == _nullHRAsset) {
			return null;
		}

		if (hrAsset == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrAsset = (HRAsset)session.get(HRAssetImpl.class,
						Long.valueOf(hrAssetId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrAsset != null) {
					cacheResult(hrAsset);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRAssetModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetImpl.class, hrAssetId, _nullHRAsset);
				}

				closeSession(session);
			}
		}

		return hrAsset;
	}

	/**
	 * Returns all the h r assets.
	 *
	 * @return the h r assets
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAsset> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r assets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r assets
	 * @param end the upper bound of the range of h r assets (not inclusive)
	 * @return the range of h r assets
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAsset> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r assets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r assets
	 * @param end the upper bound of the range of h r assets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r assets
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAsset> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRAsset> list = (List<HRAsset>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRASSET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRASSET;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRAsset>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRAsset>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the h r assets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRAsset hrAsset : findAll()) {
			hrAssetPersistence.remove(hrAsset);
		}
	}

	/**
	 * Returns the number of h r assets.
	 *
	 * @return the number of h r assets
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

				Query q = session.createQuery(_SQL_COUNT_HRASSET);

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
	 * Initializes the h r asset persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRAsset")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRAsset>> listenersList = new ArrayList<ModelListener<HRAsset>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRAsset>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRAssetImpl.class.getName());
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
	private static final String _SQL_SELECT_HRASSET = "SELECT hrAsset FROM HRAsset hrAsset";
	private static final String _SQL_COUNT_HRASSET = "SELECT COUNT(hrAsset) FROM HRAsset hrAsset";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrAsset.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRAsset exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRAssetPersistenceImpl.class);
	private static HRAsset _nullHRAsset = new HRAssetImpl() {
			public Object clone() {
				return this;
			}

			public CacheModel<HRAsset> toCacheModel() {
				return _nullHRAssetCacheModel;
			}
		};

	private static CacheModel<HRAsset> _nullHRAssetCacheModel = new CacheModel<HRAsset>() {
			public HRAsset toEntityModel() {
				return _nullHRAsset;
			}
		};
}