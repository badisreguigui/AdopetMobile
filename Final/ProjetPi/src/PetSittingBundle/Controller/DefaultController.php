<?php

namespace PetSittingBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('PetSittingBundle:Default:index.html.twig');
    }

    public function petsitAction()
    {
        return $this->render('PetSittingBundle::petsitting.html.twig');
    }
}
