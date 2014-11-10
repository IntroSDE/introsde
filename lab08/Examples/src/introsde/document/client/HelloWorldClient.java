 package introsde.document.client;
    import java.net.URL;
    import javax.xml.namespace.QName;
    import javax.xml.ws.Service;
    import introsde.document.ws.HelloWorld;
    public class HelloWorldClient{
    	public static void main(String[] args) throws Exception {
    		URL url = new URL("http://localhost:6901/ws/hello?wsdl");
            //1st argument service URI, refer to wsdl document above
    		//2nd argument is service name, refer to wsdl document above
            QName qname = new QName("http://ws.document.introsde/", "HelloWorldImplService");
            Service service = Service.create(url, qname);
            HelloWorld hello = service.getPort(HelloWorld.class);
            System.out.println(hello.getHelloWorldAsString("Pinco"));
        }
    }