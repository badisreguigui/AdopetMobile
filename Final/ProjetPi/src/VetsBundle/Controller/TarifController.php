<?php

namespace VetsBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use VetsBundle\Entity\Tarif;
use VetsBundle\Entity\TestVet;

class TarifController extends Controller
{
    public function BudgetAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $idveto=$request->get('idvet');
        $budget=$em->getRepository(TestVet::class)->findBudget($idveto);
        return $this->render('VetsBundle:Tarif:list_tarif.html.twig', array('budget'=>$budget));
    }

    public function CalculerBudgetAction($consultation, $chat, $chien,$ster,$analyse){
             $total=$consultation+$chat+$chien+$ster+$analyse;
           return new JsonResponse(['total' => $total]);

    }

    public function NewTarifAction(Request $request)
    {
        $Tarif=new Tarif();
        if($request->isMethod('POST'))
        {
            $em=$this->getDoctrine()->getManager();
            $Tarif->setIdVeto($request->get('id_veto'));
            $Tarif->setAnalyses($request->get('analyses'));
            $Tarif->setConsultation($request->get('consultation'));
            $Tarif->setVaccinationchat($request->get('vaccinationChat'));
            $Tarif->setVaccinationchien($request->get('vaccinationChien'));
            $Tarif->setSterilisation($request->get('sterilisation'));
            $em->persist($Tarif);
            $em->flush();




            return $this->redirectToRoute('list');



        }
        return $this->render('VetsBundle:Tarif:newTarif.html.twig');
    }


    public function modifierTarifAction(Request $request,$id)
    {


        $Tarif = $this->getDoctrine()->getRepository(Tarif::class)->find($id);
        if($request->isMethod('POST'))
        {

            $em=$this->getDoctrine()->getManager();
            $Tarif->setAnalyses($request->get('analyses'));
            $Tarif->setConsultation($request->get('consultation'));
            $Tarif->setVaccinationchat($request->get('vaccinationChat'));
            $Tarif->setVaccinationchien($request->get('vaccinationChien'));
            $Tarif->setSterilisation($request->get('sterilisation'));

            $em->persist($Tarif);
            $em->flush();

        }


        return $this->render('VetsBundle:Tarif:modifierTarif.html.twig',array("tarifs"=>$Tarif));
    }



}
