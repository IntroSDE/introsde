package introsde.rest.ehealth.dao;

import introsde.rest.ehealth.model.HealthProfile;
import introsde.rest.ehealth.model.Person;

import java.util.HashMap;
import java.util.Map;

public enum PersonDao {
	instance;

	private Map<Long, Person> contentProvider = new HashMap<Long, Person>();

	private PersonDao() {

		Person pallino = new Person();
		pallino.setPersonId(new Long(1));
		Person pallo = new Person(new Long(2), "Pinco","Pallo", "1987-8-23");
		HealthProfile hp = new HealthProfile(68.0,1.72);
		Person john = new Person(new Long(3), "John","Doe","1978-04-27", hp);
		
		contentProvider.put(pallino.getPersonId(), pallino);
		contentProvider.put(pallo.getPersonId(), pallo);
		contentProvider.put(john.getPersonId(), john);
	}

	public Map<Long, Person> getDataProvider() {
		return contentProvider;
	}
}