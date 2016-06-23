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

package com.liferay.docs.accounts.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.docs.accounts.AccountNameException;
import com.liferay.docs.accounts.model.Account;
import com.liferay.docs.accounts.service.base.AccountLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the account local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.docs.accounts.service.AccountLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author a616659
 * @see com.liferay.docs.accounts.service.base.AccountLocalServiceBaseImpl
 * @see com.liferay.docs.accounts.service.AccountLocalServiceUtil
 */
public class AccountLocalServiceImpl extends AccountLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.docs.accounts.service.AccountLocalServiceUtil} to access the account local service.
	 */


	public List<Account> getAccounts (long groupId) throws SystemException {
		return accountPersistence.findByGroupId(groupId);
	}



	public List<Account> getAccounts (long groupId, int start, int end)
			throws SystemException {
		return accountPersistence.findByGroupId(groupId, start, end);
	}


	protected void validate (String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new AccountNameException();
		}
	}


	public Account addAccount(long userId, 
			String firstName, 
			String familyName, 
			String email, 
			String birthday, 
			String gender, 
			String accountPassword, 
			String maritalStatus, 
			String interests, 
			String educationLevel, 
			String foreignLanguages, 
			String profession, 
			String preferences,
			ServiceContext serviceContext) throws SystemException, PortalException {

		long groupId = serviceContext.getScopeGroupId();

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		validate(firstName);
		validate(familyName); 
		validate(email); 
		validate(birthday); 
		validate(gender); 
		validate(accountPassword); 
		validate(maritalStatus); 
		validate(interests); 
		validate(educationLevel); 
		validate(foreignLanguages); 
		validate(profession); 
		validate(preferences);


		long accountId = counterLocalService.increment();

		Account account = accountPersistence.create(accountId);

		account.setUuid(serviceContext.getUuid());
		account.setUserId(userId);
		account.setGroupId(groupId);
		account.setCompanyId(user.getCompanyId());
		account.setUserName(user.getFullName());
		account.setCreateDate(serviceContext.getCreateDate(now));
		account.setModifiedDate(serviceContext.getModifiedDate(now));

		account.setFirstName(firstName);
		account.setFamilyName(familyName);
		account.setEmail(email);
		account.setBirthday(birthday);
		account.setGender(gender);
		account.setAccountPassword(accountPassword);
		account.setMaritalStatus(maritalStatus);
		account.setInterests(interests);
		account.setEducationLevel(educationLevel);
		account.setForeignLanguages(foreignLanguages);
		account.setProfession(profession);
		account.setPreferences(preferences);

		account.setExpandoBridgeAttributes(serviceContext);

		accountPersistence.update(account);

		return account;

	}

}