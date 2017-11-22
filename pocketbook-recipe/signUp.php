<?php require_once 'db.php'; ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="stylePocketCook.css">
    
</head>
    <body>
       <div id = 'logForm'>
           <form action="profile" method="post">
               <h1>Sign Up</h1>
               <p>Name:</p>
            <input type="text" name="name">
            <p>Username:</p>
            <input type="text" name="useraName">
               <p>Password:</p>
               <input type="text" name="password"><br>
               <input  name = 'UIbutton' type="submit" value="SignUp" >
           </form>
    </div>
    </body>
</html>
