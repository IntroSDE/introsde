package dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import model.Person;

@XmlRootElement(name="people")
@XmlAccessorType(XmlAccessType.FIELD)
public class PeopleStore {
	@XmlElementWrapper(name="peopleList")
	@XmlElement(name="person")
	private List<Person> data = new ArrayList<Person>();
	
	public PeopleStore () {
	}

	public List<Person> getData() {
		return data;
	}

	public void setData(List<Person> data) {
		this.data = data;
	}
}
