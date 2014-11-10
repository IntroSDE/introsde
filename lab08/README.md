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
* Create a Java Class that will serve as a *Web Service Enpoint Interface*
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

## JAX-WS Tutorial - RPC Style (4)

* Call the service via an HTTP POST request on localhost:6900/ws/hello with body

```xml
<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
  <soap:Body xmlns:m="http://ws.introsde/">
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
            xmlns:ns2="http://ws.introsde/">
            <return>Hello World JAX-WS Pinco</return>
        </ns2:getHelloWorldAsStringResponse>
    </S:Body>
</S:Envelope>
```

---

## JAX-WS Tutorial - Implementing Clients 

* Create a package **introsde.client** and add the following class

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
		QName qname = new QName("http://ws.introsde/", "HelloWorldImplService");
		Service service = Service.create(url, qname);
		HelloWorld hello = service.getPort(HelloWorld.class);
		System.out.println(hello.getHelloWorldAsString("Pinco"));
	}
}
```

---

## JAX-WS Tutorial - Implementing Clients - Automatic (1)

* You can also use **wsimport** to parse the wsdl file and generate client files (stub) to access the published web service.
* This is usefuls if you don't have the webservice interface (HelloWorld) locally available as part of some library.
* With wsimport, you will generate a local stub of the remote service that will serve you as a proxy.  
* wsimport should be in JDK_PATH/bin folder.
* Create a **my-solutions** folder on your local copy of lab10.
* From the command line, execute the following inside that new folder

```sh
wsimport -keep http://localhost:6900/ws/hello?wsdl
```

---

## JAX-WS Tutorial -  Implementing Clients - Automatic (2)

* You should now have an interface and a service implementation as follows:
* File **introsde/ws/HelloWorld.java**

```java
package introsde.ws;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;
@WebService(name = "HelloWorld", targetNamespace = "http://ws.introsde/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWorld {
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.introsde/HelloWorld/getHelloWorldAsStringRequest", 
        output = "http://ws.introsde/HelloWorld/getHelloWorldAsStringResponse")
    public String getHelloWorldAsString(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);
}
```

---

## JAX-WS Tutorial -  Implementing Clients - Automatic (3)

* File **introsde/ws/HelloWorldImplService.java**

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
    targetNamespace = "http://ws.introsde/", 
    wsdlLocation = "http://localhost:6900/ws/hello?wsdl")
public class HelloWorldImplService extends Service
{
    private final static URL HELLOWORLDIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException HELLOWORLDIMPLSERVICE_EXCEPTION;
    private final static QName HELLOWORLDIMPLSERVICE_QNAME = 
        new QName("http://ws.introsde/", "HelloWorldImplService");
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
    //continues
```

---

## JAX-WS Tutorial -  Implementing Clients - Automatic (3)

```java
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
        return super.getPort(new QName("http://ws.introsde/", "HelloWorldImplPort"), 
            HelloWorld.class);
    }
    @WebEndpoint(name = "HelloWorldImplPort")
    public HelloWorld getHelloWorldImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.introsde/", "HelloWorldImplPort"), 
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

---

## JAX-WS Tutorial -  Implementing Clients - Automatic (4)

* To use this stub, create the following program in the file **introsde/client/HelloWorldClient.java**:

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

```sh
javac introsde/client/HelloWorldClient.java
java introsde/client/HelloWorldClient
```

---

## JAX-WS Tutorial - Document style (1)

* Create a new Web Dynamic Project
* Create the packages introsde.ws, introsde.document.client, introsde.document.endpoint, introsde.document.ws.jaxws
* Create the Web Service Endpoint Interface **HelloWorld** as follows (the only change is the SOAPBinding annotation):

```java
package introsde.document.ws; 
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface HelloWorld{
	@WebMethod String getHelloWorldAsString(String name);
}

---

## JAX-WS Tutorial - Document Style (2)

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

---

## JAX-WS Tutorial - Document Style (3)

* Create the Endpoint Publisher **HelloWorldPublisher.java**
* Run it to ensure that all classes are compiled

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

---

## JAX-WS Tutorial - Document Style - Generating Artifacts (1)

* You can use **wsgen** to generate all necessary Java artifacts (mapping classes, wsdl or xsd schema). 
* Run the following command on build/classes (where the compiled classes are):

```sh
wsgen -keep -cp . introsde.document.ws.HelloWorldImpl
```

* It will generate two classes in build/classes/introsde/ws/jaxws, 
* Copy them to your **src/introsde/ws/jaxws** folder.
* These can be seen as the equivalents to the **model** in Jersey. 

---

## JAX-WS Tutorial - Document Style - Generating Artifacts (2)

* GetHelloWorldAsString.java

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

---

## JAX-WS Tutorial - Document Style - Generating Artifacts (3)

* GetHelloWorldAsStringResponse.java

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

---

## JAX-WS Tutorial - Document Style client (3)

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

---

## Assignment #3: Part 1 (1)

* Using JAX-WS, implement CRUD services for the following model including the following operations
    * readPerson(int personId) (returns the Person)
    * createPerson(Person p) (returns the personId of the new Person, or a negative number representing an error)
    * updatePerson(Person p) (returns the personId of the updated Person, or a negative number representing an error)
        * [OPTION A] If p includes changes to the "healthProfile" you can should 1) replace the current profile with a new one if no hpId is specified (in which case, the old health profile should be part of the history now) or 2) update the corrisponding health profile if an hpId is given  
    * deletePerson(int id) (returns 0, or a negative number representing an error)
    * updatePersonHealthProfile(int personId, HealthProfile hp) (returns the id of the health profile updated, or a negative number representing an error). If no health profile exists yet, create it. In any other case, you should only update the health profile which is given by the hpId inside hp. 
    * [OPTION B] addPersonHealthProfile(int personId, HealthProfile hp) (adds a new health profile to replace the current one, which should pass to the history). When creating new health profiles, you can use either option A or B, the one you prefer.  

// Person & HealthProfile
```xml
<person>
    <personId>1</personId>
    <firstname>Chuck</name>
    <lastname>Norris</lastname>
    <birthdate>1945-01-01</birthdate>
    <healthProfile>
        <hpId>999</hpId>
        <date>2013-12-05</date>
        <weight>78.9</weight>
        <height>172</height>
        <steps>5000</steps>
        <calories>2120</calories>
    </healthProfile>
</person>
```


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