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

package com.liferay.docs.accounts.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountLocalService}.
 *
 * @author a616659
 * @see AccountLocalService
 * @generated
 */
public class AccountLocalServiceWrapper implements AccountLocalService,
	ServiceWrapper<AccountLocalService> {
	public AccountLocalServiceWrapper(AccountLocalService accountLocalService) {
		_accountLocalService = accountLocalService;
	}

	/**
	* Adds the account to the database. Also notifies the appropriate model listeners.
	*
	* @param account the account
	* @return the account that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.accounts.model.Account addAccount(
		com.liferay.docs.accounts.model.Account account)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.addAccount(account);
	}

	/**
	* Creates a new account with the primary key. Does not add the account to the database.
	*
	* @param accountId the primary key for the new account
	* @return the new account
	*/
	@Override
	public com.liferay.docs.accounts.model.Account createAccount(long accountId) {
		return _accountLocalService.createAccount(accountId);
	}

	/**
	* Deletes the account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountId the primary key of the account
	* @return the account that was removed
	* @throws PortalException if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.accounts.model.Account deleteAccount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.deleteAccount(accountId);
	}

	/**
	* Deletes the account from the database. Also notifies the appropriate model listeners.
	*
	* @param account the account
	* @return the account that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.accounts.model.Account deleteAccount(
		com.liferay.docs.accounts.model.Account account)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.deleteAccount(account);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.accounts.model.impl.AccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.accounts.model.impl.AccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.docs.accounts.model.Account fetchAccount(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.fetchAccount(accountId);
	}

	/**
	* Returns the account with the matching UUID and company.
	*
	* @param uuid the account's UUID
	* @param companyId the primary key of the company
	* @return the matching account, or <code>null</code> if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.accounts.model.Account fetchAccountByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.fetchAccountByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the account matching the UUID and group.
	*
	* @param uuid the account's UUID
	* @param groupId the primary key of the group
	* @return the matching account, or <code>null</code> if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.accounts.model.Account fetchAccountByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.fetchAccountByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the account with the primary key.
	*
	* @param accountId the primary key of the account
	* @return the account
	* @throws PortalException if a account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.accounts.model.Account getAccount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccount(accountId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the account with the matching UUID and company.
	*
	* @param uuid the account's UUID
	* @param companyId the primary key of the company
	* @return the matching account
	* @throws PortalException if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.accounts.model.Account getAccountByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccountByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the account matching the UUID and group.
	*
	* @param uuid the account's UUID
	* @param groupId the primary key of the group
	* @return the matching account
	* @throws PortalException if a matching account could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.accounts.model.Account getAccountByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccountByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.accounts.model.impl.AccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of accounts
	* @param end the upper bound of the range of accounts (not inclusive)
	* @return the range of accounts
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.docs.accounts.model.Account> getAccounts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccounts(start, end);
	}

	/**
	* Returns the number of accounts.
	*
	* @return the number of accounts
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getAccountsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccountsCount();
	}

	/**
	* Updates the account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param account the account
	* @return the account that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.accounts.model.Account updateAccount(
		com.liferay.docs.accounts.model.Account account)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.updateAccount(account);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _accountLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public java.util.List<com.liferay.docs.accounts.model.Account> getAccounts(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccounts(groupId);
	}

	@Override
	public java.util.List<com.liferay.docs.accounts.model.Account> getAccounts(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.getAccounts(groupId, start, end);
	}

	@Override
	public com.liferay.docs.accounts.model.Account addAccount(long userId,
		java.lang.String firstName, java.lang.String familyName,
		java.lang.String email, java.lang.String birthday,
		java.lang.String gender, java.lang.String accountPassword,
		java.lang.String maritalStatus, java.lang.String interests,
		java.lang.String educationLevel, java.lang.String foreignLanguages,
		java.lang.String profession, java.lang.String preferences,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountLocalService.addAccount(userId, firstName, familyName,
			email, birthday, gender, accountPassword, maritalStatus, interests,
			educationLevel, foreignLanguages, profession, preferences,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AccountLocalService getWrappedAccountLocalService() {
		return _accountLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAccountLocalService(
		AccountLocalService accountLocalService) {
		_accountLocalService = accountLocalService;
	}

	@Override
	public AccountLocalService getWrappedService() {
		return _accountLocalService;
	}

	@Override
	public void setWrappedService(AccountLocalService accountLocalService) {
		_accountLocalService = accountLocalService;
	}

	private AccountLocalService _accountLocalService;
}