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
    
    def perform_create(self, serializer):
        serializer.save()

"""This class show the entire history of Health Profile for a person"""
class HProfileViewHistory(generics.ListCreateAPIView):
    serializer_class = HProfileSerializer

    def get_queryset(self):
        """
        This view should return a list of all the health profiles for a 
        person by the id provided in the URL.
        """
        pk = self.kwargs['pk']
        return HProfile.objects.filter(person_id=pk)
    
    def perform_create(self, serializer):
        serializer.save()


"""Classes that list all Health Profiles and edit them"""

class HProfileViewAll(generics.ListCreateAPIView):
    """This class defines the create behaviour of our rest api."""
    queryset =  HProfile.objects.all()
    serializer_class = HProfileSerializer

    def perform_create(self, serializer):
        """Save the post data when creating a new bucketlist."""
        serializer.save()

class HProfileViewEdit(generics.RetrieveUpdateDestroyAPIView):
    """This class handles the http GET, PUT and DELETE requests."""

    queryset = HProfile.objects.all()
    serializer_class = HProfileSerializer


