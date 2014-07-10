# LAB02: More Java, Eclipse, ANT and Firs example of a service with Axis2


**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-2 "Permalink to LAB02: More Java, Eclipse, ANT and Firs example of a service with Axis2")**

How can we automate compiling and executing java programs? What kind of technology is behind this automation? What do we mean, in practical terms, when we speak of a "service"?.&nbsp;In this session, we will provide some answers to these questions. We will briefly introduce how will we work with Eclipse during sessions (although students are free of working with the IDE of their own choosing). We will see an example of what kind of technology is behind the automation that IDEs provide (ANT), and in turn, what language fuels that technology, which is one of the cornerstones of the SOA world (XML). The lesson will finalize with a first example of a service, using Axis2. And, if we have some time free, we will see also how to manage dependencies in easy manner with Ivy.

## Slides &amp; Code

Links: [PPT slides][1] | [PDF slides][2] | [Source code][3]

## Guiding Notes

The guiding notes below are a summarized version of what is already on the slides.&nbsp;

* Start by pulling updates from the github repository. In your terminals, execute the following: 
```
$ git fetch upstream   $ git merge upstream/master
```

### Using Eclipse

* Create a new *Java Project* in eclipse (File -> New -> Java Project)
* Unmark "Use default location" and select the folder where the source code for this lab is (in your computer). Eclipse should detect the source code inside and add it to the project. 
* Take some minutes to explore the solutions to the exercises of *Lab 01* as they appear in the new version of the HealthProfileReader. Look at:
    * Person.java
    * HealthProfileReader.java
* Run the HealthProfileReader using Eclipse (see the slides to learn how to do it)

### ANT: What if there is no IDE?

* When implementing services, you will find yourself often at work with nothing else than a terminal, executing programs from a command line. 
* The example so far (the HealthProfileReader) is simple, but what if you have hundreds of classes and packages? **How to provide similar automation capabilities for compilation and packaging, as the IDE provides?**. That's where ANT comes in.
* ANT is a Java-based build tool that automates repetitive tasks (e.g. compiling source code, running tests, generating documentation) typically, without a graphical user interface directly from the command line.
* Take a look to the *ANT build script* we have in the HealthProfileReader example (the file **build.xml**). 
* ANT scripst define:
    * **Ant Project:** a collection of named targets. Each build file contains one project.
    * **Ant Target:** a fixed series of ant tasks in a specified order that can depend on other named targets.
    * **Ant Task:** something that ant can execute jobs, such as compile, create jars, copy.
    * **Ant properties:** immutable constants set once and used through the whole scritps



### Homework: things To Do BEFORE NEXT session

* If you are not familiar with git, follow the tutorial at [Try Github][7]&nbsp;(15 minutes)&nbsp;
* Make sure to install the following tools
    * [Git][8]&nbsp;(version 1.8 or higher)
    * [Msysgit (for windows) ][9]&nbsp;
    * [Ant][10]&nbsp;(version 1.9 or higher, download binaries and make them available on your PATH)&nbsp;&nbsp;
    * [Ivy][11]&nbsp;(version 2.4 or higher,&nbsp;unpack the downloaded zip file wherever you want and copy the ivy jar file into your ant lib directory - ANT_HOME/lib) &nbsp;
    * [Tomcat][12]&nbsp;(version 7.0.39 or higher, follow installation instructions [here][13]) &nbsp;
    * [Eclipse for Java EE][14]&nbsp;(any, but it is recommended to download the latest: Luna)&nbsp;&nbsp;
* Configure your Github account and repository:
    * Create your account in [Github][15] (if you haven't)
    * Fork the lab repository to your account (i.e., create a copy of the repository in your github account)
    ```
    Go to https://github.com/cdparra/introsde.git.
    Click on "Fork" in the upper right corner of the page
    ```
    * Clone your fork in your local machine (i.e., download your copy to your machine)
    ```
    git clone https://github.com/YOUR_USERNAME/introsde.git
    ```
    * Add the original repository as a remote, in order to Fetch future updates on the original repository (in case there is any)
    ```
    git remote add upstream https://github.com/cdparra/introsde.git
    ```.
* Learn the&nbsp;Lab session workflow:
    * Before each session of the Lab, pull the changes from the original repository (i.e., update your local version with changes from the original)&nbsp;
    ```
    git fetch upstream &nbsp; .
    git merge upstream/master
    ```
    * On your local repository, inside the folder for the session, create a folder called myworkspace.&nbsp;
    * Put your personal work in this folder. We will not push changes of our personal workspaces to github.&nbsp;
        * If, however, you wish to push your changes to github, you will have to edit the .gitignore in the root of your local repo and remove "myworkspace" from it.
        * Then,&nbsp;at the end of each session of work, add your changes to your repository stash
           ```
            git add myworkspace
            ```
        * Commit your changes to your local repository
            ```
            git commit ���m "my work for labXX"
            ```
        * Push your changes to your github repository
            ```
            git push master
            ```

## Additional notes

### Setting up PATH variables:&nbsp;

#### In windows:&nbsp;

* Control Panel -&gt; System -&gt; Advanced tab -&gt; Environment Variables -&gt; System Variables&nbsp;
* Edit the 'path' variable and append the location of your bin folder onto the existing value (separated by a semicolon).&nbsp;

```PATH = ;C:\Program Files\Java\jdk1.6.0_02in\;```

* Add the following to the file named .bash_profile (or .profile), located in your home directory

```export PATH=$PATH:/path/to/your/binary/folder/```

Do the same for all other binaries you will need (unless the binary folder is already added automatically) (Ant, Tomcat, etc.)&nbsp;


[1]: http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
[2]: https://github.com/cdparra/introsde/tree/master/lab01/Example/src
[3]: http://introcs.cs.princeton.edu/java/11cheatsheet/
[4]: http://www.vogella.com/tutorials/JavaIntroduction/article.html#firstjava
[5]: http://www.mkyong.com/all-tutorials-on-mkyong-com/
[6]: http://introcs.cs.princeton.edu/java/home/
[7]: https://try.github.io/
[8]: http://git-scm.com/downloads
[9]: http://msysgit.github.io/
[10]: http://ant.apache.org/
[11]: http://ant.apache.org/ivy/
[12]: http://tomcat.apache.org/
[13]: http://tomcat.apache.org/tomcat-8.0-doc/setup.html
[14]: http://www.eclipse.org/downloads/
[15]: http://github.com
[16]: https://drive.google.com/file/d/0B7ShzcEnCJFNd010SmJOV19IR3M/edit?usp=sharing
[17]: https://drive.google.com/file/d/0B7ShzcEnCJFNMkU0TWhYVDdGNDQ/edit?usp=sharing
[18]: https://github.com/cdparra/introsde/tree/master/lab01



## ANT and Java

### Example 1
In the directory [lab2/Example1/src](https://github.com/cdparra/introsde2013/blob/master/lab2/Example1/src)
Open Test.java

	public class Test {
		public static void main(String args[]){
			System.out.println("Hello World!");
		}
	}

Now, compiled the class and execute the program. 

	javac Test.java
	java Test


---

That's the typical "Hello World" example. If a class in java has a an "public static" method called "main", this will be executed by the JVM when you execute it. 

Now, a more complete example that will serve you as a commented guide to how a java class look like is the [Person](https://github.com/cdparra/introsde2013/blob/master/lab2/Example1/src/pojos/Person.java) class. Immagine you want a program to read Person's [health profile](https://github.com/cdparra/introsde2013/blob/master/lab2/Example1/src/pojos/HealthProfile.java). Check the [HealthProfileReader](https://github.com/cdparra/introsde2013/blob/master/lab2/Example1/src/HealthProfileReader.java) to see how you use this classes in action. Again, compile it, execute it.  

Now, what if I have a giant project, with many classes, many source folders, many packages, and want to package them all into a single jar, or a directory, or simply want to automate build?. That's where ANT comes in.  

Ant is a Java-based build tool. A build tool automates repetitive tasks (e.g. compiling source code, running tests, generating documentation). Typically, without a graphical user interface (headless) directly from the command line. Others with a similar purpose are Maven and Gradle.

---

Take a look to the [build.xml](https://github.com/cdparra/introsde2013/blob/master/lab2/Example1/build.xml) as an example an ant build file. And execute it

	ant init
	ant compile


---

Now, the build folder has some compiled classes that can be execute it. To compile the HealthProfileReader execute: 

	ant compile-person
	
You can also execute things using ant. Try: 
	
	ant execute-test
	ant exeucte-hprofile

Finally, classes can all be packaged in one using jar files. Try it like this. 

	ant archive

---
	
In the build folder, there should be a jar file now (project.jar), whose main class is pointed to the HealthProfileReader. To execute it, run: 

	java -jar build/project.jar Pinco Pallino
	
What did you do? You have been executing ant targets (compile, init, archive, etc.) that are defined in the build.xml, each target indicating a list of tasks (i.e. commands) to be sequentially execute it. Take a look to the build.xml to see how they were defined.  

---

In summary, ant scripts define: 
* *Ant Project*: a collection of named targets. Each build file contains one project.
* *Ant Target*: a fixed series of ant tasks in a specified order that can depend on other named targets.
* *Ant Task*: something that ant can execute jobs, such as compile, create jars, copy.   
* *Ant properties*: immutable constants set once and used through the whole scritps

---

### Exercise 1
* First create a folder ���my-solutions��� under lab2 folder for your exercise solutions (do not change the lab source code, unless you want to deal with solving conflicts in the future :P)
* Make a ANT project as follows
	* Extend the HealthProfileReader adding the attribute BMI to the HealthProfile. 
	* Executing the HealthProfileReader should now receive the name, lastname and a third parameter indicating the ���Health Measure��� we want to see (e.g. ���weight���, ���bmi���). 
	* For the BMI formula, ask google. 
* The directory structure should be as follow
	* Main Directory (named after the project)
		* src: source files 
		* build: destination of built files
			* classes: java compiled classes  
		* dist: destination of packaged files (e.g. *.jar).
* Make build.xml file with the following targets
	* init: creates the build and dist directories
	* compile: compiles your project and put the classes in build/classes
	* archive: creates a distributable jar file in ���dist���
	* execute: runs the project with three integer arguments
	* clean: goes back to the original state  

Try it! ([solution](https://github.com/cdparra/introsde2013/blob/master/lab2/solutions/Ex1))

---

## Axis2

**Important notice for 2013:** this part was just half-develope in lab2, the rest in lab3

Axis2 a web service engine that implement both client and server sides of web services (and, from v2, also REST services). It handles the generation/sending/receiving/dispatching of SOAP messages. It allows you to expose simple Java Classes with its methods and other web applications as web services. 

![axis2 middleware](http://axis.apache.org/axis2/java/core/docs/images/fig02.jpg "Axis2 middleware")

In this part we will go through the process of installing axis2 and then using it to create one service. 

First, install tomcat. Go to apache [tomcat website](http://tomcat.apache.org/). Download the zip version of Tomcat 7.x. Unzip it somewhere (e.g. /opt or C:\) and Set environment variables. 

---

	# if you are in unix/linux/mac or you are using msysgit from windows
	export CATALINA_HOME=/opt/apache-tomcat-7.0.39
	
	# windows
	set CATALINA_HOME=C:\apache-tomcat-7.0.39

**Observation:** for those using msysgit, beware that the "\" is a escape characters, so you can use either *C:\\apache-tomcat-7.0.39* or */C/apache-tomcat-7.0.39* 

---

Start the server

	# if you are in unix/linux/mac or you are using msysgit from windows
	$CATALINA_HOME/bin/startup.sh

	# windows
	%CATALINA_HOME%\bin\startup.bat

---

Now, go to http://localhost:8080/ and if you see the apache tomcat cat, you are fine. Next step: donwload and install [axis2](http://axis.apache.org/axis2/java/core/download.cgi). You can either download the war package directly, or download the binary distribution, unzip it somewhere and then build the war. Let's do the second. For this lab session, I downloaded the axis2-1.6.2-bin.zip distribution. 

	unzip axis2-1.6.2-bin.zip  
	mv axis2-1.6.2 /opt

**Observation:** there might be problems with the classpath if you are using only the war distribution. One way of checking exactly what you have in the class path is running *ant SOME_EXISTING_TARGET -diagnostics*. To avoid potential problems, use the binary distribution. 

---

Now, you need to enter the weabpp folder in the axis home and create the package war of axis2 (yes, using ant ;-) )

	cd /opt/axis2-1.6.2/webapp
	ant create.war
	...
	create.war:
		[war] Building war: /opt/axis2-1.6.2/dist/axis2.war
	...

---

Deploy the war in tomcat. For this, you need to access the tomcat manager in your browser. By default, Tomcat does not enable admin or manager access. To enable it, you will have to edit the $TOMCAT_HOME/conf/tomcat-users.xml manually by adding the following (or uncommenting if it is there)

	<user username="admin" password="whateverpasswordyouwantiuseadmin" roles="manager-gui,tomcat"/>

---

Now, you can access the manager in  http://localhost:8080/manager/html and deploy axis war. Now that it is deployed, you can open axis2 

	Follow this link http://localhost:8080/axis2. 

Should show you a page with 3 links: services, validate, administration. Open validate. 

	Follow the link http://localhost:8080/axis2/axis2-web/HappyAxis.jsp

It should should show you a HappyAxis page with the list of needed libraries and their status (if they are or not in your system).  If the Happy Axis page is coming with GREEN color then it means that axis2 is successfully deployed. 

---

Now it���s time to test a service, 
	
	Follow this link http://localhost:8080/axis2/services/Version?method=getVersion

It shows you something like this

	<ns:getVersionResponse xmlns:ns="http://axisversion.sample">
		<ns:return>Hi - the Axis2 version is 1.6.2</ns:return>
	</ns:getVersionResponse>

Now, hit the link "administration" i the axis2 home. The default user/password is admin/axis2. From here, you can deploy other services. 

---

### Example 2
Let's do one example, using one of the samples that come with axis2 distributions. Go to the **Example2** folder of this repo (or, if you prefer, use the axis2 home samples folder)

```
	cd lab2/Example2/quickstart
```

---

First, check the "service.xml" definition that it will be used to create a WSDL file describing a SOAP endpoint service based on the [StockQuoteService](https://github.com/cdparra/introsde2013/blob/master/lab2/Example2/quickstart/service/pojo/StockQuoteService.java) java class. This java class takes the stock code of a company (e.g., IBM) and returns its stocks value. Check the services.xml

	open resources/META-INF/services.xml
	
---

You should see this: 

	<service name="StockQuoteService" <!-- The name of the service -->
		scope="application"
		targetNamespace="http://quickstart.samples/"
		<!-- Scope and nameSpace must be later matched inthe build.xml -->
	>
		<!-- a description of what the service does -->
		<description>Stock Quote Service</description>
		
		<!-- the classes that will process incoming messages to the service -->
		<!-- we are using standar message receivers already bundled with axis2 -->
		<messageReceivers>
			<messageReceiver
				mep="http://www.w3.org/2004/08/wsdl/in-only"
				class="org.apache.axis2.rpc.receivers.RPCInOnlyMessageReceiver"
			/>
			<messageReceiver
				mep="http://www.w3.org/2004/08/wsdl/in-out"
				class="org.apache.axis2.rpc.receivers.RPCMessageReceiver"
			/>
		</messageReceivers>
		
		<!-- definition of the xml schema used by the service -->
		<schema schemaNamespace="http://quickstart.samples/xsd"/>
		
		<!-- specification of what Java class is being exposed as a service -->
		<parameter
			name="ServiceClass">samples.quickstart.service.pojo.StockQuoteService
		</parameter>
	</service>

---

This is the definition of the StockQuoteService. It defines: 

* one "service" with the name *StockQuoteService* 
* what java class it is going to be used as a *ServiceClass* (under params), exposing all of its public methods as services (in this case, the *samples.quickstart.service.pojo.StockQuoteService* class).  
* The other important parameters are *targetNamespace* and *schemaNamespace*, which later has to be reused in the ant build file (we will get there). 
* What receivers will be used to process incoming messages (the standard axis2 receivers org.apache.axis2.rpc.receivers).  

---

Now, you need a build.xml file to get this thing done: 

* **build.xml**: ant build file with the following three axis2 targets:
	* *generate.wsdl*: This target generates the StockQuoteService.wsdl file in the build folder, using the java2wsdl command. Make sure that *targetNamespace* and *schemaTargetNamespace* is same as in service.xml file.
	* *generate.service*: This target generates the axis2 archive (which is nothing but a jar actually) in the build folder under the name *StockQuoteService.aar*, which includes the *services.xml* and the compiled classes. You can use this *.aar file to deploy the service through axis2 webapp. 
	* *generate.client*: This target generates the client side classes. Make sure you run this after executing generate.wsdl so the MyService.wsdl file is present in the build folder.

**Observation:** in the build.xml, replace the properties AXIS2_HOME and AXIS2_TOMCAT_HOME to make it point to your local installations. If you are didn't download the binary version of axis2, remove all the references to AXIS2_HOME. 

---

Now, generate the WSDL file and generate the packaged version of the service doing the following
	
	ant generate.wsdl
	ant generate.service

And finally, we can deploy it by simply copying the *StockQuoteService.aar* in the services directory of axis2. The services directory is the following for our case. 
	
	$TOMCAT_HOME/webapps/axis2/WEB-INF/services/ 

You can now see that the StockQuoteServices is in the list of available services by going to 
	
	http://localhost:8080/axis2/axis2-admin/listService

If you click on the link to the service, you will see the wsdl specification of this SOAP endpoint

	http://localhost:8080/axis2/services/StockQuoteService?wsdl
	
And to call the specific service, you just need to ask for the service getPrice (which is a method in the StockQuoteService class definition) with the proper parameter as follows

	http://localhost:8080/axis2/services/StockQuoteService/getPrice?symbol=IBM
	
The answer is a soap message as follows: 
	
	<ns:getPriceResponse xmlns:ns="http://quickstart.samples/xsd">
		<ns:return>42.0</ns:return>
	</ns:getPriceResponse>
	
The StockQuoteService class has also an update operation which if called, updates the stock value for a company symbol. 

	http://localhost:8080/axis2/services/StockQuoteService/update?symbol=IBM&price=150
	
Calling the getPrice service now will give

	<ns:getPriceResponse xmlns:ns="http://quickstart.samples/xsd">
		<ns:return>150</ns:return>
	</ns:getPriceResponse>

Now, your turn. Do the exercises!!

### Exercise 2 

Create a POJO-based web service that takes two numbers and returns the sum ([solution](https://github.com/cdparra/introsde2013/blob/master/lab2/solutions/Ex2))

### Exercise 3

Expose the HealthProfileReader through an axis2 web service ([solution](https://github.com/cdparra/introsde2013/blob/master/lab2/solutions/Ex3))
