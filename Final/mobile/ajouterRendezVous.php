<?php
require_once('connect.php');


$id_veto=$_GET['id_veto'];
$date_rdv=$_GET['date_rdv'];
$heure=$_GET['heure'];
$sql = "INSERT INTO `rdv`( `id_veto`, `date_rdv`, `heure`) VALUES ('$id_veto','$date_rdv','$heure')" ;

if(mysqli_query($conn, $sql)){
	echo 'success';
}

mysqli_close($conn);

?>

