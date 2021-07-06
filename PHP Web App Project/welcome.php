<?php

    session_start();
    
    if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {

        header("location: index.php");
        exit;
        
    }

?>

<?php include "templates/header.php"; ?>

<div id="heading">

    <h1 id="title">Welcome, <b><?php echo htmlspecialchars($_SESSION["username"]); ?></b>.</h1>
    <h2 id="subtitle">Track your assignments. Never miss a due date!</h2>

</div>

<?php include "templates/footer.php"; ?>