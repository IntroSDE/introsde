This is space is for projects that expand our current service design tutorials to other languages or frameworks, which could potentially enrich the sessions of our lab. 

# Suggested Projects

## Other framework tutorials
Develop a tutorial of no more than 3 hours (similar to our lab session guiding notes) to learn one of the following frameworks used for web service development: 
* [Expressjs][20] - a web application framework for NodeJS
* [Sinatra][16] - a ruby lightweight framework for implementing REST Services
* [Restlet][7] - probably the first REST framework, which existed prior to JAX-RS.
* [RESTEasy][6] - JBosss JAX-RS implementation.
* [Play Framework][8] - popular nowadays, it is an MVC framework with a heavy focus on RESTful design and available for both Java and Scala programmers
* [RAILS][15] - the ruby most well known web development framework, which heavily supports the RESTful style
* [Djanjo REST framework][17] - REST development framework for the python language
* [Python EVE][18] - another python-based REST framework  
* [Spring Framework][9]  - another very popular java application framework that you can use to build RESTful services 
* [Apache CXF][4] - Apache's open source services framework that allows you to develop services using either JAX-WS or JAX-RS specifications; speaking a variety of protocols including SOAP, XML/HTTP, RESTful HTTP, or CORBA; and working over a variety of transports layers such as HTTP, JMS or JBI.
* [Slim][21] - PHP based REST framework
* [Nancy][22] - .NET based REST framework


[1]: https://drive.google.com/open?id=0B7ShzcEnCJFNWENNN1RpYU9xeUk&authuser=0
[2]: https://drive.google.com/open?id=0B7ShzcEnCJFNQ2FfR21FUUk1Y1E&authuser=0
[3]: https://github.com/cdparra/introsde/tree/master/lab05/Examples
[4]: http://cxf.apache.org/
[5]: https://jersey.java.net/
[6]: http://www.jboss.org/resteasy
[7]: http://restlet.org/
[8]: http://www.playframework.com/ 
[9]: http://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch18s02.html
[10]: http://jcp.org/en/jsr/detail?id=311 
[11]: http://www.getpostman.com
[12]: https://chrome.google.com/webstore/detail/fhjcajmcbmldlhcimfajhfbgofnpcjmb
[13]: http://code.google.com/p/rest-client/
[14]: http://code.google.com/p/cocoa-rest-client/
[15]: http://rubyonrails.org/
[16]: http://www.sinatrarb.com/
[17]: http://www.django-rest-framework.org/
[18]: http://python-eve.org/
[19]: http://docs.helloworld66.apiary.io/
[20]: http://expressjs.com/
[21]: http://www.slimframework.com/
[22]: http://nancyfx.org/

The tutorial should use the example from Lab 06 and help the learner build the a similar RESTful API (see below for the basic API spec).  Adding a connection to a database is a plus but not required.  

The Simple Health Profile API
* GET /lifecoach/person: return the list of people in the database, with their health profiles, which should include at least weight and height. 
* GET /lifecoach/person/{personId}: returns the record for person with id 'personId'
* GET /lifecoach/person/{personId}/health-profile: returns the health-profile of 'personId'  
* POST /lifecoach/person: creates a new person in the system
* PUT /lifecoach/person/{personId}: update information about the person
* DELETE /lifecoach/person/{personId}: deletes a person and her health profile
* [Suggest as Exercise] GET/lifecoach/person/{personId}/health-profile/history: returns the history of previous health profile values. For this, the learner has to add the support for storing the old health-profile value every time a there is an update throught endpoint 5
* [Suggest as Exercise] POST /lifecoach/person/{personId}/health-profile: creates a new health-profile value for the person pushing his the current values to the history

The items marked with the label [Suggest as Exercise] should be described as exercises in the tutorial, but the students have to provide the source code as a solution to all the items. 

How to deliver and evaluate the project
To deliver your tutorial, you will have to follow these instructions: 
* Fork the IntroSDE/introsde repository in Github. This will create a repository with the same name under your Github user. 
* Clone your newly created repository
```
git clone https://github.com/<Your_Github_Username>/introsde
```
* Under the folder called projects, create your project with the following name template:
```
tutorial_[name_of_framework]_2017
Examples: 
tutorial_expresjs_2017
tutorial_play_framework_2017 
```
* Commit and push all of your code to your github repository, including your tutorial in a README.md file. 
* When you have finished, create a pull request from your fork in github. A reviewer will look at your project, provide feedback and then merge if everything works fine. 
