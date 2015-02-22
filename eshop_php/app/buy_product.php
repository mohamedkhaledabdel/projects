<!DOCTYPE html>
<html>
<head>
  <title>ESHOP</title>
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css"/>
  <link rel="stylesheet" type="text/css" href="css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="css/fonts.css"/> 
  <link rel="stylesheet" type="text/css" href="css/style.css"/>
  <link rel="stylesheet" type="text/css" href="css/buy_product.css"/>
  <script type="text/javascript" src="jquery.min.js"></script>
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
            // getting product information
            mysql_connect('127.0.0.1', 'root', 'Moh0101430900');
            mysql_select_db('ESHOP');
            $product_id = $_GET['id'];
            $query = "SELECT id AS product_id, name AS product_name, image_url AS product_image, price AS product_price, stock AS product_stock
                  FROM  products
                  WHERE id = '$product_id';";
            $result = mysql_query($query) or die(mysql_error());
            if (mysql_num_rows($result) > 0) {
                $row = mysql_fetch_assoc($result);

            }
?>  
<form action="buy_product.php?id=<?php echo $product_id ?>" method="POST" > 
  <div class="cart">
      <div class="cart-top">
        <h2 class="cart-top-title">Checkout</h2>
        <div class="cart-top-info">1 item</div>
      </div>

      <ul>
        <li class="cart-item">
          <span class="cart-item-pic">
            <img src="<?php echo $row['product_image'] ?>"/>
          </span>
          <span class="cart-item-desc"><?php echo $row["product_name"] ?></span>
          <span class="cart-item-price"><?php echo $row["product_price"] ?></span>
        </li>
      </ul>

      <div class="cart-bottom">
        Total: <?php echo $row["product_price"] ?>
              <input class="cart-button" type="submit" name="insert" value="Checkout" />
      </div>
  </div>

<?php
if (isset($_REQUEST['insert'])) {
      mysql_connect('127.0.0.1', 'root', 'Moh0101430900');
      mysql_select_db('ESHOP');
      $query = "INSERT INTO user_products (user_id, product_id, time) VALUES ('$user_id', '$product_id', now());";
      $result = mysql_query($query) or die(mysql_error());
      if($result)
      {
        $query = "UPDATE products SET stock = stock - 1 WHERE id = '$product_id'";
        $result = mysql_query($query) or die(mysql_error());
        echo header( 'Location: 
          profile.php');
        }
      }
        
?>

</body>
</html>