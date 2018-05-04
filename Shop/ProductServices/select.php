<?php
require_once("connect.php");

$return_arr = array();

$sql = "SELECT a.idproduit,a.nomproduit,a.description,a.prix,a.quantite,a.imageproduit,a.idcategorie,a.nomraceproduit,a.idboutiqueproduit,
a.promotion,a.taux,Avg(c.rating) as rate from Produit a left join Rate c on (a.idproduit=c.idpr) GROUP BY a.idproduit";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $row_array['idproduit'] = $row['idproduit'];
       $row_array['nomproduit'] = $row['nomproduit'];
       $row_array['description'] = $row['description'];
       $row_array['imageproduit'] = $row['imageproduit'];
       $row_array['prix'] = $row['prix'];
       $row_array['quantite'] = $row['quantite'];
       $row_array['nomraceproduit'] = $row['nomraceproduit'];
       $row_array['rate'] = $row['rate'];
       
       
       
     array_push($return_arr,$row_array);
    }
    
}


echo json_encode($return_arr);
?>