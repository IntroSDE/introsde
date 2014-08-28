package introsde.rest.hello;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;





/**
 * http://stackoverflow.com/questions/9579970/can-not-use-the-com-sun-net-httpserver-httpserver
 * Problem:
 * "Access restriction: The type HttpServer is not accessible due to restriction 
 * on required library C:\jdk1.7.0\jre\lib\rt.jar"
 * 
 * Answer: 
 * "You shouldn't use Sun's internal packages since they can be not present in other VM's. 
 * If you're sure you won't use other VM then Sun's/Oracle's, you can disable this warning in 
 * Project properties 
 * -> Java Compiler 
 * -> Errors/Warnings 
 * -> Enable Project Specific settings
 * -> Deprecated and restricted API
 * -> Forbidden reference (access rules): -> change to warning"
 * 
 * Other options to solve the problem: 
 * - download httpserver from the url below and manually add it to the classpath 
 * http://download.java.net/maven/2/com/sun/net/httpserver/http/20070405/http-20070405.jar
 * 
 * - use a non-sun httpserver like jetty: 
 * http://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty#Creating_a_Server
 */
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
// the whole resource will be available at baseUrl/hello
@Path("/hello")
public class HelloWorldStandalone
{

    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
    {
    	String protocol = "http://";
        String port = ":5900/";
        String hostname = InetAddress.getLocalHost().getHostAddress();
        if (hostname.equals("127.0.0.1"))
        {
            hostname = "localhost";
        }

        URI baseUrl = new URI(protocol + hostname + port);

        ResourceConfig rc = new ResourceConfig(HelloWorld.class);
        final HttpServer server = JdkHttpServerFactory.createHttpServer(baseUrl, rc);
        server.start();
        System.out.println("server starts on " + baseUrl + "\n [kill the process to exit]");
    }


    // This method is called if TEXT_PLAIN is request
 	@GET
 	@Path("/plain")	// we can also configure the path relative to the baseurl
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
  	
  	// When client wants XML
 	@GET
 	@Produces(MediaType.APPLICATION_JSON)
 	public String sayHelloJson() {
 		return "{ 'msg': 'Hello World in REST' }";
 	}
}
