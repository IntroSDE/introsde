package introsde.ws;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "introsde.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}
}