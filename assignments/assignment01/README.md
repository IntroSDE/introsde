# Assignment 01: Reading/Writing objects to and from XML and JSON

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/assignments/01 "Permalink to Assignment 01: Reading/Writing objects to and from XML and JSON")**

## Pre-Requisites

**Lab Sessions:** 
* Lab Session 01: [Webpage][1] | [Source Code][2] 
* Lab Session 02: [Webpage][3] | [Source Code][4] 
* Lab Session 03: [Webpage][5] | [Source Code][6]
* Lab Session 04: [Webpage][7] | [Source Code][8]

**Technologies:**
* Java
* XML/XSD
* JSON
* XPATH
* JAXB/Jackson
* Dozer

## Guiding Notes

* Using the Health Profile Reader/Writer examples we have used in the lab, replace the array list or hash map we used as database with a xml file as follows

    ```xml
    <people>
        <person id="0001">
            <firstname>George R. R.</firstname>
            <lastname>Martin</lastname>
            <birthdate>1984-09-20T18:00:00.000+02:00</birthdate>
            <healthprofile>
                <lastupdate>2014-09-20T18:00:00.000+02:00</lastupdate>
                <weight>90</weight>
                <height>1.70</height>
                <bmi>31.14</bmi>
            </healthprofile>
        </person>
        <!-- add more people to the db --> 
    </people>
    ```


* Extend the example above to include at least 20 people (maybe your friends with fake names, **extra points if you find a bigger datasource**) 
* Use xpath to implement methods like **getWeight** and **getHeight**
* Make a function that prints all people in the list with detail (if >20, paginated)
* A function that accepts **id as parameter** and prints the **HealthProfile** of the person with that id
* A function which accepts a **weight** and an **operator (=, > , <)** as parameters and prints people that fulfill that condition (i.e., >80Kg, =75Kg, etc.).
* Create the XML schema XSD file for the example XML document of people.
* Write a java application that does the marshalling and un-marshalling using JAXB and generated classes with JAXB XJC.
* Make your java application to convert also JSON 

## Assignment Rules

* Before submission make a zip file that includes only
    * All Java source files 
    * All XML and XSD files
    * please, do not include .class or IDE generated project files
* Rename the Zip file to: your **fullname_assignmentNumber.zip** for example: "cristhian-parra-1.zip"
* Follow [this link for uploading your file][9]
* Password will be given and class and sent to the group
* The assignment is due on **10-Oct (Mid-Night)**. 
* On 11-Oct I'll copy your directory and I use that copy for the evaluation.

## Assignment Evaluation

* The assignment will be evaluated in terms of: 
    * **Documentation:** do you provide a proper documentation to understand your work? 
    * **Completeness:** did you deliver all that was asked?
    * **Functioning:** does it work? Does it do what it was requested?
    * **Organization (for extra points):** the easier you make it for us to understand what you did and how to run it, the more chances for you to have a better mark (i.e., there is self-explained ant build file to automate things, files are structured in orderly manner, source code is readable, dependencies are managed with ivy or other dependency manager, etc.)


[1]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-1
[2]: https://github.com/cdparra/introsde/tree/master/lab01
[3]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-2
[4]: https://github.com/cdparra/introsde/tree/master/lab02
[5]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-3
[6]: https://github.com/cdparra/introsde/tree/master/lab03
[7]: https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-4
[8]: https://github.com/cdparra/introsde/tree/master/lab04
[9]: http://www.dropitto.me/introsde