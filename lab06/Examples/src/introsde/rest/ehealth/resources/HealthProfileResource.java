package introsde.rest.ehealth.resources;
import introsde.rest.ehealth.dao.PersonDao;
import introsde.rest.ehealth.model.HealthProfile;
import introsde.rest.ehealth.model.Person;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
//import org.json.simple.JSONException;
import org.json.simple.JSONObject;

@Path("/ehealthv1/person")
public class HealthProfileResource {
	
	public static Map<Long,Person> database = new HashMap<Long,Person>();
	
	static
    {
    	Person pallino = new Person();
		Person pallo = new Person(new Long(2), "Pinco","Pallo", "1987-07-21");
		HealthProfile hp = new HealthProfile(68.0,1.72);
		Person john = new Person(new Long(3), "John","Doe", "1978-02-15", hp);
		
		database.put(pallino.getPersonId(), pallino);
		database.put(pallino.getPersonId(), pallo);
		database.put(pallino.getPersonId(), john);
    }

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String readHealthProfiles() {
		JSONObject result = new JSONObject();
		JSONArray jsonPeople = new JSONArray();
		for (Person person : database.values()) {
			
			JSONObject pObj=new JSONObject();
			pObj.put("firstname", person.getFirstname());
			pObj.put("lastname", person.getLastname());
			
			HealthProfile h = person.getHProfile();
			JSONObject hObj=new JSONObject();
			hObj.put("weight", h.getWeight());
			hObj.put("height", h.getHeight());
			
			pObj.put("profile", hObj);
			
			jsonPeople.add(pObj);
		}
		result.put("people",jsonPeople);
		System.out.println(result.toString());
		
		return result.toString();
	}
	
	
	
	
	
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newPerson(@FormParam("firstname") String fname,
			@FormParam("lastname") String lname,
			@FormParam("birthdate") String bdate,
			@Context HttpServletResponse servletResponse) throws IOException {
		Person person= new Person(Math.round(Math.floor(Math.random()*9998)+1), fname, lname, bdate);
		PersonDao.instance.getDataProvider().put(person.getPersonId(), person);

		servletResponse.sendRedirect("../create_todo.html");
	}
	
}