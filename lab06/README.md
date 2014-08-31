# LAB05: CRUD RESTful Services (2)

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-5 "Permalink to LAB06: CRUD RESTful Services (2)")**

In its first installment, we have explored the principles behind the REST architectural style and how to create a simple read-only RESTful web service. In this module, we will deepen our understanding by implement a set of CRUD RESTful services for our Health Profile example.  

## Slides &amp; Code

Links: [PPT slides][1] | [PDF slides][2] | [Source code][3]

## Guiding Notes

## Exercise 1: HTTP Clients

* Take a look at the [HTTP Restful Client example of lab6](https://github.com/cdparra/introsde2013/blob/master/lab6/Example1-SimpleRestful/src/introsde/simple/rest/client/Test.java)
* Create a client like the one of lab6, but in this case, it must communicate with a [WEBDIS](http://webd.is/) Pseudo-REST front-end to the [Redis](http://redis.io/) <key,value> store

---

## Exercise 1: WEBDIS Server details

The Pseudo-REST WEBIDS API (quiz: **why is Pseudo-REST and not entirely REST?**)

* BASEURL = http://test.lifeparticipation.org/webdis/ (please, be polite with this server)
* API Endpoints: 
    1. **Store a {key,value}:** GET /SET/key/value
    2. **Retrieve a {key,value}** GET /GET/key  
    3. **Store a {hashkey,{innerkey,value}}:** GET /HMSET/hashkey/innerkey1/value1/innerkey2/value2
    4. **Retrieve the value for {hashkey,innerkey}:** GET BASEURL/HGET/hashkey/key
    5. **Retrieve all the {innerkey,value} for hashkey:** GET /HGETALL/hashkey
* **Notes:** 
    * 3 and 4 can recieve more than one *{key,value}* 
    * The character "/" must be replaced by "%2f" in values and keys
    * The character "." must be replaced by "%2e" in values and keys
    * All [these commands](http://redis.io/commands) are actually supported

---

## Exercise 1: WEBDIS Server Examples

* Some examples to test (just click on the links)
    * http://test.lifeparticipation.org/webdis/SET/1/pinco pallino
    * http://test.lifeparticipation.org/webdis/GET/1
    * http://test.lifeparticipation.org/webdis/HMSET/pinco pallino/weight/78/height/1%2e67
    * http://test.lifeparticipation.org/webdis/HGETALL/pinco pallino
    * http://test.lifeparticipation.org/webdis/HMGET/pinco pallino/height/weight
* **Notes:**
    * Don't break the server, be polite with your requests :-)
    * be mindful that you will all be querying the same key,value database, so you might want to use different "keys"
    * Solution to the exercise is [here](https://github.com/cdparra/introsde2013/blob/master/lab7/solutions/WEBDISServer-Client/src/introsde/quasi/rest/client/MyClient.java)

---

## Lab Examples

* Start by creating a Web Dynamic Project (as in the last session) with the name **CRUD RESTful**
* Add three packages to your project: 
    * **introsde.crud.rest.dao**
    * **introsde.crud.rest.model**
    * **introsde.crud.rest.resources**

---

## Example 1: Standalone HTTP Server (1)

* For testing purposes, it is reccomended to use an standalone HTTP server instead of tomcat. 
* [This is an example](https://github.com/cdparra/introsde2013/blob/master/lab7/Example1-CRUDRestful/src/introsde/crud/rest/resources/StandaloneServer.java) that you can use as baseline

---

## Example 1: Standalone HTTP Server (2)

* Create this application on your **introsde.crud.rest.resources** package 
* Run it as an standard Java Application
 
```java
// the whole resource will be available at baseUrl/rest
@Path("/rest")
public class StandaloneServer
{
    public static void main(String[] args) throws IllegalArgumentException, IOException
    {
        String protocol = "http://"; // of course...
        String port = ":5900/"; // you can use any other as long as it is not in use
        String hostname = InetAddress.getLocalHost().getHostAddress();
        if (hostname.equals("127.0.0.1"))
        {
            hostname = "localhost";
        }
        String baseUrl = protocol + hostname + port;
        final HttpServer server = HttpServerFactory.create(baseUrl);
        server.start();
        System.out.println("Server starts on " + baseUrl + "\n [kill the process to exit]");
    }
}
```

---

## Example 2: Simple CRUD Jersey API (1)

* Check the structure of the [Example](https://github.com/cdparra/introsde2013/tree/master/lab7/Example1-CRUDRestful/src/introsde/crud/rest)
    * **DAO:** stands for *data access objects* and is where our data providers are, for this example, we use a [Singleton design pattern](http://www.vogella.com/articles/DesignPatternSingleton/article.html) to implement a mock of a in memory Database.
    * **Models:** the classes that define our data model. Notice the use of JAXB annotations to allow Jersey to automatically find the way of marshalling and unmarshalling objects to xml 
    * **Resources:** where our service endpoints are implemented. Notice that in [PeopleResource](https://github.com/cdparra/introsde2013/blob/master/lab7/Example1-CRUDRestful/src/introsde/crud/rest/resources/PeopleResource.java) we reference the [PersonResource](https://github.com/cdparra/introsde2013/blob/master/lab7/Example1-CRUDRestful/src/introsde/crud/rest/resources/PersonResource.java). People is a collection resource that aggregates PersonResources
* Add these classes to your project

---

## Example 2: Simple CRUD Jersey API (2)

**DAO:** for this simple example, our Data Provider is a single HashMap that associates string ids with person objects

```java
public enum PersonDao {
    instance;
    private Map<String, Person> contentProvider = new HashMap<String, Person>();
    private PersonDao() {
        Person pallino = new Person();
        Person pallo = new Person("Pinco","Pallo");
        HealthProfile hp = new HealthProfile(68.0,1.72);
        Person john = new Person("John","Doe",hp);
        pallino.setId("1");
        pallo.setId("2");
        john.setId("3");
        contentProvider.put("1", pallino);
        contentProvider.put("2", pallo);
        contentProvider.put("3", john);
    }
    public Map<String, Person> getModel() {
        return contentProvider;
    }
}
```

---

## Example 2: Simple CRUD Jersey API (3)

* **Models:** our model is composed by our typical Person/HealthProfile model (with the addition of basica JAXB Annotations)
* **Resources:** our resources allow reading the list of people, one person by id, the count of people in the database and the creation, update and delete of one person

---

## Example 2: Simple CRUD Jersey API (4)

* Since People wraps the PersonResource, we need to hava a way of passing the *Request* information to the PersonResource. 

```java
    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
```

---

## Example 2: Simple CRUD Jersey API (5)

**Reading People collection:**
```java
    // Return the list of people for applications
    @GET
    @Produces({ MediaType.APPLICATION_XML})
    public List<Person> getPersonListXML() {
        List<Person> people = new ArrayList<Person>();
        people.addAll(PersonDao.instance.getModel().values());
        return people;
    }
```

**Returning the number of People in our database**

```java
    @GET
    @Path("count") // corresponds to /person/count
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        System.out.println("Getting count...");
        int count = PersonDao.instance.getModel().size();
        return String.valueOf(count);
    }
```

---

## Example 2: Simple CRUD Jersey API (6)

**Accesing single elements in the people collection (i.e., person resources)**
```java
    // Return the list of people for applications
    @GET
    @Produces({ MediaType.APPLICATION_XML})
    public List<Person> getPersonListXML() {
        List<Person> people = new ArrayList<Person>();
        people.addAll(PersonDao.instance.getModel().values());
        return people;
    }
```

**Calling PersonResource Endpoints**

```java
    // Defines that the next path parameter after the base url is
    // treated as a parameter and passed to the PersonResources
    // Allows to type http://localhost:599/person/1
    // 1 will be treaded as parameter todo and passed to PersonResource
    @Path("{personId}")
    public PersonResource getPerson(@PathParam("personId") String id) {
        return new PersonResource(uriInfo, request, id);
    }
```

---
## Example 2: Simple Create of a Person (6)

**Creating the person (update and delete in the PersonResource)**
```java
    @Produces(MediaType.APPLICATION_XML) // will be called when content-type header set to xml
    @Consumes(MediaType.APPLICATION_XML)
    public Person newPerson(Person person) throws IOException {
        System.out.println("Creating new person...");
        int count = PersonDao.instance.getModel().size();
        String newId = count+1+"";
        person.setId(newId);
        PersonDao.instance.getModel().put(newId, person);
        return person;
    }
```

---

## Exercise 2: Extending the simple CRUD API

* Add a history attribute to the "HealthProfile" where a new value will be attached to the list of a measure every time this is updated
* Add a service to get the history of a measure
* **Where should these services go?**

---


## Additional notes



[1]: https://drive.google.com/open?id=0B7ShzcEnCJFNWENNN1RpYU9xeUk&authuser=0
[2]: https://drive.google.com/open?id=0B7ShzcEnCJFNQ2FfR21FUUk1Y1E&authuser=0
[3]: https://github.com/cdparra/introsde/tree/master/lab06/Examples
