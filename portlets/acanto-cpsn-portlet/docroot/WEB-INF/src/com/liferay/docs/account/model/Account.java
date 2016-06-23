package com.liferay.docs.account.model;

public class Account {

	private String firstName;
	private String familyName;
	private String email;
	private String birthday;
	private String gender;
	private String accountPassword;
	private String maritalStatus;
	private String interests;
	private String educationLevel;
	private String foreignLanguages;
	private String profession;
	private String preferences;

	
	

    public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getFamilyName() {
		return familyName;
	}




	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getBirthday() {
		return birthday;
	}




	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public String getAccountPassword() {
		return accountPassword;
	}




	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}




	public String getMaritalStatus() {
		return maritalStatus;
	}




	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}




	public String getInterests() {
		return interests;
	}




	public void setInterests(String interests) {
		this.interests = interests;
	}




	public String getEducationLevel() {
		return educationLevel;
	}




	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}




	public String getForeignLanguages() {
		return foreignLanguages;
	}




	public void setForeignLanguages(String foreignLanguages) {
		this.foreignLanguages = foreignLanguages;
	}




	public String getProfession() {
		return profession;
	}




	public void setProfession(String profession) {
		this.profession = profession;
	}




	public String getPreferences() {
		return preferences;
	}




	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}




	public Account (
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
			String preferences
			) {

        this.setFirstName(firstName);
        this.setFamilyName(firstName);
        this.setEmail(firstName);
        this.setBirthday(firstName);
        this.setGender(firstName);
        this.setAccountPassword(firstName);
        this.setMaritalStatus(firstName);
        this.setInterests(firstName);
        this.setEducationLevel(firstName);
        this.setForeignLanguages(firstName);
        this.setProfession(firstName);
        this.setPreferences(firstName);

    }
	
	
}

