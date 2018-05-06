<?php
require_once('connect.php');
$id=$_GET['id_produit'];
$iduser=$_GET['iduser'];
$etat=0;
$return_arr = array();

$sql = "SELECT id_produit,iduser,prix,etat,quantite FROM panier where id_produit=$id and iduser=$iduser and etat=$etat" ;
$result = $conn->query($sql);

if ($result->num_rows > 0) 
{
    // output data of each row
	while($row = $result->fetch_assoc()) {
		$row_array['id_produit'] = $row['id_produit'];
		$row_array['iduser'] = $row['iduser'];
		$row_array['prix'] = $row['prix'];
		$row_array['etat'] = $row['etat'];
		$row_array['quantite'] = $row['quantite'];




		array_push($return_arr,$row_array);
	}
} 


echo json_encode($return_arr);

?>