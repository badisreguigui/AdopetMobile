<?php
require_once('connect.php');

$return_arr = array();

$sql = "SELECT id,username,password FROM user where username="."'".$_GET['login']."'";
$result = $conn->query($sql);

if ($result->num_rows > 0) 
{
    // output data of each row
    while($row = $result->fetch_assoc()) 
    {
        $row_array['id'] = $row['id'];
        $row_array['username'] = $row['username'];
        $row_array['password'] = $row['password'];


if (password_verify($_GET['pass'],$row['password'])) 
{  
    array_push($return_arr,$row_array);
} 



    }
} 


echo json_encode($return_arr);

?>