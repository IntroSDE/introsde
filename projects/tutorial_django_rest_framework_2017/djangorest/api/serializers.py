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
