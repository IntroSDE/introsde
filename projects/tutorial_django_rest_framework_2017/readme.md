# Django REST Framework Tutorial

Django Rest Framework (or simply DRF) is a powerful module for building web APIs. It's very easy to build model-backed APIs that have authentication policies and are browsable. 

**Why DRF?**

* Authentication – From basic and session-based authentication to token-based and Oauth2 capabilities.
* Serialization – It supports both ORM and Non-ORM data sources and seemlessly integrates with your database.
* Great Documentation – If you get stuck somewhere, you can refer to it's vast online documentation and great community support
* Heroku, Mozilla, Red Hat, and Eventbrite are among the top companies using DRF in their APIs.

## Our goals on this course
#### The Simple Health Profile API

* **GET /lifecoach/person**: return the list of people in the database, with their health profiles, which should include at least weight and height.
* **GET /lifecoach/person/{personId}**: returns the record for person with id 'personId'.
* **GET /lifecoach/person/{personId}/health-profile**: returns the health-profile of 'personId'.
* **POST /lifecoach/person**: creates a new person in the system.
* **PUT /lifecoach/person/{personId}**: update information about the person.
* **DELETE /lifecoach/person/{personId}**: deletes a person and her health profile.
* **Exercise: GET/lifecoach/person/{personId}/health-profile/history**: returns the history of previous health profile values. For this, the learner has to add the support for storing the old health-profile value every time a there is an update throught endpoint 5.
* **Exercise: POST /lifecoach/person/{personId}/health-profile**: creates a new health-profile value for the person pushing his the current values to the history.

The last two points are meant to excerises for the reader. The solution is included in the source code of this repository.

## Installation and Setup

* Install Python.
* Install pip tool for Python.
* Install Django with `sudo pip install django`.
* We can test django with the Python CLI:
	```python
    >>> import django
	>>> print(django.get_version())
	1.11
	```
* Install Django REST Framework with `pip install djangorestframework`.
* Create a new Django project with `django-admin startproject <project-name>`. I will use **"djangorest"** as the project name in this course.
* Enter the newly created directory and open the file `settings.py`. Here we will search for the `INSTALLED_APPS` section and we will add the line `'rest_framework',`.
	```python
    INSTALLED_APPS = (
    	...
    	'rest_framework',
	)
	```
* We can verify DRF was correctly implemented running the server with `python manage.py runserver`.
* Open your web browser and try to access the development site running on your machine **(127.0.0.1:8000** by default **)**.
* We can now begin to development of the DRF project.

## Creating the Rest API app

In Django, we can create multiple apps that integrate to form one application. We will begin by creating our first app.

* Enter your project directory and create your app with `python manage.py startapp <app-name>`. The *startapp* command lets you create an app. I will use the name **"api"** as the app name on this course. So far, you should have a folder named `djangorest` and another one named `api`.
* We need to integrate the new app to the project, so we will edit the `settings.py` file again. This time we will add the line `'api',` to the list of **INSTALLED_APPS**.
* Now we will define some models. These classes are entities and attributes that we will migrate to our databse later on. For this course, we will use *Person and Health Profiles* example from the *[IntroSDE course](https://github.com/IntroSDE/introsde/tree/master/lab06)*. So we will define a Person's model and their Health Profile and connect them with a foreign key in a file called `models.py` in our app folder.

    **djangorest/api/models.py:**
    
    ```python
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
    ```

* Migrations are Django’s way of propagating changes you make to your models (like adding a field, deleting a model, etc.) into your database schema. Now that we have a rich model in place, we need to tell the database to create the relevant schema. To do this, we run `python manage.py makemigrations`.
* Then apply the changes with `python manage.py migrate`.    
* Now we will create the serializers for the app. Serializers serialize and deserialize data. So what's all this about, you ask? Serializing is changing the data from complex querysets from the DB to a form of data we can understand, like JSON or XML. Deserializing is reverting this process after validating the data we want to save to the DB.
* We will create a file `serializers.py` in our `api/` folder.

    **djangorest/api/serializers.py:**
    ```python
    from rest_framework import serializers
    from .models import Person, HProfile
    
    """To perform HTTP-based interaction we have to define rules to convert Django models (python objects) to the json strings and vice versa. This is a serialization/deserialization task."""
    class HProfileSerializer(serializers.ModelSerializer):
        class Meta:
            model=HProfile
            fields = '__all__'
    
    class PersonSerializer(serializers.ModelSerializer):
        class Meta:
            model=Person
        fields = '__all__'
    ```
* We will now define the views for the app. A viewset is a set of views (controllers in traditional MVC terminology). If you take a look at the ModelViewSet code you’ll see that there’s a lot of functionality automatically added there. You are able to create, view, edit and delete objects in your system (and database). It’s a full CRUD set with http as the interface protocol. Let's configure a few views for our app in the `views.py` file.

    **djangorest/api/views.py:**
    ```python
    from django.shortcuts import render
    from rest_framework import generics, viewsets
    from .serializers import PersonSerializer, HProfileSerializer
    from .models import Person, HProfile
    
    """This class shows all the people"""
    class PersonViewAll(generics.ListCreateAPIView):
        queryset =  Person.objects.all()
        serializer_class = PersonSerializer
    
        def perform_create(self, serializer):
            serializer.save()
    
    """This class handles the http GET, PUT and DELETE requests for Person"""
    class DetailsPersonView(generics.RetrieveUpdateDestroyAPIView): 
        queryset = Person.objects.all()
        serializer_class = PersonSerializer
    
    """This class shows the history of Health Profiles of a person with the latest on top"""
    class HProfileViewLast(generics.ListCreateAPIView):
        serializer_class = HProfileSerializer
    
        def get_queryset(self):
            """
            This view should return a list of health profiles for a 
            person by the id provided in the URL.
            """
            pk = self.kwargs['pk']
            return HProfile.objects.filter(person_id=pk).order_by('-id')
            
    """Classes that list all Health Profiles and allow to POST them in the database."""
    class HProfileViewAll(generics.ListCreateAPIView):
        """This class defines the create behaviour of our rest api."""
        queryset =  HProfile.objects.all()
        serializer_class = HProfileSerializer
    
        def perform_create(self, serializer):
            """Save the post data when creating a new bucketlist."""
            serializer.save()
    ```
* Lastly, we will configure the URLs for our API. Think of URLs as an interface to the outside world. If someone wants to interact with our web API, they'll have to use our URL. Create a `urls.py` in the `api/` directory.
    
    **djangorest/api/urls.py:**
    ```python
    from django.conf.urls import url, include
    from rest_framework.urlpatterns import format_suffix_patterns
    from .views import PersonViewAll, HProfileViewAll, DetailsPersonView, HProfileViewLast
    
    """Defined URLs for the classes in views.py"""
    urlpatterns = {
            url(r'^lifecoach/person/$', PersonViewAll.as_view(), name="create"),
            url(r'^lifecoach/person/(?P<pk>[0-9]+)/$', DetailsPersonView.as_view(), name="details"),
            url(r'^lifecoach/person/(?P<pk>[0-9]+)/health-profile/$', HProfileViewLast.as_view(), name="details"),
            url(r'^lifecoach/health-profiles/$', HProfileViewAll.as_view(), name="create"),
    }

    urlpatterns = format_suffix_patterns(urlpatterns)
    ```
* Create a `urls.py` for the main app in `djangorest/`.

    **djangorest/djangorest/urls.py:**
    ```python
    from django.conf.urls import url, include
    from django.contrib import admin
    
    urlpatterns = [
        url(r'^admin/', admin.site.urls),
        url(r'^', include('api.urls'))
    ]
    ```
* We can now test the service. Run `python manage.py runserver` to start the server and access the URLs from your web browser.
    * **127.0.0.1/lifecoach/person** - Lists all the people in the database.
    * **127.0.0.1/lifecoach/person/{personId}** - Lists a specific person.
    * **127.0.0.1/lifecoach/person/{personId}/health-profile** - Lists a specific person's healt profile.
    * **127.0.0.1/lifecoach/health-profiles** - Lists all the health profiles in the database.


## Additional Info
If you wish to find more information, here are some sites to help you:
* http://www.django-rest-framework.org/tutorial/quickstart/
* https://docs.djangoproject.com/en/1.11/ref/
