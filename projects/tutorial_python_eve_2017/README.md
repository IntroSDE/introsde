
# Python EVE
[Website][3]

## Setup
* Download and install [Python][1] (Remember to set up the PATH variables)
* Install Eve with pip:
```bash
python -m pip install eve
```
* Download and install [MongoDB][2] (Do not forget about the PATH variables)
* Create directories for your database and log files:
```
mkdir C:\data\db
mkdir C:\data\log
```
* To start MongoDB using all defaults, issue the following command at the system shell:
```
mongod
```

## Minimal Application
### Launch Script
Create a file named run.py with the following content:
```python
from eve import Eve
app = Eve()

if __name__ == '__main__':
    app.run()
```
### Configuration File
Create a file named settings.py (save it in the same directory as run.py) with the following content:
```python
DOMAIN = {'lifecoach/person': {}}
```

* Start an instance of MongoDB
```
$ mongod
```
* Launch the API
```
$ python run.py
```
* Consume the API
```
$ curl http://127.0.0.1:5000/ | python -m json.tool
$ curl http://127.0.0.1:5000/lifecoach/person | python -m json.tool
```

## A More Complex Application
### Database

Connect to a database by adding the following lines to settings.py

```python
MONGO_HOST = 'localhost'
MONGO_PORT = 27017
IF_MATCH = False

MONGO_DBNAME = 'lifecoach'
```

### Schema

Define a schema for our person resource by adding the following lines to settings.py

```python
schema = {
    'firstname': {
        'type': 'string',
    },
    'lastname': {
        'type': 'string',
    },
    'healthprofile':{
        'type': 'dict',
        'schema': {
            'weight': {'type': 'number'},
            'height': {'type': 'number'}
        },
    },
}
```

### Endpoint

Add the person definition to the settings.py file

```python
person = {
    'resource_methods': ['GET', 'POST'],
    'item_methods': ['GET', 'PUT', 'DELETE'],

    'schema': schema
}
```

### Domain

**Update** the domain definition
```python
DOMAIN = {
    'lifecoach/person': person,
}
```

Make sure in the settings.py file the schema, the endpoint, and the domain are defined in that order since the latter uses the definition of the former.

* Save settings.py and launch run.py
```
python run.py
```

* Insert documents at the lifecoach/person endpoint (POST /lifecoach/person)
```
 curl -d '{"firstname": "John", "lastname": "Doe", "healthprofile":{"weight": "72", "height": "1.72"}}' -H 'Content-Type: application/json' http://127.0.0.1:5000/lifecoach/person | python -m json.tool
```

* Get all the documents at the lifecoach/person endpoint (GET /lifecoach/person)
```
curl http://127.0.0.1:5000/lifecoach/person | python -m json.tool
```

* Get a specific document at the lifecoach/person endpoint (GET /lifecoach/person/{_id})
```
curl http://127.0.0.1:5000/lifecoach/person/59349fc47793e72a88d48cd5 | python -m json.tool
```

Remember to use the appropiate value for the {\_id} field.

* Update a specific document at the lifecoach/person endpoint (PUT /lifecoach/person/{personId})

```
curl -X PUT -d "firstname=Joe" http://127.0.0.1:5000/lifecoach/person/59349fc47793e72a88d48cd5 | python -m json.tool
```

Remember to use the appropiate value for the {\_id} field.

* Remove a specific document from the lifecoach/person endpoint (DELETE /lifecoach/person/{personId})
```
curl -X DELETE http://127.0.0.1:5000/lifecoach/person/59349fc47793e72a88d48cd5
```

Remember to use the appropiate value for the {\_id} field.


[1]: https://www.python.org/
[2]: https://www.mongodb.com/
[3]: http://python-eve.org/
[4]: http://python-eve.org/features.html#sub-resources
[5]: http://python-eve.org/features.html#document-versioning
