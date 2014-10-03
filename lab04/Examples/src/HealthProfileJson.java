import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;

public class HealthProfileJson {  	
	public static PeopleStore people = new PeopleStore();

	public static void initializeDB() {
		Person pallino = new Person();
		Person pallo = new Person(new Long(1), "Fulano", "De Tal", "1984-06-21");
		HealthProfile hp = new HealthProfile(68.0, 1.72);
		Person john = new Person(new Long(2), "Jane", "Doe", "1985-03-20", hp);

		people.getData().add(pallino);
		people.getData().add(pallo);
		people.getData().add(john);
	}	

	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        String result = mapper.writeValueAsString(people);
        System.out.println(result);
        mapper.writeValue(new File("people.json"), people);
    }
}
