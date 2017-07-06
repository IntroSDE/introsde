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

