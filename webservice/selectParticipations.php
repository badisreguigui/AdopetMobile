<?php
require_once("connect.php");

$eventlist = array();
$return_arr = array();
$id=$_GET['id'];
$sql = "SELECT idevent FROM participations WHERE iduser=".$id;
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $row_array1['idevent'] = $row['idevent'];
        array_push($eventlist,$row_array1);
    }
}

foreach ($eventlist as $eventid){
    $sql = "SELECT * FROM events WHERE id=".$eventid['idevent'];
    $result = $conn->query($sql);
    while ($row = $result->fetch_assoc()){
        $row_array['id'] = $row['id'];
        $row_array['nom'] = $row['nom'];
        $row_array['description'] = $row['description'];
        $row_array['type'] = $row['type'];
        $row_array['lieu'] = $row['lieu'];
        $row_array['datedebut'] = $row['datedebut'];
        $row_array['datefin'] = $row['datefin'];
        $row_array['nbrpart'] = $row['nbrpart'];
        array_push($return_arr,$row_array);
    }
}
//var_dump($return_arr);
echo (json_encode($return_arr));
?>