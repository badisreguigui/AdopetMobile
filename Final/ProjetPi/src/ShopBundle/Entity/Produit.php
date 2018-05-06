<?php

namespace ShopBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Produit
 *
 * @ORM\Table(name="produit", uniqueConstraints={@ORM\UniqueConstraint(name="nomproduit", columns={"nomproduit"})}, indexes={@ORM\Index(name="nomcategorie", columns={"idcategorie"}), @ORM\Index(name="nomraceproduit_4", columns={"nomraceproduit", "idboutiqueproduit"}), @ORM\Index(name="fk_bout", columns={"idboutiqueproduit"})})
 * @ORM\Entity
 */
class Produit
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idproduit", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idproduit;

    /**
     * @var string
     *
     * @ORM\Column(name="nomproduit", type="string", length=500, nullable=false)
     */
    private $nomproduit;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=500, nullable=false)
     */
    private $description;

    /**
     * @var integer
     *
     * @ORM\Column(name="prix", type="integer", nullable=false)
     */
    private $prix;

    /**
     * @var integer
     *
     * @ORM\Column(name="quantite", type="integer", nullable=false)
     */
    private $quantite;


    /**
     * @var int
     *
     * @ORM\Column(name="rating", type="integer",nullable=true)
     */
    private $rating;

    /**
     * @var int
     *
     * @ORM\Column(name="taux", type="integer",nullable=true)
     */
    private $taux;

    /**
     * @var int
     *
     * @ORM\Column(name="promotion", type="integer",nullable=true)
     */
    private $promotion;

    /**
     * @return int
     */
    public function getRating()
    {
        return $this->rating;
    }

    /**
     * @param int $rating
     */
    public function setRating($rating)
    {
        $this->rating = $rating;
    }


    /**
     * @var string
     *
     * @ORM\Column(name="imageproduit", type="string", length=500, nullable=false)
     */
    private $imageproduit;

    /**
     * @var string
     *
     * @ORM\Column(name="nomraceproduit", type="string", length=500, nullable=false)
     */
    private $nomraceproduit;

    /**
     * @var \Boutique
     *
     * @ORM\ManyToOne(targetEntity="Boutique")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idboutiqueproduit", referencedColumnName="idboutique")
     * })
     */
    private $idboutiqueproduit;

    /**
     * @var \Categorie
     *
     * @ORM\ManyToOne(targetEntity="Categorie")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idcategorie", referencedColumnName="idcategorie")
     * })
     */
    private $idcategorie;

    /**
     * @return int
     */
    public function getIdproduit()
    {
        return $this->idproduit;
    }

    /**
     * @param int $idproduit
     */
    public function setIdproduit($idproduit)
    {
        $this->idproduit = $idproduit;
    }

    /**
     * @return string
     */
    public function getNomproduit()
    {
        return $this->nomproduit;
    }

    /**
     * @param string $nomproduit
     */
    public function setNomproduit($nomproduit)
    {
        $this->nomproduit = $nomproduit;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return int
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * @param int $prix
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;
    }

    /**
     * @return int
     */
    public function getQuantite()
    {
        return $this->quantite;
    }

    /**
     * @param int $quantite
     */
    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;
    }

    /**
     * @return string
     */
    public function getImageproduit()
    {
        return $this->imageproduit;
    }

    /**
     * @param string $imageproduit
     */
    public function setImageproduit($imageproduit)
    {
        $this->imageproduit = $imageproduit;
    }

    /**
     * @return string
     */
    public function getNomraceproduit()
    {
        return $this->nomraceproduit;
    }

    /**
     * @param string $nomraceproduit
     */
    public function setNomraceproduit($nomraceproduit)
    {
        $this->nomraceproduit = $nomraceproduit;
    }

    /**
     * @return \Boutique
     */
    public function getIdboutiqueproduit()
    {
        return $this->idboutiqueproduit;
    }

    /**
     * @param \Boutique $idboutiqueproduit
     */
    public function setIdboutiqueproduit($idboutiqueproduit)
    {
        $this->idboutiqueproduit = $idboutiqueproduit;
    }

    /**
     * @return \Categorie
     */
    public function getIdcategorie()
    {
        return $this->idcategorie;
    }

    /**
     * @param \Categorie $idcategorie
     */
    public function setIdcategorie($idcategorie)
    {
        $this->idcategorie = $idcategorie;
    }

    /**
     * @return int
     */
    public function getTaux()
    {
        return $this->taux;
    }

    /**
     * @param int $taux
     */
    public function setTaux($taux)
    {
        $this->taux = $taux;
    }

    /**
     * @return int
     */
    public function getPromotion()
    {
        return $this->promotion;
    }

    /**
     * @param int $promotion
     */
    public function setPromotion($promotion)
    {
        $this->promotion = $promotion;
    }



}

