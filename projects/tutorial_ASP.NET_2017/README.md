# ASP.NET Web REST Framework

## Introduction

This tutorial is based on **Lab06** for its implementation in the **ASP.NET Web Framework**. We will use the **classes** previously defined on **Lab06** and then we will adapt them to the **ASP.NET Web Framework**

For this project, we will use **[Microsoft Visual Studio Community 2017][1]** as our **IDE**

## Before start coding

### Work enviroment setup

* First [download][1] and install **Microsoft Visual Studio Community 2017** in your prefered platform
* Make sure you enabled the **ASP.NET Web App Development** package when installing the IDE
* [Download][2] all the contents in **introsde/lab06/Examples/.../ehealth** from **Lab06** to your home directory

### Create an ASP.NET Web Application template

* When you already downloaded and installed **Microsoft Visual Studio Community 2017** open it and go to **New Project** on the **home page**. A new window will pop up
* Look at the leftmost column and deploy the **▼Templates** section
* Look for and then deploy the **▼Visual C#** section
* In the **Visual C#** content area, look for and then deploy the **▼Windows** section
* In the **Windows** area, look for and select the **Web** option
* In the middle section of the **New Project** window, select **ASP.NET Web Application (.NET Framework)**
* Choose a project name and path and then press **Accept**. At the end of this step, we will be successfully created our **ASP.NET Web Framework** template

## Developing

### Adding objects to the project: Classes

* Once the project is created, look at the rightmost upperside column, and then select and right-click on the **▼Models** section
* Go to **Add** and then select **Class**. A new **Add new element** window will pop up
* In the center area of the **Add new element** window, select **Class**
* Choose **PersonCR** as name and press **Accept**. A new class called **PersonCR.cs** will be added to our project. The class **PersonCR.cs** is a **ASP.NET** implementation of the **PersonCollectionResource.java** file in **introsde/lab06/Examples/.../ehealth/resources/**
* Choose **HealthProfile** as name and press **Accept**. A new class called **HealthProfile.cs** will be added to our project. The class **HealthProfile.cs** is a **ASP.NET** implementation of the **HealthProfileResource.java** file in **introsde/lab06/Examples/.../ehealth/resources/**
* Choose **PersonController** as name and press **Accept**. A new class called **PersonController.cs** will be added to our project. The class **PersonController.cs** is a **ASP.NET** implementation of the **PersonResource.java** file in **introsde/lab06/Examples/.../ehealth/resources/**. The code in **PersonController.cs** determines the way we interact with the **classes** from the front-end

### Classes contents: PersonCR.cs

* The content in **PersonCR.cs** look like this:
```C#
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using lab06.Models;

namespace lab06.Models
{
    public class PersonCR
    {
        public string firstname;
        public string lastname;
        public HealthProfile hProfile;
        public string birthdate;
        public long personId;

        public PersonCR(long personId, string fname, string lname, string birthdate, HealthProfile hp)
        {
            this.personId = personId;
            this.firstname = fname;
            this.lastname = lname;
            this.birthdate = birthdate;
            this.hProfile = new HealthProfile();
        }

        public PersonCR()
        {
            this.firstname = "Pinco";
            this.lastname = "Pallino";
            this.hProfile = new HealthProfile();
            this.personId = 1;
            this.birthdate = "24/02/1998";
        }
    }
}
```

### Classes contents: HealthProfile.cs

* The content in **HealthProfile.cs** look like this:
```C#
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace lab06.Models
{
    public class HealthProfile
    {
        private double weight; // in kg
        private double height; // in m

        public HealthProfile(double weight, double height)
        {
            this.weight = weight;
            this.height = height;
        }

        public HealthProfile()
        {
            this.weight = 85.5;
            this.height = 1.72;
        }

        
    }
}
```
### Classes contents: PersonController.cs

* **GET /api/person:**
```C#
public IEnumerable<PersonCR> GetAllPersons()
    {
        return persona;
    }
```

* **GET /api/person/{personId}:**
```C#
public IHttpActionResult GetPersonid(int id)
{
    var pers = persona.FirstOrDefault((p) => p.personId == id);
    if (pers == null)
    {
        return NotFound();
    }
        return Ok(pers);
}
```

* **GET /api/person/{personId}/health-profile:**
```C#
public IHttpActionResult Get(long perId)
{
    var pers = persona.FirstOrDefault((p) => p.personId == perId);
    if (pers == null)
    {
        return NotFound();
    }

        return Ok(pers.hProfile);
}
```

* **POST /api/person:**
```C#
public void Post(PersonCR pers)
{
    Array.Resize(ref persona, persona.Length + 1);
    persona[persona.Length-1] = new PersonCR(pers.personId,pers.firstname,pers.lastname,pers.birthdate);
}
```

* **PUT /api/person/{personId}:**
```C#
public void Put(int id, PersonCR perso)
{
    var pers = persona.FirstOrDefault((p) => p.personId == id);
    if (pers != null)
    {
        pers.firstname = perso.firstname;
        pers.lastname = perso.lastname;
        pers.birthdate = perso.birthdate;       
    }
}
```

* **DELETE /api/person/{personId}:**
```C#
public void Delete(int id)
{
    Array.Clear(persona, id, 1);        
}
```

* **PersonController.cs** entire content:
```C#
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using lab06.Models;

namespace lab06.Models
{
    public class PersonController : ApiController
    {
        public PersonCR[] persona = new PersonCR[]
        {
            new PersonCR()
        }

        public IEnumerable<PersonCR> GetAllPersons()
        { 
            return persona;
        }
        
        public IHttpActionResult GetPersonid(int id)
        {
            var pers = persona.FirstOrDefault((p) => p.personId == id);
            if (pers == null)
            {
                return NotFound();
            }
            return Ok(pers);
        }

        public IHttpActionResult Get(long perId)
        {
            var pers = persona.FirstOrDefault((p) => p.personId == perId);
            if (pers == null)
            {
                  return NotFound();
            }
            return Ok(pers.hProfile);
        }

        public void Post(PersonCR pers)
        {
            Array.Resize(ref persona, persona.Length + 1);
            persona[persona.Length-1] = new PersonCR(pers.personId,pers.firstname,pers.lastname,pers.birthdate);
        }

        public void Put(int id, PersonCR perso)
        {
            var pers = persona.FirstOrDefault((p) => p.personId == id);
            if (pers != null)
            {
                pers.firstname = perso.firstname;
                pers.lastname = perso.lastname;
                pers.birthdate = perso.birthdate;
            }
        }
        
        public void Delete(int id)
        {
            Array.Clear(persona, id, 1);
        }
    }
}
```

## Finishing

* To test our API, just click on the **▶ Play** button located in the toolbar
* We can test specific options like **GET /api/person/{personId}/health-profile** by typing **/api/person/{personId}/health-profile** next to the address in the browser address field. Example: http://localhost:50528/api/person/1/health-profile

This document was made by Eusebio Gomez and Gabriel Pastore on June 2017

[1]:https://www.visualstudio.com/vs/community/
[2]:https://github.com/eu1793/introsde/tree/master/lab06/Examples/src/introsde/rest/ehealth
