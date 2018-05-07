<?php

require_once('connect.php');

$nom=$_GET['nom'];
$type=$_GET['type'];
$lieu=$_GET['lieu'];
$description=$_GET['description'];
$datedebut=$_GET['datedebut'];
$datefin=$_GET['datefin'];
$organisateur=$_GET['organisateur'];

$sql = "INSERT INTO events (nom,description,type,lieu,datedebut,datefin,organisateur)
VALUES ('$nom','$description','$type','$lieu','$datedebut','$datefin',$organisateur)";

if (mysqli_query($conn, $sql)) {
    echo "Event added";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
?>



<!--http://localhost/mobile/ajouterevent.php?nom=test&type=aaa&lieu=aaa&description=aaa&datedebut=2018-05-03&datefin=2018-05-03&organisateur=1-->