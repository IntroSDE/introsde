package introsde.document.ws;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "introsde.document.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}
}