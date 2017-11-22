<?php require_once 'db.php'; ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Recipe</title>
    <link rel="stylesheet" href="stylePocketCook.css">
    
</head>
    <body>
      <div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="profile.php">Profile</a>
          <hr>
  <a href="save.php">Add Recipe</a>
          <hr>
  <a href="about.php">About</a>
</div>

<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; <img src="img/53365-recipes-cooking-book.svg"></span>
         <div id = 'searchForm'>
               <h2>Search Recipe</h2>
           <form action="save.php" method="post">
               <span><p>Main Ingredients:</p></span>
               <span><select name = "ingredient"></select> </span>
               <span><p>Second Ingredients:</p></span>
               <span><select name = "ingredient"></select> </span>
               <span><p>Third Ingredients:</p></span>
               <span><select name = "ingredient"></select> </span>
               <span><input id = "logIn"  name = 'search' type="submit" value="Search" style="margin-top:5vh; width: "></span>
           </form>
        </div>
   
         <div id = 'suggested'>
              <hr>
               <h2>Suggested Recipes</h2>
           
        </div>
          <footer>
              <b><i><p>PocketCook</p>
  <p>University of Strathclyde 2017</p></i></b>
</footer>

<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
        </script>
    </body>
</html>