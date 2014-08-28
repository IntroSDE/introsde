# LAB05: The REST architectural style & RESTful web services (1)

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-5 "Permalink to LAB05: The REST architectural style & RESTful web services (1)")**

Whenever we talk about the World Wide Web, and its underlying architectural principles, what we are really talking about is of REST. What is to say, the Representational state transfer is an abstraction of the architecture of the WWW. In this module, we will explore the basis for designing and implementing services following this architectural style.  

## Slides &amp; Code

Links: [PPT slides][1] | [PDF slides][2] | [Source code][3]

## Guiding Notes

### REST recap (15 min)

* **Representational State Transfer (REST)** was introduced and defined in 2000 by Roy Fielding in his doctoral dissertation
* It is an **architectural style** of networked systems (not a protocol - not a specification), by which **resources** are exposed through out the system. 
* It is a **client-server architecture**.
* Only representations of **resources** are exposed to the client
* The representation of resources places the client application in a **state**.
* The **client's state** may evolve by **traversing hyperlinks** and obtaining **new representations** of resources

### REST Services Principles
* **Uniform Resource Identifier (URI)** for identifying resources (every resource must have at least one) 
    * http://www.example.com/software/release/1.0.3.tar.gz
* **Addressability** of services by exposing data as resources (i.e., a conceptually infinite number of URIs)
    * Filesystem on your computer is an addressable system
    * The cells in a spreadsheet are addressable (cell referencing)
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
* To install Tomcat, WTP and configure Tomcat in eclipse, check the [Additional notes](Additional notes) section. 

## REST Example 1: Hello World Servlet

* REST does not requires you to use a specific client or server-side framework in order to write your Web services. All you need is: 
    * a client or server that supports the HTTP protocol (i.e., a web server, a browser).
    * choose a language of your choice
* **In Java:** the simplest way of implementing REST services is by extending the **javax.servlet.http.HttpServlet** class and overriding it **doGet()**, **doPost()**, **doPUT()** and **doDelete()**
    * URLs contains: servlet path + path info (all you need to process a request in REST)
    * You could use a third-party library for generating specific content type (CSV, JSON or XML, etc.) or use Strings concatenations for simple responses.

* Open the **Examples/src/introsde/rest/servlet/SydneyServlet.java** example. 



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

[1]: https://drive.google.com/open?id=0B7ShzcEnCJFNWENNN1RpYU9xeUk&authuser=0
[2]: https://drive.google.com/open?id=0B7ShzcEnCJFNQ2FfR21FUUk1Y1E&authuser=0
[3]: https://github.com/cdparra/introsde/tree/master/lab05/Examples
