import java.util.HashMap;
import java.util.Map;

import pojos.HealthProfile;
import pojos.Person;

public class HealthProfileReader {

	/* Solution to Exercise #02-2b */
	public static Map<Long, Person> database = new HashMap<Long, Person>(); //

	static {
		Person pallino = new Person();
		Person pallo = new Person(new Long(1), "Pinco", "Pallo", "1984-06-21");
		HealthProfile hp = new HealthProfile(68.0, 1.72);
		Person john = new Person(new Long(2), "John", "Doe", "1985-03-20", hp);

		database.put(pallino.getPersonId(), pallino);
		database.put(pallo.getPersonId(), pallo);
		database.put(john.getPersonId(), john);
	}

	/* Solution to Exercise #01-2b 
	 * Check the browser with: 
	 * http://localhost:8080/axis2/services/HealthProfileReader/createPerson?personId=15&firstname=Alicia&lastname=Recalde&birthdate=1985-03-20
	 * 
	 */
	public Person createPerson(Long personId, String firstname,
			String lastname, String birthdate) {
		Person p = new Person(personId, firstname, lastname, birthdate);
		database.put(p.getPersonId(), p);
		System.out.println("A new person record (" + p.getPersonId()
				+ ") has been created for " + p.getLastname() + ", "
				+ p.getFirstname() + " born on " + p.getBirthdate());
		return p;
	}

	/* Solution to Exercise #01-2c 
	 * Check in browser with: 
	 * http://localhost:8080/axis2/services/HealthProfileReader/displayHealthProfile?personId=1
	 */
	public HealthProfile displayHealthProfile(Long personId) {
		Person p = database.get(personId);
		HealthProfile hp = p.gethProfile();
		System.out.println(p.getFirstname() + " has a weight of "
				+ hp.getWeight() + " Kg. and a height of " + hp.getHeight());
		return hp;
	}

	/* Solution to Exercise #01-2d 	 
	 * Check in browser with: 
	 * http://localhost:8080/axis2/services/HealthProfileReader/updateHealthProfile?personId=1&height=1.78&weight=74
	 */
	public HealthProfile updateHealthProfile(Long personId, Double height,
			Double weight) {
		Person p = database.get(personId);
		HealthProfile hp = p.gethProfile();
		hp.setHeight(height);
		hp.setWeight(weight);
		System.out.println(p.getFirstname() + " has updated weight to "
				+ hp.getWeight() + " Kg. and updated height to "
				+ hp.getHeight());
		return hp;
	}

}