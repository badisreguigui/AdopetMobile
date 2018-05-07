<?php

require_once('connect.php');

$iduser=$_GET['iduser'];
$idevent=$_GET['idevent'];

$sql = "INSERT INTO participations (iduser,idevent)
VALUES ('$iduser','$idevent')";

if (mysqli_query($conn, $sql)) {
    echo "Participation added";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

$sql = "update events set nbrpart=nbrpart+1 where id=".$idevent;
if (mysqli_query($conn, $sql)) {
    echo "Participation increased";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
?>



