<?php

namespace ShopBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Categorie
 *
 * @ORM\Table(name="categorie", indexes={@ORM\Index(name="nomcategorie", columns={"nomcategorie"})})
 * @ORM\Entity
 */
class Categorie
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idcategorie", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcategorie;

    /**
     * @var string
     *
     * @ORM\Column(name="nomcategorie", type="string", length=50, nullable=false)
     */
    private $nomcategorie;

    /**
     * @return int
     */
    public function getIdcategorie()
    {
        return $this->idcategorie;
    }

    /**
     * @param int $idcategorie
     */
    public function setIdcategorie($idcategorie)
    {
        $this->idcategorie = $idcategorie;
    }

    /**
     * @return string
     */
    public function getNomcategorie()
    {
        return $this->nomcategorie;
    }

    /**
     * @param string $nomcategorie
     */
    public function setNomcategorie($nomcategorie)
    {
        $this->nomcategorie = $nomcategorie;
    }




}

