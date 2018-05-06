<!DOCTYPE html>
<html>
<body>

<script>
var x = document.getElementById("demo");
var a = 0 ;
var b = 0 ;
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.watchPosition(showPosition);
    }
}

function showPosition(position) {
    a = position.coords.latitude;
    b = position.coords.longitude;
    console.log(a);
    console.log("rfdfefe");
    console.log(b);

}



getLocation();
</script>

</body>
</html>


<?php
/*
class Coords {
  public $alt;
  public $lng;

  function __construct($alt, $lng){
        //$this->coordinates = array("altitude" => $alt,"longitude" => $lng);
        $this->alt = $alt;
        $this->lng = $lng;
    }
}
class Gps {
  public $val;



  function __construct($alt, $lng){
        //$this->coordinates = array("altitude" => $alt,"longitude" => $lng);
        $this->val = new coords($alt, $lng);
    }

}
$alt = doubleval($_GET['alt']);
$lng = doubleval($_GET['lng']);

$coordinates1 = new Gps($alt, $lng);
$coordinatesData = json_encode($coordinates1);
echo $coordinatesData . "<br /><br />";
$fp = fopen('/Users/mac/Desktop/coords.json', 'w');
fwrite($fp, $coordinatesData);
fclose($fp);
*/
echo "
            <script type=\"text/javascript\">
            for (var i = 0; i < 3; i++) {
              console.log(a);
            }
            </script>
        ";
?>
