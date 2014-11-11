# LAB09: SOAP Web Services (2)

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-9 "Permalink to LAB09: SOAP Web Services (2)")**

In this module, we will give a second look to implementing SOAP web services using JAX-WS, adding JPA to access databases

## Code

Links: [Source code][1]

## Guiding Notes

### JAX-WS + JPA

* Create a **Dynamic Web Project** in eclipse named **sdelab09-server**. Add both **ivy.xml** and **build.xml** that we used in the last session of the laboratory (they are also in this lab's session repository). 
* What we will do in this session is equivalent to merging **Lab 07** with **Lab 08**. 
* First, create the following packages to your newly created project
 * introsde.document.client
 * introsde.document.dao
 * introsde.document.enpoint
 * introsde.document.model
 * introsde.document.test
 * introsde.document.ws


### JAX-WS + JPA - The Model and JPA 

* For this part, take the classes from Lab 07, and copy them to the correspondent packages (i.e., introsde.document.dao, introsde.document.model and introsde.document.test). Make sure to edit the classes so that they declare the right packages at the beginning.    
* Copy also the **META-INF** foler from the source folder of lab 07 (lab07/Examples/src/META-INF) and paste it on the source folder of the new project. 
 * Make sure that the **javax.persistence.jdbc.url** points to the right sqlite file in your local machine.
 * Make also sure to update the model declarations in this file to map the right classes (which are now in the package introsde.document.model)
* To enable Eclipse JPA extensions, you may want to Rigth click your project -> Configure -> Convert to JPA project. 
* You can now run the test class JPAStarterTest to check that everything is working correctly (don't worry if the tests fail, probably we have changed too many things already in that small database)

* Using classes as parameters is straightforward. 
* Using the same JAX-WS examples of last session, create a package **introsde.ws.model** 
* Add the following **Person** class to the model package

```java
package introsde.ws.model;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Person {
	private String firstname;
	private String lastname;
	public Person() {
	}
	public Person(String fname, String lname) {
		this.firstname=fname;
		this.lastname=lname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}	
	public String toString() {
		return this.firstname+" "+this.lastname;
	}
}
```

---

## JAX-WS with models (2)

* Add the following services to the HelloWorld interface

```java
@WebMethod String sayHelloTo(@WebParam(name="person") Person person);
@WebMethod @WebResult(name="person") Person readPerson(@WebParam(name="personId") int personId);
```

* And implement them on the implementaion class

```java
package introsde.ws;
import introsde.ws.model.Person;
import javax.jws.WebService;
//Service Implementation
@WebService(endpointInterface = "introsde.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}
	@Override
	public String sayHelloTo(Person person) {
		return "Hello " + person.getFirstname() + " " + person.getLastname();
	}
	@Override
	public Person readPerson(int personId) { 
		return new Person("Person","Test");
	}
}
```

---

## JAX-WS with models (3) 

* Test the new service in the client by adding:

```java
    String response = hello.sayHelloTo(new Person("Person","Test"));
    System.out.println("Response from the service to 'sayHelloTo': " + response);
    Person person = hello.readPerson(0);
    System.out.println("Response from the service to 'sayHelloTo': " + person.toString()); 
```





---

## JAX-WS with models

* Generating stubs for the client

```sh
wsimport -keep http://localhost:6902/ws/people?wsdl
```

* Use generated sources to create your client


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