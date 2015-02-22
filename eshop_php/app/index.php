<?php error_reporting(0); ?>=
<?php setcookie("user", "",time()+3600,
	'/');
?>
<!DOCTYPE html>
<html>
<head>
	<title>ESHOP</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css"/>	
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	<link rel="stylesheet" type="text/css" href="css/login.css"/>
</head>
<body>
<h3>
<!--this html part is a picture and a form
for the members login -->
<div class="text-center">
	<span class="label label-primary">
		Welcome To Our E-Shop
	</span>
</div>
</h3>
<div id="wrapper">
	<div class="input-group">
		<form name="login-form" class="login-form" action="index.php" method="POST" >
			<div class="header">
				<h1>Login Form</h1>
			</div>
			
			<div class="content">
			<input name="name" type="text" class="input username" placeholder="Username" />
			<div class="user-icon"></div>
			<input name="pass" type="password" class="input password" placeholder="Password" />
			<div class="pass-icon"></div>		
			</div>

			<div class="footer">
			<input type="submit" name="submit" value="Login" class="button" />
			<a href="register.php" class="register">Register</a>

			</div>
  		</form>
	</div>
</div>
<?php 

/* this follwoing php part is done for the 
connection of mysql and checking the values
entered by the user in Database for equality
*/	
$con = mysqli_connect("127.0.0.1", "root",
	 "Moh0101430900","ESHOP");
$name = mysqli_real_escape_string($con,
$_POST["name"]);
setcookie("user",$name,time()+3600,
	'/');
$query = mysqli_query($con,"
	SELECT *
	FROM  users
	WHERE userName = '$name' 
	");
$result = mysqli_fetch_array($query);
$pass = mysqli_real_escape_string($con, $_POST["pass"]);
if($result['userName'] == $name && 
	$name != null && $pass != null && 
	$result['password'] == $pass) {	
	$update = mysqli_query($con," 
		UPDATE users SET loggedIn = 1
		WHERE userName='$name' 
		");
	echo header( 'Location: 
		profile.php' ) ;
}
elseif ($result['userName'] != $name ||
		$result['password'] != $pass) {
	echo '	<div class="text-left">
				<span class="label label-primary">
					Wrong Username or Password,  
					for Sign up 
					<a class="label label-primary"
						href="http://localhost:8000/register.php">
						click here 
					</a>
				</span>
			</div> ';
}


?>

</body>
</html>
 
