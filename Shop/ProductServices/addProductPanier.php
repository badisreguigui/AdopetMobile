<?php
require_once('connect.php');
$id=$_GET['id_produit'];
$iduser=$_GET['iduser'];
$etat=0;
$prix=$_GET['prix'];
$quantite=$_GET['quantite'];
$sql = "INSERT INTO panier (id_produit,iduser,etat,prix,quantite)
VALUES ( '$id','$iduser','$etat','$prix',$quantite)";
if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>