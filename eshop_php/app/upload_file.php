<!DOCTYPE html>
<html>
<head>
	<title>ESHOP</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css"/>	
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<?php
if ($_FILES["file"]["error"] > 0) {
  echo "Error: " . $_FILES["file"]["error"] . "<br>";
} 
else {	
	$con = mysqli_connect("127.0.0.1", "root",
	"Moh0101430900","ESHOP");
	$image = mysqli_real_escape_string($con, 
		addslashes(file_get_contents (
		$_FILES['file']['tmp_name'])));
	$con = mysqli_connect("127.0.0.1", "root",
	"Moh0101430900","ESHOP");
	$name = mysqli_real_escape_string($con,
		$_COOKIE["user"]);
	$query = mysqli_query($con,"
		SELECT *
		FROM  users
		WHERE userName = '$name' 
	");
$result = mysqli_fetch_array($query);
$id = $result['userID'];
$insert = mysqli_query($con, "
	INSERT INTO picture (img, userID)
	VALUES ('$image', '$id')");  
mysqli_close($con);
echo header( 'Location: 
		profile.php');;
	
}


?> 
</body>
</html>