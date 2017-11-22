<?php
        $host = 'devweb2017.cis.strath.ac.uk';
        $user = 'cs312_e';
        $pass = 'Xoh4xai4ugaa';
        $db = 'cs312_e';
        $conn = mysqli_connect($host,$user,$pass,$db);


      if($conn ->connect_error){
            die("Connection failed: ".$conn ->connect_error);
        }