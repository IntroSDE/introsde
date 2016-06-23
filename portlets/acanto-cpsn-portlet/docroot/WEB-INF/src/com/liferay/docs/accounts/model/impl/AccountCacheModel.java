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

package com.liferay.docs.accounts.model.impl;

import com.liferay.docs.accounts.model.Account;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Account in entity cache.
 *
 * @author a616659
 * @see Account
 * @generated
 */
public class AccountCacheModel implements CacheModel<Account>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", familyName=");
		sb.append(familyName);
		sb.append(", email=");
		sb.append(email);
		sb.append(", birthday=");
		sb.append(birthday);
		sb.append(", gender=");
		sb.append(gender);
		sb.append(", accountPassword=");
		sb.append(accountPassword);
		sb.append(", maritalStatus=");
		sb.append(maritalStatus);
		sb.append(", interests=");
		sb.append(interests);
		sb.append(", educationLevel=");
		sb.append(educationLevel);
		sb.append(", foreignLanguages=");
		sb.append(foreignLanguages);
		sb.append(", profession=");
		sb.append(profession);
		sb.append(", preferences=");
		sb.append(preferences);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Account toEntityModel() {
		AccountImpl accountImpl = new AccountImpl();

		if (uuid == null) {
			accountImpl.setUuid(StringPool.BLANK);
		}
		else {
			accountImpl.setUuid(uuid);
		}

		accountImpl.setAccountId(accountId);
		accountImpl.setGroupId(groupId);
		accountImpl.setCompanyId(companyId);
		accountImpl.setUserId(userId);

		if (userName == null) {
			accountImpl.setUserName(StringPool.BLANK);
		}
		else {
			accountImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			accountImpl.setCreateDate(null);
		}
		else {
			accountImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountImpl.setModifiedDate(null);
		}
		else {
			accountImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (firstName == null) {
			accountImpl.setFirstName(StringPool.BLANK);
		}
		else {
			accountImpl.setFirstName(firstName);
		}

		if (familyName == null) {
			accountImpl.setFamilyName(StringPool.BLANK);
		}
		else {
			accountImpl.setFamilyName(familyName);
		}

		if (email == null) {
			accountImpl.setEmail(StringPool.BLANK);
		}
		else {
			accountImpl.setEmail(email);
		}

		if (birthday == null) {
			accountImpl.setBirthday(StringPool.BLANK);
		}
		else {
			accountImpl.setBirthday(birthday);
		}

		if (gender == null) {
			accountImpl.setGender(StringPool.BLANK);
		}
		else {
			accountImpl.setGender(gender);
		}

		if (accountPassword == null) {
			accountImpl.setAccountPassword(StringPool.BLANK);
		}
		else {
			accountImpl.setAccountPassword(accountPassword);
		}

		if (maritalStatus == null) {
			accountImpl.setMaritalStatus(StringPool.BLANK);
		}
		else {
			accountImpl.setMaritalStatus(maritalStatus);
		}

		if (interests == null) {
			accountImpl.setInterests(StringPool.BLANK);
		}
		else {
			accountImpl.setInterests(interests);
		}

		if (educationLevel == null) {
			accountImpl.setEducationLevel(StringPool.BLANK);
		}
		else {
			accountImpl.setEducationLevel(educationLevel);
		}

		if (foreignLanguages == null) {
			accountImpl.setForeignLanguages(StringPool.BLANK);
		}
		else {
			accountImpl.setForeignLanguages(foreignLanguages);
		}

		if (profession == null) {
			accountImpl.setProfession(StringPool.BLANK);
		}
		else {
			accountImpl.setProfession(profession);
		}

		if (preferences == null) {
			accountImpl.setPreferences(StringPool.BLANK);
		}
		else {
			accountImpl.setPreferences(preferences);
		}

		accountImpl.resetOriginalValues();

		return accountImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		accountId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		firstName = objectInput.readUTF();
		familyName = objectInput.readUTF();
		email = objectInput.readUTF();
		birthday = objectInput.readUTF();
		gender = objectInput.readUTF();
		accountPassword = objectInput.readUTF();
		maritalStatus = objectInput.readUTF();
		interests = objectInput.readUTF();
		educationLevel = objectInput.readUTF();
		foreignLanguages = objectInput.readUTF();
		profession = objectInput.readUTF();
		preferences = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(accountId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (firstName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (familyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(familyName);
		}

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (birthday == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(birthday);
		}

		if (gender == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(gender);
		}

		if (accountPassword == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountPassword);
		}

		if (maritalStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(maritalStatus);
		}

		if (interests == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(interests);
		}

		if (educationLevel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(educationLevel);
		}

		if (foreignLanguages == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(foreignLanguages);
		}

		if (profession == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(profession);
		}

		if (preferences == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(preferences);
		}
	}

	public String uuid;
	public long accountId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String firstName;
	public String familyName;
	public String email;
	public String birthday;
	public String gender;
	public String accountPassword;
	public String maritalStatus;
	public String interests;
	public String educationLevel;
	public String foreignLanguages;
	public String profession;
	public String preferences;
}