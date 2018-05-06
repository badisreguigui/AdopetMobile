<?php

namespace ShopBundle\Controller;

use ShopBundle\Entity\Test;
use ShopBundle\Entity\Commande;
use ShopBundle\Entity\Panier;
use ShopBundle\Entity\Produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;


class PanierController extends Controller
{
    public function AddProductAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $id=$request->get('id');
        $produits=new Produit();
        $produit = $em->getRepository(Produit::class)->find($id);
        $l=$em->getRepository(Panier::class)->findByIdProduit($produit->getIdProduit());
        if($l==null) {
            $prod = new Panier();
            $prod->setQuantite(1);
            $prod->setIdProduit($produit);
            if($produit->getPromotion()==1){
                $prod->setPrix($produit->getPrix()*$produit->getTaux()/100);
            }
            if($produit->getPromotion()==0){
                $prod->setPrix($produit->getPrix());
            }

            $prod->setIdUser($this->getUser()->getId());
            $em = $this->getDoctrine()->getManager();
            $em->persist($prod);

        }
        else
            foreach($l as $li)
            {
                $li->setQuantite($li->getQuantite()+1);

            }
        $em->flush();

            return $this->redirectToRoute('shop_store');

    }

    public function AddProductAjaxAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $produits=new Produit();
        $produit = $em->getRepository(Produit::class)->find($id);
        $l=$em->getRepository(Test::class)->findProductUser($this->getUser()->getId(),$id);
        $nbProduit = count($l);
        if($l==null) {
            $prod = new Panier();
            $prod->setQuantite(1);
            $prod->setIdProduit($produit);
            if($produit->getPromotion()==1){
                $prod->setPrix($produit->getPrix()*$produit->getTaux()/100);
            }
            if($produit->getPromotion()==0){
                $prod->setPrix($produit->getPrix());
            }
            $prod->setIdUser($this->getUser()->getId());
            $prod->setEtat(0);
            $em = $this->getDoctrine()->getManager();
            $em->persist($prod);
            $msg = 1;
        }
        else
            foreach($l as $li)

            {

                $li->setQuantite($li->getQuantite()+1);
                $msg = $li->getQuantite();

            }
        $em->flush();

        return new JsonResponse(['msg' => $msg]);

    }


    public function AfficherPanierAction()
    {

        $em = $this->getDoctrine()->getManager();

        $paniers = $em->getRepository(Test::class)->findProductParameterDQL($this->getUser()->getId());
        $produits = $this->getDoctrine()->getRepository(Produit::class)->findAll();
        $rate = $em->getRepository(Test::class)->affiche();
        $prixTotal = 0;
        $resultCount=0;
        if ($paniers != null)
        {

            foreach ($paniers as $panier) {
                $prixTotal += $panier->getPrix()*$panier->getQuantite();
            }
            $resultCount = count($paniers);

            return $this->render('ShopBundle::cart.html.twig', array('produits' => $produits,'paniers' => $paniers, 'prixTotal' => $prixTotal,'resultCount' => $resultCount));
        }
        return $this->render('ShopBundle::cart.html.twig',array('rate'=>$rate,'paniers'=>$paniers,'produits'=>$produits,'prixTotal' => $prixTotal,'resultCount' => $resultCount));
    }

    public function UpdateLineAction($id,$body)
    {
        $em=$this->getDoctrine()->getManager();
        $line=$em->getRepository(Panier::class)->find($id);
        $prod=$em->getRepository(Produit::class)->findByIdproduit($line->getIdProduit());

            $line->setQuantite($body);

        $em->flush();
        $newPrice=$line->getPrix()*$body;
        foreach ($prod as $p)
        {
            if($body>$p->getQuantite() || $body<1)
            {

                $line->setQuantite(1);
                $em->flush();
                $i=1;
            }
            else {$i=0;}
        }

        return new JsonResponse(array("newPrice"=>$newPrice,"i"=>$i,'prod'=>$prod,"price"=>$line->getPrix()));



    }

    public function deleteAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $modele = $em->getRepository(Panier::class)->find($id);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('shop_cart_show');

    }


    public function deleteAllAction(){
        $em= $this->getDoctrine()->getManager();
        $paniers = $em->getRepository(Test::class)->findProductParameterDQL($this->getUser()->getId());
        foreach($paniers as $panier){
            $em->remove($panier);
            $em->flush();
            return $this->redirectToRoute('shop_cart_show');
        }
        return $this->redirectToRoute('shop_cart_show');
    }






}
