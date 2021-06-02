<?php

    session_start();
    
    if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {
        
        header("location: index.php");
        exit;
        
    }

?>

<?php
    
    require "config.php";
    require "common.php";

    if (isset($_GET["id"])) {
        
        try {
            
            $connection = new PDO($dsn, $username, $password, $options);
            
            $id = $_GET["id"];
            
            $sql = "DELETE FROM assignments WHERE id = :id";

            $statement = $connection->prepare($sql);
            $statement->bindValue(':id', $id);
            $statement->execute();

            header("Location: update.php");

        } catch(PDOException $error) {
            echo $sql . "<br>" . $error->getMessage();
        }
    
    };

?>