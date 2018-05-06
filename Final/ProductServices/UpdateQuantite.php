<?php
require_once('connect.php');
$id=$_GET['idproduit'];
$quantite=$_GET['quantite'];
$sql = "UPDATE produit SET quantite=$quantite WHERE idproduit=$id";
if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>