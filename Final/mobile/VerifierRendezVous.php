<?php
require_once('connect.php');

$return_arr = array();


$id_veto=$_GET['id_veto'];
$dateRendezVous=$_GET['date_rdv'];

$heure1=$_GET['heure1'];
$heure2=$_GET['heure2'];


$d="'".$_GET['date_rdv']."'";
$d1="'".$_GET['heure1']."'";
$d2="'".$_GET['heure2']."'";



$sql = "SELECT count(*) as count from rdv where(id_veto=$id_veto AND date_rdv=$d AND heure BETWEEN $d1 AND $d2)";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $row_array['count'] = $row['count'];
        array_push($return_arr,$row_array);
    }
} 
else{
		$row_array['count'] = 0;
        array_push($return_arr,$row_array);
}

echo json_encode($return_arr);

?>