<?php

namespace AdoptBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Pet
 *
 * @ORM\Table(name="pet", indexes={@ORM\Index(name="fk_userId1", columns={"user_id"})})
 * @ORM\Entity(repositoryClass="AdoptBundle\Repository\PetRepository")
 */
class Pet
{
    /**
     * @var string
     *
     * @ORM\Column(name="name_pet", type="string", length=255, nullable=false)
     */
    private $namePet;

    /**
     * @var string
     *
     * @ORM\Column(name="breed", type="string", length=255, nullable=false)
     */
    private $breed;

    /**
     * @var integer
     *
     * @ORM\Column(name="age", type="integer", nullable=false)
     */
    private $age;

    /**
     * @var string
     *
     * @ORM\Column(name="sex", type="string", length=255, nullable=false)
     */
    private $sex;

    /**
     * @var string
     *
     * @ORM\Column(name="color", type="string", length=255, nullable=false)
     */
    private $color;

    /**
     * @var string
     *
     * @ORM\Column(name="governate", type="string", length=255, nullable=false)
     */
    private $governate;

    /**
     * @var string
     *
     * @ORM\Column(name="city", type="string", length=255, nullable=false)
     */
    private $city;

    /**
     * @var integer
     *
     * @ORM\Column(name="zip", type="integer", nullable=false)
     */
    private $zip;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="pet_image", type="string", length=255, nullable=false)
     */
    private $petImage;

    /**
     * @var integer
     *
     * @ORM\Column(name="user_id", type="integer", nullable=false)
     */
    private $userId;

    /**
     * @var float
     *
     * @ORM\Column(name="lat", type="float", precision=10, scale=0, nullable=false)
     */
    private $lat;

    /**
     * @var float
     *
     * @ORM\Column(name="lng", type="float", precision=10, scale=0, nullable=false)
     */
    private $lng;

    /**
     * @var string
     *
     * @ORM\Column(name="typePet", type="string", length=255, nullable=false)
     */
    private $typepet;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255, nullable=false)
     */
    private $type;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_pet", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idPet;

    /**
     * @return string
     */
    public function getNamePet()
    {
        return $this->namePet;
    }

    /**
     * @param string $namePet
     */
    public function setNamePet($namePet)
    {
        $this->namePet = $namePet;
    }



    /**
     * @return string
     */
    public function getBreed()
    {
        return $this->breed;
    }

    /**
     * @param string $breed
     */
    public function setBreed($breed)
    {
        $this->breed = $breed;
    }

    /**
     * @return int
     */
    public function getAge()
    {
        return $this->age;
    }

    /**
     * @param int $age
     */
    public function setAge($age)
    {
        $this->age = $age;
    }

    /**
     * @return string
     */
    public function getSex()
    {
        return $this->sex;
    }

    /**
     * @param string $sex
     */
    public function setSex($sex)
    {
        $this->sex = $sex;
    }

    /**
     * @return string
     */
    public function getColor()
    {
        return $this->color;
    }

    /**
     * @param string $color
     */
    public function setColor($color)
    {
        $this->color = $color;
    }

    /**
     * @return string
     */
    public function getGovernate()
    {
        return $this->governate;
    }

    /**
     * @param string $governate
     */
    public function setGovernate($governate)
    {
        $this->governate = $governate;
    }

    /**
     * @return string
     */
    public function getCity()
    {
        return $this->city;
    }

    /**
     * @param string $city
     */
    public function setCity($city)
    {
        $this->city = $city;
    }

    /**
     * @return int
     */
    public function getZip()
    {
        return $this->zip;
    }

    /**
     * @param int $zip
     */
    public function setZip($zip)
    {
        $this->zip = $zip;
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
     * @return string
     */
    public function getPetImage()
    {
        return $this->petImage;
    }

    /**
     * @param string $petImage
     */
    public function setPetImage($petImage)
    {
        $this->petImage = $petImage;
    }

    /**
     * @return int
     */
    public function getUserId()
    {
        return $this->userId;
    }

    /**
     * @param int $userId
     */
    public function setUserId($userId)
    {
        $this->userId = $userId;
    }

    /**
     * @return float
     */
    public function getLat()
    {
        return $this->lat;
    }

    /**
     * @param float $lat
     */
    public function setLat($lat)
    {
        $this->lat = $lat;
    }

    /**
     * @return float
     */
    public function getLng()
    {
        return $this->lng;
    }

    /**
     * @param float $lng
     */
    public function setLng($lng)
    {
        $this->lng = $lng;
    }

    /**
     * @return string
     */
    public function getTypepet()
    {
        return $this->typepet;
    }

    /**
     * @param string $typepet
     */
    public function setTypepet($typepet)
    {
        $this->typepet = $typepet;
    }

    /**
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType($type)
    {
        $this->type = $type;
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



}

