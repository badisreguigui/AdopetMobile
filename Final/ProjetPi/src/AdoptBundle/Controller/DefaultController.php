<?php

namespace AdoptBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('AdoptBundle:Default:index.html.twig');
    }
    public function redirectAction()
    {
        return $this->render('::base.html.twig');
    }
    public function adoptAction()
    {
        return $this->render('AdoptBundle::adopt.html.twig');
    }
}
