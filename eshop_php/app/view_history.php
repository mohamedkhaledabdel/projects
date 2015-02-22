<!DOCTYPE html>
<html>
<head>
  <title>ESHOP</title>
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css"/> 
  <link rel="stylesheet" type="text/css" href="css/style.css"/>
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
            // getting user id
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
            $user_id = $result['userID'];

?>

<table class="table table-hover table-nomargin table-bordered usertable tablesorter" style="width: 90%; margin: auto;">
                  <thead>
                    <tr class="main_tr">
                      <th>Name</th>
                      <th>Price</th>
                      <th>Image</th>
                      <th>Time</th> 
                    </tr>
                  </thead>
                  <tbody>
<?php
						mysql_connect('127.0.0.1', 'root', 'Moh0101430900');
						mysql_select_db('ESHOP');
            $query = "SELECT product_id, time FROM user_products WHERE user_id = '$user_id';";
            $result = mysql_query($query) or die(mysql_error());
						if (mysql_num_rows($result) > 0) {
						    while($row = mysql_fetch_assoc($result)) {
                  $time = $row['time'];
                  $product_id =$row['product_id'];
                  $query2 = "SELECT id AS product_id, name AS product_name, image_url AS product_image, price  AS product_price, stock AS product_stock
                  FROM  products
                  WHERE id = '$product_id';";
                  $result2 = mysql_query($query2) or die(mysql_error());
						    	$row = mysql_fetch_assoc($result2);
                            		echo '<tr>
                                      </td>
                                      <td>'.$row["product_name"].'</td>
                                      <td>'.$row["product_price"].'</td>
                                      <td><img src="'.$row["product_image"].'"></td>   
                                      <td>'.$time.'</td>
                                   
                                    </tr>';
    
                       		} 
						}else{
                      		echo "<tr><td colspan = '12' style='text-align:center;color: rgba(0,0,0,0.3)'><h4><span class='glyphicon glyphicon-ban-circle'></span>&nbsp;&nbsp;No products</h4></td></tr>";
                  		}


                  		mysql_close();   
?>
                  </tbody>
                </table>
      </div>
</body>
</html>