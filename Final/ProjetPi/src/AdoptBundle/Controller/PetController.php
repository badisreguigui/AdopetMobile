<?php

namespace AdoptBundle\Controller;


use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;
use Symfony\Component\HttpFoundation\Response;

use AdoptBundle\Entity\Pet;
use AdoptBundle\Entity\tests;

use AdoptBundle\Entity\Comments;
use AdoptBundle\Entity\Matching;
use AdoptBundle\Entity\Petrecup;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;


use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;

use Symfony\Component\Filesystem\Filesystem;
use Symfony\Component\Filesystem\Exception\IOExceptionInterface;

use Gregwar\CaptchaBundle\Type\CaptchaType;
use Skies\QRcodeBundle\Generator\Generator;

use Symfony\Component\HttpFoundation\ResponseHeaderBag;

class PetController extends Controller
{
    public function listpetBackAction(){
        $pets = new Pet();
        $em = $this->getDoctrine()->getManager();
        $pets = $em->getRepository(Petrecup::class)->findAll();
        return $this->render('AdoptBundle::back.html.twig', ['pets' => $pets]);
        //return $this->render('AdoptBundle::myPetProfile.html.twig');
    }



    public function modifierAction(Request $request,$id)
    {


        $pets = $this->getDoctrine()->getRepository(Pet::class)->find($id);
        if($request->isMethod('POST'))
        {
            $em=$this->getDoctrine()->getManager();
            $pets->setNamePet($request->get('name'));
            $pets->setBreed($request->get('breed'));
            $pets->setAge($request->get('age'));
            $pets->setSex($request->get('sex'));
            $pets->setColor($request->get('color'));
            $pets->setGovernate($request->get('governate'));
            $pets->setCity($request->get('city'));
            $pets->setZip($request->get('zip'));
            $pets->setDescription($request->get('description'));
            $pets->setPetImage($request->get('image'));
            $pets->setTypepet($request->get('type'));
            $pets->setType($request->get('typep'));

            $pets->setUserId(0);
            $pets->setLat(0);
            $pets->setLng(0);

            $em->persist($pets);
            $em->flush();

        }


        return $this->render('AdoptBundle::edit.html.twig',array("pets"=>$pets));
    }

    public function AjouterpetAction(Request $request){
        $p = new Pet();
        $form = $this->createFormBuilder($p)
           ->add('captcha', CaptchaType::class)
            ->getForm();
        if($request->isMethod('POST')){
            $p->setNamePet($request->get('name'));
            $p->setBreed($request->get('breed'));
            $p->setAge($request->get('age'));
            $p->setSex($request->get('sex'));
            $p->setColor($request->get('color'));
            $p->setGovernate($request->get('governate'));
            $p->setCity($request->get('city'));
            $p->setZip($request->get('zip'));
            $p->setDescription($request->get('description'));
            $p->setPetImage($request->get('image'));
            $p->setTypepet($request->get('type'));
            $p->setType($request->get('typep'));

            $p->setUserId(0);
            $p->setLat(0);
            $p->setLng(0);

            $em = $this->getDoctrine()->getManager();
            $em->persist($p);
            $em->flush();


            return  $this->redirectToRoute('adopt_list');
        }
        return $this->render('AdoptBundle::add.html.twig', array(
            'form' => $form->createView()));
    }

    public function listpetAction(){

        $phpExcelObject = $this->get('phpexcel')->createPHPExcelObject();

        $phpExcelObject->getProperties()->setCreator("liuggio")
            ->setLastModifiedBy("Giulio De Donato")
            ->setTitle("Office 2005 XLSX Test Document")
            ->setSubject("Office 2005 XLSX Test Document")
            ->setDescription("Test document for Office 2005 XLSX, generated using PHP classes.")
            ->setKeywords("office 2005 openxml php")
            ->setCategory("Test result file");
        $phpExcelObject->setActiveSheetIndex(0)
            ->setCellValue('A1', 'Hello')
            ->setCellValue('B2', 'world!');
        $phpExcelObject->getActiveSheet()->setTitle('Simple');
        // Set active sheet index to the first sheet, so Excel opens this as the first sheet
        $phpExcelObject->setActiveSheetIndex(0);

        // create the writer
        $writer = $this->get('phpexcel')->createWriter($phpExcelObject, 'Excel5');
        // create the response
        $response = $this->get('phpexcel')->createStreamedResponse($writer);
        // adding headers
        $dispositionHeader = $response->headers->makeDisposition(
            ResponseHeaderBag::DISPOSITION_ATTACHMENT,
            'stream-file.xls'
        );
        $response->headers->set('Content-Type', 'text/vnd.ms-excel; charset=utf-8');
        $response->headers->set('Pragma', 'public');
        $response->headers->set('Cache-Control', 'maxage=1');
        $response->headers->set('Content-Disposition', $dispositionHeader);

        $writer = $this->get('phpexcel')->createWriter($phpExcelObject, 'Excel5');
        $writer->save('fileee.xls');


        $pets = new Pet();
        $em = $this->getDoctrine()->getManager();
        $pets = $em->getRepository(Pet::class)->findAll();
        return $this->render('AdoptBundle::adoptPets.html.twig', ['pets' => $pets]);
        //return $this->render('AdoptBundle::myPetProfile.html.twig');
    }

    public function myProfileAction($id)
    {
        $options = array(
            'code'   => 'string to encode',
            'type'   => 'qrcode',
            'format' => 'html',
        );

        $generator = new Generator();
        $barcode = $generator->generate($options);



        $comments = $this->ListCommentsAction($id);
        $em=$this->getDoctrine()->getManager();
        $Pet=$em->getRepository(Pet::class)->find($id);
        $em->flush();
        return $this->render('AdoptBundle::profile.html.twig',['Pets' => $Pet, 'comments' => $comments]);
    }

    public function EcrireCommentaireAction($post_id, $body)
    {
        $comment = new Comments();
        $user_id = 10;
        $comment->setComment($body);
        $comment->setPostid($post_id);
        $comment->setUserid($this->getUser()->getIdUser());

        //Ajouter fonction pour la censure
        $pieces = explode(" ", $comment->getComment());
        $em = $this->getDoctrine()->getManager();
        foreach ($pieces as $piece)
        {
            $test =  $em->getRepository(tests::class)->findInsultes($piece);
            if($test != null){
                $boolean = true;
                break;
            }
            $boolean = false;
        }

        if(!$boolean){
            $em->persist($comment);
            $em->flush();
            $msg = "success";
        }
        else{
            $msg = "non";
        }
        return new JsonResponse(array('msg' => $msg));
    }

    public function ListCommentsAction($postId){
        $em = $this->getDoctrine()->getManager();
        //$comments = $em->getRepository(Comments::class)->findByPostId($postId);
        $comments = $em->getRepository(Comments::class)->findAll();

        return $comments;
    }

    public function tinderrAction($id){
        $pets = new Pet();
        $em = $this->getDoctrine()->getManager();
        $pets = $em->getRepository(Pet::class)->findTinder();

        $petss = new Pet();
        $em = $this->getDoctrine()->getManager();
        $petss = $em->getRepository(Pet::class)->findMatched($id);

        return $this->render('AdoptBundle::tinder.html.twig', ['pets' => $pets, 'petss' => $petss]);
    }

    /*
    public function tinderrAction(){
        $pets = new Pet();
        $em = $this->getDoctrine()->getManager();
        $pets = $em->getRepository(Pet::class)->findTinder();

        $petss = new Pet();
        $em = $this->getDoctrine()->getManager();
        $petss = $em->getRepository(Pet::class)->findAll();

        return $this->render('AdoptBundle::tinder.html.twig', ['pets' => $pets, 'petss' => $petss]);
    }
    */

    public function matchAction(Request $request, $idPet){
        $p = new Matching();
        $p->setIdUser(1);
        $p->setIdPet($idPet);
        $p->setMatching(1);
        $em = $this->getDoctrine()->getManager();
        $em->persist($p);
        $em->flush();
        sleep(5);
        return  $this->redirectToRoute('adopt_tinderr');
        //return jsonresponse
        //return new Response("a");
    }

    public function unmatchAction(Request $request, $idPet){
        $p = new Matching();
        $p->setIdUser(1);
        $p->setIdPet($idPet);
        $p->setMatching(0);
        $em = $this->getDoctrine()->getManager();
        $em->persist($p);
        $em->flush();
        return  $this->redirectToRoute('adopt_tinderr');
    }

    public function listpetmapAction(){
        $pets = new Pet();
        $em = $this->getDoctrine()->getManager();
        $pets = $em->getRepository(Pet::class)->findAll();
        return $this->render('AdoptBundle::map.html.twig', ['pets' => $pets]);
        //return $this->render('AdoptBundle::myPetProfile.html.twig');
    }

    public function myPetProfileAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $Pet=$em->getRepository(Pet::class)->find($id);
        $em->flush();
        return $this->render('AdoptBundle::myprofile.html.twig',array('Pets'=>$Pet));
    }

    public function supprimerPetAction($id){
        $Pet = new Pet();
        $p = new Petrecup();
        $em = $this->getDoctrine()->getManager();
        $Pet = $em->getRepository(Pet::class)->find($id);
        $p->setNamePet($Pet->getNamePet());
        $p->setBreed($Pet->getBreed());
        $p->setAge($Pet->getAge());
        $p->setSex($Pet->getSex());
        $p->setColor($Pet->getColor());
        $p->setGovernate($Pet->getGovernate());
        $p->setCity($Pet->getCity());
        $p->setZip($Pet->getZip());
        $p->setDescription($Pet->getDescription());
        $p->setPetImage($Pet->getPetImage());
        $p->setTypepet($Pet->getTypepet());
        $p->setType($Pet->getType());
        $p->setUserId(0);
        $p->setLat(0);
        $p->setLng(0);

        $em->remove($Pet);
        $em->persist($p);
        $em->flush();
        return  $this->redirectToRoute('adopt_list');
    }


    public function supprimerPetBackAction($id){
        $p = new Pet();
        $Pet = new Petrecup();
        $em = $this->getDoctrine()->getManager();
        $Pet = $em->getRepository(Petrecup::class)->find($id);
        $p->setNamePet($Pet->getNamePet());
        $p->setBreed($Pet->getBreed());
        $p->setAge($Pet->getAge());
        $p->setSex($Pet->getSex());
        $p->setColor($Pet->getColor());
        $p->setGovernate($Pet->getGovernate());
        $p->setCity($Pet->getCity());
        $p->setZip($Pet->getZip());
        $p->setDescription($Pet->getDescription());
        $p->setPetImage($Pet->getPetImage());
        $p->setTypepet($Pet->getTypepet());
        $p->setType($Pet->getType());
        $p->setUserId(0);
        $p->setLat(0);
        $p->setLng(0);

        $em->remove($Pet);
        $em->persist($p);
        $em->flush();
        return  $this->redirectToRoute('adopt_listpetBack');
    }




    public function convertoxmlAction(){
        $filePath = __DIR__ . '/../../../app/logs/xml/';
        $fileName = 'xmlLog.xml';

        $pets = new Pet();
        $em = $this->getDoctrine()->getManager();
        $pets = $em->getRepository(Pet::class)->findAll();


            // create new file if not exist
            $mainNode = new \SimpleXMLElement('<?xml version="1.0" encoding="UTF-8" standalone="yes"?><markers></markers>');

            //$productNode = $mainNode->addChild('Markers');



            foreach ($pets as $pet) {
                $rN = $mainNode->addChild('marker');
                //$rN = $productNode->addChild('marker');
                $rN->addAttribute('id', $pet->getIdPet());
                $rN->addAttribute('name', $pet->getNamePet());
                $rN->addAttribute('adress', $pet->getGovernate());
                $rN->addAttribute('lat', $pet->getLat());
                $rN->addAttribute('lng', $pet->getLng());
            }

            $fs = new Filesystem();
            $fs->mkdir($filePath);

            $mainNode->asXML($filePath . $fileName);

        //return $this->render('AdoptBundle::test.html.twig');
        return  $this->redirectToRoute('adopt_listmap');

    }

    public function testAction(){
        return $this->render('AdoptBundle::autocompletetest.html.twig');
    }

}
