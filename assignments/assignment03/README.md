# Assignment 03: SOAP Web Services

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/assignments/02 "Permalink to Assignment 02: RESTful Services")**

## Pre-Requisites

**Lab Modules:** 
* Lab Session 07: [Webpage][1] | [Source Code][2] 
* Lab Session 08: [Webpage][3] | [Source Code][4] 
* Lab Session 09: [Webpage][5] | [Source Code][6] 

**Technologies:**
* Java
* JSON/XML
* JAX_WS
* JPA

## Assignment #3
## Server
* Please use *introsde.assignment.soap* as a package for your service interface. Use *People* as the name for your endpoint interface. User https://github.com/cdparra/introsde/blob/master/lab09/Server/src/introsde/document/ws/People.java as a reference point, but implement your own methods.
* Using JAX-WS, implement CRUD services for the following model including the following operations
   * **Method #1**: readPersonList() => List<Person> | should list all the people in the database (see below Person model to know what data to return here) in your database (wrapped under the root element "people")
   * **Method #2**: readPerson(Long id) => Person | should give all the Personal information plus current measures of one Person identified by {id} (e.g., current measures means current healthProfile)
   * **Method #3**: updatePerson(Person p) => Person | should update the Personal information of the Person identified by {id} (e.g., only the Person's information, not the measures of the health profile)
   * **Method #4**: createPerson(Person p) => Person | should create a new Person and return the newly created Person with its assigned id (if a health profile is included, create also those measurements for the new Person).
   * **Method #5**: deletePerson(Long id) should delete the Person identified by {id} from the system
   * **Method #6**: readPersonHistory(Long id, String measureType) => List<Measure> should return the list of values (the history) of {measureType} (e.g. weight) for Person identified by {id}
   * **Method #7**: readPersonMeasurement(Long id, String measureType, Long mid) => Measure should return the value of {measureType} (e.g. weight) identified by {mid} for Person identified by {id}
   * **Method #8**: savePersonMeasurement(Long id, Measure m) =>should save a new measure object {m} (e.g. weight) of Person identified by {id} and archive the old value in the history
   * **Method #9**: readMeasureTypes() => List<String> should return the list of measures 

   The minimal model supported by your service should include: 
   ```java
   public class Person {
     Long id;
     String firstname;
     String lastname;
     List<Measure> currentHealth; // one for each type of measure
     List<Measure> healthHistory; // all measurements
   }
   
   public class Measure {
     Long mid,
     Date dateRegistered;
     String measureType;
     String measureValue;
     String measureValueType; // string, integer, real
   }
   ```
   // Person & HealthProfile
  ```xml
  <person>
      <personId>1</personId>
      <firstname>Chuck</name>
      <lastname>Norris</lastname>
      <birthdate>1945-01-01</birthdate>
      <currentHealth>
        <measure>
          <value>999</value>
          <dateRegistered>2014-01-03</dateRegistered> 
          <measureType>weight</measureType>
          <measureValue>80</measureValue>
          <measureValueType>Int</measureValueType>
        </measure>
        ....
      </currentHealth>
  </person>
  ```

* **Extra #1**: Having a real database in sqlite
* **Extra #2** (Method #10): updatePersonMeasure(Long id, Measure m) => Measure | should update the measure identified with {m.mid}, related to the Person identified by {id}
* **Extra #3** (Method #11): readPersonMeasureByDates(Long id, String measureType, Date before, Date after) => List<Measure> | should return the history of {measureType} (e.g., weight) for Person {id} in the specified range of date
* **Extra #4** (Method #12): readPersonListByMeasurement(String measureType, String maxValue, String minValue) | retrieves people whose {measureType} (e.g., weight) value is in the [{min},{max}] range (if only one for the query params is provided, use only that)
  
  
  // History of the health profile
  
  ```xml
  <healthProfile-history> 
      <measure>
          <value>999</value>
          <dateRegistered>2014-01-03</dateRegistered> 
          <measureType>weight</measureType>
          <measureValue>80</measureValue>
          <measureValueType>Int</measureValueType>
        </measure>
      <measure>
          <value>1000</value>
          <dateRegistered>2014-01-02</dateRegistered> 
          <measureType>weight</measureType>
          <measureValue>82</measureValue>
          <measureValueType>Int</measureValueType>
        </measure>
      <measure>
          <value>1001</value>
          <dateRegistered>2014-01-01</dateRegistered> 
          <measureType>weight</measureType>
          <measureValue>81</measureValue>
          <measureValueType>Int</measureValueType>
        </measure>
  </healthProfile-history> 
  ```

## Client

* Create a simple client that call each of these services and prints the result (using your service implementation).

## VIVA Testing Procedures for #3 

On the VIVA, the server student must run its application and then give the client its IP, PORT (preferebly 443, as it is not blocked in the unitn network) and Service Endpoint Name. The client will then use this IP + PORT + ENDPOINT as BASEURL to run its application. The results displayed on the screen must be copy and pasted in a text file with the name [serverstudentname]_[BASEURL]_results.txt.

Once the first client finished running, students will shift roles and do the same procedure.
Once the second client finished running, both students will have 30 min to correct things in their server to approach potential limitations encountered during execution.
After this time, a second test round will be followed, which results must be stored in the file[serverstudentname]_[BASEURL]_results_secondround.txt
Each student must then decide which of the results use as the final.

## Assignment Evaluation

* Before submission make a zip file that includes only
    * All Java source files 
    * All XML and XSD files
    * please, do not include .class or IDE generated project files
* Rename the Zip file to: your **student_name_assignmentNumber.zip**. As **student_name**, use your name as registered in [this spreadsheet][10], replacing spaces by "_" (e.g.,"Ans-Riaz-3.zip"
* Follow [this link for uploading your file][9]
* Password will be given in class and sent to the group
* The assignment is due on **27-Nov (Mid-Night)**. 
* On 28-Nov, the **VIVA Session** will be held where we will randomly pair servers and clients to check that they work.

[1]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-7
[2]: https://github.com/cdparra/introsde/tree/master/lab07
[3]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-8
[4]: https://github.com/cdparra/introsde/tree/master/lab08
[5]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-9
[6]: https://github.com/cdparra/introsde/tree/master/lab09
[9]: http://www.dropitto.me/introsde
[10]: https://docs.google.com/spreadsheets/d/1lQQS7BCcYJbVZqHpX3ELrxe6pHHXrG0Owjw20pTN0f0/edit?usp=sharing

