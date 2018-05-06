<?php
require_once('connect.php');

$id_pet=$_GET['id_pet'];

$sql = "INSERT INTO matching (id_user,id_pet, matching) VALUES(1, $id_pet, 1)" ;

if(mysqli_query($conn, $sql)){
	echo 'success';
}

mysqli_close($conn);

?>

