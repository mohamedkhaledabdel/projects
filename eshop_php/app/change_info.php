<?php error_reporting(0); ?>
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
<div class="input-position">
<form role="form" action="change_info.php" method="POST" >
	<div class="form-group">
		<label for="exampleInputEmail1">
		   	First Name 
		</label> 
		<input class="form-control" 
		type="text" name="firstname"><br>
	</div>
	<div class="form-group">	
		<label for="exampleInputEmail1">
		    Last Name 
		</label>
		<input class="form-control"
		type="text" name="lastname"><br>
	</div>
	<div class="form-group">
		<label for="exampleInputEmail1">
		    Email
		</label> 
		<input class="form-control" 
		type="text" name="email"><br>
	</div>
	
	<div class="form-group">
		<label for="exampleInputEmail1">
		    Password 
		</label>	
		<input class="form-control" id="email" 
  	 	type="password" name="pass"><br>
  	</div> 	
  	<div class="form-group">
  		<button type="submit" 
  		class="btn btn-primary">
  			Update</button>
  		</div>
</form>
<form action="profile.php">
	<div class="form-group">
  		<button type="submit" 
  		class="btn btn-primary">
  			Back To Profile</button>
  		</div>	
</form>
</div>



<?php 
$con = mysqli_connect("127.0.0.1", "root",
	 "Moh0101430900","ESHOP");
$name = mysqli_real_escape_string($con,
$_COOKIE["user"]);
$firstName = mysqli_real_escape_string($con,
$_POST["firstname"]);
$lastName = mysqli_real_escape_string($con,
$_POST["lastname"]);
$email = mysqli_real_escape_string($con,
$_POST["email"]);
$password = mysqli_real_escape_string($con,
$_POST["pass"]);
$queryEmail = mysqli_query($con,"
	SELECT *
	FROM  users
	WHERE userName = '$name' 
	");
$resultInfo = mysqli_fetch_array($queryEmail);
$myEmail = $resultInfo["email"];

$usedEmail = mysqli_query($con,"
	SELECT *
	FROM  users
	WHERE email = '$email' 
	");
$resultEmail = mysqli_fetch_array($usedEmail);

$str = strpos($email, "@");
if($str == false && $email != null) {
	die (' <p id = "wrong-email">
 	<img src="http://www.capturebilling.com/wp-content/uploads/2013/01/Error.jpg"
 	width="30" >
 	Enter a valid email.(@gmail, @yahoo, ...)
		 </p>');
}

if($resultEmail['email'] != null &&
	$resultEmail['email'] == $email &&
	$email != $myEmail) {
	echo ' <p id = "error-email">
 	<img src="http://www.capturebilling.com/wp-content/uploads/2013/01/Error.jpg"
 	width="30" >
 	This email is already taken, Please choose another
		 </p>'; 
}


if ($firstName != null) {
	$update = mysqli_query($con," 
		UPDATE users SET firstName = '$firstName'
		WHERE userName='$name' 
		");		
}	
if($lastName != null) {
	$update = mysqli_query($con," 
		UPDATE users SET lastName = '$lastName'
		WHERE userName='$name' 
		");		
}
if($password != null) {
	$update = mysqli_query($con," 
		UPDATE users SET password = '$password'
		WHERE userName='$name' 
		");		
}
if($email != null && $email != $myEmail) {
	$update = mysqli_query($con," 
		UPDATE users SET email = '$email'
		WHERE userName='$name' 
		");		
}
elseif ($email == $myEmail && $email != null) {
echo ' <p id = "error-email">
 	<img src="http://www.capturebilling.com/wp-content/uploads/2013/01/Error.jpg"
 	width="30" >
 	You are already using this Email.
		 </p>';	
}

else{
	echo '	<div id = "hint">
			<div class="text-center">
			<span class="label label-primary">
				please enter any information 
				to change or go back to your profile
			</span>
			</div>
			</div>';
}



?>

</body>
</html>
