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

package com.liferay.docs.accounts.model;

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Account}.
 * </p>
 *
 * @author a616659
 * @see Account
 * @generated
 */
public class AccountWrapper implements Account, ModelWrapper<Account> {
	public AccountWrapper(Account account) {
		_account = account;
	}

	@Override
	public Class<?> getModelClass() {
		return Account.class;
	}

	@Override
	public String getModelClassName() {
		return Account.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("accountId", getAccountId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("firstName", getFirstName());
		attributes.put("familyName", getFamilyName());
		attributes.put("email", getEmail());
		attributes.put("birthday", getBirthday());
		attributes.put("gender", getGender());
		attributes.put("accountPassword", getAccountPassword());
		attributes.put("maritalStatus", getMaritalStatus());
		attributes.put("interests", getInterests());
		attributes.put("educationLevel", getEducationLevel());
		attributes.put("foreignLanguages", getForeignLanguages());
		attributes.put("profession", getProfession());
		attributes.put("preferences", getPreferences());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String firstName = (String)attributes.get("firstName");

		if (firstName != null) {
			setFirstName(firstName);
		}

		String familyName = (String)attributes.get("familyName");

		if (familyName != null) {
			setFamilyName(familyName);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String birthday = (String)attributes.get("birthday");

		if (birthday != null) {
			setBirthday(birthday);
		}

		String gender = (String)attributes.get("gender");

		if (gender != null) {
			setGender(gender);
		}

		String accountPassword = (String)attributes.get("accountPassword");

		if (accountPassword != null) {
			setAccountPassword(accountPassword);
		}

		String maritalStatus = (String)attributes.get("maritalStatus");

		if (maritalStatus != null) {
			setMaritalStatus(maritalStatus);
		}

		String interests = (String)attributes.get("interests");

		if (interests != null) {
			setInterests(interests);
		}

		String educationLevel = (String)attributes.get("educationLevel");

		if (educationLevel != null) {
			setEducationLevel(educationLevel);
		}

		String foreignLanguages = (String)attributes.get("foreignLanguages");

		if (foreignLanguages != null) {
			setForeignLanguages(foreignLanguages);
		}

		String profession = (String)attributes.get("profession");

		if (profession != null) {
			setProfession(profession);
		}

		String preferences = (String)attributes.get("preferences");

		if (preferences != null) {
			setPreferences(preferences);
		}
	}

	/**
	* Returns the primary key of this account.
	*
	* @return the primary key of this account
	*/
	@Override
	public long getPrimaryKey() {
		return _account.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account.
	*
	* @param primaryKey the primary key of this account
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_account.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this account.
	*
	* @return the uuid of this account
	*/
	@Override
	public java.lang.String getUuid() {
		return _account.getUuid();
	}

	/**
	* Sets the uuid of this account.
	*
	* @param uuid the uuid of this account
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_account.setUuid(uuid);
	}

	/**
	* Returns the account ID of this account.
	*
	* @return the account ID of this account
	*/
	@Override
	public long getAccountId() {
		return _account.getAccountId();
	}

	/**
	* Sets the account ID of this account.
	*
	* @param accountId the account ID of this account
	*/
	@Override
	public void setAccountId(long accountId) {
		_account.setAccountId(accountId);
	}

	/**
	* Returns the group ID of this account.
	*
	* @return the group ID of this account
	*/
	@Override
	public long getGroupId() {
		return _account.getGroupId();
	}

	/**
	* Sets the group ID of this account.
	*
	* @param groupId the group ID of this account
	*/
	@Override
	public void setGroupId(long groupId) {
		_account.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this account.
	*
	* @return the company ID of this account
	*/
	@Override
	public long getCompanyId() {
		return _account.getCompanyId();
	}

	/**
	* Sets the company ID of this account.
	*
	* @param companyId the company ID of this account
	*/
	@Override
	public void setCompanyId(long companyId) {
		_account.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this account.
	*
	* @return the user ID of this account
	*/
	@Override
	public long getUserId() {
		return _account.getUserId();
	}

	/**
	* Sets the user ID of this account.
	*
	* @param userId the user ID of this account
	*/
	@Override
	public void setUserId(long userId) {
		_account.setUserId(userId);
	}

	/**
	* Returns the user uuid of this account.
	*
	* @return the user uuid of this account
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _account.getUserUuid();
	}

	/**
	* Sets the user uuid of this account.
	*
	* @param userUuid the user uuid of this account
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_account.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this account.
	*
	* @return the user name of this account
	*/
	@Override
	public java.lang.String getUserName() {
		return _account.getUserName();
	}

	/**
	* Sets the user name of this account.
	*
	* @param userName the user name of this account
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_account.setUserName(userName);
	}

	/**
	* Returns the create date of this account.
	*
	* @return the create date of this account
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _account.getCreateDate();
	}

	/**
	* Sets the create date of this account.
	*
	* @param createDate the create date of this account
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_account.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this account.
	*
	* @return the modified date of this account
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _account.getModifiedDate();
	}

	/**
	* Sets the modified date of this account.
	*
	* @param modifiedDate the modified date of this account
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_account.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the first name of this account.
	*
	* @return the first name of this account
	*/
	@Override
	public java.lang.String getFirstName() {
		return _account.getFirstName();
	}

	/**
	* Sets the first name of this account.
	*
	* @param firstName the first name of this account
	*/
	@Override
	public void setFirstName(java.lang.String firstName) {
		_account.setFirstName(firstName);
	}

	/**
	* Returns the family name of this account.
	*
	* @return the family name of this account
	*/
	@Override
	public java.lang.String getFamilyName() {
		return _account.getFamilyName();
	}

	/**
	* Sets the family name of this account.
	*
	* @param familyName the family name of this account
	*/
	@Override
	public void setFamilyName(java.lang.String familyName) {
		_account.setFamilyName(familyName);
	}

	/**
	* Returns the email of this account.
	*
	* @return the email of this account
	*/
	@Override
	public java.lang.String getEmail() {
		return _account.getEmail();
	}

	/**
	* Sets the email of this account.
	*
	* @param email the email of this account
	*/
	@Override
	public void setEmail(java.lang.String email) {
		_account.setEmail(email);
	}

	/**
	* Returns the birthday of this account.
	*
	* @return the birthday of this account
	*/
	@Override
	public java.lang.String getBirthday() {
		return _account.getBirthday();
	}

	/**
	* Sets the birthday of this account.
	*
	* @param birthday the birthday of this account
	*/
	@Override
	public void setBirthday(java.lang.String birthday) {
		_account.setBirthday(birthday);
	}

	/**
	* Returns the gender of this account.
	*
	* @return the gender of this account
	*/
	@Override
	public java.lang.String getGender() {
		return _account.getGender();
	}

	/**
	* Sets the gender of this account.
	*
	* @param gender the gender of this account
	*/
	@Override
	public void setGender(java.lang.String gender) {
		_account.setGender(gender);
	}

	/**
	* Returns the account password of this account.
	*
	* @return the account password of this account
	*/
	@Override
	public java.lang.String getAccountPassword() {
		return _account.getAccountPassword();
	}

	/**
	* Sets the account password of this account.
	*
	* @param accountPassword the account password of this account
	*/
	@Override
	public void setAccountPassword(java.lang.String accountPassword) {
		_account.setAccountPassword(accountPassword);
	}

	/**
	* Returns the marital status of this account.
	*
	* @return the marital status of this account
	*/
	@Override
	public java.lang.String getMaritalStatus() {
		return _account.getMaritalStatus();
	}

	/**
	* Sets the marital status of this account.
	*
	* @param maritalStatus the marital status of this account
	*/
	@Override
	public void setMaritalStatus(java.lang.String maritalStatus) {
		_account.setMaritalStatus(maritalStatus);
	}

	/**
	* Returns the interests of this account.
	*
	* @return the interests of this account
	*/
	@Override
	public java.lang.String getInterests() {
		return _account.getInterests();
	}

	/**
	* Sets the interests of this account.
	*
	* @param interests the interests of this account
	*/
	@Override
	public void setInterests(java.lang.String interests) {
		_account.setInterests(interests);
	}

	/**
	* Returns the education level of this account.
	*
	* @return the education level of this account
	*/
	@Override
	public java.lang.String getEducationLevel() {
		return _account.getEducationLevel();
	}

	/**
	* Sets the education level of this account.
	*
	* @param educationLevel the education level of this account
	*/
	@Override
	public void setEducationLevel(java.lang.String educationLevel) {
		_account.setEducationLevel(educationLevel);
	}

	/**
	* Returns the foreign languages of this account.
	*
	* @return the foreign languages of this account
	*/
	@Override
	public java.lang.String getForeignLanguages() {
		return _account.getForeignLanguages();
	}

	/**
	* Sets the foreign languages of this account.
	*
	* @param foreignLanguages the foreign languages of this account
	*/
	@Override
	public void setForeignLanguages(java.lang.String foreignLanguages) {
		_account.setForeignLanguages(foreignLanguages);
	}

	/**
	* Returns the profession of this account.
	*
	* @return the profession of this account
	*/
	@Override
	public java.lang.String getProfession() {
		return _account.getProfession();
	}

	/**
	* Sets the profession of this account.
	*
	* @param profession the profession of this account
	*/
	@Override
	public void setProfession(java.lang.String profession) {
		_account.setProfession(profession);
	}

	/**
	* Returns the preferences of this account.
	*
	* @return the preferences of this account
	*/
	@Override
	public java.lang.String getPreferences() {
		return _account.getPreferences();
	}

	/**
	* Sets the preferences of this account.
	*
	* @param preferences the preferences of this account
	*/
	@Override
	public void setPreferences(java.lang.String preferences) {
		_account.setPreferences(preferences);
	}

	@Override
	public boolean isNew() {
		return _account.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_account.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _account.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_account.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _account.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _account.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_account.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _account.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_account.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_account.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_account.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountWrapper((Account)_account.clone());
	}

	@Override
	public int compareTo(com.liferay.docs.accounts.model.Account account) {
		return _account.compareTo(account);
	}

	@Override
	public int hashCode() {
		return _account.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.docs.accounts.model.Account> toCacheModel() {
		return _account.toCacheModel();
	}

	@Override
	public com.liferay.docs.accounts.model.Account toEscapedModel() {
		return new AccountWrapper(_account.toEscapedModel());
	}

	@Override
	public com.liferay.docs.accounts.model.Account toUnescapedModel() {
		return new AccountWrapper(_account.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _account.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _account.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_account.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountWrapper)) {
			return false;
		}

		AccountWrapper accountWrapper = (AccountWrapper)obj;

		if (Validator.equals(_account, accountWrapper._account)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _account.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Account getWrappedAccount() {
		return _account;
	}

	@Override
	public Account getWrappedModel() {
		return _account;
	}

	@Override
	public void resetOriginalValues() {
		_account.resetOriginalValues();
	}

	private Account _account;
}