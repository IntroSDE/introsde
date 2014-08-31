package introsde.rest.ehealth.resources;

import introsde.rest.ehealth.dao.PersonDao;
import introsde.rest.ehealth.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;


//Will map the resource to the URL /person
@Path("/person")
public class PersonCollectionResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of people to the user in the browser
	@GET
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Person> getPersonsList() {
		System.out.println("--> PersonCollectionResource request...");
		System.out.println("--> URI = "+uriInfo);
		System.out.println("--> request = "+request);
		List<Person> people = new ArrayList<Person>();
		people.addAll(PersonDao.instance.getDataProvider().values());
		return people;
	}

	// retuns the number of people
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		System.out.println("--> PersonCollectionResource request...");
		System.out.println("--> URI = "+uriInfo);
		System.out.println("--> request = "+request);
		System.out.println("Getting count...");
		int count = PersonDao.instance.getDataProvider().size();
		return String.valueOf(count);
	}

	@POST  
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) // will be called when content-type header set to xml
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Person newPerson(Person person) throws IOException {
		System.out.println("--> PersonCollectionResource request...");
		System.out.println("--> URI = "+uriInfo.getPath());
		System.out.println("--> request = "+request.getMethod());
		System.out.println("Creating new person...");
		int count = PersonDao.instance.getDataProvider().size();
		Long newId = new Long(count+1);
		person.setPersonId(newId);
		PersonDao.instance.getDataProvider().put(newId, person);
		return person;
	}
	
	// Defines that the next path parameter after the base url is
	// treated as a parameter and passed to the PersonResources
	// Allows to type http://localhost:5900/base_url/1
	// 1 will be treated as parameter todo and passed to PersonResource
	@Path("{personId}")
	public PersonResource getPerson(@PathParam("personId") Long id) {
		System.out.println("--> PersonResource request...");
		System.out.println("--> URI = "+uriInfo);
		System.out.println("--> request = "+request);
		System.out.println("--> personId = "+id);
		return new PersonResource(uriInfo, request, id);
	}

}
