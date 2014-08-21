import java.io.File;
import java.io.FileReader;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;

public class HealthProfileWriter {  	
	public static PeopleStore people = new PeopleStore();

	public static void initializeDB() {
		Person pallino = new Person();
		Person pallo = new Person(new Long(1), "Pallo", "Pinco", "1984-06-21");
		HealthProfile hp = new HealthProfile(68.0, 1.72);
		Person john = new Person(new Long(2), "John", "Doe", "1985-03-20", hp);

		people.getData().add(pallino);
		people.getData().add(pallo);
		people.getData().add(john);
	}	

	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        m.marshal(people,new File("people.xml")); // marshalling into a file
        m.marshal(people, System.out);			  // marshalling into the system default output
    }
}
