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
$configuration = [
    'settings' => [
        'displayErrorDetails' => true,
    ],
];
$c = new \Slim\Container($configuration);
$app = new \Slim\App($c);

$v = new v;

//endpoints
require '../endpoints/endpoints.php';

//creamos el web service
$app->run();
