package introsde.rest.ehealth;
import introsde.rest.ehealth.model.HealthProfile;
import introsde.rest.ehealth.model.Person;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Path("/ehealth/person")
public class PersonHealthProfile {
	
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

	// When client wants XML
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String readHealthProfiles() {
		JSONObject result = new JSONObject();
		JSONArray jsonPeople = new JSONArray();
		for (Person person : database.values()) {
			
			JSONObject pObj=new JSONObject();
			pObj.put("firstname", person.getFirstname());
			pObj.put("lastname", person.getLastname());
			
			HealthProfile h = person.gethProfile();
			JSONObject hObj=new JSONObject();
			hObj.put("weight", h.getWeight());
			hObj.put("height", h.getHeight());
			
			pObj.put("profile", hObj);
			
			jsonPeople.add(pObj);
		}
		result.put("people",jsonPeople);
		System.out.println(result.toJSONString());
		
		return result.toJSONString();
	}
}