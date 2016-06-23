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

import com.liferay.docs.accounts.service.AccountLocalServiceUtil;
import com.liferay.docs.accounts.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author a616659
 */
public class AccountClp extends BaseModelImpl<Account> implements Account {
	public AccountClp() {
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
	public long getPrimaryKey() {
		return _accountId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_accountRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAccountId() {
		return _accountId;
	}

	@Override
	public void setAccountId(long accountId) {
		_accountId = accountId;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountId", long.class);

				method.invoke(_accountRemoteModel, accountId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_accountRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_accountRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_accountRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_accountRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_accountRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_accountRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getFirstName() {
		return _firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		_firstName = firstName;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setFirstName", String.class);

				method.invoke(_accountRemoteModel, firstName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getFamilyName() {
		return _familyName;
	}

	@Override
	public void setFamilyName(String familyName) {
		_familyName = familyName;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setFamilyName", String.class);

				method.invoke(_accountRemoteModel, familyName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEmail() {
		return _email;
	}

	@Override
	public void setEmail(String email) {
		_email = email;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setEmail", String.class);

				method.invoke(_accountRemoteModel, email);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getBirthday() {
		return _birthday;
	}

	@Override
	public void setBirthday(String birthday) {
		_birthday = birthday;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setBirthday", String.class);

				method.invoke(_accountRemoteModel, birthday);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getGender() {
		return _gender;
	}

	@Override
	public void setGender(String gender) {
		_gender = gender;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setGender", String.class);

				method.invoke(_accountRemoteModel, gender);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAccountPassword() {
		return _accountPassword;
	}

	@Override
	public void setAccountPassword(String accountPassword) {
		_accountPassword = accountPassword;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountPassword",
						String.class);

				method.invoke(_accountRemoteModel, accountPassword);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getMaritalStatus() {
		return _maritalStatus;
	}

	@Override
	public void setMaritalStatus(String maritalStatus) {
		_maritalStatus = maritalStatus;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setMaritalStatus", String.class);

				method.invoke(_accountRemoteModel, maritalStatus);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getInterests() {
		return _interests;
	}

	@Override
	public void setInterests(String interests) {
		_interests = interests;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setInterests", String.class);

				method.invoke(_accountRemoteModel, interests);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEducationLevel() {
		return _educationLevel;
	}

	@Override
	public void setEducationLevel(String educationLevel) {
		_educationLevel = educationLevel;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setEducationLevel",
						String.class);

				method.invoke(_accountRemoteModel, educationLevel);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getForeignLanguages() {
		return _foreignLanguages;
	}

	@Override
	public void setForeignLanguages(String foreignLanguages) {
		_foreignLanguages = foreignLanguages;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setForeignLanguages",
						String.class);

				method.invoke(_accountRemoteModel, foreignLanguages);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getProfession() {
		return _profession;
	}

	@Override
	public void setProfession(String profession) {
		_profession = profession;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setProfession", String.class);

				method.invoke(_accountRemoteModel, profession);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPreferences() {
		return _preferences;
	}

	@Override
	public void setPreferences(String preferences) {
		_preferences = preferences;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setPreferences", String.class);

				method.invoke(_accountRemoteModel, preferences);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Account.class.getName()));
	}

	public BaseModel<?> getAccountRemoteModel() {
		return _accountRemoteModel;
	}

	public void setAccountRemoteModel(BaseModel<?> accountRemoteModel) {
		_accountRemoteModel = accountRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _accountRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_accountRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			AccountLocalServiceUtil.addAccount(this);
		}
		else {
			AccountLocalServiceUtil.updateAccount(this);
		}
	}

	@Override
	public Account toEscapedModel() {
		return (Account)ProxyUtil.newProxyInstance(Account.class.getClassLoader(),
			new Class[] { Account.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AccountClp clone = new AccountClp();

		clone.setUuid(getUuid());
		clone.setAccountId(getAccountId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setFirstName(getFirstName());
		clone.setFamilyName(getFamilyName());
		clone.setEmail(getEmail());
		clone.setBirthday(getBirthday());
		clone.setGender(getGender());
		clone.setAccountPassword(getAccountPassword());
		clone.setMaritalStatus(getMaritalStatus());
		clone.setInterests(getInterests());
		clone.setEducationLevel(getEducationLevel());
		clone.setForeignLanguages(getForeignLanguages());
		clone.setProfession(getProfession());
		clone.setPreferences(getPreferences());

		return clone;
	}

	@Override
	public int compareTo(Account account) {
		long primaryKey = account.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountClp)) {
			return false;
		}

		AccountClp account = (AccountClp)obj;

		long primaryKey = account.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", accountId=");
		sb.append(getAccountId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", firstName=");
		sb.append(getFirstName());
		sb.append(", familyName=");
		sb.append(getFamilyName());
		sb.append(", email=");
		sb.append(getEmail());
		sb.append(", birthday=");
		sb.append(getBirthday());
		sb.append(", gender=");
		sb.append(getGender());
		sb.append(", accountPassword=");
		sb.append(getAccountPassword());
		sb.append(", maritalStatus=");
		sb.append(getMaritalStatus());
		sb.append(", interests=");
		sb.append(getInterests());
		sb.append(", educationLevel=");
		sb.append(getEducationLevel());
		sb.append(", foreignLanguages=");
		sb.append(getForeignLanguages());
		sb.append(", profession=");
		sb.append(getProfession());
		sb.append(", preferences=");
		sb.append(getPreferences());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(64);

		sb.append("<model><model-name>");
		sb.append("com.liferay.docs.accounts.model.Account");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountId</column-name><column-value><![CDATA[");
		sb.append(getAccountId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>firstName</column-name><column-value><![CDATA[");
		sb.append(getFirstName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>familyName</column-name><column-value><![CDATA[");
		sb.append(getFamilyName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>email</column-name><column-value><![CDATA[");
		sb.append(getEmail());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>birthday</column-name><column-value><![CDATA[");
		sb.append(getBirthday());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>gender</column-name><column-value><![CDATA[");
		sb.append(getGender());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountPassword</column-name><column-value><![CDATA[");
		sb.append(getAccountPassword());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maritalStatus</column-name><column-value><![CDATA[");
		sb.append(getMaritalStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>interests</column-name><column-value><![CDATA[");
		sb.append(getInterests());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>educationLevel</column-name><column-value><![CDATA[");
		sb.append(getEducationLevel());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>foreignLanguages</column-name><column-value><![CDATA[");
		sb.append(getForeignLanguages());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>profession</column-name><column-value><![CDATA[");
		sb.append(getProfession());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>preferences</column-name><column-value><![CDATA[");
		sb.append(getPreferences());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _accountId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
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
	private BaseModel<?> _accountRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.docs.accounts.service.ClpSerializer.class;
}