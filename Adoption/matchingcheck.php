

<?php
require_once("connect.php");

$return_arr = array();

//$id=$_GET['id'];
$id_user=$_GET['id_user'];
$id_pet=$_GET['id_pet'];
//matching$sql = "SELECT  * FROM Pet where id_pet=$id";
$sql = "SELECT COUNT(*) as counting FROM matching	 WHERE (id_user=$id_user && id_pet=$id_pet)";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {

      $row_array['counting'] = $row['counting'];



     array_push($return_arr,$row_array);
    }

}


echo json_encode($return_arr);
?>
