<?php
require_once("connect.php");

$return_arr = array();
$id=$_GET['id'];
$sql = "SELECT * FROM notification WHERE iduser=".$id." and etat=0";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
while ($row = $result->fetch_assoc()){
    $row_array['idnotif'] = $row['idnotif'];
    $row_array['iduser'] = $row['iduser'];
    $row_array['contenu'] = $row['contenu'];
    $row_array['etat'] = $row['etat'];
    array_push($return_arr,$row_array);
}
}
echo (json_encode($return_arr));
?>