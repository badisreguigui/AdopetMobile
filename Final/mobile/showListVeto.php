<?php
require_once("connect.php");

$return_arr = array();

$sql = "SELECT  * FROM users";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $row_array['id'] = $row['id'];
       $row_array['nom'] = $row['nom'];
       $row_array['ville'] = $row['ville'];
	     $row_array['image'] = $row['image'];
       array_push($return_arr,$row_array);
    }

}


echo json_encode($return_arr);
?>
