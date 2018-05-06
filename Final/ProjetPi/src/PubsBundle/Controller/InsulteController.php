<?php

namespace PubsBundle\Controller;

use PubsBundle\Entity\insultes;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class InsulteController extends Controller
{
    public function showInsultesAction()
    {

        $produits= $this->getDoctrine()->getRepository(insultes::class)->findAll();

        return $this->render('PubsBundle:Pubs:insultes.html.twig',['produits'=>$produits]);

    }
    public function addInsultesAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $i=new insultes();
        if($request->isMethod('POST'))
        {
            $i->setChaine($request->get("chaine"));
            $em->persist($i);
            $em->flush();
            return $this->redirectToRoute('backinsultes');
        }
        return $this->render('PubsBundle:Pubs:insultes.html.twig');

    }
}
