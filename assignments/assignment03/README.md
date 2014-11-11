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


* **Extra points:** Include also the service getHealthProfileHistory(int personId)
* **Extra points:** Connect to a database. 

    // History of the health profile
    ```xml
    <healthProfile-history> 
        <healthProfile>
            <hpId>999</hpId>
            <date>2013-12-05</date>
            <weight>78.9</weight>
            <height>172</height>
            <steps>5000</steps>
            <calories>2120</calories>
        </healthProfile>
        <healthProfile>
            <hpId>998</hpId>
            <date>2013-11-29</date>
            <weight>null</weight>
            <height>null</height>
            <steps>6430</steps>
            <height>null</height>
        </healthProfile>
        <healthProfile>
            <hpId>1000</hpId>
            <date>2013-11-05</date>
            <weight>null</weight>
            <height>null</height>
            <steps>12083</steps>
            <height>null</height>
        </healthProfile>
    </healthProfile-history> 
    ```

## Assignment #3: Part 2 

* Create a simple client that call each of this services and prints the result (using your service implementation).

## VIVA Testing Procedures for #3 

5. **Assignment 3:**
    * Stop your REST services. 
    * Agree with your partner who will be the client/server first.
    * The server should search for his/her SOAP services implementation and run it 
    * The implementation will be at $HOME/assignments/servername_serverlastname_3/
    * The server should run his/her own client implementation
    * The server must copy all the output of the his/her client to a file named *servername_serverlastname_2_output.txt* in the *servername_serverlastname_2* folder
    * Using the url to the WSDL of the server, the client must now generate the stubs for creating a quick client. 
    * The server must select which of his services should be tested. 
    * The client will have 10 minutes to:
        * implement a simple client in java that will call this service
        * call the selected SOAP service from the browser, via HTTP POST of the SOAP message 
    * The server will have 10 minutes for making quick changes on the server (or requesting quick changes on the client) to make things work.
    * The client must indicate on the evaluation document which services worked and which didn't (from both the server's own tests and the client's test). 
    * Repeat the whole procedure changing roles 

## Assignment Evaluation

* Before submission make a zip file that includes only
    * All Java source files 
    * All XML and XSD files
    * please, do not include .class or IDE generated project files
* Rename the Zip file to: your **student_name_assignmentNumber.zip**. As **student_name**, use your name as registered in [this spreadsheet][10], replacing spaces by "_" (e.g.,"Ans-Riaz-2.zip"
* Follow [this link for uploading your file][9]
* Password will be given and class and sent to the group
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

