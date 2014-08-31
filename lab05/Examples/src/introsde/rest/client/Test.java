package introsde.rest.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;

public class Test {
	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		// // GET BASEURL/rest/helloworld
		// // Accept: text/plain
		System.out.println(service.path("salutation").request().accept(MediaType.TEXT_PLAIN).get().readEntity(String.class));
		// // Get plain text
		System.out.println(service.path("salutation")
				.request().accept(MediaType.TEXT_PLAIN).get().readEntity(String.class));
		// Get XML
		System.out.println(service.path("salutation")
				.request()
				.accept(MediaType.TEXT_XML).get().readEntity(String.class));
		// // The HTML
		System.out.println(service.path("salutation").request()
				.accept(MediaType.TEXT_HTML).get().readEntity(String.class));

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/sdelab05/rest").build();
	}

}