<?php
require_once("connect.php");

$return_arr = array();

$id=$_GET['id'];

$sql = "SELECT  * FROM users where id=$id";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
    	
       $row_array['id'] = $row['id'];
       $row_array['nom'] = $row['nom'];
       $row_array['prenom'] = $row['prenom'];
	   $row_array['image'] = $row['image'];
	   $row_array['telephone'] = $row['telephone'];
	   $row_array['rue'] = $row['rue'];
	   $row_array['ville'] = $row['ville'];
	   $row_array['gouvernorat'] = $row['gouvernorat'];
	   $row_array['email'] = $row['email'];
	   $row_array['description'] = $row['description'];


	   
	   
	   
     array_push($return_arr,$row_array);
    }
	
}


echo json_encode($return_arr);
?>