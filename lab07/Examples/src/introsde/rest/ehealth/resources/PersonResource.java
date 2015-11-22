package introsde.rest.ehealth.resources;

import introsde.rest.ehealth.model.Person;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Stateless
@LocalBean
public class PersonResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public PersonResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public PersonResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Person getPerson() {
		Person person = this.getPersonById(id);
		if (person == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return person;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Person getPersonHTML() {
		Person person = this.getPersonById(id);
		if (person == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		System.out.println("Returning person... " + person.getIdPerson());
		return person;
	}

	@PUT
    	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putPerson(Person person) {
        Response res;
        Person existing = getPersonById(this.id);

        // if the person is not in the db it will create a new one with the
        // given ID 
        if (existing == null) {
            System.out.println("--> The given ID is not in our DB " + id);
            System.out.println("--> A new person will be created with this ID: " + id);
            // we set the ID that the client provided from the URI
            person.setIdPerson(id);
            //Maybe also Person.savePerson(person) could be ok?
            Person.updatePerson(person);
            // actually i've putted the response to "created" because a new entity is just created
            res = Response.created(uriInfo.getAbsolutePath()).build();
            // in my opinion this was not very correct: res = Response.noContent().build();
        } else {
            System.out.println("--> Updating Person... " +this.id);
            System.out.println("--> "+person.toString());
            person.setIdPerson(this.id);
            Person.updatePerson(person);
            res = Response.ok().build();
            // we create an "ok" response because the request have been well accomplished
            // OR maybe could be also good 
            //res = Response.noContent().build();
            // but NOT "created" because nothing have been created
            //res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        return res;
    }
	


	@DELETE
	public void deletePerson() {
		Person c = getPersonById(id);
		if (c == null)
			throw new RuntimeException("Delete: Person with " + id
					+ " not found");

		Person.removePerson(c);
	}

	
	public Person getPersonById(int personId) {
		System.out.println("Reading person from DB with id: "+personId);
		//Person person = entityManager.find(Person.class, personId);
		
		Person person = Person.getPersonById(personId);
		//I've added this if in order to not break things if the given ID is not in the DB
		if (person!=null){
	   		System.out.println("Person: "+person.toString());
		}
		return person;
	}
}
