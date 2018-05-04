<?php
require_once("connect.php");

$return_arr = array();

$id=$_GET['id'];

$sql = "SELECT  * FROM Pet where id_pet=$id";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {

      $row_array['id_pet'] = $row['id_pet'];
       $row_array['name_pet'] = $row['name_pet'];
      // $row_array['prenom'] = $row['prenom'];
	   $row_array['breed'] = $row['breed'];
	   $row_array['age'] = $row['age'];
	   $row_array['sex'] = $row['sex'];
	   $row_array['description'] = $row['description'];
	   $row_array['demande'] = $row['demande'];
	   //$row_array['description'] = $row['description'];
	   $row_array['pet_image'] = $row['pet_image'];




     array_push($return_arr,$row_array);
    }

}


echo json_encode($return_arr);
?>
