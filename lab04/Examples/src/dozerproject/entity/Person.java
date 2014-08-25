package dozerproject.entity;

import org.joda.time.DateTime;

public class Person {
    
    private String firstName;
    private String lastName;
    private String address;
    private String dbID;
    private DateTime birthdate;
  
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDbID() {
        return dbID;
    }

    public void setDbID(String dbID) {
        this.dbID = dbID;
    }

	public DateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(DateTime birthdate) {
		this.birthdate = birthdate;
	}
	
	public String toString() {
		return this.dbID+", "+this.firstName+", "+this.lastName+", "+this.address+", "+this.birthdate;
	}
}
