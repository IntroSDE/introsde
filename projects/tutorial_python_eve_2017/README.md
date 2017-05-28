
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

[1]: https://www.python.org/
[2]: https://www.mongodb.com/
[3]: http://python-eve.org/
