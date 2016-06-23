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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.docs.accounts.service.http.AccountServiceSoap}.
 *
 * @author a616659
 * @see com.liferay.docs.accounts.service.http.AccountServiceSoap
 * @generated
 */
public class AccountSoap implements Serializable {
	public static AccountSoap toSoapModel(Account model) {
		AccountSoap soapModel = new AccountSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAccountId(model.getAccountId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFirstName(model.getFirstName());
		soapModel.setFamilyName(model.getFamilyName());
		soapModel.setEmail(model.getEmail());
		soapModel.setBirthday(model.getBirthday());
		soapModel.setGender(model.getGender());
		soapModel.setAccountPassword(model.getAccountPassword());
		soapModel.setMaritalStatus(model.getMaritalStatus());
		soapModel.setInterests(model.getInterests());
		soapModel.setEducationLevel(model.getEducationLevel());
		soapModel.setForeignLanguages(model.getForeignLanguages());
		soapModel.setProfession(model.getProfession());
		soapModel.setPreferences(model.getPreferences());

		return soapModel;
	}

	public static AccountSoap[] toSoapModels(Account[] models) {
		AccountSoap[] soapModels = new AccountSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountSoap[][] toSoapModels(Account[][] models) {
		AccountSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountSoap[] toSoapModels(List<Account> models) {
		List<AccountSoap> soapModels = new ArrayList<AccountSoap>(models.size());

		for (Account model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountSoap[soapModels.size()]);
	}

	public AccountSoap() {
	}

	public long getPrimaryKey() {
		return _accountId;
	}

	public void setPrimaryKey(long pk) {
		setAccountId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getFamilyName() {
		return _familyName;
	}

	public void setFamilyName(String familyName) {
		_familyName = familyName;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getBirthday() {
		return _birthday;
	}

	public void setBirthday(String birthday) {
		_birthday = birthday;
	}

	public String getGender() {
		return _gender;
	}

	public void setGender(String gender) {
		_gender = gender;
	}

	public String getAccountPassword() {
		return _accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		_accountPassword = accountPassword;
	}

	public String getMaritalStatus() {
		return _maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		_maritalStatus = maritalStatus;
	}

	public String getInterests() {
		return _interests;
	}

	public void setInterests(String interests) {
		_interests = interests;
	}

	public String getEducationLevel() {
		return _educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		_educationLevel = educationLevel;
	}

	public String getForeignLanguages() {
		return _foreignLanguages;
	}

	public void setForeignLanguages(String foreignLanguages) {
		_foreignLanguages = foreignLanguages;
	}

	public String getProfession() {
		return _profession;
	}

	public void setProfession(String profession) {
		_profession = profession;
	}

	public String getPreferences() {
		return _preferences;
	}

	public void setPreferences(String preferences) {
		_preferences = preferences;
	}

	private String _uuid;
	private long _accountId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _firstName;
	private String _familyName;
	private String _email;
	private String _birthday;
	private String _gender;
	private String _accountPassword;
	private String _maritalStatus;
	private String _interests;
	private String _educationLevel;
	private String _foreignLanguages;
	private String _profession;
	private String _preferences;
}