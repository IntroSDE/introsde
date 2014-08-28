package introsde.rest.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloworld")
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
		return "{ 'msg': 'Hello World in REST' }";
	}
}
