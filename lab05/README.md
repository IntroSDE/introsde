# LAB05: The REST architectural style & RESTful web services (1)

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-5 "Permalink to LAB05: The REST architectural style & RESTful web services (1)")**

Whenever we talk about the **World Wide Web** and its underlying architectural principles, what we are really talking about is REST. What is to say, the **Representational state transfer** is an abstraction of the architecture of the WWW. In this module, we will explore the basis for designing and implementing services following this architectural style.  

## Slides &amp; Code

Links: [PPT slides][1] | [PDF slides][2] | [Source code][3]

## Guiding Notes

### REST recap (10 min)

* **Representational State Transfer (REST)** was introduced and defined in 2000 by Roy Fielding in his doctoral dissertation
* It is an **architectural style** of networked systems (not a protocol - not a specification), by which **resources** are exposed through out the system. 
* It is a **client-server architecture**.
* Only representations of **resources** are exposed to the client
* The representation of resources places the client application in a **state**.
* The **client's state** may evolve by **traversing hyperlinks** and obtaining **new representations** of resources

### REST Services Principles
* **Uniform Resource Identifier (URI)** for identifying resources (every resource must have at least one, e.g., http://www.example.com/software/release/1.0.3.tar.gz) 
* **Addressability** of services by exposing data as resources (i.e., conceptually, an infinite number of URIs)
   * *Filesystem* on your computer is an addressable system
   * The *cells in a spreadsheet* are addressable (cell referencing)
* **Statelessness** of the client application state (i.e., each request happens in a complete isolation, when a client makes an HTTP request, it includes all the necessary information for the server to fulfill the request)
* **Resource Representation** is needed for it to be sent to the client.  
    * One resource can have multiple representations (xml, json, english, italian, etc.)
* **Connectedness** of resources means that resource representations are hypermedia, i.e., they have *resources (data)* and *references to other resources (hyperlinks)*
* **Uniform interface** of operations by which resources are manipulated. They consists of a fixed set of HTTP operations (GET, POST, PUT and DELETE) 
    * POST /starbucks/orders (to create an order)
        * returns: location: /starbucks/orders/order?id=1234
    * GET /starbucks/orders/order?id=1234 (to read an order)
    * PUT /starbucks/orders/order?id=1234 (to update an existing order)
    * DELETE /starbucks/orders/order?id=1234 (to delete an existing order)
    * If followed properly, gives you two important properties:
        * **Safety (GET):** Read-only operations. The operations on a resource do not change any server state. The client can call the operations 10 times, 1000 times, it has no effect on the server state.
        * **Idempotence (GET, PUT and DELETE):** Operations that have the same effect whether you apply them once or more than once. An effect here may well be a change of server state. An operation on a resource is idempotent if making a request once has the same effect as making the identical request multiple times.
    
### RESTful Services
    
* **RESTful** indicates that all REST principles are followed **strictly** (if you relax on some principles, then you a have a **RESTlike** service). Often, it is reduced to the **stateless principle**: every interaction with a resource should be self-contained, the state of a system stays on the client side and should be part of the URI. 
    
### REST Before we Start: Tomcat and Eclipse WTP (10 min)

* For this lab, make sure you have installed **Apache Tomcat** and the **Web Tools Platform (WTP)** in eclipse (this should already be on it by default with the Juno Java EE release) 
* To install Tomcat, WTP and configure Tomcat in eclipse, check the Additional notes section. 

### REST Example 1: Creating a simple REST service with Java Servlets (45 min)

* REST does not requires you to use a specific client or server-side framework in order to write your Web services. All you need is: 
    * a client or server that supports the HTTP protocol (i.e., a web server, a browser).
    * choose a language of your choice
* **In Java:** the simplest way of implementing REST services is by extending the **javax.servlet.http.HttpServlet** class and overriding its **doGet()**, **doPost()**, **doPut()** and **doDelete()** methods.

**Let's see how it is done!**

* **Step 0:** Design your REST API. How would a HelloWorld REST API look like? What's the resource we are exposing and what operation can we have on it?. In a very simple example, "Hello World!" is a **Salutation** message, which is our resource. And the first operation we can have on it is that of reading it, which is to say, **GET** the message. In order words, we will implement the following simple API

    ```
    Request:
    GET /salutation
    
    Response: 
    200 (OK)
    Content-type: text/plain
    "Hello World!"
    ```    

* **Step 1:** Create a *Dynamic Web Project* named *"sdelab05-local"* in your local workspace. When asked about opening the *Java EE Perspective*, choose YES. 
* **Step 2:** Create a new Java Class using the HelloWorld template in the lab's source code (**Examples/src/introsde/rest/servlet/HelloWorld.java**). The same is generated by Eclipse if you use *File -> New -> Servlet*
   * If you do use Eclipse's wizard, it is suggested to later modify the *@WebServlet* Annotation so that it defines both a name and urlPatterns - e.g., *@WebServlet(name = "HelloWorldServlet", urlPatterns = "/salutation")*.
   * It is also advised that the *serialVersionUID* is different for each servlet (Eclipse might generate it with the same value always - 1L - you can deleted and generate a new random one)
   * If deployment fails, sometimes the best thing to do is to eliminate the tomcat server configuration from eclipse, clean and rebuild the project, eliminate (if there is) any remaining folder in your Tomcat's webapp folder related to your project, and then start from scratch creating the Tomcat server again in Eclipse (as indicated in "Additional Notes") 
* **Step 3:** You can now implement your services inside the operation you want to use to expose your resources. In this case, the resource is a simple "Hello World!" String message. The object *request* contains all the information about the request that will be received by the servlet, and the object *response* is where the servlet's reply will be prepared and send. Use *response.getWriter().write("Hello World!")* for the GET. 

* **Step 4 - Exercise 05.01: ** What about receiving parameters?. Take a look to the **Examples/src/introsde/rest/servlet/SydneyServlet.java** to see how to get *path and query parameters* from the request. Modify your HelloWorld servlet example to receive a language code as part of the path (e.g., "en", "it", "es") and format code as a query parameter. Your server should then answer accordingly. 
   * Example (implement at least this one for the lab session): 
   
    ```
    Request:
    GET /salutation/es?format=json
    
    Response: 
    200 (OK)
    Content-type: text/xml
    { "salutation": "Hola Mundo!" }
    ```

* This is example is not entirely **restful**. On a more RESTful design, path params will only be used to identify resources (e.g., nouns) and not representations of resources. For filtering resources according to specific characteristics (that will not necessarily yield one resource only), request query params or body content is used. Take a look to this more ["rest-like"][19] simple example of a salutation REST API  
    
### REST Services Frameworks (5 min) 

* As seen in the initial example, there is **no need of a specific client or server-side framework**. The only requirement is **supporting the HTTP protocol**
* However, *just servlets* can be complicated in reality (just think of the **SydneyServlet** example, way too complicated isn't?). For this reason, frameworks have been created to reduce the amount of boilerplate code needed and, preferably, making developing REST services more **REST like** (meaning, you define *Resources* and make them available through *HTTP operations*). 
* One standard API specification in java that has been widely adopted for REST services is [JAX-RS][10]. Most frameworks are implementations of this standard. 
* In this lab, we will focus our work on the use of [Jersey][5] - a JAX-RS implementation created by Sun. See the additionals notes for a list of other propular frameworks

### REST Example 2: Hello World with Jersey (30 min)

* To get all the libraries you will need, copy to your **sdelab05-local** project the **ivy.xml** in the lab source code folder 
* Right Click on the ivy.xml file and select **Add Ivy Library**. This will add all ivy libraries to your project's classpath. 
* Moreover, for ivy to work also when deploying the services into Tomcat from Eclipse, we will need to copy the jars downloaded by ivy into the project's **WebContent/WEB-INF/lib** folder
* To do so, we will proceed as follows:
  * Right click on the **Project -> Properties -> Ivy
  * Click on **New** to create a new **retrieve configuration** 
  * Set name as **tomcat**
  * Set Retrieve pattern as **WebContent/WEB-INF/lib/[type]s-[artifact]-[revision].[ext]**
* Now, you can copy all the jars ivy previously downloaded to the lib folder by right click on the **Project -> Ivy -> Retrieve 'tomcat'**. You should now see all the required jars in the lib folder that is inside WEB-INF. 
* **What's inside Jersey?** 
    * A core server, implemented as a servlet dispatcher for REST requests
    * A core client that provides a library to communicate with the server

### REST Example 2: Hello World with Jersey - Creating our first resource

* For this hello world example, we are going to create a single java class that will be our **"salutation"** Resource
* Create the following java class in a package named **introsde.rest.jersey.hello** (the full source code is part of the labs examples, but don't look on it, try to do it yourself)

    ```java
    package introsde.simple.rest.hello;
    import javax.ws.rs.GET;
    import javax.ws.rs.Path;
    import javax.ws.rs.Produces;
    import javax.ws.rs.core.MediaType;
    @Path("/salutation") // indicates the path under which this resource will be available
    public class HelloWorld {
        @GET
        @Produces(MediaType.TEXT_HTML)
        public String sayHelloHtml() {
            return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                    + "<body><h1>" + "Hello World in REST" + "</body></h1>"
                    + "</html> ";
        }
        @GET
        @Produces(MediaType.TEXT_XML)
        public String sayHelloXML() {
            return "<?xml version=\"1.0\"?>" + "<msg>" + "Hello World in REST"
                    + "</msg>";
        }
    }
    ```

* The above resource supports two representations (XML and HTML). 
* Jersey uses **content negotiation** to decide what representation to send as response.
   * JAX-RS understands the **Accept** header and will use it when dispatching the answer.
   * For example, an **Accept: text/html,application/xhtml,application/xml;q=0.9,*/*;q=0.8** means that the client prefers html or xhtml (q is 1 by default), raw XML second, and any other content type third 
   * Your browser will always request HTML MIME type as the first preference.


### REST Example 2: Hello World with Jersey - Registering the servlet dispatcher

* You need to add the resource you created and a jersey servlet dispatcher in the **web.xml** file
   * If you don't find a web.xml file in the folder **WebContent/WEB-INF** you can create a stub by right click on the **Project -> Java EE Tools -> Generate Deployment Descriptor Stub**
* The correct content of the web.xml file is shown below (add the servlet and servlet-mapping below to your default web.xml)

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
      <display-name>Testing REST Services</display-name>
      <servlet>
        <servlet-name>Jersey Web Application</servlet-name>
        <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
        <init-param>
          <param-name>jersey.config.server.provider.packages</param-name>
          <param-value>introsde.rest</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
      </servlet>
      <servlet-mapping>
        <servlet-name>Jersey Web Application</servlet-name>
        <url-pattern>/jersey/*</url-pattern>
      </servlet-mapping>
    </web-app>
    ```

* **Important Note**: the 'param-name' tag **jersey.config.server.provider.packages** is a configuration parameter. Its corresponding 'param-value' tag value should point to the package where the resource classes are located (i.e. the package **"introsde.rest"** in our case).
* If your resource classes are located in the default package you can put either "." (without quotes) as the value or completely omit the 'param-name' and 'param-value' tags.
* Note the url-pattern in servlet-mapping. Any URL with this pattern will be handled by Jersey servlet dispatcher.

### REST Example 2: Hello World with Jersey - Testing

* **[Run the project]** Run the project (while the project is highlighted, do right mouse click, then choose 'Run As' -> 'Run on Server'. Follow the prompt to deploy the project to your Tomcat runtime.

* **[Test it first with your browser]** try 'http://localhost:8080/sdelab05-local/rest/salutation'. You should see the result of sayHelloHtml() (try viewing the source of the page returned). Notice that also the first example is available in /salutation.

* **[Test it with a REST-client tool]** you can use any REST client tool, below you will find links to some of them.
   * [Postman][11] - Chrome extension (also available as packaged app)  
   * [Simple REST Client][12] - Chrome extension
   * [rest-client][13] - Java, multi-platform
   * [cocoa-rest-client][14] - Mac OS X app
   * Let me know if you find others!

### REST Example 3: A Simple client in Java  (15 min)

* Create a simple java program with a main as follows: 

    ```java  
    import java.net.URI;    
    import javax.ws.rs.client.Client;
    import javax.ws.rs.client.ClientBuilder;
    import javax.ws.rs.client.WebTarget;
    import javax.ws.rs.core.MediaType;
    import javax.ws.rs.core.UriBuilder;
    public class Test {
        public static void main(String[] args) {
            ClientConfig clientConfig = new ClientConfig();
            Client client = ClientBuilder.newClient(clientConfig);
            WebTarget service = client.target(getBaseURI());
            
            // // GET BASEURL/rest/salutation
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
                    "http://localhost:8080/sdelab05-local/rest").build();
        }
    ```
    
### REST Example 4: Path Params and Query Params

* Add the following to your HelloWorld resource class. 
    
    ```java
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
    ```

* This method will be called upon requests that ask for **/helloworld/Cristhian%20Parra?age=35**
    
### REST Example 4: A Standalone HTTP Server (15 min) 

* Finally, since all what is necessary for exposing REST services is an HTTP server, you can also make your services available using a standalone HTTP server like the following (instead of an Servlet container like tomcat) 

    ```java
    package introsde.rest.jersey.hello;

    import java.io.IOException;
    import java.net.InetAddress;
    import java.net.URI;
    import java.net.URISyntaxException;
    import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
    import org.glassfish.jersey.server.ResourceConfig;
    
    public class HelloWorldStandalone
    {
        public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
        {
            String protocol = "http://";
            String port = ":5700/";
            String hostname = InetAddress.getLocalHost().getHostAddress();
            if (hostname.equals("127.0.0.1"))
            {
                hostname = "localhost";
            }
    
            URI baseUrl = new URI(protocol + hostname + port);
    
            ResourceConfig rc = new ResourceConfig(HelloWorld.class);
            JdkHttpServerFactory.createHttpServer(baseUrl, rc);
            System.out.println("server starts on " + baseUrl + "\n [kill the process to exit]");
        }
    }
    ```

* Run as a Java Application and then request for: **http://localhost:5700/helloworld/Cristhian%20Parra?age=35**


### Brief Summary of Jersey annotations (10 min)

* **@PATH(your_path):** Sets the path to base URL + /your_path. The base URL is based on your application name, the servlet and the URL pattern from the web.xml configuration file.
* **@POST:** Indicates that the following method will answer to a HTTP POST request
* **@GET:** Indicates that the following method will answer to a HTTP GET request
* **@PUT:** Indicates that the following method will answer to a HTTP PUT request
* **@DELETE:** Indicates that the following method will answer to a HTTP DELETE request
* **@Produces(MediaType.TEXT_PLAIN [, more-types ]):** defines which MIME type is delivered by a method annotated with @GET. 
* **@Consumes(type [, more-types ]):** defines which MIME type is consumed by this method.
* **@PathParam:** inject values from the URL into a method parameter. This way you inject for example the ID of a resource into the method to get the correct object.
* **@QueryParam:** inject values from the query parameters in the URL into a method parameter. 
* The complete path to a resource is based on the base URL and the @PATh annotation in your class.

    ```
        http://your_domain:port/display-name/url-pattern/path_from_rest_class 
    ```
    
### Exercise (40 min) 

* **Exercise 05.03:** Based on the the examples from previous modules, add the Person and HealthProfile resources to the project under the package **introsde.rest.ehealth**

### Final note: ant

* You can also use ant to create the example project's war and then manually deploy it using Tomcat's manager. Check the build.xml for the "create.war" target to see how. 

## Additional notes

### Installation of Eclipse WTP (Web Tools Platform)

* **Eclipse WTP** provides tools for developing standard Java web applications and Java EE applications
* To install, use **Help -> Install new software -> All Available Sites**
* Search for **Web Tools Platform** in the list and install all what's inside that category for the latest version
* In old versions of eclipse, there might be a category "Web, XML, Java EE Development and OSGi Enterprise Development". 
* Existing projects can seize the possibilities of WTP by converting them into **Dynamic Web** Projects. This is done in Eclipse by going to **Project -> Properties -> Project Facets -> Enable "Dynamic Web Module"**.  

### Configuration of the Tomcat Server in Eclipse 

* Open the Servers view: **Window -> Show View -> Other -> Server -> Servers**
* If no server is available, you will see the link *No servers are available. Click this link to create a new server...*. Click on it to create a one.
* Follow the steps of the wizard making sure of the following:
    * Select the version of Apache Tomcat you have installed (e.g., 7.0)
    * "Tomcat installation directory" points to the home folder of tomcat in your machine (e.g., /opt/apache-tomcat-7.0.39)
    * "JRE" points to the folder where you have installed the Java JDK (make sure is the JDK not just the JRE) 
* Once created, double click on the server to open its configuration file. In this file, make sure the option **Use Tomcat installation (takes control of tomcat installation)** is enabled and **Deploy path** is **webapps**
* Right click on the server and select **Start** to test everything works. You should see the standard welcome page of tomcat in http://localhost:8080/

### Other REST frameworks
* [Restlet][7] - probably the first REST framework, which existed prior to JAX-RS.
* [RESTEasy][6] - JBosss JAX-RS implementation.
* [Play Framework][8] - popular nowadays, it is an MVC framework with a heavy focus on RESTful design and available for both Java and Scala programmers
* [Spring Framework][9]  - another very popular java application framework that you can use to build RESTful services 
* [Apache CXF][4] - Apache's open source services framework that allows you to develop services using either JAX-WS or JAX-RS specifications; speaking a variety of protocols including SOAP, XML/HTTP, RESTful HTTP, or CORBA; and working over a variety of transports layers such as HTTP, JMS or JBI.
* [RAILS][15] - the ruby most well known web development framework, which heavily supports the RESTful style
* [Sinatra][16] - a ruby lightweight framework for implementing REST Services
* [Djanjo REST framework][17] - REST development framework for the python language
* [Python EVE][18] - another python-based REST framework  
* [Expressjs][20] - a web application framework for NodeJS

[1]: https://drive.google.com/open?id=0B7ShzcEnCJFNWENNN1RpYU9xeUk&authuser=0
[2]: https://drive.google.com/open?id=0B7ShzcEnCJFNQ2FfR21FUUk1Y1E&authuser=0
[3]: https://github.com/cdparra/introsde/tree/master/lab05/Examples
[4]: http://cxf.apache.org/
[5]: https://jersey.java.net/
[6]: http://www.jboss.org/resteasy
[7]: http://restlet.org/
[8]: http://www.playframework.com/) 
[9]: http://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch18s02.html
[10]: http://jcp.org/en/jsr/detail?id=311 
[11]: http://www.getpostman.com
[12]: https://chrome.google.com/webstore/detail/fhjcajmcbmldlhcimfajhfbgofnpcjmb
[13]: http://code.google.com/p/rest-client/
[14]: http://code.google.com/p/cocoa-rest-client/
[15]: http://rubyonrails.org/
[16]: http://www.sinatrarb.com/
[17]: http://www.django-rest-framework.org/
[18]: http://python-eve.org/
[19]: http://docs.helloworld66.apiary.io/
[20]: http://expressjs.com/
