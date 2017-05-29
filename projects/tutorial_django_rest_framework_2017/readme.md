# Django REST Framework Tutorial

Django Rest Framework (or simply DRF) is a powerful module for building web APIs. It's very easy to build model-backed APIs that have authentication policies and are browsable. 

**Why DRF?**

* Authentication – From basic and session-based authentication to token-based and Oauth2 capabilities.
* Serialization – It supports both ORM and Non-ORM data sources and seemlessly integrates with your database.
* Great Documentation – If you get stuck somewhere, you can refer to it's vast online documentation and great community support
* Heroku, Mozilla, Red Hat, and Eventbrite are among the top companies using DRF in their APIs.

## Installation and Setup

* Install Python
* Install pip tool for Python
* Install Django **(sudo pip install django)**
* We can test django with the Python CLI:
	``` 
    >>> import django
	>>> print(django.get_version())
	1.11
	```
* Install Django REST Framework **(pip install djangorestframework)**
* Create a new Django project **(django-admin startproject <project-name>)**
* Enter the newly created directory and open the file settings.py. Here we will search for the "INSTALLED_APPS" section and we will add the line "'rest_framework',"
	``` 
    INSTALLED_APPS = (
    	...
    	'rest_framework',
	)
	```
* We can verify DRF was correctly implemented running the server **(python manage.py runserver)**
* Open your web browser and try to access the development site running on your machine **(127.0.0.1:8000 by default)**
* We can now begin to development of the DRF project.

## Creating the Rest API app

In Django, we can create multiple apps that integrate to form one application. We will begin by creating our first app.

* Enter your project directory and create your app **(python manage.py startapp <app-name>)**. The *startapp* command lets you create an app. So far, you should have a folder name *<app-name>* and *<project-name>*.
* We need to integrate the new app to the project, so we will edit the **settings.py** file again. This time we will add the line "'<app-name>',' to the list of INSTALLED_APPS.
* Now we will define some models. These classes are entities and attributes that we will migrate to our databse later on. For this course, we will use *Person and Health Profiles* example from the IntroSDE course. So we will define a Person's model and their Health Profile and connect them with a foreign key in a file called models.py in our app folder.

**<project-name>/<app-name>/models.py**

	from django.db import models

    """Here we define the models we will be using in our project.
    These classes will be migrated to the database later."""
    class Person(models.Model):
        #first name of person
        firstname = models.CharField( max_length = 30 )
        #last name of person
        lastname = models.CharField( max_length = 30 )
        def __str__(self):
            #Return a human readable representation of the model instance.
            return '%s %s' % (self.firstname, self.lastname)
    
    class HProfile(models.Model): 
        #weight of person
        weight = models.FloatField()
        #height of person
        height = models.FloatField()
        #foreign key of person
        person = models.ForeignKey(Person)
        def __str__(self):
            #Return a human readable representation of the model instance.
            return '%0.2f m %0.2f kg' % (self.height, self.weight)
