<?php
require_once('connect.php');

$return_arr = array();

$sql = "SELECT idproduit,nomproduit,prix,imageproduit,quantite FROM produit where idproduit=".$_GET['idproduit'];
$result = $conn->query($sql);

if ($result->num_rows > 0) 
{
    // output data of each row
	while($row = $result->fetch_assoc()) {
		$row_array['idproduit'] = $row['idproduit'];
		$row_array['nomproduit'] = $row['nomproduit'];
		$row_array['prix'] = $row['prix'];
		$row_array['imageproduit'] = $row['imageproduit'];
		$row_array['quantite'] = $row['quantite'];




		array_push($return_arr,$row_array);
	}
} 


echo json_encode($return_arr);

?>