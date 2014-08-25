package dozerproject.transfer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.dozer.Mapping;
import org.joda.time.DateTime;

import utils.DateTimeAdapter;

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonAnnotationBean {
    
	@Mapping("firstName")
    private String fName;
	@Mapping("lastName")
    private String lName;
	
	@Mapping("birthdate")
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

	public DateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(DateTime birthdate) {
		if (birthdate==null) {
			this.birthdate = new DateTime();
		}
		this.birthdate = birthdate;
	}
	
	public String toString() {
		return this.fName+", "+this.lName+", "+this.birthdate;
	}
}
