<?php
require_once('connect.php');
$rate=$_GET['rate'];
$Id_Product=$_GET['idpr'];
$User_id=$_GET['iduser'];
$sql = "INSERT INTO rate (rating,idpr,iduser)
VALUES ( '$rate','$Id_Product','$User_id')";
if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>