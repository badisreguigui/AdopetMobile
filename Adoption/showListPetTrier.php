<?php
require_once("connect.php");

$return_arr = array();

$sql = "SELECT * from pediatre ORDER BY rating DESC LIMIT 3";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $row_array['id'] = $row['id'];
       $row_array['nom'] = $row['nom'];
      // $row_array['prenom'] = $row['prenom'];
	   $row_array['email'] = $row['email'];
	   $row_array['specialite'] = $row['specialite'];
	   $row_array['rating'] = $row['rating'];
	   $row_array['adresse'] = $row['adresse'];
	   $row_array['demande'] = $row['demande'];
	   //$row_array['description'] = $row['description'];
	   $row_array['image'] = $row['image'];
	   //$row_array['formation'] = $row['formation'];
	   //$row_array['parcours'] = $row['parcours'];
	   $row_array['likes'] = $row['likes'];
	   $row_array['nbrQuiz'] = $row['nbrQuiz'];
	   $row_array['num'] = $row['num'];
	   $row_array['nbJetons'] = $row['nbJetons'];
	   
	   
	   
	   
     array_push($return_arr,$row_array);
    }
	
}


echo json_encode($return_arr);
?>
