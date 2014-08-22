# LAB04: Mapping XML to/from Objects

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-4 "Permalink to LAB04: Mapping XML to/from Objects")**

To facilitate the lives of programmers around the world (at least for some), a long time ago we humans created object-oriented programming. But how do we go from objects to serialized representations of them in XML or JSON? And viceversa?. In this session, we will be mapping XML/JSON from and to Objects. What we covered up to here is also the base-ground we need before we start implementing actual services.  

## Slides &amp; Code

Links: [PPT slides][1] | [PDF slides][2] | [Source code][3]

## Guiding Notes

For the purpose of mapping XML (and JSON) to and from Objects (i.e., serializing/deserializing), we will explore three main technologies: Java Annotations (wich will come handy for many other things), JAXB, and (briefly) Dozer.  

### Java Annotations and JAXB brief overview (20 min)

* Annotations provide data about a program that is not part of the program itself.
* They have no direct effect on the operation of the code they annotate.
* Annotations can be applied to a program's  declarations of classes, fields, methods, and other program  elements.
* They uses include: 
    * **Information for the compiler:** to detect errors or suppress warnings.
    * **Compile-time and deployment-time processing:** software tools can process annotation information to generate code, XML files, and so forth.
    * **Runtime processing:** some annotations are available to be examined at runtime
* **JAXB** stands for **J**ava **A**rchitecture for **X**ML **B**inding. Is a Java standard that defines how Java objects are converted **from** and **to** XML. 
* As opposed to XPATH, it allows us to map XML to Java Classes, allowing our Java program to operate only on plain old java objects (not a document tree)
* Using java annotations, JAXB libraries can
    * **marshal** (i.e., convert) Java objects into XML 
    * **un-marshal** XML back into Java objects
* JAXB also includes a compiler that generates Java Classes from an XML Schema
    
### JAXB Examples: before we start (10 min)

* To use JAXB, you will need to have it in your classpath (or at least in the build path of your project).
* To solve this, we will use [**Apache Ivy**][5], a lightweight **dependency manager** that easily integrates with ant scripts (whenever possible, we will use Ivy to get libraries we need)
* Learning ivy is beyond the scope of the lab, but it is actually very simple. You will find a simple way to use it in this session's "build.xml" file.
* To use IVY, you need to (1) **copy PART 1** of the build script (see comments in build.xml) to your future build scripts; and (2) have an **ivy.xml** file in your project home (same folder where the build.xml is) where you will specify the dependencies. 
* You can use the [Maven Repository][7] website to search for libraries and to get the proper ivy dependency declarations. Below is an example from this session ivy.xml, which will bring **JAXB** API and **XJC compiler** to this session's example

    ```
    <dependency org="javax.xml.bind" name="jaxb-api" rev="2.2.11"/>
    <dependency org="com.sun.xml.bind" name="jaxb-xjc" rev="2.2.7"/>
    ```

* **Optionally:** 
    
    * If you want to have ivy already installed in your computer (so that you don't need PART 1 of this session build.xml in your future scripts) you can download it from [here][5],  unpack it wherever you want and then copy the ivy jar file into your ant lib directory, ANT_HOME/lib.
      
    ```sh
    cp ivy.jar /opt/apache-ant-1.9.2/lib/
    ```
    
    * Moreover, in the [installation guide for Eclipse][8] we have included the installation of [Apache IvyDE][6] plugin, which uses its own ivy installation to manage dependencies in eclipse projects (we will see this later when we create a project for this session) 
    * Similarly, if you want to have JAXB in your system (including xjc binary), you can download it from [here][4], unpack the it somewhere and then add its "bin" folder to your system PATH. 


### JAXB Examples: first run (10 min)
* 

* Remember the path to the JAXB home folder (i.e., /opt/jaxb-ri-2.2.6, C:\Program Files\jaxb-ri-2.2.6)
* Open Eclipse and create a project with the location at the **lab04** folder
* There should be an ANT script **build.xml** and another file named **ivy.xml**. 
Since from now one our java programs will require us to use different libraries, we will  
* 
 installed the  from Eclipse market place and also download ivy from [here][5] and

  
* Check the example on the slides to get a quick ideas of how to annotate classes with JAXB
* Open and explore the new HealthProfileReader example to get acquainted with JAXB annotations
    * Explore **Example/src/model/Person.java** 
    * Explore **Example/src/model/HealthProfile.java**
    * Explore **Example/src/dao/PeopleStore.java** (dao stands for "data access object", a typical data accessing pattern) 
    * Explore and run **Example/src/HealthProfileWriter.java**  
* Now, as a first exercise, let's try writing a **HealthProfileReader** that will use the "people.xml" generated by the reader.

### Generating classes from XML Schemas

* JAXB comes with an XML Schema binding compilation tool. 

```sh
    ant generate
```

* Explore the classes under the newly created "generated folder"
* Now, Marshal these classes into an XML

```sh
    ant execute.JAXBUnMarshaller
```

* Explore catalog.xml
* UnMarshal them into Java objects

```sh
    ant execute.JAXBUnMarshaller
```

---

## Exercise 3

* What should you change in [Example7-JAXB](https://github.com/cdparra/introsde2013/tree/master/Example7-JAXB) to add an **element year** within each article of a journal? 

---

## Domain Objects vs Transfer Objects

* **You have a domain model** (i.e., the model that is mapped to your database) 

```java
public class PersonDB {  
    private String firstName;
    private String lastName;
    private String address;
    private String dbID;
```

* **What if your client is waiting this, and it does not care about ids?** 

```xml
<person>
    <fName>Cristhian</fName>
    <lName>Parra</lName>
    <address>Povo Trento</address>
</person>
```

* In other words, we want to keep domain objects separate from the logics that manage the transformation into XML/JSON representations of the resources in our model
* To do se, we need **Transfer Objects** in the middle

---

## Dozer basics

* Dozer is a Java Bean to Java Bean mapper that recursively copies data from one object to another
* Dozer supports mapping between attribute names and between types.
* Standard conversions are provided automatically
* You are allowed to specify custom conversions via XML
* With Dozer, your internal domain objects are not exposed to external presentation layers or to external consumers.
* Dozer maps your domain objects to external APIs calls and vice-versa.
* Dozer can works both with XML and JSON 

---

## Dozer Installation

* Download Dozer and extract the archive: https://github.com/DozerMapper/dozer/archive/v5.4.0.zip
* Add ${dozer.home}/dist/dozer.jar to your classpath.
* Add required thirdparty runtime jars to your classpath http://dozer.sourceforge.net/dependencies.html

---

## Dozer Example (1)

* Open [Example8-Dozer](https://github.com/cdparra/introsde2013/tree/master/Example8-Dozer)
* The basic idea is that you have two set of classes in different packages:
    * **[entity classes](https://github.com/cdparra/introsde2013/tree/master/Example8-Dozer/src/dozerproject/entity)**: here we put pure domain objects
    * **[transfer classes](https://github.com/cdparra/introsde2013/tree/master/Example8-Dozer/src/dozerproject/entity)**: here we put objects as they are going to be mapped to the presentation layer (xml, json)
* Dozer maps domain objects into jaxb objects, that can later be marshalled to xml


## Dozer Example (2)
```java
package dozerproject.entity;
public class PersonDB {
    private String firstName;
    private String lastName;
    private String address;
    private String dbID;
```

```java
package dozerproject.transfer;
@XmlRootElement(name="person")
public class PersonUI {
    private String fName;
    private String lName;
    private String address;
    // getters and setters
```

---

## Dozer Example (3)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<mappings xmlns="http://dozer.sourceforge.net"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://dozer.sourceforge.net
          http://dozer.sourceforge.net/schema/beanmapping.xsd">          
  <mapping> 
    <class-a>dozerproject.PersonDB</class-a>
    <class-b>lsde.lab4.presentation.PersonUI</class-b>   
    <field>
      <a>firstName</a>
      <b>fName</b>
    </field>
    <field>
      <a>lastName</a>
      <b>lName</b>
    </field>
  </mapping>  
</mappings>
```

---

## Dozer Example (4)

```java
    public static void main (String argus[]){        
        DozerMapper mapper = new DozerMapper();
        PersonDB pdb = getPersonFromDB(); 
        // load mapping files
        List myMappingFiles = new ArrayList();
        myMappingFiles.add("File:./dozerMappings.xml");
        // prepare DozerMapper
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(myMappingFiles);
        // do the mapping
        PersonUI destObject = (PersonUI) mapper.map(sourceObject, PersonUI.class);
        // serialize the mapped object to the final representation (e.g., xml)
        String xmlFile = "person.xml";
        File xmlDocument = newFile(xmlFile);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonUI.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
            marshaller.marshal(destObject, new FileOutputStream(xmlDocument));
        } catch (IOException e) {
         
        }
```



## For next session

* Install Eclipse: http://www.eclipse.org/downloads/packages/eclipse-standard-431/keplersr1
* Install Maven (newly added requirement): http://maven.apache.org/download.cgi
* Prepare yourselves for a long session: we will go till 7pm. 
* Stay tunned to the list, will send a list of plugins for eclipse to add later in the week. 


---

## References:

* XML Schemas
    * http://www.w3schools.com/schema/default.asp
    * http://www.xfront.com/files/xml-schema.html
    * Validators: 
        * http://tools.decisionsoft.com/schemaValidate/
        * http://www.utilities-online.info/xsdvalidation/#.Ul0rkGRvj40
* JAXB
* Dozer 

## Additional notes

### Other suggested resources


[1]: https://drive.google.com/open?id=0B7ShzcEnCJFNSGEzdWltNUtIa3M
[2]: https://drive.google.com/file/d/0B7ShzcEnCJFNTTUwcy1QcGZMRjg/edit?usp=sharing
[3]: https://github.com/cdparra/introsde/tree/master/lab04/Examples
[4]: https://jaxb.java.net/2.2.7
[5]: http://ant.apache.org/ivy/download.cgi
[6]: http://ant.apache.org/ivy/ivyde/ 
[7]: http://maven-repository.com/
[8]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-sessions-starting-up