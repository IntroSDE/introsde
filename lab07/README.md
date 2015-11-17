# LAB07: Reading and writing from Databases & JPA (Java Persistence API)

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-7 "Permalink to LAB07: Reading and writing from Databases & JPA (Java Persistence API)")**

To close the loop of our service design, we will need to store (i.e., persist) data in real databases. Nowadays, there are two major flavours of databases: **relational** and **non-relational** (also known as **NoSQL**). In this module, we will add database access to our RESTful services using the Java Persistence API (JPA). We will do so using SQLite as an exemple of relational database. 

## Code

Links: [Source code][3]

## Guiding Notes

### Adding persistence: setting up the environment 

* Download and Install [SQLite][4]
* Download [SQLite Browswer][5]

### JPA Overview 

* JPA stands for **Java Persistence API**
* JPA is a **Java specification** for **ORM** (another is [JDO][8]). 
 * ORM stands for **Object-relational mapping:** the process of mapping objects to relational tables (and vice versa).
 * ORM allow developers to **work directly with objects** rather then with SQL statements. 
 * The JPA implementation is typically called **persistence provider**
 * Some implementations are: [Hibernate][9],[EclipseLink][10] and [Apache OpenJPA][11]
* Mapping between Java objects and database tables is defined via **persistence metadata**. 
* JPA providers use persistence metadata to perform the correct database operations.
* Persistence metadata is tipically specified via **annotations in the Java class**. 
* Alternatively the metadata can be defined in XML files or using a combination of both annotations and xml configuration files. An XML configuration overwrites the annotations (see [this example][12])

### JPA Tutorial: Simple JPA project - Setup

* Setup the Project:
 * In your local workspace, create a new Eclipse **Web Dynamic Project** named "sdelab07-local"
 * After creating it, open your Project properties and to to **Project Facets** to enable **JPA**
 * Using the **ivy.xml** provided in this module's code (lab07/ivy.xml), make a copy in your local project and then right click on it, choose **Add Ivy Library** and configure it to retrieve jars into the **WebContent/WEB-INF/lib** (as we saw in the last module of the laboratory)
* Notes about the project:
 * If you do not use eclipse, all you need is the libraries we have defined in the ivy.xml, which include jpa persistence api, EclipseLink, jersey, sqlite drivers/connectors, among others (see the ivy.xml for details)
 * These libraries should be placed on the classpath of the project. We suggest the classpath should point to **WebContent/WEB-INF/lib**  
* In your source folder (e.g., src) create a folder named **"META-INF"** and add a file named **"persistence.xml"** inside of it. 
* Open the **persistence.xml** and copy the following:

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <persistence version="2.1" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
        <persistence-unit name="introsde-jpa">
            <properties>
                <!-- the jdbc driver we will use to connect to the database 
                     change it depending of what database you use -->
                <property name="javax.persistence.jdbc.driver" value="org.sqlite.JDBC" />
                <!-- the last part of the url is the path to the sqlite file of the db, in this case it should be
                     on the root folder of the project -->
                <property name="javax.persistence.jdbc.url" value="jdbc:sqlite:lifecoach.sqlite" />
                <!-- set it to ALL to see all the JPA related debugging information --> 
                <property name="eclipselink.logging.level" value="INFO" />
                <!-- we will use this to automatically add elements to our database if we modify the Java model -->
                <property name="eclipselink.ddl-generation" value="create-or-extend-tables" />
                <!-- it means that automatic changes will be directly applied to the database
                     this is not reccommended, but we will use it as example only -->  
                <property name="eclipselink.ddl-generation.output-mode" value="database"/>
            </properties>
        </persistence-unit>
    </persistence>
    ```

* Mimicking the last module, create the following packages in your project: 
 * **introsde.rest.ehealth** - you can already copy both **App.java** and **MyApplicationConfig.java** from the previous module
 * **introsde.rest.ehealth.dao** - no need to copy PersonDao.java
 * **introsde.rest.ehealth.resources** - do not copy the resources from previous moduel yet
 * **introsde.rest.ehealth.model** - do not copy the original models, we will create a new one for this module

### JPA Tutorial: The DAO

* On the **dao  package**, we will craete the class that will connect our model to the database
* Create a class call **LifeCoachDao** and copy the following on it: 

    ```java
    package introsde.rest.ehealth.dao;
    import java.util.List;
    import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.EntityTransaction;
    import javax.persistence.Persistence;
    
    public enum LifeCoachDao {
        instance;
        private EntityManagerFactory emf;
        
        private LifeCoachDao() {
            if (emf!=null) {
                emf.close();
            }
            emf = Persistence.createEntityManagerFactory("introsde-jpa");
        }
        
        public EntityManager createEntityManager() {
            try {
                return emf.createEntityManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;    
        }
    
        public void closeConnections(EntityManager em) {
            em.close();
        }
    
        public EntityTransaction getTransaction(EntityManager em) {
            return em.getTransaction();
        }
        
        public EntityManagerFactory getEntityManagerFactory() {
            return emf;
        }  
    }
    ```

* Notice that, again, we are using a **Singleton** Design pattern. The **LifeCoachDao** is a singleton java instance that contains an **EntityManagerFactory**, which is configured by the persistence unit **"introsde-jpa"**
* This class will be used to create and **Entity Manager** whenever we need to execute an operation in the Database. 
* The entity manager provides the operations from and to the database, 
 * e.g. find objects, persists them, remove objects from the database, etc. 
* In JavaEE applications (i.e., applications that make use of the whole Java EE platform and are deployed in Java EE application containers), the entity manager is automatically inserted in the web application (via the **EJB** libraries that allow automatic class injection). Outside JavaEE you need to manage the entity manager yourself.
* To save objects in the database, the Entity Manager provides the method **persist()**
* To synchronize objects again with the database a Entity Manager provides the **merge()** method.
* If the Entity Manager is closed (via **close()**) then the managed entities are in a detached state. 
* Whenever we get an entity from the Database, or when we persist, merge or refresh it, that entity is said to be **attached** to the entity manager. 
* And whenever we remove, serialize or clone an entity, this is **detached** from the entity manager. 

### JPA Tutorial: Adding Models

* The **lifecoaach.sqlite** database presents an evolved data model. Open it in *Sqlite Browser* and explore the schema. You can also take a look to the physical ER model as presented by **lab07/lifecoach.png** 
* Now that we have an idea of our data model, according to the tables in the database and their relationships, the next step is to create java classes that will represent this data model in our system. **This is what we call the MODEL**
* Let's start with the Person. In the package **introsde.model** and add the first model for the table **Person**

    ```java
    import java.io.Serializable;
    import javax.persistence.*;
    @Entity  // indicates that this class is an entity to persist in DB
    @Table(name="Person") // to whate table must be persisted
    public class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        @Id // defines this attributed as the one that identifies the entity
        @GeneratedValue(strategy=GenerationType.AUTO) 
        @Column(name="idPerson") // maps the following attribute to a column
        private int idPerson;
        @Column(name="lastname")
        private String lastname;
        @Column(name="name")
        private String name;
        @Column(name="username")
        private String username;
        @Temporal(TemporalType.DATE) // defines the precision of the date attribute
        @Column(name="birthdate")
        private Date birthdate; 
        @Column(name="email")
        private String email;
        // add below all the getters and setters of all the private attributes
    }
    ```

* Classes that will be persisted in a database must be annotated with **@Entity** annotation. 
* By default, the table name corresponds to the class name. You can change this with the addition to the annotation **@Table(name="NEWTABLENAME")**.
* An entity represents a *table* in the database. Instances of the class will represents its *rows*.
* Fields of the Entity will be saved in the database and JPA can use either instance variables (fields) or the corresponding getters and setters to access the fields. **It is not allowed to mix both methods**. 
* By default each field is mapped to a column with the name of the field. You can change the default name via **@Column (name="newColumnName").**
* The following annotations can be used in fields:
 * **@Id:** Identifies the unique ID of the database entry
 * **@GeneratedValue:** Together with ID defines that this value is generated automatically.
 * **@Transient** Field will not be saved in database

### JPA Tutorial - Exercise: Creating JPA Models 
* **Exercise 07.01:** Create the model that represents the the **LifeStatus** table in the Lifecoach database. 
* **How should this model be connected with the Person?**

### JPA Tutorial: Relationships sidenote 

* Person contains one or more LifeStatuses, which means we need to create a **@OneToMany** connection from the Person model to the LifeStatus model. If you haven't done so, add the following attribute to Person:

    ```java
    // mappedBy must be equal to the name of the attribute in LifeStatus that maps this relation
    @OneToMany(mappedBy="person",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<LifeStatus> lifeStatus;
    ```java

* Then you can use XmlElementWrapper to define the name of the node in which the list of LifeStatus will appear once it gets serialized to XML or JSON

    ```java
    @XmlElementWrapper(name = "Measurements")
    public List<LifeStatus> getLifeStatus() {
        return lifeStatus;
    }
    ```

* Similarly, if we want to have a Person reference inside LifeStatus, we need to add the **@ManyToONe** annotation. Add the following to LifeStatus if you haven't done so

    ```java
    @ManyToOne
    @JoinColumn(name="idPerson",referencedColumnName="idPerson")
    private Person person;
    ```

* And finally, some serialization libraries (like jackson or jaxb) will try to keep serializing all nested objects, potentially leading to an infinite loop. To avoid this, we will tell the marshaller that the Person inside LifeStatus must be ignored in conversion, using the JAXB annotation **@XMLTransient**

    ```java  
    @XmlTransient
    public Person getPerson() {
        return person;
    }```

### JPA Tutorial: SQLite sequences sidenote

* Although JPA makes it very easy to implement the model only once, and then use the database provider we wish, some databases have peculiarities that must be addressed. 
* **SQLite**, for example, implements auto increment ids through **named sequences** that are stored in a special table named **"sqlite_sequence"**
* For this reason, you will need to use the following **@GeneratedValue** annotation for ids in the model (replacing person for the proper name of the model in each case)

    ```java
    @GeneratedValue(generator="sqlite_person")
    @TableGenerator(name="sqlite_person", table="sqlite_sequence",
        pkColumnName="name", valueColumnName="seq",
        pkColumnValue="Person")
    @Column(name="idPerson")
    ```

* Add the annotation to you Person and LifeStatus models
* We will get back to relationships later on this module

### JPA Tutorial: Querying the Database

* Now that we have an object oriented map of our data model, **how and where do we set queries?**
* There are many design patterns about **where** to put data queries. We will follow the most traditional one and place them in the model classes. 
* Add the following methods to the Person model

    ```java
        public static Person getPersonById(int personId) {
            EntityManager em = LifeCoachDao.instance.createEntityManager();
            Person p = em.find(Person.class, personId);
            LifeCoachDao.instance.closeConnections(em);
            return p;
        }
        
        public static List<Person> getAll() {
            EntityManager em = LifeCoachDao.instance.createEntityManager();
            List<Person> list = em.createNamedQuery("Person.findAll", Person.class)
                .getResultList();
            LifeCoachDao.instance.closeConnections(em);
            return list;
        }
        
        public static Person savePerson(Person p) {
            EntityManager em = LifeCoachDao.instance.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(p);
            tx.commit();
            LifeCoachDao.instance.closeConnections(em);
            return p;
        } 
        
        public static Person updatePerson(Person p) {
            EntityManager em = LifeCoachDao.instance.createEntityManager(); 
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            p=em.merge(p);
            tx.commit();
            LifeCoachDao.instance.closeConnections(em);
            return p;
        }
        
        public static void removePerson(Person p) {
            EntityManager em = LifeCoachDao.instance.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            p=em.merge(p);
            em.remove(p);
            tx.commit();
            LifeCoachDao.instance.closeConnections(em);
        }
    }
    ```

* Notice that in **getAll()**, we have useda **namedQuery** with the name **Person.findAll**.
* Name queries must be defined via annotations. Adde the following on top of the Person class. 

    ```java
    @NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
    ```
    
* Notice how the query language resembles SQL, but uses objects from our instead of the tables int he database
* **Exercise 07.02:** Add similar methods to LifeStatus
   
## JPA Tutorial: Testing our models

* Add JUnit support to your project 
    * In Eclipse: *Project -> Build Path -> Configure Build Path -> Add Library -> JUnit*
    * Without IDE: download the jars from the [JUnit Website](http://junit.org/)  
* Create a package named **introsde.rest.ehealth.test.model**
* We use JUnit here to introduce a new concept of designing and engineering services: **unit tests**
* Although we will not explore them in detail, it is important you get acquainted and start implementing tests for all the models you create
* Ideally, every model and every resource on your service API should have a corresponding **Test Unit**. 
* It is a **good practice** to follow a somewhat **test driven** development by which you make sure all your tests pass before you deploy anything of your service API
* Create the following junit test class for testing the Person Model

    ```java
    package introsde.rest.ehealth.test.model;
    import static org.junit.Assert.*;
    import introsde.rest.ehealth.model.Person;
    import java.util.Calendar;
    import java.util.List;
    import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.EntityTransaction;
    import javax.persistence.Persistence;   
    import org.junit.AfterClass;
    import org.junit.Before;
    import org.junit.BeforeClass;
    import org.junit.Test;
    
    public class PersonTest {
    
        @Test
        public void readPersonListTest() {
            System.out.println("--> TEST: readPersonList");
            List<Person> list = em.createNamedQuery("Person.findAll", Person.class)
                    .getResultList();
            for (Person person : list) {
                System.out.println("--> Person = "+person.toString());
            }
            assertTrue(list.size()>0);
        }
    
        @Test
        public void addPersonWithDaoTest() {
            System.out.println("--> TEST: addPersonWithDao");
    
            List<Person> list = Person.getAll();
            int personOriginalCount = list.size();
            
            Person p = new Person();
            p.setName("Pinco");
            p.setLastname("Pallino");
            Calendar c = Calendar.getInstance();
            c.set(1984, 6, 21);
            p.setBirthdate(c.getTime());
    
            System.out.println("--> TEST: addPersonWithDao ==> persisting person");
            Person.savePerson(p);
            assertNotNull("Id should not be null", p.getIdPerson());
    
            System.out.println("--> TEST: addPersonWithDao ==> getting the list");
            list = Person.getAll();
            assertEquals("Table has two entities", personOriginalCount+1, list.size());
            
            Person newPerson = Person.getPersonById(p.getIdPerson());
    
            System.out.println("--> TEST: addPersonWithDao ==> removing new person");
            Person.removePerson(newPerson);
            list = Person.getAll();
            assertEquals("Table has two entities", personOriginalCount, list.size());
        }
    
        @BeforeClass
        public static void beforeClass() {
            System.out.println("Testing JPA on lifecoach database using 'introsde-jpa' persistence unit");
            emf = Persistence.createEntityManagerFactory("introsde-jpa");
            em = emf.createEntityManager();
        }
    
        @AfterClass
        public static void afterClass() {
            em.close();
            emf.close();
        }
    
        @Before
        public void before() {
            tx = em.getTransaction();
        }
    
        private static EntityManagerFactory emf;
        private static EntityManager em;
        private EntityTransaction tx;
    
    }
    ```

* And create the following junit test class for testing the LifeStatus Model

    ```java
    package introsde.rest.ehealth.test.model;
    
    import static org.junit.Assert.*;
    import introsde.rest.ehealth.model.LifeStatus;
    import introsde.rest.ehealth.model.MeasureDefinition;
    import introsde.rest.ehealth.model.Person;
    
    import java.util.List;
    
    import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.EntityTransaction;
    import javax.persistence.Persistence;
    
    import org.junit.AfterClass;
    import org.junit.Before;
    import org.junit.BeforeClass;
    import org.junit.Test;
    
    public class LifeStatusTest {
    
        @Test
        public void readAllLifeStatusListDaoTest() {
            System.out.println("--> TEST: readAllLifeStatusWithDao");
            List<LifeStatus> mList = LifeStatus.getAll();
            assertTrue("LifeStatus not empty in DB", mList.size()>0);
        }
    
        @Test
        public void readLifeStatusInPersonTest() {
            System.out.println("--> TEST: readLifeStatusPersonRelationship");
            // setting weight for an existing person with existing measures
            Person person = Person.getPersonById(1);
            assertTrue("Person should have at least one measurement", person.getLifeStatus().size()>0);
            LifeStatus l = person.getLifeStatus().get(1);
            assertNotNull("LifeStatus measure was created", l.getIdMeasure());
        }
    
        @BeforeClass
        public static void beforeClass() {
            System.out.println("Testing JPA on lifecoach database using 'introsde-jpa' persistence unit");
            emf = Persistence.createEntityManagerFactory("introsde-jpa");
            em = emf.createEntityManager();
        }
    
        @AfterClass
        public static void afterClass() {
            em.close();
            emf.close();
        }
    
        @Before
        public void before() {
            tx = em.getTransaction();
        }
    
        private static EntityManagerFactory emf;
        private static EntityManager em;
        private EntityTransaction tx;
    }
    ```

* Now run you tests by right click the Test class -> Run As -> JUnit

### JPA Tutorial: Generating Entities (1)

* This part of the tutorial is **for eclipse only**
 * If not using eclipse, jump to the part of mapping relationships with JPA 
* Open data source view and add a new conection (Right Click on *Database connections -> New..*)
* **Add the correct SQLite Driver**
 * Click on the add button right of the "Drivers" select
 * Select "SQLite JDBC Driver"
 * If eclipse is unable to locat the jar of the driver, go to the tab *JAR List*, remove the current jar and add the SQLite JDBC driver you have downloaded  
* **Configure the connection**
 * Database = lifecoach
 * Database Location = PATH_TO_YOUR_LOCAL_LIFECOACH_SQLITE_FILE (the file browser might not allow you to select the file, so make sure you complete the path manually)
* Right click on your project and select **JPA tools --> Generate Entities from Tables**
* Select the connection, get the list of tables and select the tables that you want to use to generate entities (everything but Person and LifeStatus) 
* The second step for generating entities is to specify **Table Associations** between entities to map relationship between tables. Add a relationship between *LifeStatus* and *MeasureDefinition* 
* Specify the columns that define the association
* Specify the cardinality of the association
* Finally, you can choose whether which entities in the association should have a property referencing the other entity (it is not required that both have a property referencing the other entity)
* The next step to select some defaults properties for the mapping, like the default key generator. Since we are using SQLite and there is a special generator for this, let's choose "None" 
* The last step is to define how each property will be mapped (name of the attributes int the model classes, type of each attribute, etc.). Make sure all the primary keys have the mapping kind to "id".

### JPA Tutorial: Relationships 

* You should have now all the entity classes generated in the model package.
 * If you didn't use the generation wizard, copy the model classes from the the lab's source folder
 * In generated classes, you can delete **\"**
* On the LifeStatus model. Add the following if you haven't yet should have the following attribute. This is how we map a **@ManyToOne** relationship between LifeStatus and MeasureDefinition:

    ```java
        @ManyToOne
        @JoinColumn(name = "idMeasureDef", referencedColumnName = "idMeasureDef", insertable = true, updatable = true)
        private MeasureDefinition measureDefinition;
    ```

* JPA allows to define relationships between classes, e.g. it can be defined that a class is part of another class (containment).
* Classes can have one to one, one to many, many to one, and many to many relationships with other classes.
* A relationship can be bidirectional or unidirectional, e.g. in a bidirectional relationship both classes store a reference to each other while in an unidirectional case only one class has a reference to the other class.
* Within a bidirectional relationship you need to specify the owning side of this relationship in the other class with the attribute "mappedBy", e.g. **@ManyToMany(mappedBy="attributeOfTheOwningClass".**
* JPA Relationship annotations:

    ```java
    @OneToOne
    @OneToMany
    @ManyToOne
    @ManyToMany
    ```
---

## JPA Tutorial: Exercise

* **Exercise 07.03:** Control the code of the models and, 
 * add the relationships that are missing in all the models
 * for each class, add the correct @GeneratedValue annotations following the example of Person

* **Exercise 07.04:** Now that you have all the models of the database, add CRUD operations to all of them like the ones in Person (i.e., using transactions to persist, merge and remove entities in the database)

### JPA Tutorial + Jersey: Setup

* Now that we can connect to a Database, the next step is to use this model and connection in REST resources that are exposed using Jersey. 
* **With Eclipse:** if you did not create the project as a **Dynamic Web Project**, the first is to go into Project Properties -> Project Facets -> and **Enable Dynamic Web Module**
* **Without Eclipse:** create a WebContent folder in the root of the project with a subdirectory named WEB-INF and a web.xml as the one below.
* Jersey libraries are already defined in ivy.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app id="WebApp_ID" version="3.0" 
        xmlns="http://java.sun.com/xml/ns/javaee" 
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
                    http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">
    </web-app>
    ```

### JPA Tutorial + Jersey: Adding Resources

* In your **introsde.rest.ehealth.resources** package create the following two classes corresponding to the **PersonResourceCollection** and the **PersonResource** of the previous module, but adapted to use this module models

    ```java
    package introsde.rest.ehealth.resources;
    import introsde.rest.ehealth.model.Person;
    
    import java.io.IOException;
    import java.util.List;
    import javax.ejb.*;
    import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.PersistenceContext;
    import javax.persistence.PersistenceContextType;
    import javax.persistence.PersistenceUnit;
    import javax.servlet.http.HttpServletResponse;
    import javax.ws.rs.Consumes;
    import javax.ws.rs.FormParam;
    import javax.ws.rs.GET;
    import javax.ws.rs.POST;
    import javax.ws.rs.Path;
    import javax.ws.rs.PathParam;
    import javax.ws.rs.Produces;
    import javax.ws.rs.core.Context;
    import javax.ws.rs.core.MediaType;
    import javax.ws.rs.core.Request;
    import javax.ws.rs.core.UriInfo;
    
    @Stateless // will work only inside a Java EE application
    @LocalBean // will work only inside a Java EE application
    @Path("/person")
    public class PersonCollectionResource {
    
        // Allows to insert contextual objects into the class,
        // e.g. ServletContext, Request, Response, UriInfo
        @Context
        UriInfo uriInfo;
        @Context
        Request request;
    
        // will work only inside a Java EE application
        @PersistenceUnit(unitName="introsde-jpa")
        EntityManager entityManager;
        
        // will work only inside a Java EE application
        @PersistenceContext(unitName = "introsde-jpa",type=PersistenceContextType.TRANSACTION)
        private EntityManagerFactory entityManagerFactory;
    
        // Return the list of people to the user in the browser
        @GET
        @Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
        public List<Person> getPersonsBrowser() {
            System.out.println("Getting list of people...");
            List<Person> people = Person.getAll();
            return people;
        }
    
        // retuns the number of people
        // to get the total number of records
        @GET
        @Path("count")
        @Produces(MediaType.TEXT_PLAIN)
        public String getCount() {
            System.out.println("Getting count...");
            List<Person> people = Person.getAll();
            int count = people.size();
            return String.valueOf(count);
        }
    
        @POST
        @Produces(MediaType.APPLICATION_XML)
        @Consumes(MediaType.APPLICATION_XML)
        public Person newPerson(Person person) throws IOException {
            System.out.println("Creating new person...");            
            return Person.savePerson(person);
        }
        
        // Defines that the next path parameter after the base url is
        // treated as a parameter and passed to the PersonResources
        // Allows to type http://localhost:599/base_url/1
        // 1 will be treaded as parameter todo and passed to PersonResource
        @Path("{personId}")
        public PersonResource getPerson(@PathParam("personId") int id) {
            return new PersonResource(uriInfo, request, id);
        }
    }
    ```
    

    ```java
    package introsde.rest.ehealth.resources;
    
    import introsde.rest.ehealth.model.Person;
    
    import javax.ejb.LocalBean;
    import javax.ejb.Stateless;
    import javax.persistence.EntityManager;
    import javax.ws.rs.Consumes;
    import javax.ws.rs.DELETE;
    import javax.ws.rs.GET;
    import javax.ws.rs.PUT;
    import javax.ws.rs.Produces;
    import javax.ws.rs.core.Context;
    import javax.ws.rs.core.MediaType;
    import javax.ws.rs.core.Request;
    import javax.ws.rs.core.Response;
    import javax.ws.rs.core.UriInfo;
    
    
    @Stateless // only used if the the application is deployed in a Java EE container
    @LocalBean // only used if the the application is deployed in a Java EE container
    public class PersonResource {
        @Context
        UriInfo uriInfo;
        @Context
        Request request;
        int id;
    
        EntityManager entityManager; // only used if the application is deployed in a Java EE container
    
        public PersonResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
            this.uriInfo = uriInfo;
            this.request = request;
            this.id = id;
            this.entityManager = em;
        }
        
        public PersonResource(UriInfo uriInfo, Request request,int id) {
            this.uriInfo = uriInfo;
            this.request = request;
            this.id = id;
        }
    
        
        // Application integration
        @GET
        @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
        public Person getPerson() {
            Person person = this.getPersonById(id);
            if (person == null)
                throw new RuntimeException("Get: Person with " + id + " not found");
            return person;
        }
    
        // for the browser
        @GET
        @Produces(MediaType.TEXT_XML)
        public Person getPersonHTML() {
            Person person = this.getPersonById(id);
            if (person == null)
                throw new RuntimeException("Get: Person with " + id + " not found");
            System.out.println("Returning person... " + person.getIdPerson());
            return person;
        }
    
        @PUT
        @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
        public Response putPerson(Person person) {
            System.out.println("--> Updating Person... " +this.id);
            System.out.println("--> "+person.toString());
            Person.updatePerson(person);
            Response res;
            Person existing = getPersonById(this.id);
            
            if (existing == null) {
                res = Response.noContent().build();
            } else {
                res = Response.created(uriInfo.getAbsolutePath()).build();
                person.setIdPerson(this.id);
                Person.updatePerson(person);
            }
            return res;
        }
    
        @DELETE
        public void deletePerson() {
            Person c = getPersonById(id);
            if (c == null)
                throw new RuntimeException("Delete: Person with " + id
                        + " not found");
            Person.removePerson(c);
        }
        
        public Person getPersonById(int personId) {
            System.out.println("Reading person from DB with id: "+personId);
            
            // this will work within a Java EE container, where not DAO will be needed
            //Person person = entityManager.find(Person.class, personId); 
            
            Person person = Person.getPersonById(personId);
            System.out.println("Person: "+person.toString());
            return person;
        }
    }
    ```

### JPA Tutorial + Jersey: Remember JAXB? 

* We have already introduced JAXB annotations in our models, so this is only a revisiting
* **Examples:** 
 * **@XmlRootElement** on top of the Person model
 * **@XmlRootElement(name="Measure")** on top of the LifeStatus model
 * The person element on LifeStatus should not be serialized (to avoid infinites loops because lifestatus is serialized within Person), so add **@XmlTransient** on the getter of person in the LifeStatus class
 * To serialize each LifeStatus instance add the following to the LifeStatus getter on Person 

    ```java
    @XmlElementWrapper(name = "Measurements")
    @XmlElement(name = "Measure")
    ```

### JPA Tutorial + Jersey: Try it! 

* Run the App server and test the services using the postman client
* Now try the added resources with the following requests: 

    ```
    GET /sdelab/person 
    Accept: application/json
    
    GET /sdelab/person/1 
    Accept: application/json
    
    POST /sdelab/person HTTP/1.1
    Accept: application/json
    Content-Type: application/json
    {
     "firstname":"Pinco",
     "lastname":"Pallino",
     "birthdate":"1978-9-2"
    }
    
    
    POST /sdelab/person HTTP/1.1
    Accept: application/json
    Content-Type: application/xml
    <person>
     <firstname>Cristhian</firstname>
     <lastname>Doe</lastname>
     <birthdate>1957-1-11</birthdate>
    </person>
    
    POST /sdelab/person HTTP/1.1
    Accept: application/json
    Content-Type: application/xml
    <person>
    <firstname>Cristhian</firstname>
    <lastname>Parra</lastname>
    <birthdate>1984-06-21</birthdate>
     <healthprofile>
      <weight>72</weight>
      <height>1.72</height>
     </healthprofile> 
    </person> 
    
    PUT /sdelab/person/6 HTTP/1.1
    Accept: application/json
    Content-Type: application/json
    {
     "firstname": "Cristhian Daniel",
     "lastname": "Parra",
     "birthdate": "1984-06-21",
     "healthprofile": {
        "weight": 72,
        "height": 1.70,
     }
    }
    ```

## Additional notes

### Other Resources

* [JUnit Tutorial][13]
* [JPA tutorial from where we took some of the explanations][14]
* Checkout also [Mashape][15] and signup with your GitHub account (we will try to use an API from there in the future sessions)
* To try a non-relational database, download [MongoDB][6] and install 
 * For windows, download the appropriate installer
 * For *nix systems, download the binaries and upack the compressed file somwhere in your PATH. 
 * For *nix systems you can also use the [packaged versions][7] through package managers
 * For how to use JPA+MongoDB, see [this tutorial][16]
 
    ```sh
    # Homebrew (Mac)
    brew install mongodb
    
    # Macports (Mac)
    sudo port install mongodb
    
    # apt-get (Ubuntu)
    sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10
    echo 'deb http://downloads-distro.mongodb.org/repo/ubuntu-upstart dist 10gen' | sudo tee /etc/apt/sources.list.d/mongodb.list
    sudo apt-get update
    sudo apt-get install mongodb-org
    ```
    
[1]: https://drive.google.com/open?id=0B7ShzcEnCJFNWENNN1RpYU9xeUk&authuser=0
[2]: https://drive.google.com/open?id=0B7ShzcEnCJFNQ2FfR21FUUk1Y1E&authuser=0
[3]: https://github.com/cdparra/introsde/tree/master/lab07/Examples
[4]: http://www.sqlite.org/download.html
[5]: http://sqlitebrowser.org/
[6]: http://www.mongodb.org/downloads
[7]: http://www.mongodb.org/downloads#packages
[8]: http://db.apache.org/jdo/ 
[9]: http://www.hibernate.org/ 
[10]: http://www.eclipse.org/eclipselink/downloads/ 
[11]: http://openjpa.apache.org/ 
[12]: http://java.dzone.com/articles/persisting-entity-classes
[13]: http://www.vogella.com/articles/JUnit/article.html
[14]: http://www.vogella.com/articles/JavaPersistenceAPI/article.html
[15]: http://ww.mashape.com
[16]: http://java.dzone.com/articles/nosql-jpa
