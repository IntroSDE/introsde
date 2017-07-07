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

public function getLastHealthProfile() {

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
