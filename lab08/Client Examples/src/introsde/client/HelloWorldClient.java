package introsde.client;

import introsde.ws.HelloWorld;
import introsde.ws.HelloWorldImplService;

public class HelloWorldClient {
	public static void main(String[] args) {
		HelloWorldImplService helloService = new HelloWorldImplService();
		HelloWorld hello = helloService.getHelloWorldImplPort();
		System.out.println(hello.getHelloWorldAsString("Pinco"));
	}
}