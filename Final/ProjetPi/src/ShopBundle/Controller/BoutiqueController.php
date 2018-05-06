<?php

namespace ShopBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use ShopBundle\Entity\Boutique;

class BoutiqueController extends Controller
{
    public function AjouterBoutiqueAction(Request $request){
        $boutique = new Boutique();
        if($request->isMethod('POST'))
        {
            $boutique->setNomboutique($request->get('nomboutique'));
            $boutique->setTelboutique($request->get('telboutique'));
            $boutique->setAdresseboutique($request->get('adresseboutique'));
            $boutique->setImageboutique($request->get('imageboutique'));
            $em = $this->getDoctrine()->getManager();
            $em->persist($boutique);
            $em->flush();
            return $this->redirectToRoute('shop_Afficher');
        }
        return $this->render('ShopBundle::AjouterBoutique.html.twig');
    }


    public function showBoutiquesAction()
    {

        $boutiques= $this->getDoctrine()->getRepository(Boutique::class)->findAll();

        return $this->render('ShopBundle::listeBoutiques.html.twig',['boutiques'=>$boutiques]);

    }

    public function fetchBoutiqueAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $id=$request->get('idboutique');
        $boutique = $em->getRepository(Boutique::class)->find($id);
        return $this->render('ShopBundle::modifierBoutique.html.twig',array('boutique'=>$boutique));


    }

    public function updateBoutiqueAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $modele=new Boutique();
        $modele = $em->getRepository(Boutique::class)->find($request->request->get('id'));

        if (!$modele) {
            throw $this->createNotFoundException(
                'No Boutique found for id '.$request->request->get('id')
            );
        }
        $modele->setNomboutique($request->get('nomboutique'));
        $modele->setTelboutique($request->get('telboutique'));
        $modele->setAdresseboutique($request->get('adresseboutique'));
        $modele->setImageboutique($request->get('imageboutique'));
        $em->flush();

        return $this->redirectToRoute('shop_AfficherBoutique');
    }


    public function deleteBoutiqueAction($idboutique)
    {
        $em = $this->getDoctrine()->getManager();
        $modele = $em->getRepository(Boutique::class)->find($idboutique);
        echo $modele->getNomboutique();
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('shop_AfficherBoutique');
    }

    public function DeniedAction()
    {
        return $this->render('ShopBundle::accessDenied.html.twig');
    }
}
