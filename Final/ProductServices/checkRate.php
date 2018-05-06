<?php
require_once("connect.php");

$return_arr = array();

$Id_Product=$_GET['idpr'];
$User_id=$_GET['iduser'];

$sql = "SELECT COUNT(*) as ratep FROM `rate` where idpr=$Id_Product and iduser=$User_id";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $row_array['ratep'] = $row['ratep'];
       
	   
	   
	   
     array_push($return_arr,$row_array);
    }
	
}


echo json_encode($return_arr);
?>