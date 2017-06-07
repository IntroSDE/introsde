# NANCY.NET
The main inspiration is the Sinatra framework for Ruby and, hence, Nancy was named after the daughter of Frank Sinatra :) Many people wonder what Fx in NancyFx means so here it is (drum roll); it just means framework! NancyFx is the name of the umbrella project that contains all the components.
Nancy is a lightweight, low-ceremony, framework for building HTTP based services on .NET and Mono. The goal of the framework is to stay out of the way as much as possible and provide a super-duper-happy-path to all interactions.
 
## Before the lab
**Set environment:**
two installation:
* visual studio
* fiddler

Install visual studio 2010 or higher, you can download on the official page of [Microsoft](https://www.visualstudio.com/es/?rr=https%3A%2F%2Fwww.google.com.py%2F).

* For the installation of visual studio:
##### Install the ASP.NET package.
It takes time the installation and when everything is installed we can start the lab.
Other program we need is `fiddler` so you can test the PUT, DELETE and POST
* The installation its very simple just download from this page:
https://www.telerik.com/download/fiddler
For more information you can read in this page of microsoft: [fildder](https://docs.microsoft.com/en-us/azure/search/search-fiddler).
 
## Getting Started: Install packages
Let's start exploring this framework. Open Visual Studio and  then: 
``` create an "ASP.Net Empty Web Site" from "File" -> "New" -> "Project..." -> Web Site.```
Now we need to install the Nancy Framework before starting coding, for that first ensure that the NuGet Packager is installed for your Visual Studio.
`The NuGet Package Manager` in Visual Studio is a GUI tool for managing packages and includes a PowerShell console through which you can use certain NuGet commands directly within Visual Studio. The Package Manager UI and Console are both included with Visual Studio 2012 and later and can be installed manually for earlier versions.
You can go to this page for more information: https://docs.microsoft.com/en-us/nuget/guides/install-nuget
 
Once NuGet is installed, open:
`"Package Manager Console" from "Tools" -> "Library Package Manager"` and run the following commands one after another. We can select the default project to which the installation should happen. 
`PM > Install-Package Nancy`
We will get a successful installation message like:
```
'Nancy 1.4.3' was successfully installed on WebApp the execution of
NuGet took 425,21 ms
```
And at the same time another new file named packages.config will be added to the project. This file contains the framework version information. Whenever we add more Nancy packages this file will be updated with those new lists.
Also Nancy handler's information will
be updated into the web.config. Through these handlers Nancy runs and executes
the application.

###### Getting Started: Write Code
Add a class file and give a name, here the example **“MainModule.cs"** has the following lines of codes.

we have 3 classes:
1. MainModule.cs   the principal
2. Person.cs       the data structure of person
3. HealthP.cs       data structure of healthprofile

1. MainModule.cs : add this in your main module 
``` 
using Nancy;
using System;
 
namespace WebApplication1
{
 private static List<Person> persons = new List<Person>()
    	{
        	new Person() { Name = "Kierkegaard", Lastname = "Shaw", Status = "Inflated"},
        	new Person() { Name = "Kierkegaard2", Lastname = "Shaw2", Status = "Inflated2"}
    	};
    	public MainModule()
    	{
        	Get["/person"] = Personlist;
 
    	}
    	private dynamic Personlist(dynamic parameters)
    	{
        	return Response.AsJson(persons);
    	}
}
```
2. Person.cs
```
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
namespace WebApplication1
{
    public class Person
    {
        public string Name { get; set; }
        public string Lastname { get; set; }
        public string Birthdate { get; set; }
        public double Weight { get; set; }
        public double Height { get; set; }
        public double Bmi { get; set; }

    }
}
```

3. HealthP.cs
```
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication1
{
    public class HealthP
    {
        public string Name { get; set; }
        public string Lastname { get; set; }
        public string Birthdate { get; set; }

    }
}
```
A small change could be:
 
 
``` 
using Nancy;
using Nancy.ModelBinding;
 
namespace WebApplication1
{
 
public class MainModule : NancyModule
	{
    	private static List<Person> persons = new List<Person>()
    	{
        	new Person() { Name = "Kierkegaard", Lastname = "Shaw", Status = "Inflated"},
        	new Person() { Name = "Kierkegaard2", Lastname = "Shaw2", Status = "Inflated2"}
    	};
 
 
 
    	public MainModule()
    	{
        	Get["/person"] = Personlist;
 
        	Get["/person/{id}"] =  PersonId;
 
                           	Post["/persons"] = parameters =>
        	{
            	var model = this.Bind<Person>();
            	persons.Add(model);
            	return Response.AsJson(persons);
        	};
    	}
    	private dynamic Personlist(dynamic parameters)
    	{
            return Response.AsJson(persons);
    	}
     //here add this funtion
     private dynamic PersonId(dynamic parameters)
    	{
       	int id = (parameters.id -1);
       	return Response.AsJson(persons.ElementAt(id));
        	
    	} 
	}
}
```

**We have to edit MainModule by add PUT and DELETE**

``` 
public MainModule()
    	{
        	Get["/person"] = Personlist;
        	Get["/person/{id}"] =  PersonId;
            Get["/person/{id}/health-profile"] = PersonHealth;
        	Post["/person"] = PostPerson;
        	Put["/person/{id}"] = PutPerson;
        	Delete["/person/{id}"] = DeletePerson;
    	}
 ```
 
>  Now we add the modules PutPerson and DeletePerson

It can be add in the same module or add a new class for each code, for example add new class named Putperson and copy the code **private dynamic PutPerson(dynamic parameters)**
It will be the same for **private dynamic DeletePerson(dynamic parameters)** or just leave in the same module class as we are.
 
 ```
 private dynamic PutPerson(dynamic parameters) //PUT update information about the person
    	{
        	int id = (parameters.id - 1);
        	var model = this.Bind<Person>();
        	//persons.Add(model);
        	persons.RemoveAt(id);
        	persons.Insert(id, model);
        	return Response.AsJson(persons);
	    }
``` 

```
    	private dynamic DeletePerson(dynamic parameters) //DELETE deletes a person and her health profile
    	{
        	int id = (parameters.id - 1);
        	//persons.RemoveAt(id);}
        	var itemToRemove = persons.ElementAt(id);
        	if (itemToRemove != null)
            	persons.Remove(itemToRemove);
        	return Response.AsJson(persons);
    	}
```
## Code Explained
The `"NancyModule"` class is
referenced from the Nancy library and inherited into "MainModule".
Like Controllers in MVC there are Modules in Nancy. A Module defines the
behavior of the application. A single Module must be defined for a Nancy
application, which is the starting point of an application. We can create any
number of Modules inherited from NancyModule. MainModule is the constructor
having two routes defined, `Get["/"]` and
`Get["/Person/{id}"]`.  GET, POST, PUT, DELETE, HEAD etc. are
Methods supported by Nancy.  A Route must follow an exact pattern's like
Literal segments `("/Person/}")`, Capture segments `({id})` and Regular
Expressions.
 
 
 
 
## Getting Started: Run the application
It's done, now just hit F5.
```
Output 1 -> Get["/"], pointing to the root folder.
Output 2 ->
Get[["/Person/{id}"], pointing to a route path.
```
## Test POST, PUT and DELETE with Fiddle
###### Step 1:
>In Composer: Select POST and put `http://localhost:52961/persons` or
 Select PUT and put `http://localhost:52961/person/id` or
Select DELETE and put `http://localhost:52961/person/id`
In the Request headers tab, enter a
JSON content type.
`Content-Type:application/json; charset=utf-8`
You need more info here but Fiddler
will fill the rest for you when you hit execute.
Put your JSON string array in the Request Body
* For POST method:
`:{"Name":"Andrea","Lastname":"Shaw3","Birthdate":"Inflated3",”Weight”:85.5, “Height”:1.72}`
 
 
 
* For DELETE method:
In the composer select DELETE and  put `http://localhost:52961/person/id` , the id you pick for example delete the first person on the list so we will put `http://localhost:52961/person/1`
`The list id 1 is: [{"name":"Kierkegaard","lastname":"Shaw","birthdate":"Inflated"}]`
 
After enter you can check if the person was deleted put in the browser `http://localhost:52961/person` and should print this list:
 `[{"name":"Kierkegaard2","lastname":"Shaw2","birthdate":"Inflated2","weight":85.5,"height":1.72,"bmi":28.9007571660357}]`
 


