# Tutorial Slim + Doctrine
## 1. Introducción
En el tutorial vamos a desarrollar una servicio Web utilizando el framewokr Slim para PHP.
## 2. Preparación del ambiente de trabajo (30 min)
### 2.1. Instalación de Composer
Composer es una manejador de dependencias, que nos va a permitir instalar todas las librerias (y sus dependencias) que vamos a necesitar en el proyecto. Para instalar composer se debe acceder al [sitio oficial](https://getcomposer.org) y seguir las instrucciones de instalación. Composer esta disponible tanto para Windows, Linux y MACOS.

Una vez que hayamos instalado (siguiendo las instrucciones del sitio oficial), debemos comprobar que el mismo funciona correctamente abriendo la consola del sistema y escribiendo el siguiente comando:
```
C:\Users\username>composer -V
Composer version 1.0.0 2016-01-10 20:34:53
```
### 2.2. Instalación de Postgresql 
Como repositorio de datos, vamos a utilizar Postgresql. Una base de datos relacional y orientada a objetos libre.
Se puede descargar e instalar, siguiendo las instrucción del [sitio oficial](https://www.postgresql.org/).
### 2.3. Instalación de PHP
Necesitaremos PHP, para realizar pruebas. Podemos descargarlo en el [sitio oficial](http://windows.php.net/download/). Para este tutorial utilizaremos la versión 5.6.
También necesitamos que se encuentre habilitada la extensión PDO. Las instrucciones para instalar se pueden ver [aquí](http://php.net/manual/es/book.pdo.php).

Para verificar que PHP se encuentra correctamente instalado, probamos lo siguiente desde la consola de comandos:
```
C:\Users\migue> php -v
PHP 5.6.28 (cli) (built: Nov  9 2016 06:40:27)
Copyright (c) 1997-2016 The PHP Group
Zend Engine v2.6.0, Copyright (c) 1998-2016 Zend Technologies
```
### 2.4. Instalación de Postman
Postman es un cliente REST, que nos va a permitir probar nuestro servicio web. Se puede descargar desde el [sitio oficial](https://www.getpostman.com/).
### 2.5. Instalación de ATOM (opcional)
A fin de codificar mas cómodamente, recomiento instalar in IDE. Propongo [ATOM](https://atom.io/), que es un IDE open source y altamente customizable.
Se puede descargar del sitio oficial y está disponible para Linux, Windows y MACOS.


## 3. Instalación de SLIM, DOCTRINE y RESPECT
Para empezar nos ubicamos en la carpeta del proyecto y generamos la siguiente estructura:
```
├── proyecto
│   └── src
│       └── public
│       └── endpoints
│       └── entity
```
La carpeta public, sera la raiz de nuestro sitio. La carpeta endpoints, contendrá las defininiciones de los endpoints. La carpeta entity contendrá las clases.
Luego creamos en la raiz de la carpeta un archivo *composer.json* con el siguiente contenido:
```
{
    "require": {
        "slim/slim": "*",
        "doctrine/orm": "*",
        "respect/validation": "*"
    },
    "autoload": {
        "psr-4": {"Custom\\": "src/"}
    }
}
```
Le indicamos al composer, que instale Slim que es el framework para generar el web service. Doctrine es un mapeador de objetos-relacional (ORM) escrito en PHP que proporciona una capa de persistencia para objetos PHP. Respect es una librería para validar datos.
Instalamos los paquetes con el siguiente comando, desde la consola:
```
composer update
```
En la carpeta vendor, se encuentran todas las librerías necesarias para ejecutar el framework. El archivo composer contiene las definiciones de las dependencias que estan instaladas.
Si abrimos el archivo composer, tiene la siguiente estructura:
```
{
    "require": {
        "slim/slim": "3.0"
    }
}
```
Creamos un archivo *index.php* en *src/public*:
```
<?php

//establecemos los espacios de nombres
use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;
use \Doctrine\ORM\Tools\Setup;
use \Doctrine\ORM\EntityManager;
use \Respect\Validation\Validator as v;

//hacemos el autoload de las librerias
require '../../vendor/autoload.php';

//Doctrine
$paths = array(__DIR__ . "/src");
$isDevMode = false;
//aca especificamos los datos de la conexion a la base de datos
$dbParams = array(
    'driver' => 'pdo_pgsql',
    'host' => '127.0.0.1',
    'user' => 'postgres',
    'password' => 'postgres',
    'dbname' => 'slimphp',
);
$configDoctrine = Setup::createAnnotationMetadataConfiguration($paths, $isDevMode, null, null, false);
$entityManager = EntityManager::create($dbParams, $configDoctrine);

//Slim
$app = new \Slim\App;

//endpoints
include_once '../endpoints/endpoints.php'

//creamos el web service
$app->run();
```
Creamos un archivo *endpoints.php* en *src/endpoints*:
```
<?php
$app->get('/index', function ($request,$response) {
    $response->getBody()->write("Hola");
    return $response;
});
```
Luego desde la consolta, ubicados en la raiz del proyecto, ejecutamos el siguiente comando:
```
php -S localhost:80
```
Usando Postman hacemos una solicitud GET al endpoint que hemos creado:
```
http://localhost/src/public/index.php/index
```
Si todo se hizo correctamente, recibiremos lo siguiente:
```
Hola
```

## 4. Endpoints
Ahora ya estamos listos para empezar a crear los endpoints. Seguiremos el estilo de arquitectura REST como protocolo para intercambio de información entre el usuario final y el almacén de datos (base de datos).
REST define un conjunto de operaciones bien definidas que se aplican a todos los recursos de información que son, entre otras, las siguientes:
- GET: Pide una representación del recurso especificado
- POST: Envía los datos para que sean procesados por el recurso identificado. Creación de un recurso.
- PUT: Igual que POST solo que lo utilizaremos para modificar un recurso existente.
- DELETE: Elimina un recurso.

| Operación     | Ruta          | Descripción   |
| ------------- | ------------- | ------------- |
| GET           | /lifecoach/person  |  Devuelve la lista de personas, con sus peso y altura |
| GET           | /lifecoach/person/{personId}  |  Devuelve los datos de la persona |
| GET           | /lifecoach/person/{personId}/health-profile   | Devuelve el perfil de salud de la persona |
| POST           | /lifecoach/person | Crea una nueva persona   |
| PUT           | /lifecoach/person/{personId}  | Actualiza los datos de la persona |
| DELETE        | /lifecoach/person/{personId}  | Elimina a la persona y su perfil de salud |
| GET           | /lifecoach/person/{personId}/health-profile/history | obtiene el perfil de salud historico |
| POST          | /lifecoach/person/{personId}/health-profile | Crea un nuevo perfil de salud |

### 4.1. Clases
Usando Doctrine vamos a crear las dos entidades o clases que vamos a utilizar y que luego serán mapeadas a la base de datos.
#### 4.1.1. Person
En la carpeta *src/entity* creamos un nuevo archivo con el nombre *Person.php*.
En la cabecera declaramos el nombre de la tabla e indicamos que se trata de una entidad.
Luego especificamos las variables de la clase: id, firstName, lastName y birthDate.
También indicamos que esta clase tiene una relación uno a muchos con HealthProfile.
Por ultimo, especificamos el setter y el getter.
```
<?php

namespace Custom\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/**
* Person
* @ORM\Table(name="Person")
* @ORM\Entity
*/
class Person

{
/**
 * @ORM\Column(name="id", type="integer")
 * @ORM\Id
 * @ORM\GeneratedValue(strategy="AUTO")
 */
protected $id;
/**
 * @ORM\OneToMany(targetEntity="\Custom\Entity\HealthProfile", mappedBy="person")
 */
protected $healthProfile;
/**
 * @ORM\Column(name="firstName", type="string", length=255)
 */
private $firstName;
/**
 * @ORM\Column(name="lastName", type="string", length=255)
 */
private $lastName;
/**
 * @ORM\Column(name="birthDate", type="date")
 */
private $birthDate;



public function addHealthProfile(\Custom\Entity\HealthProfile $hp){
  $this->healthProfile[] = $hp;
}


public function __construct() {
        $this->healthProfile = new ArrayCollection();
    }

public
function __get($property)
  {
  if (property_exists($this, $property))
    {
    return $this->$property;
    }
  }

public

function __set($property, $value)
  {
  if (property_exists($this, $property))
    {
    $this->$property = $value;
    }
  }
}

```
#### 4.1.1. HealthProfile
En la carpeta *src/entity* creamos un nuevo archivo con el nombre *HealthProfile.php*.
En la cabecera declaramos el nombre de la tabla e indicamos que se trata de una entidad.
Luego especificamos las variables de la clase: id, wight, height y date (que corresponden al peso, altura en una determinada fecha).
También indicamos que esta clase tiene una relación muchos a uno con Person.
Por ultimo, especificamos el setter y el getter.
```
<?php

namespace Custom\Entity;

use Doctrine\ORM\Mapping as ORM;
/**
 * @ORM\Table(name="HealthProfile")
 * @ORM\Entity
 */

class HealthProfile
{
    /**
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;
    /**
     * @ORM\ManyToOne(targetEntity="\Custom\Entity\Person", inversedBy="healthProfile",cascade={"remove"})
     * @ORM\JoinColumn(name="person_id", referencedColumnName="id")
     */
    protected $person;
    /**
     * @ORM\Column(name="weight", type="decimal")
     */
    private $weight;
    /**
     * @ORM\Column(name="height", type="decimal")
     */
    private $height;
    /**
     * @ORM\Column(name="date", type="date")
     */
    private $date;

    public function setPerson(\Custom\Entity\Person $p) {
      $this->person = p;
    }

    public function __get($property)
    {
        if (property_exists($this, $property)) {
            return $this->$property;
        }
    }
    public function __set($property, $value)
    {
        if (property_exists($this, $property)) {
            $this->$property = $value;
        }
    }
}
```
Una vez que hayamos creado las dos clases, generamos la estructura en la base de datos, con el siguiente comando:
```
php vendor/doctrine/orm/bin/doctrine.php orm:schema-tool:create
```
En este punto, ya podemos utilizar las entidades Person, HealthProfile y manejarlos como objetos PHP.
A modo de ejemplo, vamos a crear una entidad *Person* y luego hacerla persistente en la base de datos.
En este ejemplo, creamos el objeto en *$person* , e asignamos sus atributos y luego lo hacemos persistente en la base de datos.
El resultado de la ejecución de esta porción de código, debe imprimir el id de Person asignado.
```
$person = new \Custom\Entity\Person;
$person->firstName = "Miguel";
$person->lastName = "Torio";
$person->birthDate = new DateTime();
$entityManager->persist($person);
$entityManager->flush();

echo $person->id;
```
Para recuperar un dato específico, podemos hacer:
```
//buscar la Person con id = 30
$person = $entityManager->find('\Custom\Entity\Person', 30);
echo $person->lastName;
```

Ahora, ya podemos empezar a generar los endpoints.
Todos los endpoints los vamos a colocar en *src/endpoints/endpoints.php*.
A modo de ejemplo, tenemos un endpoint:
```
$app->get('/index', function ($request,$response) use($entityManager,$v) {
    $response->getBody()->write("Hola");
    return $response;
});
```
En este endpoint que tiene como ruta */index*, recibe como parámetros un request y un response. El request contiene los datos recibidos y en el response se encapsulan los datos a ser enviados. Además usamos una instancia del entityManager y v que corresponden al ORM Doctrine y al Validador respectivamente. Devolvemos la instancia del response. 

En los ejemplos siguientes, veremos las distintas variaciones del método.

#### 4.2.1. GET lifecoach/person
En este ejemplo realizamos una consulta sql en "duro". Retornamos lalista de todas las personas, con su último perfil de salud.
```
$app->get('/lifecoach/person',
function ($request, $response) use($entityManager, $v)
	{
	$conection = $entityManager->getConnection();
	$sql = "select * from Person P join
          HealthProfile H ON P.id = H.person_id where H.id IN
          (select H2.id from HealthProfile H2 where H2.person_id = P.id order by 1 desc limit 1) ";
	$sqlStatement = $conection->prepare($sql);
	$sqlStatement->execute();
	$data = $sqlStatement->fetchAll();
	return $response->withJson(['data' => $data], 200);
	});
```

#### 4.2.2. GET lifecoach/person/{personId}
En este ejemplo, utilizando el entity manager, buscamos la persona por su id y retornamos los datos.
```
$app->get('/lifecoach/person/{personId}',
function ($request, $response) use($entityManager, $v)
	{
	$personId = $request->getAttribute('personId');
	if ($v::intVal()->validate($personId) == false)
		{
		return $response->withJson(['message' => 'personId debe ser entero'], 400);
		}

	$query = $entityManager->createQueryBuilder()->select('p')->from('\Custom\Entity\Person', 'p')->where('p.id = ?1')->setParameter(1, $personId)->getQuery();
	$data = $query->getArrayResult();
	return $response->withJson(['data' => $data], 200);
	});
```    
#### 4.2.3. GET lifecoach/person/{personId}/health-profile
Nuevamente utilizando el entity manager, buscamos a la persona por su id y luego obtenemos todo su historial de salud.
```
$app->get('/lifecoach/person/{personId}/health-profile',
function ($request, $response) use($entityManager, $v)
	{
	$personId = $request->getAttribute('personId');
	if ($v::intVal()->validate($personId) == false)
		{
		return $response->withJson(['message' => 'personId debe ser entero'], 400);
		}

	$query = $entityManager->createQueryBuilder()->select('hp')->from('\Custom\Entity\HealthProfile', 'hp')->where('hp.id = ?1')->setParameter(1, $personId)->getQuery();
	$data = $query->getArrayResult();
	return $response->withJson(['data' => $data], 200);
	});
```    

#### 4.2.4. POST lifecoach/person
En este ejemplo, primero validamos que se reciban los datos de la pesona y su perfil de salud inicial. Luego insertamos la persona y su perfil de salud utilizando el entity manager.
```
$app->post('/lifecoach/person',
function ($request, $response) use($entityManager, $v)
	{
	$body = $request->getBody();
	$body = json_decode($body, true);

	// validamos firstName

	if ($v::key('firstName', $v::stringType() , true)->validate($body) == false)
		{
		return $response->withJson(['message' => 'debe proporcionar firstName'], 400);
		}

	// validamos lastName

	if ($v::key('lastName', $v::stringType() , true)->validate($body) == false)
		{
		return $response->withJson(['message' => 'debe proporcionar lastName'], 400);
		}

	// validamos birthDate

	if ($v::key('birthDate', $v::date('Y-m-d') , true)->validate($body) == false)
		{
		return $response->withJson(['message' => 'debe proporcionar birthDate en el formato Y-m-d'], 400);
		}

	// validamos height

	if ($v::key('height', $v::floatType() , true)->validate($body) == false)
		{
		return $response->withJson(['message' => 'debe especificar height'], 400);
		}

	// validamos weight

	if ($v::key('weight', $v::floatType() , true)->validate($body) == false)
		{
		return $response->withJson(['message' => 'debe especificar weight'], 400);
		}

	$person = new CustomEntityPerson;
	$person->firstName = $body['firstName'];
	$person->lastName = $body['lastName'];
	$person->birthDate = new DateTime($body['birthDate']);
	$healthProfile = new CustomEntityHealthProfile;
	$healthProfile->weight = $body['weight'];
	$healthProfile->height = $body['height'];
	$healthProfile->date = new DateTime();
	$healthProfile->person = $person;
	$entityManager->persist($person);
	$entityManager->persist($healthProfile);
	$entityManager->flush();
	return $response->withJson(['message' => 'persona insertada', 'id' => $person->id], 200);
	});
```    

#### 4.2.5. DELETE	/lifecoach/person/{personId}
Eliminamos la persona, siempre utilizando el entity manager. Verificamos antes que el id de la persona sea correcto.
```

$app->delete('/lifecoach/person/{personId}',
function ($request, $response) use($entityManager, $v)
	{
	$personId = $request->getAttribute('personId');
	if ($v::intVal()->validate($personId) == false)
		{
		return $response->withJson(['message' => 'personId debe ser entero'], 400);
		}

	$person = $entityManager->find('\Custom\Entity\Person', $personId);
	if ($person == null)
		{
		return $response->withJson(['message' => 'la persona no existe'], 200);
		}

	$entityManager->remove($person);
	$entityManager->flush();
	return $response->withJson(['message' => 'persona eliminada'], 200);
	});
```

#### 4.2.6. GET /lifecoach/person/{personId}/health-profile/history
Utilizando el entity manager, obtenemos la lista completa de perfiles de salud
```
$app->get('/lifecoach/person/{personId}/health-profile/history',
function ($request, $response) use($entityManager, $v)
	{
	$personId = $request->getAttribute('personId');
	if ($v::intVal()->validate($personId) == false)
		{
		return $response->withJson(['message' => 'personId debe ser entero'], 400);
		}

	$person = $entityManager->find('\Custom\Entity\Person', $personId);
	if ($person == null)
		{
		return $response->withJson(['message' => 'la persona no existe'], 200);
		}

	$query = $entityManager->createQueryBuilder()->select('hp')->from('\Custom\Entity\HealthProfile', 'hp')->innerJoin('\Custom\Entity\Person', 'p')->where('p.id = ?1')->setParameter(1, $personId)->getQuery();
	$data = $query->getArrayResult();
	return $response->withJson(['data' => $data], 200);
	});

```

#### 4.2.7. POST /lifecoach/person/{personId}/health-profile/history
Agregamos un nuevo perfil de salud.
```

$app->post('/lifecoach/person/{personId}/health-profile/history',
function ($request, $response) use($entityManager, $v)
	{
	$personId = $request->getAttribute('personId');
	if ($v::intVal()->validate($personId) == false)
		{
		return $response->withJson(['message' => 'personId debe ser entero'], 400);
		}

	$body = $request->getBody();
	$body = json_decode($body, true);

	// validamos height

	if ($v::key('height', $v::floatType() , true)->validate($body) == false)
		{
		return $response->withJson(['message' => 'debe especificar height'], 400);
		}

	// validamos weight

	if ($v::key('weight', $v::floatType() , true)->validate($body) == false)
		{
		return $response->withJson(['message' => 'debe especificar weight'], 400);
		}

	$person = $entityManager->find('\Custom\Entity\Person', $personId);
	if ($person == null)
		{
		return $response->withJson(['message' => 'la persona no existe'], 200);
		}

	$healthProfile = new CustomEntityHealthProfile;
	$healthProfile->weight = $body['weight'];
	$healthProfile->height = $body['height'];
	$healthProfile->date = new DateTime();
	$healthProfile->person = $person;
	$person->healthProfile[] = $healthProfile;
	$entityManager->persist($person);
	$entityManager->persist($healthProfile);
	$entityManager->flush();
	$query = $entityManager->createQueryBuilder()->select('hp')->from('\Custom\Entity\HealthProfile', 'hp')->innerJoin('\Custom\Entity\Person', 'p')->where('p.id = ?1')->setParameter(1, $person->id)->getQuery();
	$data = $query->getArrayResult();
	return $response->withJson(['data' => $data], 200);
	});

```
