import java.util.HashMap;
import java.util.Map;

import pojos.HealthProfile;
import pojos.Person;


public class HealthProfileReader {
	
	public static Map<String,Person> database = new HashMap<String,Person>();
	
	static
    {
    	Person pallino = new Person();
		Person pallo = new Person("Pinco","Pallo");
		HealthProfile hp = new HealthProfile(68.0,1.72);
		Person john = new Person("John","Doe",hp);
		
		database.put(pallino.getFirstname()+" "+pallino.getLastname(), pallino);
		database.put(pallo.getFirstname()+" "+pallo.getLastname(), pallo);
		database.put(john.getFirstname()+" "+john.getLastname(), john);
    }
	/**
	 * The health profile reader gets information from the command line about
	 * weight and height and calculates the BMI of the person based on this 
	 * parameters
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//initializeDatabase();
		int argCount = args.length;
		if (argCount == 0) {
			System.out.println("I cannot create people out of thing air. Give me at least a name and lastname.");
		} else if (argCount < 2) {
			System.out.println("Are you sure you gave me a first and lastname?");
		} else if (argCount == 2) {
			String fname = args[0];
			String lname = args[1];
			// read the person from the DB
			Person p= database.get(fname+" "+lname);
			if (p!=null) { 
				System.out.println(fname+" "+lname+"'s health profile is: "+p.gethProfile().toString());
			} else {
				System.out.println(fname+" "+lname+" is not in the database");
			}
		}
		// add the case where there are 3 parameters, the third being a string that matches "weight", "height" or "bmi"
	}
	
	//public static void initializeDatabase() {
	//	Person pallino = new Person();
	//	Person pallo = new Person("Pinco","Pallo");
	//	HealthProfile hp = new HealthProfile(68.0,1.72);
	//	Person john = new Person("John","Doe",hp);
	//	
	//	database.put(pallino.getFirstname()+" "+pallino.getLastname(), pallino);
	//	database.put(pallo.getFirstname()+" "+pallo.getLastname(), pallo);
	//	database.put(john.getFirstname()+" "+john.getLastname(), john);
	//}
}