<?php
require_once("connect.php");

$return_arr = array();

$id_veto=$_GET['id_veto'];

$sql = "SELECT  * FROM tarif where id_veto=$id_veto";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
 
       $row_array['id_veto'] = $row['id_veto'];
       $row_array['consultation'] = $row['consultation'];
       $row_array['vaccinationChat'] = $row['vaccinationChat'];
       $row_array['vaccinationChien'] = $row['vaccinationChien'];
       $row_array['sterilisation'] = $row['sterilisation'];
     $row_array['analyses'] = $row['analyses'];

     
     
     array_push($return_arr,$row_array);
    }
  
}


echo json_encode($return_arr);
?>