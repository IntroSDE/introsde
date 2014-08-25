# LAB04: Mapping XML (and JSON) to (and from) Objects

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-4 "Permalink to LAB04: Mapping XML (and JSON) to (and from) Objects")**

To facilitate the lives of programmers around the world (at least for some), a long time ago we humans created object-oriented programming. But how do we go from objects to serialized representations of them in XML or JSON? And viceversa?. In this module, we will be mapping XML/JSON from and to Objects. What we covered up to here is also the base-ground we need before we start implementing actual services.  

## Slides &amp; Code

Links: [PPT slides][1] | [PDF slides][2] | [Source code][3]

## Guiding Notes

For the purpose of mapping XML (and JSON) to and from Objects (i.e., serializing/deserializing), we will explore three main technologies: Java Annotations (wich will come handy for many other things), JAXB, and (briefly) Dozer.  

### Java Annotations and JAXB brief overview (20 min)

* Java Annotations provide data about a program that is not part of the program itself.
* They have no direct effect on the operation of the code they annotate.
* Annotations can be applied to a program's  declarations of classes, fields, methods, and other program  elements.
* They uses include: 
    * **Information for the compiler:** to detect errors or suppress warnings.
    * **Compile-time and deployment-time processing:** software tools can process annotation information to generate code, XML files, and so forth.
    * **Runtime processing:** some annotations are available to be examined at runtime
* In this module, we will java annotations to specify how a class must be transformed from and to XML/JSON
* **JAXB** stands for *Java Architecture for XML Binding*. Is a Java standard that defines how Java objects are converted **from** and **to** XML. 
* As opposed to XPATH, it allows us to map XML to Java Classes, allowing our Java program to operate only on plain old java objects (not a document tree)
* Using java annotations, JAXB libraries can
    * **marshal** (i.e., convert) Java objects into XML 
    * **un-marshal** XML back into Java objects
* JAXB also includes a compiler that generates Java Classes from an XML Schema
    
### JAXB Examples: before starting (10 min)

* To use JAXB, you will need to have it in your classpath (or at least in the build path of your project).
* To solve this, we will use [**Apache Ivy**][5], a lightweight **dependency manager** that easily integrates with ant scripts (whenever possible, we will use Ivy to get libraries we need)
* Learning ivy is beyond the scope of the lab, but it is actually very simple. You will find a simple way to use it in this modules's "build.xml" file.
* To use IVY, you need to (1) **copy PART 1** of the build script (see comments in build.xml) to your future build scripts; and (2) have an **ivy.xml** file in your project home (same folder where the build.xml is) where you will specify the dependencies. 
* You can use the [Maven Repository][7] website to search for libraries and to get the proper ivy dependency declarations. Below is an example from this module's ivy.xml, which will bring **JAXB** API and **XJC compiler** to this module's example

    ```xml
    <dependency org="javax.xml.bind" name="jaxb-api" rev="2.2.11"/>
    <dependency org="com.sun.xml.bind" name="jaxb-xjc" rev="2.2.7"/>
    ```

* **Optionally:** 
    * If you want to have ivy already installed in your computer (so that you don't need PART 1 of this session build.xml in your future scripts) you can download it from [here][5],  unpack it wherever you want and then copy the ivy jar file into your ant lib directory, ANT_HOME/lib.
    * Moreover, in the [installation guide for Eclipse][8] we have included the installation of [Apache IvyDE][6] plugin, which uses its own ivy installation to manage dependencies in eclipse projects (we will see this later when we create a project for this module) 
    * Similarly, if you want to have JAXB in your system (including xjc binary), you can download it from [here][4], unpack the it somewhere and then add its "bin" folder to your system PATH. 


### JAXB Examples: first run (10 min)

* Open your terminal window in your local **lab04** folder and run the following: 

    ```bash
    ant compile
    ant execute.HPWriter
    ```
* You will see that before executing the "compile" target, ivy.jar will be downloaded into an "ivy" folder, and libraries that are specified in "ivy.xml" will be downloaded to "lib" 
* Moreover, the target **generate** would have created the folder **Examples/src/bookstore/generated** with four classes in it (we will get back to this later)
* And finally, as a result from **execute.HPWriter**, there is a new **people.xml** in your lab04 folder. 
* So, **what has happened here?**  

### JAXB Examples: source code (20 minutes)

* Open Eclipse and create a project with the location at the **lab04** folder
* Open and explore the new HealthProfileReader example to get acquainted with JAXB annotations
    * Explore **Example/src/model/Person.java** 
    * Explore **Example/src/model/HealthProfile.java**
    * Explore **Example/src/dao/PeopleStore.java** (dao stands for "data access object", a typical data accessing pattern) 
    * Explore and run **Example/src/HealthProfileWriter.java**  
* **Exercise 04.01:** let's try writing ourselves the **HealthProfileReader** that will use the "people.xml" generated by the writer.

### JAXB Examples: generating classes from XML Schemas (20 minutes)

* **Where do those generated classes we saw earlier come from?**
* JAXB comes with an XML Schema binding compilation tool (which was invoked by the target **generate** in our build script by using a "taskdef" definition). 
* Explore the classes under the newly created "generated folder". They are the result of generating classes based on the XML schema defined in catalog.xsd
* Now, Marshal these classes into an XML

    ```sh
        ant execute.JAXBUnMarshaller
    ```

* Explore catalog.xml
* UnMarshal them into Java objects

    ```sh
        ant execute.JAXBUnMarshaller    
    ```

* **Exercise 04.02:** What should you change in in the bookstore example to add an **element year** within each article of a journal?. 

### Domain Objects vs Transfer Objects (10 min)

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

* In other words, we want to keep domain objects separated from the logic that manages the transformation into XML/JSON representations of the resources in our model
* A design pattern to achieve this is to use **Transfer Objects** in the middle (i.e., the objects that are actually exchanged between client and server) 

### The Dozer Library (5 min) 
* **Dozer** is a *Java Bean* to *Java Bean* mapper that recursively copies data from one object to another
* It supports mapping between attribute names and between types.
* Standard conversions are provided automatically
* You are allowed to specify custom conversions via XML
* With Dozer, your internal domain objects are not exposed to external presentation layers or to external consumers.
* Dozer maps your domain objects to external APIs calls and vice-versa.
* Dozer can work both with XML and JSON 
* You can install it in your project via **ivy**

    ```xml
        <!-- Ivy dependency declaration for Dozer --> 
        <dependency org="net.sf.dozer" name="dozer" rev="5.5.1"/>
    ```

* If you do not use ivy, you can download it from [github][9], unpack it and add **${dozer.home}/dist/dozer.jar** to your classpath.
* You will also need to add required [thirdparty runtime jars][10] to your classpath 

### Dozer Example (30 min)

* Chek the package **dozerproject** in the examples of this module. Exlore the files:
    * dozerproject.entity.Person.java
    * dozerproject.transfer.PersonBean.java
    * dozerproject.delegate.PersonBeanDelegate.java

* The basic idea is that you have create:
    * **entity classes** to represent your domain model (i.e., your data model)
    * **transfer classes** to manage what and how the domain model is going to be mapped to the presentation layer (xml, json)
* Additionally, we can create **delegate classes** to manage the mapping operation (i.e., instantiating the dozer mapper and setting up the operation). 
* Dozer maps domain objects (entities) into (transfer objects) that can be implemented with jaxb annotations, facilitating their serialization
* The marshalling is then separated from the management of the data objects (and therefore, JAXB annotations are not mixed with the data, but only with presentation objects)
* Mapping from **entities** to **transfer objects** can be defined via an **xml mapping file** (e.g., Example/dozerMappings.xml) or via its more experimental version with **java annotations** in the transfer classes.
* A third mapping option is by using dozer API programmatically (we will not cover this, but you can check it out [here][11]) 
* **Exercise 04.03: ** Let's add to our Dozer example the HealthProfile class (using annotations) and in such a way that the BMI calculation is only included in the transfer bean.

### Dozer Example: XML Mapping

* Below is an example of an XML mapping file for Dozer

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

### Dozer Example: Mapping Example

* Below is an example of an Java program that uses Dozer to map beans

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

### Final Example: Serializing to JSON

* Finally, although XML is one of the cornerstones of service oriented computing, nowadays, JSON is the most common language for message exchange.  
* The main reason behind this comes from the advent of mobile computing: JSON is less complex to process, thereby requiring less computation resources, and less **battery**. 
* One way of having both (XML and JSON) to coexist (which they do in most web services implementation frameworks) is **reusing annotations**. 
* A library that supports reusing JAXB annotations for mapping also to and from JSON is [Jackson][12]. 
* Open and run **HealthProfileJson** Example to see how it works

## Additional notes

* Checkout the details of [Assignment 1][13] and stay tune for the deadline announcement in the hands-on session. 

[1]: https://drive.google.com/open?id=0B7ShzcEnCJFNSGEzdWltNUtIa3M
[2]: https://drive.google.com/file/d/0B7ShzcEnCJFNTTUwcy1QcGZMRjg/edit?usp=sharing
[3]: https://github.com/cdparra/introsde/tree/master/lab04/Examples
[4]: https://jaxb.java.net/2.2.7
[5]: http://ant.apache.org/ivy/download.cgi
[6]: http://ant.apache.org/ivy/ivyde/ 
[7]: http://maven-repository.com/
[8]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-sessions-starting-up
[9]: https://github.com/DozerMapper/dozer/archive/v5.4.0.zip
[10]: http://dozer.sourceforge.net/dependencies.html
[11]: http://dozer.sourceforge.net/documentation/apimappings.html
[12]: http://jackson.codehaus.org
[13]: https://sites.google.com/site/introsdeunitn/assignments/assighment-1