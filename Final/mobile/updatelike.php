<?php
require_once('connect.php');

$id=$_GET['id'];
$zup=$_GET['zip'];

$sql = "update pet set zip=$zip where id_pet=$id" ;

if(mysqli_query($conn, $sql)){
	echo 'success';
}

mysqli_close($conn);

?>
