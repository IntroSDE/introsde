# Assignment 02: RESTful Services

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/assignments/02 "Permalink to Assignment 02: RESTful Services")**

## Pre-Requisites

**Lab Modulse:** 
* Lab Session 04: [Webpage][1] | [Source Code][2] 
* Lab Session 05: [Webpage][3] | [Source Code][4] 
* Lab Session 06: [Webpage][5] | [Source Code][6] 
* Lab Session 06: [Webpage][7] | [Source Code][8]

**Technologies:**
* Java
* JSON/XML
* XPATH
* JAXB/Jackson
* Dozer
* Jersey
* JPA

## Part 1

* Create a model that supports
  * **People** identified by an **id** and having at least *birthdate*, *first* and *lastname*
  * A Health/Lifestyle profile for each person, with measures such as **weight** and **height** (and others if you like)
  * The history of these measures by date

* To clients, the model should look like as follows: 

**Person**
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

**History of one measure (e.g., weight)**
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

**Person in XML**
```xml
<person>
    <firstname>Chuck</name>
        <lastname>Norris</lastname>
        <birthdate>1945-01-01</birthdate>
        <healthProfile>
            <weight>78.9</weight>
            <height>172</height>
        </healthProfile>
</person>
```

**History of one measure in XML (e.g., weight)**
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

## Part 2

* With that model, expose the following services through a RESTful API as follows:	* CRUD operations for person (GET,PUT,DELETE) on /person/{id} and POST on /person
	* GET /person should list all the names in your database (wrapped under the root element "people") 
	* GET /person/{id} should give all the personal information plus current measures of person identified by {id} (e.g., current measures means current health profile) 
	* GET /person/{id}/{measure} should return the list of values (the history) of {measure} for person identified by {id}
	* GET /person/{id}/{measure}/{mid} should return the value of {measure} identified by {mid} for person identified by {id}
	* POST /person/{id}/{measure} should save a new value for the {measure} of person identified by {id} and archive the old value in the history
	* GET /measures should return the list of measures your model supports in the following formats:

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
      "measureType"     : "weight",
      "measureType"     : "height",
      "measureType"     : "steps",
      "measureType"     : "bloodpreasure"
}
```
	

* **Extra points:**
	* Having a real database in sqlite	
	* PUT /person/{id}/{measure}/{mid} should update the value for the {measure} identified by {mid}, related to the person identified by {id}
	* GET /person/{id}/{measure}?before={beforeDate}&after={afterDate} should return the history of {measure} for person {id} in the specified range of date
	* GET /person?measure={measure}&max={max}&min={min} retrieves people whose {measure} value is in the [{min},{max}] range (if only one fo the query params is provided, use only that)


## Part 3
* Implement a client that call all these services and print the returned information (you can print them both on screen and into a file that summarizes what works and what not)
* Notes: 
	* Use only the **Standalone server running in the port 443 of your local machine**
	* On the date of the **VIVA** evaluation, we will test your implementation live using the client of one of your fellow students (pairs of server and client will be send out before the VIVA, together with server deployment details)
	* Either XML or JSON support is (only one is required)
	* The client must request both and print the one that works or a message saying "NOT IMPLEMENTED" if it does not work
	* Some of these services are going to be part of your final projet, so try to do them well. 
	* For POST/PUT services, use the examples provided in this webpage (or similar model)


## Assignment Rules

* Before submission make a zip file that includes only
    * All Java source files 
    * All XML and XSD files
    * please, do not include .class or IDE generated project files
* Rename the Zip file to: your **firstname-lastname_assignmentNumber.zip** for example: "cristhian-parra-1.zip"
* Follow [this link for uploading your file][9]
* Password will be given and class and sent to the group
* The assignment is due on **30-Oct (Mid-Night)**. 
* On 31-Oct, a VIVA Session will be held where we will randomly pair servers and clients to check that they work.

## Assignment Evaluation

* The assignment will be evaluated in terms of:
	* Completeness: Requirements satisfaction 
	* Functioning: Execution & Deployment  
	* Organization: code structure (how well is your work organized, the easier you make it for us to understand what you did and how to run it, the more chances for you to have a better mark) 
	* Extra work (if you work in a group, extra/additional points are also a requirement) 
	* Submitted on time ? 
	* Report (you should provide at least a README file explaining how to deploy and run your work)
* Extra points are used as "recovery" you didn't finish the requirements or didn't submit in time

[1]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-4
[2]: https://github.com/cdparra/introsde/tree/master/lab04
[3]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-5
[4]: https://github.com/cdparra/introsde/tree/master/lab05
[5]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-6
[6]: https://github.com/cdparra/introsde/tree/master/lab06
[7]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-7
[8]: https://github.com/cdparra/introsde/tree/master/lab07
[9]: http://www.dropitto.me/introsde
