<?php
require_once('connect.php');

$cin=$_GET['cin'];
$nom=$_GET['nom'];
$age=$_GET['age'];
$sql = "INSERT INTO etudiant ( cin, nom, age)
VALUES ( '$cin','$nom','$age')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>