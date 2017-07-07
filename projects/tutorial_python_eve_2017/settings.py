
MONGO_HOST = 'localhost'
MONGO_PORT = 27017

MONGO_DBNAME = 'lifecoach'
IF_MATCH = False

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

person = {
    'resource_methods': ['GET', 'POST'],
    'item_methods': ['GET', 'PUT', 'DELETE'],
    'schema': schema
}

DOMAIN = {
    'lifecoach/person': person,
}
