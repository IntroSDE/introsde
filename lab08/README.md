# LAB08: SOAP Web Services (1)

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-7 "Permalink to LAB07: Reading and writing from Databases & JPA (Java Persistence API)")**

Last, but not least, your knowledge of service design and engineering would not be complete if you do not know how to implement services that follows the SOAP protocol. In this module, we will get acquainted with JAX-WX as the framework for implementing SOAP services.   


## Code

Links: [Source code][1]

## Guiding Notes

### JAX-WS Overview

* JAX-WS (*Java API for XML Web Services*), is a technology for building web services and clients that communicate using XML. 
* Web service invocation is represented by an XML-based protocol such as SOAP. 
* SOAP defines the envelope structure, encoding rules, and conventions for representing web service invocations and responses. 
* Calls and responses are transmitted as SOAP messages (XML files) over HTTP.
* JAX-WS API hides SOAP's complexity from the application developer. 
* On the server side, the developer specifies the web service operations by defining methods in an interface 
* The developer also codes one or more classes that implement those methods. 
* A client creates a proxy (a local object representing the service) and then simply invokes methods on the proxy. 
* The developer does not generate or parse SOAP messages. JAX-WS runtime system converts API calls and responses to and from SOAP messages

![][6]

There are two ways to structure a SOAP message 
* **RPC Style** web service uses the names of the method and its parameters to generate XML structures that represent a methods call stack. In the early versions of SOAP (before it was publicly published),  When using RPC style, the contents of the SOAP Body must conform to a structure that indicates the method name and contains a set of parameters.
* **Document style** indicates that the SOAP body contains a XML document which can be validated against pre-defined XML schema document. When using Document style, you can structure the contents of the SOAP Body any way you like.
* The response message has a similar structure containing the return value and any output parameters. For example, you can pass a **purchase order** as a document or as a parameter in a method called placeOrder. 

* **Document style:**

    ```xml
    <env:Body> 
        <m:purchaseOrder xmlns:m="someURI"> [purchase order document] </m:purchaseOrder> 
    </env:Body> 
    ```

* **RPC style:**

    ```xml
    <env:Body> 
        <m:placeOrder xmlns:m="someURI"> 
            <m:purchaseOrder> [purchase order document] </m:purchaseOrder> 
        </m:placeOrder> 
    </env:Body> 
    ```

### JAX-WS Tutorial - RPC Style (1)

* Create a Web Dynamic Project in eclipse (mark "Generate Deployment Descriptor") named *sdelab08*
* Copy both *build.xml* and *ivy.xml* files from the lab's repository to your project
* Add ivy to your project (see *Additional notes* at the end of the guiding notes) 
* Create a package named **introsde.ws**
* Create a Java Class that will serve as a *Web Service Endpoint Interface*
 * A web service endpoint is a service that is published for users to access
 * The web service client is the party who access the published service
 * In this case, a **HelloWorld** java interface like the following

    ```java
    package introsde.ws;
    import javax.jws.WebMethod;
    import javax.jws.WebService;
    import javax.jws.soap.SOAPBinding;
    import javax.jws.soap.SOAPBinding.Style; 
    //Service Endpoint Interface
    @WebService
    @SOAPBinding(style = Style.RPC)
    public interface HelloWorld{ 
        @WebMethod String getHelloWorldAsString(String name);
    }
    ```

* Now, you can create the *Web Service Endpoint Implementation*, which as the name implies, implements the interface we created before. Notice how we reference the interface class with the *@WebService* annotation. 

    ```java
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
    ```

* Now, in order to publish this web service entpoint, create a package **introsde.endpoint** and the following Java class in it. This is the *Web Service Endpoint Publisher*. This is the equivalent to our **Standalone HTTP Server** in jersey examples. 

    ```java
    package introsde.endpoint;
    import javax.xml.ws.Endpoint;
    import introsde.ws.HelloWorldImpl;
    //Endpoint publisher
    public class HelloWorldPublisher{
        public static void main(String[] args) {
           Endpoint.publish("http://localhost:6900/ws/hello", new HelloWorldImpl());
        }
    }
    ```

* Run your first JAX-WS Service as a Java application
* Test that is working by accessing the following in your browser: http://localhost:6900/ws/hello?wsdl 
* The former shows you the description XML of your SOAP web service. Notice the line where your service is declared with the name **HelloWorldImplService**. We will use that later. 

    ```xml
        <service name="HelloWorldImplService">
            <port name="HelloWorldImplPort" binding="tns:HelloWorldImplPortBinding">
                <soap:address location="http://localhost:6900/ws/hello"/>   
            </port>
        </service>
    ```
 
* Now, using the service **address location**, let's call the service with an **HTTP POST request** on that address (http://localhost:6900/ws/hello) with the following body (the SOAP invocation message). Make sure the encoding is **text/xml**

    ```xml
    <soap:Envelope
    xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
    soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
      <soap:Body xmlns:m="http://ws.document.introsde/">
      <m:getHelloWorldAsString>
        <arg0>Pinco</arg0>
      </m:getHelloWorldAsString>
    </soap:Body>
    </soap:Envelope>
    ```

* This should be the response

    ```xml
    <?xml version="1.0" ?>
    <S:Envelope
        xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
        <S:Body>
            <ns2:getHelloWorldAsStringResponse
                xmlns:ns2="http://ws.document.introsde/">
                <return>Hello World JAX-WS Pinco</return>
            </ns2:getHelloWorldAsStringResponse>
        </S:Body>
    </S:Envelope>
    ```

### JAX-WS Tutorial - Implementing Clients 

* Create a package **introsde.client** and add the following class. Notice how we **QName** clase is used to reference the service class we are calling. The package of the Service implementation is reversed (**ws.document.introsde**) and the second argument refers to the name of the service as define in the WSDL (**HelloWorldImplService**)

    ```java
    package introsde.client;
    import java.net.URL;
    import javax.xml.namespace.QName;
    import javax.xml.ws.Service;
    import introsde.ws.HelloWorld;
    public class HelloWorldClient {
        public static void main(String[] args) throws Exception {
            URL url = new URL("http://localhost:6900/ws/hello?wsdl");
            // 1st argument service URI, refer to wsdl document above
            // 2nd argument is service name, refer to wsdl document above
            QName qname = new QName("http://ws.document.introsde/", "HelloWorldImplService");
            Service service = Service.create(url, qname);
            HelloWorld hello = service.getPort(HelloWorld.class);
            System.out.println(hello.getHelloWorldAsString("Pinco"));
        }
    }
    ```

* Run the client. You should see only the output of the service, not the full SOAP message as we have seen before. Notice that you are invoking the service in as simple Java method. So whatever that method returns, you can manipulate it as an standar java object. 
* Notice also how to implement the client, we need the **Web Service Endpoint Interface class**
* Since we are also creating the service, the **HelloWorld.class** is in our build path. **But what if we are not the service developer? What if we are working in a fully different project?**

### JAX-WS Tutorial - Implementing Clients - Automatic generation 

* If you are not the service developer and you don't have access to its **Web Service Endpoint Interface class**, you can use **wsimport** command to parse the **wsdl** of the service and generate client **stub files** to access the published web service. 
* With wsimport, you will generate a local stub of the remote service that will serve you as a proxy.  
* wsimport should be in JDK_PATH/bin folder. If not, download the full JAX-WS bundle from [here][7], unzip the compressed file and copy the binaries from **bin** folder inside somewhere in your path.
* To generate the **stub files**, create another **Dynamic Web Project** with the **sdelab08-clients**, copying inside the same **ivy.xml** and **build.xml** of our service project. 
* Then execute the following on the command line, inside the **src** folder of this new project

    ```sh
    wsimport -keep http://localhost:6900/ws/hello?wsdl
    
    Output: 
    parsing WSDL...
    Generating code...
    Compiling code...
    ```

* You should now have an interface and a service implementation in the **introsde.ws** package of the new project (including its compiled versions). The newly generated classes follow. 
 * **introsde/ws/HelloWorldImplService.java**
 * **introsde/ws/HelloWorld.java**
 
    ```java
    package introsde.ws;
    import javax.jws.WebMethod;
    import javax.jws.WebParam;
    import javax.jws.WebResult;
    import javax.jws.WebService;
    import javax.jws.soap.SOAPBinding;
    import javax.xml.ws.Action;
    @WebService(name = "HelloWorld", targetNamespace = "http://ws.document.introsde/")
    @SOAPBinding(style = SOAPBinding.Style.RPC)
    public interface HelloWorld {
        @WebMethod
        @WebResult(partName = "return")
        @Action(input = "http://ws.document.introsde/HelloWorld/getHelloWorldAsStringRequest", 
            output = "http://ws.document.introsde/HelloWorld/getHelloWorldAsStringResponse")
        public String getHelloWorldAsString(
            @WebParam(name = "arg0", partName = "arg0")
            String arg0);
    }
    ```

    ```java
    package introsde.ws;
    import java.net.MalformedURLException;
    import java.net.URL;
    import javax.xml.namespace.QName;
    import javax.xml.ws.Service;
    import javax.xml.ws.WebEndpoint;
    import javax.xml.ws.WebServiceClient;
    import javax.xml.ws.WebServiceException;
    import javax.xml.ws.WebServiceFeature;
    @WebServiceClient(name = "HelloWorldImplService", 
        targetNamespace = "http://ws.document.introsde/", 
        wsdlLocation = "http://localhost:6900/ws/hello?wsdl")
    public class HelloWorldImplService extends Service
    {
        private final static URL HELLOWORLDIMPLSERVICE_WSDL_LOCATION;
        private final static WebServiceException HELLOWORLDIMPLSERVICE_EXCEPTION;
        private final static QName HELLOWORLDIMPLSERVICE_QNAME = 
            new QName("http://ws.document.introsde/", "HelloWorldImplService");
        static {
            URL url = null;
            WebServiceException e = null;
            try {
                url = new URL("http://localhost:6900/ws/hello?wsdl");
            } catch (MalformedURLException ex) {
                e = new WebServiceException(ex);
            }
            HELLOWORLDIMPLSERVICE_WSDL_LOCATION = url;
            HELLOWORLDIMPLSERVICE_EXCEPTION = e;
        }
    
        public HelloWorldImplService() {
            super(__getWsdlLocation(), HELLOWORLDIMPLSERVICE_QNAME);
        }
        public HelloWorldImplService(WebServiceFeature... features) {
            super(__getWsdlLocation(), HELLOWORLDIMPLSERVICE_QNAME, features);
        }
        public HelloWorldImplService(URL wsdlLocation) {
            super(wsdlLocation, HELLOWORLDIMPLSERVICE_QNAME);
        }
        public HelloWorldImplService(URL wsdlLocation, WebServiceFeature... features) {
            super(wsdlLocation, HELLOWORLDIMPLSERVICE_QNAME, features);
        }
        public HelloWorldImplService(URL wsdlLocation, QName serviceName) {
            super(wsdlLocation, serviceName);
        }
        public HelloWorldImplService(URL wsdlLocation, QName serviceName, 
            WebServiceFeature... features) {
            super(wsdlLocation, serviceName, features);
        }
        @WebEndpoint(name = "HelloWorldImplPort")
        public HelloWorld getHelloWorldImplPort() {
            return super.getPort(new QName("http://ws.document.introsde/", "HelloWorldImplPort"), 
                HelloWorld.class);
        }
        @WebEndpoint(name = "HelloWorldImplPort")
        public HelloWorld getHelloWorldImplPort(WebServiceFeature... features) {
            return super.getPort(new QName("http://ws.document.introsde/", "HelloWorldImplPort"), 
                HelloWorld.class, features);
        }
        private static URL __getWsdlLocation() {
            if (HELLOWORLDIMPLSERVICE_EXCEPTION!= null) {
                throw HELLOWORLDIMPLSERVICE_EXCEPTION;
            }
            return HELLOWORLDIMPLSERVICE_WSDL_LOCATION;
        }
    }
    ```

* To use this stub, you can create the **introsde.client** package in your new project and then put the following class in it (**introsde/client/HelloWorldClient.java**):

    ```java
    package introsde.client;
    import introsde.ws.HelloWorld;
    import introsde.ws.HelloWorldImplService;
    public class HelloWorldClient{
        public static void main(String[] args) {
            HelloWorldImplService helloService = new HelloWorldImplService();
            HelloWorld hello = helloService.getHelloWorldImplPort();
            System.out.println(hello.getHelloWorldAsString("Pinco"));
        }
    }
    ```
    
* Notice how we simply create an instance of the service implementation class, and then use it to execute the method we are interested in. Then we get the port of the service, represented by the interface of the endpoint. With this, we can effectively call the service. 
* Run this client either from eclipse, or if you want directly from the command line. 
    
    ```sh
    javac introsde/client/HelloWorldClient.java
    java introsde/client/HelloWorldClient
    ```

* The example client project is also in the github repository of this lab (see the **Client Examples** folder)

### JAX-WS Tutorial - Document style

* As we have seen earlier, another way of implementing SOAP web services is to not include a service invocation insde of the soap message, but only make reference to a **document**. 

    ```xml
    <env:Body> 
        <m:purchaseOrder xmlns:m="someURI"> [purchase order document] </m:purchaseOrder> 
    </env:Body> 
    ```

* From a development point of view, these style requires a bit more of work. In the following we will create a web service following this message style.
* In the service project, create the packages 
 * **introsde.document.ws**
 * **introsde.document.client**
 * **introsde.document.endpoint**
 * **introsde.document.ws.jaxws**
 
* Create the Web Service Endpoint Interface **HelloWorld** as follows (the only change is the SOAPBinding annotation):

    ```java
    package introsde.document.ws; 
    import javax.jws.WebMethod;
    import javax.jws.WebService;
    import javax.jws.soap.SOAPBinding;
    import javax.jws.soap.SOAPBinding.Style;
    import javax.jws.soap.SOAPBinding.Use;
    //Service Endpoint Interface
    @WebService
    @SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
    public interface HelloWorld{
        @WebMethod String getHelloWorldAsString(String name);
    }

* Create the Web Service Endpoint Implementation **HelloWorldImpl.java** (no changes here)

    ```java
    package introsde.document.ws;
    import javax.jws.WebService;
    //Service Implementation
    @WebService(endpointInterface = "introsde.document.ws.HelloWorld")
    public class HelloWorldImpl implements HelloWorld{
        @Override
        public String getHelloWorldAsString(String name) {
            return "Hello World JAX-WS " + name;
        }
    }

* Create the Endpoint Publisher **HelloWorldPublisher.java**

    ```java
    package introsde.document.endpoint;
    import javax.xml.ws.Endpoint;
    import introsde.document.ws.HelloWorldImpl;
    //Endpoint publisher
    public class HelloWorldPublisher{
        public static void main(String[] args) {
           Endpoint.publish("http://localhost:6901/ws/hello", new HelloWorldImpl());
        }
    }
    ```

* If you run this at is service, you will encounter the following error either at starting it, or when calling a service

    ```java
    Wrapper class introsde.document.ws.jaxws.GetHelloWorldAsString is not found. 
        Have you run APT to generate them?. 
    ```

* To avoid this, we need to generate the requeste class

### JAX-WS Tutorial - Document Style - Generating Artifacts 

* In the same way we refere to **resources** in jersey to serve representations of our model through web services, in the document style we need to create the **document artifacts** that will be served through this service.  
* We can use **wsgen** to generate all necessary **Java artifacts** that fulfil this purpose (mapping classes, wsdl or xsd schema).
* Before we generate the classes, make sure all the classes are compiled. To ensure this, disable Eclipse's **Project -> Build Automatically** option and then run **Project -> Build Project**  
* Run the following command on **build/classes** (where the compiled classes are):

    ```sh
    wsgen -keep -cp . introsde.document.ws.HelloWorldImpl
    ```

* It will generate two classes in **build/classes/introsde/ws/jaxws**
 * GetHelloWorldAsString.java
 * GetHelloWorldAsStringResponse.java
* These two classes are the **documents** that will be inserted in the body of the SOAP message. They can be seen as the equivalents to the **model** in Jersey. 
* Copy these two classes to your **src/introsde/ws/jaxws** folder, in order to reference them.

* **GetHelloWorldAsString.java.** Since the HelloWorld service returns a string, this is a class whose only property is a string.

    ```java
    package introsde.document.ws.jaxws;
    import javax.xml.bind.annotation.XmlAccessType;
    import javax.xml.bind.annotation.XmlAccessorType;
    import javax.xml.bind.annotation.XmlElement;
    import javax.xml.bind.annotation.XmlRootElement;
    import javax.xml.bind.annotation.XmlType;
    @XmlRootElement(name = "getHelloWorldAsString", namespace = "http://ws.document.introsde/")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "getHelloWorldAsString", namespace = "http://ws.document.introsde/")
    public class GetHelloWorldAsString {
        @XmlElement(name = "arg0", namespace = "")
        private String arg0;
        public String getArg0() {
            return this.arg0;
        }
        public void setArg0(String arg0) {
            this.arg0 = arg0;
        } 
    }
    ```

* **GetHelloWorldAsStringResponse.java.** Similarly, the document to be sent in responses of the service is also a class whose only property is a string. 

    ```java
    package introsde.document.ws.jaxws;
    import javax.xml.bind.annotation.XmlAccessType;
    import javax.xml.bind.annotation.XmlAccessorType;
    import javax.xml.bind.annotation.XmlElement;
    import javax.xml.bind.annotation.XmlRootElement;
    import javax.xml.bind.annotation.XmlType;
    @XmlRootElement(name = "getHelloWorldAsStringResponse", namespace = "http://ws.document.introsde/")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "getHelloWorldAsStringResponse", namespace = "http://ws.document.introsde/")
    public class GetHelloWorldAsStringResponse {
        @XmlElement(name = "return", namespace = "")
        private String _return;
        public String getReturn() {
            return this._return;
        }
        public void setReturn(String _return) {
            this._return = _return;
        }
    }
    ```

* Now, try to run your service again. 

## JAX-WS Tutorial - Document Style client

* Create the Web Service Client

    ```java
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
    ```

* Run this client and then use the POSTMAN client to send the same HTTP POST request we used for the first RPC example. Use the address (http://localhost:6901/ws/hello) with the following body. Make sure the encoding is **text/xml**

    ```xml
    <soap:Envelope
    xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
    soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
      <soap:Body xmlns:m="http://ws.document.introsde/">
      <m:getHelloWorldAsString>
        <arg0>Pinco</arg0>
      </m:getHelloWorldAsString>
    </soap:Body>
    </soap:Envelope>
    ```

* Although in this particular example, the difference between both styles is not really noticeable, there is a difference in between the **RPC** and the **Document** styles. 
* In the first, the SOAP message body contains an XML representation of a method call and uses the names of the method and its parameters to generate XML structures that represent a method's call stack.
* In the second,the SOAP body contains a XML document which can be completely validated against a pre-defined XML schema document.  
* The document/literal approach, in this sense, can be easier if there are XML Schemas describing exactly what the SOAP message looks like. 
* If you compare the **WSDL** of both styles, you will see that the latter has as schema definition on it under the element **type** and pointing to the following url: http://localhost:6901/ws/hello?xsd=1

    ```xml
    ...
    <types>
        <xsd:schema>
        <xsd:import namespace="http://ws.document.introsde/" schemaLocation="http://localhost:6901/ws/hello?xsd=1"/>
        </xsd:schema>
    </types>
    ...
    ```
    
    ```xml
    <xs:schema xmlns:tns="http://ws.document.introsde/" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0" targetNamespace="http://ws.document.introsde/">
        <xs:element name="getHelloWorldAsString" type="tns:getHelloWorldAsString"/>
        <xs:element name="getHelloWorldAsStringResponse" type="tns:getHelloWorldAsStringResponse"/>
        <xs:complexType name="getHelloWorldAsString">
            <xs:sequence>
                <xs:element name="arg0" type="xs:string" minOccurs="0"/>    
            </xs:sequence>
        </xs:complexType>   
        <xs:complexType name="getHelloWorldAsStringResponse">
            <xs:sequence>
                <xs:element name="return" type="xs:string" minOccurs="0"/>
            </xs:sequence>
        </xs:complexType>
    </xs:schema>
    ```

* So the main advantage of the **Document Style** is easier to validate while for **RPC style** the it is not very easy to do so since there is no complete schema defined for it. This means the schema alone does not tell you what the message body contains because some of the soap:body contents comes from WSDL definitions. Because of this, schema describing an RPC/literal message is not sufficient to validate that message

## Additional Notes

### Integrating IVY to a Web Project
* To get you all what you need for this module, we will use **IVY**. The **ivy.xml** in the lab source code folder defines the list of libraries we need.   
* **With ANT:** the **build.xml** contains targets to download the ivy.jar, the dependencies and then copy all these jars to the web project classpath (which will be the **WebContent/WEB-INF/lib** folder)
* **With Eclipse:** to integrate IVY to Eclipse's classpath, right click on the ivy.xml file and select **Add Ivy Library**. 
 * In the window that follows, in order for ivy to work also when deploying the services into Tomcat from Eclipse, we will configure IVY to put jars  into the project's **WebContent/WEB-INF/lib** folder. 
 * Go to the **Claspath tab** and marke **Enable project specific settings**. 
 * On **Build the classpath with** enable the option **retrieved artifacts**
 * On **Retrieve Pattern** put **WebContent/WEB-INF/lib/[type]s-[artifact]-[revision].[ext]**

### Other Resources 
 
* This lab session is heavily based on examples from [JAX-WS Tutorials online][2]
* [Oracle Java EE tutorials on JAX-WS][3]
* [SOAP Binding: difference between Document and RPC Style][4]
* Tutorial that mixes [JAX-WS with JPA][5]



[1]: https://github.com/cdparra/introsde/tree/master/lab08
[2]: http://www.mkyong.com/tutorials/jax-ws-tutorials/
[3]: http://docs.oracle.com/javaee/5/tutorial/doc/bnayl.html
[4]: http://java.globinch.com/enterprise-java/web-services/soap-binding-document-rpc-style-web-services-difference/#document_style_rpc_style
[5]: http://www.middlewareguru.com/mw/?p=795
[6]: http://docs.oracle.com/javaee/5/tutorial/doc/figures/jaxws-simpleClientAndService.gif
[7]: http://repo.maven.apache.org/maven2/com/sun/xml/ws/jaxws-ri/2.2.8/jaxws-ri-2.2.8.zip