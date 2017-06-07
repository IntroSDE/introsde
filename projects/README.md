NANCY.NET
The main inspiration is the Sinatra framework for Ruby and, hence, Nancy was named after the daughter of Frank Sinatra :) Many people wonder what Fx in NancyFx means so here it is (drum roll); it just means framework! NancyFx is the name of the umbrella project that contains all the components.
Nancy is a lightweight, low-ceremony, framework for building HTTP based services on .NET and Mono. The goal of the framework is to stay out of the way as much as possible and provide a super-duper-happy-path to all interactions.
 
Before the lab
Set environment:
#Installation
-visual studio 2010 or higher, you can download on the official page of Microsoft:
 
https://www.visualstudio.com/es/?rr=https%3A%2F%2Fwww.google.com.py%2F
For the installation of visual studio:
-Install the ASP.NET package.
It takes time the installation and when everything is installed we can start the lab.
Other program we need is fiddle so you can test the PUT, DELETE and POST
The installation its very simple just download from this page:
https://www.telerik.com/download/fiddler
For more information you can read in this page of microsoft https://docs.microsoft.com/en-us/azure/search/search-fiddler
 
Getting Started: Install packages
Let's start exploring this framework. Open Visual Studio, and then create an "ASP.Net Empty Web Site" from "File" -> "New" -> "Project..." -> Web Site.
Now we need to install the Nancy Framework before starting coding, for that first ensure that the NuGet Packager is installed for your Visual Studio.
The NuGet Package Manager in Visual Studio is a GUI tool for managing packages and includes a PowerShell console through which you can use certain NuGet commands directly within Visual Studio. The Package Manager UI and Console are both included with Visual Studio 2012 and later and can be installed manually for earlier versions.
You can go to this page for more information: https://docs.microsoft.com/en-us/nuget/guides/install-nuget
 
Once NuGet is installed, open the "Package Manager Console" from "Tools" -> "Library Package Manager" and run the following commands one after another. We can select the default project to which the installation should happen. 

