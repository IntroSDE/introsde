package com.liferay.docs.account.model;

public class Account {

	private String name;
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
    public Account() {

        this.name = null;
        this.password = null;

    }

    public Account (String name, String password) {

        setName(name);
        setPassword(password);

    }
	
	
}

