<?php
require_once("connect.php");

$return_arr = array();
$id=$_GET['id'];
$sql = "update notification set etat=1 where idnotif=".$id;
if (mysqli_query($conn, $sql)) {
    echo "notification seen";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
?>
