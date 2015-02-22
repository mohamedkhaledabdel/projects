
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
            //checking if user logged in?
            $con = mysqli_connect("127.0.0.1", "root",
               "Moh0101430900","ESHOP");
            $name = $_COOKIE["user"];
            $query = mysqli_query($con,"
              SELECT *
              FROM  users
              WHERE userName = '$name' 
              "); 
            $result = mysqli_fetch_array($query);
            $logged = $result["loggedIn"];
            if($name == null && $logged == 0) {
              echo header( 'Location: 
                index.php');
            }
?> 
<div class="background">
	<div id = "change-pic">
		<form action = "change_photo.php">
			<button type = "submit"
			class = "btn btn-primary">
			Change Picture
			</button>
		</form>	
	</div>

	<div id = "view-shop">
		<form action = "AllProducts.php">	
			<button type = "submit"
			class = "btn btn-primary">
				View Shop 
			</button>
		</form>
	</div>
			
	<div id = "view-history">
		<form action = "view_history.php">	
			<button type = "submit"
			class = "btn btn-primary">
				View Items History 
			</button>
		</form>
	</div>

	<div id = "logout">
		<form action = "logout.php">	
			<button type = "submit"
			class = "btn btn-primary">
				Logout  
			</button>
		</form>
	</div>
</div>
<?php 
$con = mysqli_connect("127.0.0.1", "root",
	 "Moh0101430900","ESHOP");
$query = mysqli_query($con,"
	SELECT *
	FROM  picture
	WHERE pictureID = '10' 
	");
$result = mysqli_fetch_array($query);
$image = $result["img"];
/*echo '	<div class = "picture">
		<img src="$image"
		width="175" height="200"/>
		</div>';*/
mysqli_close($con);
$con = mysqli_connect("127.0.0.1", "root",
	 "Moh0101430900","ESHOP");		
$name = mysqli_real_escape_string($con,
$_COOKIE["user"]);
$queryInfo = mysqli_query($con,"
	SELECT *
	FROM  users
	WHERE userName = '$name' 
	");
$resultInfo = mysqli_fetch_array($queryInfo);
mysqli_close($con);
$firstName = $resultInfo["firstName"];
$lastName = $resultInfo["lastName"];
$email = $resultInfo["email"];
$password = $resultInfo["password"];


?>
<div id = "table"> <p>
	<table class="table table-hover">
		<tr>
			<td>First Name</td>
    		<td>Last Name</td>		
    		<td>Email</td>
  		</tr>
  		<tr>
  			<td> <?php echo $firstName; ?></td>
  			<td> <?php echo $lastName;  ?></td>
  			<td> <?php echo $email; ?></td>
  		</tr>   
	</table>

	<div id = "change-info">
		<form action = "change_info.php">	
			<button type = "submit"
			class = "btn btn-primary">
				Change Information 
			</button>
		</form>
	</div>
</div>


</body>
</html>