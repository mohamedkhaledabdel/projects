<?php error_reporting(0); ?>
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
	<div class="lablel-position">
	<div class="text-center">
	<span class="label label-primary">
	Join our E-shop to enjoy a varity of products
	</span>
</div>
</div>
</h3>
<!-- this form is for registeration -->

<div id="wrapper">
	<div class="input-group">
		<form name="login-form" class="login-form" action="register.php" method="POST" >
			<div class="header">
				<h1>Login Form</h1>
			</div>
			
			<div class="content">
			<input name="firstname" type="text" class="input username" placeholder="First Name" />
			<div class="user-icon"></div>
			<input name="lastname" type="text" class="input username" placeholder="Last Name" />
			<div class="user-icon"></div>
			<input name="email" type="email" class="input username" placeholder="E-mail" />
			<div class="user-icon"></div>
			<input name="username" type="text" class="input username" placeholder="Userame" />
			<div class="user-icon"></div>
			<input name="pass" type="password" class="input password" placeholder="Password" />
			<div class="pass-icon"></div>		
			</div>

			<div class="footer">
			<input type="submit" name="submit" value="Register" class="button" />
			<a href="index.php" class="register">Login</a>
			</div>
  		</form>
	</div>
</div>
<?php 
/* this part is done for the connection to the 
database and checking if the username enterd
by someone who is signing up is already taken
or not, if taken an error appears
*/


$con = mysqli_connect("127.0.0.1", "root",
 "Moh0101430900","ESHOP");
$name = mysqli_real_escape_string($con,
	$_POST["username"]);
$query = mysqli_query($con,"
	SELECT *
	FROM  users
	WHERE userName = '$name' 
	");
$firstName = mysqli_real_escape_string($con,
	$_POST["firstname"]);
$lastName = mysqli_real_escape_string($con,
	$_POST["lastname"]);
$email = mysqli_real_escape_string($con,
	$_POST["email"]);
$pass = mysqli_real_escape_string($con,
	$_POST["pass"]) ;
$result = mysqli_fetch_array($query);

$queryEmail = mysqli_query($con,"
	SELECT *
	FROM  users
	WHERE email = '$email' 
	");
$resultEmail = mysqli_fetch_array($queryEmail);

$str = strpos($email, "@");
if($str == false && $email != null) {
	echo ' <p id = "wrong-email">
 	<img src="http://www.capturebilling.com/wp-content/uploads/2013/01/Error.jpg"
 	width="30" >
 	Enter a valid email.(@gmail, @yahoo, ...)
		 </p>';
}
else { 	 
$strSplit = explode("@", $email);
if (($strSplit[1] != "gmail.com" || 
    $strSplit[1] != "yahoo.com" ||
	$strSplit[1] != "hotmail.com" ||
	$strSplit[1] != "live.com") && ($email != null)
	&& (sizeof($strSplit) == 2)){
	echo ' <p id = "wrong-email">
 	<img src="http://www.capturebilling.com/wp-content/uploads/2013/01/Error.jpg"
 	width="30" >
 	Enter a valid email.(@gmail, @yahoo, ...)
		 </p>';
}	
}

if($resultEmail['email'] != null &&
 $resultEmail['email'] == $email) {
	echo ' <p id = "error-email">
 	<img src="http://www.capturebilling.com/wp-content/uploads/2013/01/Error.jpg"
 	width="30" >
 	This email is already taken, Please choose another
		 </p>'; 
} 
elseif($name != null &&
 $result['userName'] == $name) {
	echo ' <p id = "error">
 	<img src="http://www.capturebilling.com/wp-content/uploads/2013/01/Error.jpg"
 	width="30" >
 	This username is already taken, Please choose another
		 </p>'; 
} 

elseif($pass != null && $email != null 
	&& $name != null && $firstName != null
	&& $lastName != null){
$insert = mysqli_query($con, "
INSERT INTO users (firstName, lastName, email,
password, loggedIn, userName)
VALUES ('$firstName', '$lastName', '$email',
'$pass',1, '$name')");
echo header( 'Location: 
		index.php');
} 
?>


</body>
</html>