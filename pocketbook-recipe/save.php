
<!DOCTYPE html>
<html lang="en">
<head>
   <?php require_once 'db.php'; ?>
    <meta charset="UTF-8">
    <title>Save Recipe</title>
    <link rel="stylesheet" href="stylePocketCook.css">
    
</head>
    <body>
      <div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="profile.php">Profile</a>
          <hr>
  <a href="search.php">Search</a>
          <hr>
  <a href="about.php">About</a>
</div>
      <?php
      $result = mysqli_query($conn, "SELECT * FROM ingredients");
      $array = $result->fetch_all();

      $result->free();
      mysqli_close($conn);

      ?>

<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; <img src="img/53365-recipes-cooking-book.svg"></span>
         <div id = 'logForm' class='save'>
           <form action="save.php" method="post">
               <h1>Add New Recipe</h1>
           <p>Name:</p>
            <input type="text" name="recipeName">
               <p>Ingredients:</p>
               <select name = "ingredient">
                   <?php foreach($array as list($value1, $value2)) { ?>
                       <option value=><?php echo $value2. "\n"?></option>
                   <?php }?>

               </select> <button id="signUp" style="margin-left:0;">Add</button><br>
               <p style="font-size:1em">Other ingedient:</p>
               <input type="text" name="newIngredient" style="width: 50%;">
               <button id="logIn">Add</button><br>
               <p>Selected:</p>
               <p>URL link to the image:</p>
               <input type="text" name="image"><br>
               <p>Description:</p>
               <textarea name="description"></textarea><br>
               <input  name = 'UIbutton' type="submit" value="Add">
           </form>
        </div>

      <?php



      if(isset($_POST['logIn'])) {
          echo "add new ingredient button pressed";



          $addingIngredient = $conn->real_escape_string(!empty($_POST['newIngredient']) ? $_POST['newIngredient'] : '');
          $sql1 = "INSERT INTO ingredients(ingredient) 
          VALUES ('$addingIngredient')";
          if ($conn->query($sql1) === TRUE) {
              echo "New ingredient added successfully";
          } else {
              echo "Error: " . $sql1 . "<br>" . $conn->error;
          }
      }

      if(isset($_POST['UIbutton'])) {
          $ingredient = $conn->real_escape_string(!empty($_POST['ingredient']) ? $_POST['ingredient'] : '');
          $recipeName = $conn->real_escape_string(!empty($_POST['recipeName']) ? $_POST['recipeName'] : '');
          $image = $conn->real_escape_string(!empty($_POST['image']) ? $_POST['image'] : '');
          $description = $conn->real_escape_string(!empty($_POST['description']) ? $_POST['description'] : '');

          //$ingredient = $recipeName = $image = $description = '';
          //$ingredient = $_POST['ingredient'];
          //$recipeName = $_POST['recipeName'];
          //$image = $_POST['image'];
          //$description = $_POST['description'];
          $sql = "INSERT INTO recipies(meal, ingredients, picture, Decription) 
          VALUES ('$recipeName', '$ingredient', '$image','$description')";

          if ($conn->query($sql) === TRUE) {
              echo "New record created successfully";
          } else {
              echo "Error: " . $sql . "<br>" . $conn->error;
          }
      }
      ?>

<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
    $('description').autoResize();
        </script>
    </body>
</html>
