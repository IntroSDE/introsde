package introsde.rest.jersey.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/salutation")
public class HelloWorld {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello World in REST";
	}

	// When client wants XML
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayHelloXML() {
		return "<?xml version=\"1.0\"?>" + "<msg>" + "Hello World in REST"
				+ "</msg>";
	}

	// When client wants HTML
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloHtml() {
		return "<html> " + "<title>" + "Hello Jersey" + "</title>"
				+ "<body><h1>" + "Hello World in REST" + "</body></h1>"
				+ "</html> ";
	}

	// When client wants XML
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHelloJson() {
		return "{ 'salutation': 'Hello World in REST' }";
	}
	
 	// When client wants HTML
  	@GET
  	@Path("/{name}") // you can pass path params to a service
  	@Produces(MediaType.TEXT_HTML)
  	public String sayHelloHtmlToPerson(@PathParam("name") String name,
  			@QueryParam("age") int age) {
  		
  		String printAlsoAge = "";
  		
  		// QueryParams can be used as optional parameters that you use if they are present
  		// 
  		if (age > 0) {
  			printAlsoAge = " You are "+age+ "years old, but don't worry. It's never late to learn!";
  		}
  		
  		return "<html> " + "<title>" + "Hello Jersey" + "</title>"
  				+ "<body><h1>" + "Hello "+name+". Welcome to Jersey REST." +printAlsoAge+ "</body></h1>"
  				+ "</html> ";
  	} 
  	

	
}
