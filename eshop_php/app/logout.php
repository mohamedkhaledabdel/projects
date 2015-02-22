<!DOCTYPE html>
<html>
<head>
	<title>ESHOP</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css"/>	
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
	<div id = "hint">
			<div class="text-left">
			<span class="label label-primary">
				Logging Out.......
			</span>
			</div>
			</div>

<?php 
$con = mysqli_connect("127.0.0.1", "root",
	 "Moh0101430900","ESHOP");
$name = $_COOKIE["user"];
$update = mysqli_query($con," 
		UPDATE users SET loggedIn = 0
		WHERE userName='$name' 
		");

echo header( 'Location: 
		index.php');
 ?>
</body>
</html>