<?php

namespace AdoptBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Matching
 *
 * @ORM\Table(name="matching", indexes={@ORM\Index(name="fk_petmatch", columns={"id_pet"}), @ORM\Index(name="fk_usermatch", columns={"id_user"})})
 * @ORM\Entity
 */
class Matching
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_user", type="integer", nullable=false)
     */
    private $idUser;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_pet", type="integer", nullable=false)
     */
    private $idPet;

    /**
     * @var integer
     *
     * @ORM\Column(name="matching", type="integer", nullable=false)
     */
    private $matching;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_matching", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idMatching;

    /**
     * @return int
     */
    public function getIdUser()
    {
        return $this->idUser;
    }

    /**
     * @param int $idUser
     */
    public function setIdUser($idUser)
    {
        $this->idUser = $idUser;
    }

    /**
     * @return int
     */
    public function getIdPet()
    {
        return $this->idPet;
    }

    /**
     * @param int $idPet
     */
    public function setIdPet($idPet)
    {
        $this->idPet = $idPet;
    }

    /**
     * @return int
     */
    public function getMatching()
    {
        return $this->matching;
    }

    /**
     * @param int $matching
     */
    public function setMatching($matching)
    {
        $this->matching = $matching;
    }

    /**
     * @return int
     */
    public function getIdMatching()
    {
        return $this->idMatching;
    }

    /**
     * @param int $idMatching
     */
    public function setIdMatching($idMatching)
    {
        $this->idMatching = $idMatching;
    }


}

