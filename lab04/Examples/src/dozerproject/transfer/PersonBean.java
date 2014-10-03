package dozerproject.transfer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import utils.DateTimeAdapter;

import org.joda.time.DateTime;

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonBean {
    
	private String fName;
	private String lName;
	private String address;
	
	@XmlJavaTypeAdapter(type=DateTime.class, value=DateTimeAdapter.class)
	private DateTime birthdate; 

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public DateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(DateTime birthdate) {
		this.birthdate = birthdate;
	}    
	
	public String toString() {
		return this.fName+", "+this.lName+", "+this.address+", "+this.birthdate;
	}
}
