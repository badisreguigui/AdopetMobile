<?php
require_once('connect.php');
$imagename=$_GET['imagename'];

//l'emplacement de l'image sous wamp
if(move_uploaded_file($_FILES['file']['tmp_name'],'../ProjetPi/web/images/'.$imagename.'.png'))
	echo "ok";
else
	echo "erreur";

//mysqli_close($conn);

?>