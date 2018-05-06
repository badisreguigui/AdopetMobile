<?php

namespace PubsBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * insultes
 *
 * @ORM\Table(name="insultes")
 * @ORM\Entity(repositoryClass="PubsBundle\Repository\insultesRepository")
 */
class insultes
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
     * @var string
     *
     * @ORM\Column(name="chaine", type="string", length=255)
     */
    private $chaine;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set chaine
     *
     * @param string $chaine
     *
     * @return insultes
     */
    public function setChaine($chaine)
    {
        $this->chaine = $chaine;

        return $this;
    }

    /**
     * Get chaine
     *
     * @return string
     */
    public function getChaine()
    {
        return $this->chaine;
    }
}

