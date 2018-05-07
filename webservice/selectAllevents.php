<?php
require_once("connect.php");

$return_arr = array();
$eventlist = array();
$checklist = array();
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
        array_push($checklist,$row_array);
    }
}

$sql = "SELECT lieu FROM preference WHERE id=".$id;
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $row_array1['lieu'] = $row['lieu'];
    }

}else{
    $row_array1['lieu'] = null;
}
$sql = "SELECT * FROM events WHERE status='upcoming' and acc=1 and organisateur!=".$id."";

if ($row_array1['lieu'] != null)
$sql = $sql . " ORDER BY lieu = '".$row_array1['lieu']."' Desc" ;

$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
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

foreach ($return_arr as $finalevent) {
    $i = array_search($finalevent,$return_arr);
    foreach ($checklist as $testevent) {
        if ($finalevent == $testevent) {
            array_splice($return_arr, $i, 1);
        }

    }
}
echo (json_encode($return_arr));
?>