T<?php
require_once("connect.php");

$return_arr = array();

$sql = "SELECT  * FROM pet ORDER BY user_id DESC LIMIT 3";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $row_array['id_pet'] = $row['id_pet'];
       $row_array['name_pet'] = $row['name_pet'];
       $row_array['user_id'] = $row['user_id'];
       $row_array['pet_image'] = $row['pet_image'];
       $row_array['zip'] = $row['zip'];
       array_push($return_arr,$row_array);
    }

}


echo json_encode($return_arr);
?>
