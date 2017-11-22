<?php require_once 'db.php' ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="stylePocketCook.css">
    
</head>
    <body>
       <div id = 'logForm'>
           <form action="profile" method="post">
               <h1>Login</h1>
           <p>Username:</p>
            <input type="text" name="useraName">
               <p>Password:</p>
               <input type="text" name="password"><br>
               <input id = "logInButton"  name = 'UIbutton' type="submit" value="Login">
           </form>
    </div>
    </body>
</html>
