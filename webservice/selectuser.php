<?php
require_once("connect.php");

$return_arr = array();
$username=$_GET['username'];
$password=$_GET['password'];
$sql = "SELECT * FROM user WHERE username='".$username."' and password='".$password."'";

$result = $conn->query($sql);
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $row_array['id'] = $row['id'];
    }
    echo $row_array['id'];

}else{
    echo 'try again';
}
?>