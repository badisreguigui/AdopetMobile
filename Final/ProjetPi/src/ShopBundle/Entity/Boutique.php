<?php

namespace ShopBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Boutique
 *
 * @ORM\Table(name="boutique", indexes={@ORM\Index(name="nomboutique", columns={"nomboutique"})})
 * @ORM\Entity
 */
class Boutique
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idboutique", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idboutique;

    /**
     * @var string
     *
     * @ORM\Column(name="nomboutique", type="string", length=50, nullable=false)
     */
    private $nomboutique;

    /**
     * @var integer
     *
     * @ORM\Column(name="telboutique", type="integer", nullable=false)
     */
    private $telboutique;

    /**
     * @var string
     *
     * @ORM\Column(name="adresseboutique", type="string", length=50, nullable=false)
     */
    private $adresseboutique;

    /**
     * @var string
     *
     * @ORM\Column(name="imageboutique", type="string", length=500, nullable=false)
     */
    private $imageboutique;

    /**
     * @var integer
     *
     * @ORM\Column(name="totalachat", type="integer", nullable=false)
     */
    private $totalachat;

    /**
     * @return int
     */
    public function getIdboutique()
    {
        return $this->idboutique;
    }

    /**
     * @param int $idboutique
     */
    public function setIdboutique($idboutique)
    {
        $this->idboutique = $idboutique;
    }

    /**
     * @return string
     */
    public function getNomboutique()
    {
        return $this->nomboutique;
    }

    /**
     * @param string $nomboutique
     */
    public function setNomboutique($nomboutique)
    {
        $this->nomboutique = $nomboutique;
    }

    /**
     * @return int
     */
    public function getTelboutique()
    {
        return $this->telboutique;
    }

    /**
     * @param int $telboutique
     */
    public function setTelboutique($telboutique)
    {
        $this->telboutique = $telboutique;
    }

    /**
     * @return string
     */
    public function getAdresseboutique()
    {
        return $this->adresseboutique;
    }

    /**
     * @param string $adresseboutique
     */
    public function setAdresseboutique($adresseboutique)
    {
        $this->adresseboutique = $adresseboutique;
    }

    /**
     * @return string
     */
    public function getImageboutique()
    {
        return $this->imageboutique;
    }

    /**
     * @param string $imageboutique
     */
    public function setImageboutique($imageboutique)
    {
        $this->imageboutique = $imageboutique;
    }

    /**
     * @return int
     */
    public function getTotalachat()
    {
        return $this->totalachat;
    }

    /**
     * @param int $totalachat
     */
    public function setTotalachat($totalachat)
    {
        $this->totalachat = $totalachat;
    }




}

