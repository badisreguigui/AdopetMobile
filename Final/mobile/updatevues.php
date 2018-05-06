<?php
require_once('connect.php');

$id=$_GET['id'];
$vues=$_GET['vues'];

$sql = "update pet set user_id=$vues where id_pet=$id" ;

if(mysqli_query($conn, $sql)){
	echo 'success';
}

mysqli_close($conn);

?>
