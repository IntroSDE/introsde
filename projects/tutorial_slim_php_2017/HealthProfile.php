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
