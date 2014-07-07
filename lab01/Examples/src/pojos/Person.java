// classes are grouped together in 'packages'
// Classes in the same pakage already see each other. 
// If a class is in another package, in other to see it, you need to import it
package pojos;

// This is a typical Java Class. 
public class Person {

	// As with any other object oriente language, classes have attributes (i.e. the properties of the class). 
	// Each attribute is in turn of another class
	private String firstname;	// this is an attribute of the class String, and it is 'private'
								// private attributes are only accesible inside the object
	private String lastname;	// this is an attribute of the class String
	private HealthProfile hProfile;	// this is an attribute of the class HealthProfile 
	
	// constructors in java are used to create an object of the class 
	// (a java program basically plays with objects of different classes)
	// this constructor creates a Person object with a particular firstname, lastname and health profile
	public Person(String fname, String lname, HealthProfile hp) {
		this.setFirstname(fname);
		this.setLastname(lname);
		this.hProfile=hp;
	}
	public Person(String fname, String lname) {
		this.setFirstname(fname);
		this.setLastname(lname);
		this.hProfile=new HealthProfile();
	}
	public Person() {
		this.firstname="Pinco";
		this.lastname="Pallino";
		this.hProfile=new HealthProfile();
	}

	// classes have methods, which are basically pieces of programs that can be executed on objects of the class
	// this dummy class, has only 'accesor' methods (i.e. methods to access its properties, which are all private)
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
}
