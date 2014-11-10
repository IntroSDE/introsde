package introsde.document.endpoint;

import javax.xml.ws.Endpoint;
import introsde.document.ws.HelloWorldImpl;

//Endpoint publisher
public class HelloWorldPublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:6901/ws/hello", new HelloWorldImpl());
	}
}