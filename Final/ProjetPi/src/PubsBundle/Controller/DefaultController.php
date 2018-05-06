<?php

namespace PubsBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('PubsBundle:Default:index.html.twig');
    }

    public function indexAdminAction()
    {
        return $this->render(':Layout:layoutAdmin.html.twig');
    }

    public function RedirectionAction()
    {
        return $this->render('::base.html.twig');
    }
}
