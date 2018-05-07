<?php
require_once("connect.php");
$type=$_GET['type'];
$sql="select count(*) from publications where type='".$type."' ";
$result = $conn->query($sql);
if ($result->num_rows > 0){
    while ($row = $result->fetch_assoc()){
        $row_array['count(*)'] = $row['count(*)'];
    }
    echo $row_array['count(*)'];
}
?>