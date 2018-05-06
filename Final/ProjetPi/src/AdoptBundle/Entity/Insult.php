<?php

namespace AdoptBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Insult
 *
 * @ORM\Table(name="insult")
 * @ORM\Entity
 */
class Insult
{
    /**
     * @var string
     *
     * @ORM\Column(name="insult", type="string", length=255, nullable=false)
     */
    private $insult;

    /**
     * @var integer
     *
     * @ORM\Column(name="idInsult", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idinsult;


}

