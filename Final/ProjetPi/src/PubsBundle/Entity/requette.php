<?php

namespace PubsBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * requette
 *
 * @ORM\Table(name="requette")
 * @ORM\Entity(repositoryClass="PubsBundle\Repository\requetteRepository")
 */
class requette
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }
}
