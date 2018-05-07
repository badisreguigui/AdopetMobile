<?php
require_once("connect.php");

$return_arr = array();
$id=$_GET['id'];

$sql = "SELECT * FROM events WHERE organisateur=".$id;


$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
//        $row_array['id'] = $row['id'];
        $row_array['nom'] = $row['nom'];
        $row_array['description'] = $row['description'];
        $row_array['type'] = $row['type'];
        $row_array['lieu'] = $row['lieu'];
        $row_array['datedebut'] = $row['datedebut'];
        $row_array['datefin'] = $row['datefin'];
        $row_array['nbrpart'] = $row['nbrpart'];
        $row_array['acc'] = $row['acc'];
        array_push($return_arr,$row_array);
    }

}
//var_dump($return_arr);
echo (json_encode($return_arr));
?>