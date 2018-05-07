<?php

require_once('connect.php');

$iduser=$_GET['iduser'];
$idevent=$_GET['idevent'];

$sql = "DELETE FROM participations WHERE iduser=".$iduser." and idevent=".$idevent." ";
if (mysqli_query($conn, $sql)) {
    echo "Participation deleted";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

$sql = "update events set nbrpart=nbrpart-1 where id=".$idevent;
if (mysqli_query($conn, $sql)) {
    echo "Participation decreased";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
?>



