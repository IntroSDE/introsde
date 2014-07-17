/* 
 * Classes are grouped together in 'packages'
 * Classes in the same pakage already see each other. 
 * If a class is in another package, in other to see it, you need to import it
 */
package pojos;

import java.util.Calendar;

/* This is a typical Java Class. */
public class Person {
	/* 
	 * As with any other object oriented language, classes have attributes (i.e. the properties of the class). 
	 * Each attribute is in turn of another class (or of a java primitive type)
	 */
	private String firstname;		// this is an attribute of the class String, and it is 'private'
									// private attributes are only accesible inside the object
	
	private String lastname;		// this is an attribute of the class String
	
	private HealthProfile hProfile;	// this is an attribute of the class HealthProfile 
	
	/* Solution to Exercise #01-1a and #01-1b*/
	private String birthdate;
	private Long personId;
	
	/* 
	 * Constructors in java are used to create an object of the class 
	 * (a java program basically plays with objects of different classes)
	 * this constructor creates a Person object with a particular firstname, lastname and health profile
	 */
	public Person(Long personId, String fname, String lname, String birthdate, HealthProfile hp) {
		this.setPersonId(personId); 	// Solution to Exercise #01-1c
		this.setFirstname(fname);
		this.setLastname(lname);
		this.setBirthdate(birthdate); 	// Solution to Exercise #01-1c
		this.hProfile=hp;
	}
	
	public Person(Long personId, String fname, String lname, String birthdate) {
		this.setPersonId(personId); 	// Solution to Exercise #01-1c
		this.setFirstname(fname);
		this.setLastname(lname);
		this.setBirthdate(birthdate); 	// Solution to Exercise #01-1c
		this.hProfile=new HealthProfile();
	}
	
	public Person() {
		
		this.firstname="Pinco";
		this.lastname="Pallino";
		this.hProfile=new HealthProfile();

		/* 
		 * Solution to Exercise #01-1d
		 * setting personId to a random number between 1 and 9999
		 */
		this.personId = Math.round(Math.floor(Math.random()*9998)+1); // Solution to Exercise #01-1d
		this.birthdate = this.getRandomDate();
	}

	/*
	 *  Classes have methods, which are basically pieces of programs that can be executed on objects of the class
	 *  this dummy class, has only 'accesor' methods (i.e. methods to access its properties, which are all private)
	 */
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public HealthProfile gethProfile() {
		return hProfile;
	}
	public void sethProfile(HealthProfile hProfile) {
		this.hProfile = hProfile;
	}
	
	/* Solution to Exercise #01-1a and #01-1b*/
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/* 
	 * Solution to Exercise #01-1e
	 * creating a random date between now and 1950
	 */
	private String getRandomDate() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR); 		// 1. get the current year
		int year = (int) Math.round(Math.random()*(currentYear-1950)+1950); // 2. generate a random year 
																			//    between 1950 and currentYear
		int month = (int) Math.round(Math.floor(Math.random()*11)+1);		// 3. select a random month of the year
		// 4. select a random day in the selected month
		// 4.1 prepare a months array to store how many days in each month
		int[] months = new int[]{31,28,30,30,31,30,31,31,30,31,30,31};
		// 4.2 if it is a leap year, feb (months[1]) should be 29
		if ((currentYear % 4 == 0) && ((currentYear % 100 != 0) || (currentYear % 400 == 0))) {
			months[1] = 29;
		}
		long day = Math.round(Math.floor(Math.random()*(months[month-1]-1)+1));
		return ""+year+"-"+month+"-"+day;
	}
}
