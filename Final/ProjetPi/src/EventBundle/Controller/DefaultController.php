<?php

namespace EventBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('EventBundle:Default:index.html.twig');
    }

    public function EventsAction()
    {
        return $this->render('EventBundle::events.html.twig');
    }
}
