<?php

namespace AdoptBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Adoption
 *
 * @ORM\Table(name="adoption", indexes={@ORM\Index(name="fk_pet", columns={"id_pet"}), @ORM\Index(name="fk_user", columns={"id_user"})})
 * @ORM\Entity
 */
class Adoption
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_adoption", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idAdoption;

    /**
     * @var \AdoptBundle\Entity\User
     *
     * @ORM\ManyToOne(targetEntity="AdoptBundle\Entity\User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_user", referencedColumnName="user_id")
     * })
     */
    private $idUser;

    /**
     * @var \AdoptBundle\Entity\Pet
     *
     * @ORM\ManyToOne(targetEntity="AdoptBundle\Entity\Pet")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_pet", referencedColumnName="id_pet")
     * })
     */
    private $idPet;


}

