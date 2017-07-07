<?php
$app->get('/index',
function ($request, $response) use($entityManager, $v)
	{
	return $response->getBody()->write("Hola");
	});

// GET lifecoach/person
// Devuelve la lista de personas, con sus peso y altura

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

// / GET lifecoach/person/{personId}
// Devuelve la lista de personas

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

// GET lifecoach/person/{personId}/health-profile
// Devuelve el perfil de salud de la persona

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

// POST lifecoach/person

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

// DELETE	/lifecoach/person/{personId}

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

// GET /lifecoach/person/{personId}/health-profile/history

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

// POST /lifecoach/person/{personId}/health-profile/history

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
