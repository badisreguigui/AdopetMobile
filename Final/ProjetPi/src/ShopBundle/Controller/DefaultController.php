<?php

namespace ShopBundle\Controller;

use ShopBundle\Entity\Boutique;
use ShopBundle\Entity\Produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use ShopBundle\Entity\Panier;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('::base.html.twig');
    }
    public function LayoutAction()
    {
        return $this->render(':Layout:Layout.html.twig');
    }
    public function LayoutAdminAction()
    {
        return $this->render(':layout:LayoutAdmin.html.twig');
    }

    public function PayementAction(Request $request)
    {
        $prix= $request->get('prixTotal');
        return $this->render('ShopBundle::payement.html.twig',['prixTotal' => $prix]);
    }

    public function PayerAction(Request $request){
        $em=$this->getDoctrine()->getManager();
        \Stripe\Stripe::setApiKey('sk_test_XA950iYFnIrsHN5H9xjtp1In');
        $prix= $request->get('prixTotal');
        $charge = \Stripe\Charge::create(

            array(

                'amount' => $prix*100,

                'currency' => 'eur',

                'source' => 'tok_mastercard',

                'description' => 'Payement pour badis'

            ));
        $paniers = $em->getRepository(Panier::class)->findByIduser($this->getUser()->getId());
        foreach ($paniers as $panier){
            $produit=$em->getRepository(Produit::class)->find($panier->getIdproduit());
            $boutique=$em->getRepository(Boutique::class)->find($produit->getIdboutiqueproduit());
            $boutique->setTotalachat($boutique->getTotalachat()+1);
            $panier->setEtat(1);
        }
        $em->flush();
        return $this->redirectToRoute('shop_store');
    }

}
