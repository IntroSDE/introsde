# Assignment 02: RESTful Services

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/assignments/02 "Permalink to Assignment 02: RESTful Services")**

## Pre-Requisites

**Lab Modules:** 
* Lab Session 04: [Webpage][1] | [Source Code][2] 
* Lab Session 05: [Webpage][3] | [Source Code][4] 
* Lab Session 06: [Webpage][5] | [Source Code][6] 
* Lab Session 07: [Webpage][7] | [Source Code][8]

**Technologies:**
* Java
* JSON/XML
* XPATH
* JAXB/Jackson
* Dozer
* Jersey
* JPA

## Part 1 - The Model

* Create a model that supports
  * **People** identified by an **id** and having at least *birthdate*, *first* and *lastname*
  * A Health/Lifestyle profile for each person, with measures such as **weight** and **height** (and others if you like)
  * The history of these measures by date

* To clients, the model should look like as follows (beware that IDs are only available when the resource has been created, if you are posting a new resource, no ID is passed): 

* **Person resource**

    ```json
    {
          "personId"      : 999,
          "firstname"     : "Chuck",
          "lastname"      : "Norris",
          "birthdate"     : "1945-01-01",
          "healthProfile" : {
                    "weight"  : 78.9,
                    "height"  : 172
          }
    }
    ```

* **History of one measure (e.g., weight).** Notice that this is a list of **Measure resources**.

    ```json
    [ 
        { 
            "mid" : 992,
            "value" : 78.9,
            "created" : "2007-12-09"
        },
        { 
            "mid" : 999,
            "value" : 75,
            "created" : "2009-12-09"
        },
        { 
            "mid" : 1002,
            "value" : 72,
            "created" : "2012-12-09"
        }
    ]
    ```

* **Person in XML**

    ```xml
    <person>
        <personId>999</personId>
        <firstname>Chuck</firstname>
            <lastname>Norris</lastname>
            <birthdate>1945-01-01</birthdate>
            <healthProfile>
                <weight>78.9</weight>
                <height>172</height>
            </healthProfile>
    </person>
    ```
    
In case you keep healthProfile *static* (stays with weight and height only, even if other measures are available) the structure is:
	
	```xml
	<healthProfile>
	        <weight>78.9</weight>
	        <height>172</height>
	</healthProfile>
	```
	
In case you have healthProfile build *dynamicly* (according to measureTypes - you get extra points for that),then the structure is approximately like this (**it can be different and it is fine**, as this part is not checked by **Client**):
	```xml
	<healthProfile>
		<measureType>
			<measure>Height</measure>
			<value>172</value>
		</measureType>
		<measureType>
			<measure>Weight</measure>
			<value>78.9</value>
		</measureType>
	</healthProfile>
	```
	
* **History of one measure in XML (e.g., weight)**

    ```xml
    <measure-history> 
        <measure>
            <mid>992</mid>
            <value>78.9</value>
            <created>2007-12-09</created>
        </measure>
        <measure>
            <mid>999</mid>
            <value>75</value>
            <created>2009-12-09</created>
        </measure>
        <measure>
            <mid>1002</mid>
            <value>72</value>
            <created>2011-12-09</created>
        </measure>
    </measure-history> 
    ```

## Part 2 - The Server

* With that model, expose the following services through a RESTful API as follows: these are basically all the **CRUD operations for person** plus some services to accessing and updating health profile measurements.
 * **Request #1:** *GET /person* should list all the names in your database (wrapped under the root element "people") 
 * **Request #2:** *GET /person/{id}* should give all the personal information plus current measures of person identified by {id} (e.g., current measures means current health profile) 
 * **Request #3:** *PUT /person/{id}* should update the personal information of the person identified by {id} (e.g., only the person's information, not the measures of the health profile) 
 * **Request #4:** *POST /person* should create a new person and return the newly created person with its assigned id (if a health profile is included, create also those measurements for the new person). The body of the request should contain a **Person resource** that follows the examples presented before, without an id (which should be generated after being created) 
 * **Request #5:** *DELETE /person/{id}* should delete the person identified by {id} from the system 
 * **Request #6:** *GET /person/{id}/{measureType}* should return the list of values (the history) of {measureType} (e.g. weight) for person identified by {id}
 * **Request #7:** *GET /person/{id}/{measureType}/{mid}* should return the value of {measureType} (e.g. weight) identified by {mid} for person identified by {id}
 * **Request #8:** *POST /person/{id}/{measureType}* should save a new value for the {measureType} (e.g. weight) of person identified by {id} and archive the old value in the history. The body of the request should contain a **Measure resource** that follows the examples presented before, without an id (which should be generated after being created)
 * **Request #9:** *GET /measureTypes* should return the list of measures your model supports in the following formats:

    ```xml
    	<measureTypes>
        	<measureType>weight</measureType>
        	<measureType>height</measureType>
        	<measureType>steps</measureType>
        	<measureType>bloodpressure</measureType>
    	</measureTypes>
    ```
    
    ```json
    {
        "measureTypes":[
            "weight",
            "height",
            "steps",
            "bloodpressure"
        ]
    }
    ```
	

* **Extra points:**
 * **Extra #1:** Having a real database in sqlite	
 * **Extra #2 (Request #10):** *PUT /person/{id}/{measureType}/{mid}* should update the value for the {measureType} (e.g., weight) identified by {mid}, related to the person identified by {id}. The body of the request should contain a **Measure resource** that follows the examples presented before.
 * **Extra #3 (Request #11):** *GET /person/{id}/{measureType}?before={beforeDate}&after={afterDate}* should return the history of {measureType} (e.g., weight) for person {id} in the specified range of date
 * **Extra #4 (Request #12):** *GET /person?measureType={measureType}&max={max}&min={min}* retrieves people whose {measureType} (e.g., weight) value is in the [{min},{max}] range (if only one for the query params is provided, use only that)

## Part 3 - The Client

* Implement a client that can send all of these **requests** and print the **responses** 
* Instructions:
 * **Step 1.** Ask as an input, the **BASE_URL** of the server (e.g. http://192.10.168.3:8000/johndoe). This should be given to you by another student who will be assigned to you in the day of the VIVA evaluation. 
 * **Step 2.** For each of of requests in the plan of **Step 3**, including extras, you have to send the request first with the **Accept** header set to **Application/XML** and then with **Application/JSON**. Same for Content-Type in the **write requests**. After getting the response, print the following in the console: 
 
    ```bash
    Request #[NUMBER]: [HTTP METHOD] [URL] Accept: [TYPE] Content-type: [TYPE] 
    => Result: [RESPONSE STATUS = OK, ERROR]
    => HTTP Status: [HTTP STATUS CODE = 200, 404, 500 ...]
    [BODY]  
    Example: 
        Request #7: GET /person/5/weight/899 Accept: APPLICATION/XML Content-Type: APPLICATION/XML  
        => Result: OK
        => HTTP Status: 200
        <measure>
            <mid>899</mid>
            <value>72</value>
            <created>2011-12-09</created>
        </measure>
    ```
    
* **Step 3.** Use the following plan of requests to guide your implementation of the client. 
 * **Step 3.1.** Send **R#1 (GET BASE_URL/person)**. Calculate how many people are in the response. If more than 2, result is OK, else is ERROR (less than 3 persons). Save into a variable id of the first person (**first_person_id**) and of the last person (**last_person_id**)
 * **Step 3.2.** Send **R#2** for **first_person_id**. If the responses for this is 200 or 202, the result is OK.  
 * **Step 3.3.** Send **R#3** for **first_person_id** changing the firstname. If the responses has the name changed, the result is OK. 
 * **Step 3.4.** Send **R#4** to create the following person (first using JSON and then using XML). Store the id of the new person. If the answer is 201 (200 or 202 are also applicable) with a person in the body who has an ID, the result is OK.
 
    ```json
        {
              "firstname"     : "Chuck",
              "lastname"      : "Norris",
              "birthdate"     : "1945-01-01"
              "healthProfile" : {
                        "weight"  : 78.9,
                        "height"  : 172
              }
        }
    ```
    
 * **Step 3.5.** Send **R#5** for the person you have just created. Then send **R#1** with the id of the person that person. If the answer is 404, your result must be OK.
 * **Step 3.6.** Follow now with the **R#9 (GET BASE_URL/measureTypes)**. If response contains more than 2 measureTypes - result is OK, else is ERROR (less than 3 measureTypes). Save all measureTypes into array (**measure_types**)
 * **Step 3.7.** Send **R#6 (GET BASE_URL/person/{id}/{measureType})** for the first person you obtained at the beginning and the last person, and for each measure types from **measure_types**. If no response has at least one measure - result is ERROR (no data at all) else result is OK. Store one **measure_id** and one **measureType**. 
 * **Step 3.8.** Send **R#7 (GET BASE_URL/person/{id}/{measureType}/{mid})** for the stored measure_id and measureType. If the response is 200, result is OK, else is ERROR.
 * **Step 3.9.** Choose a **measureType** from **measure_types** and send the request **R#6 (GET BASE_URL/person/{first_person_id}/{measureType})** and save count value (e.g. 5 measurements). Then send **R#8 (POST BASE_URL/person/{first_person_id}/{measureTypes})** with the measurement specified below. Follow up with another **R#6** as the first to check the new count value. If it is 1 time more - print OK, else print ERROR. Remember, first with JSON and then with XML as content-types 
 
    ```xml
            <measure>
                <value>72</value>
                <created>2011-12-09</created>
            </measure>
    ```

 * **Step 3.10.** Send **R#10** using the {mid} or the measure created in the previous step and updating the value at will. Follow up with at **R#6** to check that the value was updated. If it was, result is OK, else is ERROR.
 
     ```xml
            <measure>
                <value>90</value>
                <created>2011-12-09</created>
            </measure>
    ```
 
  * **Step 3.11.** Send **R#11** for a measureType, before and after dates given by your fellow student (who implemnted the server). If status is 200 and there is at least one measure in the body, result is OK, else is ERROR
  * **Step 3.12.** Send **R#12** using the same parameters as the preivious steps. If status is 200 and there is at least one person in the body, result is OK, else is ERROR
 
* **IMPORTANT:** Be mindful of **Step 2** for each request.
* Notes: 
 * Use only the **Standalone server running in the port 443 of your local machine**
 * Either XML or JSON support is (only one is required)
 * The client must request both (as indicated in the request plan) 
 * Some of these services are going to be part of your final projet, so try to do them well. 
 * For POST/PUT services, use the examples provided in the plan

* Notes about the **VIVA SESSION**
 * On the date of evaluation, we will test your implementation live using the client of one of your fellow students (pairs of **server** and **client** students will be assigned during the VIVA).
 * **Running servers and clients (30 minutes)**
  * On the VIVA, the **server** student must run its application and then give the client its IP. The client will then use this IP plus the port 8000 as the **BASE_URL** of run its requests. The results displayed on the screen must be **copy and pasted** in a text file with the name **server_fullname_results.txt** (where fullname refers to the name of the student's who implemented the server).
  * Additionally, in order to provide us with a way of comparing results better, the server owner should also run his/her own client against the server, and copy the output to a text file with the name **server_fullname_results_SELF.txt** 
  * Once the first client finished running, students will shift roles and follow the same procedure.
 * **Bugfixing and second run (45 minutes)** 
  * Once the second client finished running, both students will have *45 minutes* to correct things in their server to answer potential limitations encountered during execution. 
  * A second test round should follow.
  * Each student must then decide which of the results use as the final, and copy those results in the text file, which must then be shared with instructors. 

## Assignment Rules

* Before submission make a zip file that includes only
    * All Java source files 
    * All XML and XSD files
    * please, do not include .class or IDE generated project files
* Rename the Zip file to: your **studentfullname_assignmentNumber.zip**. As **studentfullname**, use your name as registered in [this spreadsheet][10], replacing spaces by "_" (e.g.,"Ans-Riaz-2.zip"
* Follow [this link for uploading your file][9]
* Password will be given and class and sent to the group
* The assignment is due on **18-Nov (Mid-Night)**. 
* On 28-Nov, the **VIVA Session** will be held where we will randomly pair servers and clients to check that they work.

## Assignment Evaluation

* The assignment will be evaluated in terms of:
	* Completeness: Requirements satisfaction 
	* Functioning: Execution & Deployment  
	* Organization: code structure (how well is your work organized, the easier you make it for us to understand what you did and how to run it, the more chances for you to have a better mark) 
	* Extra work (if you work in a group, extra/additional points are also a requirement) 
	* Submitted on time ? 
	* Report (you should provide at least a README file explaining how to deploy and run your work)
* Extra points are used as "recovery" you didn't finish the requirements or didn't submit in time
* We will also take into account your ability to solve problems on the spot, under the pressure of time during the VIVA session. 

[1]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-4
[2]: https://github.com/cdparra/introsde/tree/master/lab04
[3]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-5
[4]: https://github.com/cdparra/introsde/tree/master/lab05
[5]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-6
[6]: https://github.com/cdparra/introsde/tree/master/lab06
[7]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-7
[8]: https://github.com/cdparra/introsde/tree/master/lab07
[9]: http://www.dropitto.me/introsde
[10]: https://docs.google.com/spreadsheets/d/1lQQS7BCcYJbVZqHpX3ELrxe6pHHXrG0Owjw20pTN0f0/edit?usp=sharing
