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
<h3>
	<div class="lablel-position">
	<div class="text-center">
	<span class="label label-primary">
	Join our E-shop to enjoy a varity of products
	</span>
</div>
</div>
</h3>



<table class="table table-hover table-nomargin table-bordered usertable tablesorter" style="width: 90%; margin: auto;">
                  <thead>
                    <tr class="main_tr">
                      <th>Name</th>
                      <th>Price</th>
                      <th>Stock items</th>
                      <th>Image</th>
                      <th></th>

                    </tr>
                  </thead>
                  <tbody>
                      <?php
						mysql_connect('127.0.0.1', 'root', 'Moh0101430900');
						mysql_select_db('ESHOP');
						$query = "SELECT id AS product_id, name AS product_name, image_url AS product_image, price 				AS product_price, stock AS product_stock
								  FROM  products;";
						$result = mysql_query($query) or die(mysql_error());
						if (mysql_num_rows($result) > 0) {
						    while($row = mysql_fetch_assoc($result)) {
						    	if ($row["product_stock"] > 0){
                            		echo '<tr>
                                      </td>
                                      <td>'.$row["product_name"].'</td>
                                      <td>'.$row["product_price"].'</td>
                                      <td>'.$row["product_stock"].'</td>
                                      <td><img src="'.$row["product_image"].'"></td>   
                                      <td><a href="buy_product.php?id='.$row['product_id'].'" class="btn btn-primary btn-sm btn-block" data-toggle="modal">Buy</a></td>
                                   
                                    </tr>';
                                }
                                else {
                                	echo '<tr>
                                      </td>
                                      <td>'.$row["product_name"].'</td>
                                      <td>'.$row["product_price"].'</td>
                                      <td>'.$row["product_stock"].'</td>
                                      <td><img src="'.$row["product_image"].'"</td>   
                                      <td><span style="color:red">Sold out!</span></td>
                                   
                                    </tr>';
                                }

                                
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