package introsde.endpoint;

import javax.xml.ws.Endpoint;
import introsde.ws.HelloWorldImpl;

//Endpoint publisher
public class HelloWorldPublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:6900/ws/hello", new HelloWorldImpl());
	}
}