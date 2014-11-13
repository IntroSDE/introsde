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
 * introsde.document.endpoint
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

### JAX-WS + JPA - The Web Services 

* Now, using the document style services of lab 08 as examples, we can create the **Web Service Endpoint Interface**, its **implementation** and its **publisher**.
* Add the following two classes in the **introsde.document.ws** package, and the third one to **introsde.document.endpoint**

* **People.java**

    ```java
    package introsde.document.ws;
    import introsde.document.model.LifeStatus;
    import introsde.document.model.Person;
    
    import java.util.List;
    
    import javax.jws.WebMethod;
    import javax.jws.WebParam;
    import javax.jws.WebService;
    import javax.jws.WebResult;
    import javax.jws.soap.SOAPBinding;
    import javax.jws.soap.SOAPBinding.Style;
    import javax.jws.soap.SOAPBinding.Use;
    
    @WebService
    @SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
    public interface People {
        @WebMethod(operationName="readPerson")
        @WebResult(name="person") 
        public Person readPerson(@WebParam(name="personId") int id);
     
        @WebMethod(operationName="getPeopleList")
        @WebResult(name="people") 
        public List<Person> getPeople();
     
        @WebMethod(operationName="createPerson")
        @WebResult(name="personId") 
        public int addPerson(@WebParam(name="person") Person person);
     
        @WebMethod(operationName="updatePerson")
        @WebResult(name="personId") 
        public int updatePerson(@WebParam(name="person") Person person);
        
        @WebMethod(operationName="deletePerson")
        @WebResult(name="personId") 
        public int deletePerson(@WebParam(name="personId") int id);
        
        @WebMethod(operationName="updatePersonHealthProfile")
        @WebResult(name="hpId") 
        public int updatePersonHP(@WebParam(name="personId") int id, @WebParam(name="healthProfile") LifeStatus hp);
    }
    ```

* **PeopleImpl.java** 
    
    ```java
    package introsde.document.ws;
    
    import introsde.document.model.LifeStatus;
    import introsde.document.model.Person;
    
    import java.util.List;
    
    import javax.jws.WebService;
    
    //Service Implementation
    
    @WebService(endpointInterface = "introsde.document.ws.People",
        serviceName="PeopleService")
    public class PeopleImpl implements People {
    
        @Override
        public Person readPerson(int id) {
            System.out.println("---> Reading Person by id = "+id);
            Person p = Person.getPersonById(id);
            if (p!=null) {
                System.out.println("---> Found Person by id = "+id+" => "+p.getName());
            } else {
                System.out.println("---> Didn't find any Person with  id = "+id);
            }
            return p;
        }
    
        @Override
        public List<Person> getPeople() {
            return Person.getAll();
        }
    
        @Override
        public int addPerson(Person person) {
            Person.savePerson(person);
            return person.getIdPerson();
        }
    
        @Override
        public int updatePerson(Person person) {
            Person.updatePerson(person);
            return person.getIdPerson();
        }
    }
    ```

* **PeoplePublisher.java**

    ```java
    package introsde.document.endpoint;
    import introsde.document.ws.PeopleImpl;
    
    import javax.xml.ws.Endpoint;
    
    public class PeoplePublisher {
        public static String SERVER_URL = "http://localhost";
        public static String PORT = "6902";
        public static String BASE_URL = "/ws/people";
        
        public static String getEndpointURL() {
            return SERVER_URL+":"+PORT+BASE_URL;
        }
     
        public static void main(String[] args) {
            String endpointUrl = getEndpointURL();
            System.out.println("Starting People Service...");
            System.out.println("--> Published at = "+endpointUrl);
            Endpoint.publish(endpointUrl, new PeopleImpl());
        }
    }
    ```

### JAX-WS + JPA - The Web Services - Exercise 

* As you might have noticed, there are two methods defined in the interface but not implementated in the implementation class. **Implement them**.  



### JAX-WS + JPA - The Client

* The Client is also similar to the example from Lab 08
* **PeopleClient.java**

    ```java
    package introsde.document.client;
     
    import introsde.document.model.Person;
    import introsde.document.ws.People;
    
    import java.net.URL;
    import java.util.List;
    
    import javax.xml.namespace.QName;
    import javax.xml.ws.Service;
     
    public class PeopleClient{
        public static void main(String[] args) throws Exception {
            URL url = new URL("http://localhost:6902/ws/people?wsdl");
            //1st argument service URI, refer to wsdl document above
            //2nd argument is service name, refer to wsdl document above
            QName qname = new QName("http://ws/", "PeopleService");
            Service service = Service.create(url, qname);
            
            People people = service.getPort(People.class);
            Person p = people.readPerson(1);
            List<Person> pList = people.getPeople();
            System.out.println("Result ==> "+p);
            System.out.println("Result ==> "+pList);
            System.out.println("First Person in the list ==> "+pList.get(0).getName());
        }
    }
    ```

* Now we can try run the publisher and then client to see if it works. 

### JAX-WS + JPA: Generating Stubs for the Client

* How can we create the client if we do not have the model classes?
* Create another **Dynamic Web Project** with the name sdelab09-client, adding both **ivy.xml** and **build.xml** 
* From the command line, inside of the src folder, run the following command

    ```sh
    wsimport -keep http://localhost:6902/ws/people?wsdl
    ```
    
* Refresh the project 
* Use generated sources to create the client class in the following way

    ```java
    
    package client;
    
    import java.util.List;
    
    import introsde.document.ws.PeopleService;
    import introsde.document.ws.People;
    import introsde.document.ws.Person;
     
    public class PeopleClient{
        public static void main(String[] args) throws Exception {
            PeopleService service = new PeopleService();
            People people = service.getPeopleImplPort();
            Person p = people.readPerson(1);
            List<Person> pList = people.getPeopleList();
            System.out.println("Result ==> "+p);
            System.out.println("Result ==> "+pList);
            System.out.println("First Person in the list ==> "+pList.get(0).getName());
        }
    }
    ```
    
### JAX-WS + JPA: Generating Stubs for the Client - Exercise 

* Using postman and the request examples from the last session, try to send requests via HTTP
* Complete the client above implemented the call to all the other methods in the class

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