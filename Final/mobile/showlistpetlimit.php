<?php
require_once("connect.php");

$return_arr = array();

//$sql = "SELECT  * FROM pet LIMIT 1";
$sql = "SELECT  * FROM pet  where id_pet NOT IN (SELECT id_pet FROM matching) LIMIT 1";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $row_array['id_pet'] = $row['id_pet'];
       $row_array['name_pet'] = $row['name_pet'];
       $row_array['breed'] = $row['breed'];
       $row_array['description'] = $row['description'];
       $row_array['pet_image'] = $row['pet_image'];
       array_push($return_arr,$row_array);
    }

}


echo json_encode($return_arr);
?>
