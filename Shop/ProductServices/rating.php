<?php
require_once('connect.php');

$return_arr = array();

$id=$_GET['idpr'];

$sql = "SELECT IFNULL(ROUND(AVG(rating)),0) as ratepr FROM `rate` where idpr=$id";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $row_array['ratepr'] = $row['ratepr'];
       
	   
	   
	   
     array_push($return_arr,$row_array);
    }
	
}


echo json_encode($return_arr);
?>