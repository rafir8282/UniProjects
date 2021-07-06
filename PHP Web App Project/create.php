<?php

    session_start();
    
    if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {

        header("location: index.php");
        exit;
    
    }

?>

<?php 

    if (isset($_POST['submit'])) {
        
        require "config.php"; 
        
        try {

            $connection = new PDO($dsn, $username, $password, $options);
            
            $new_work = array( 
                "assignmentname" => $_POST['assignmentname'], 
                "unit" => $_POST['unit'],
                "duedate" => $_POST['duedate'],
                "username" => $_POST['username']
            );
            
            $sql = "INSERT INTO assignments (assignmentname, unit, duedate, username) VALUES (:assignmentname, :unit, :duedate, :username)";   
            

            $statement = $connection->prepare($sql);
            $statement -> execute($new_work);

        } catch(PDOException $error) {
            echo $sql . "<br>" . $error->getMessage();
        }
    
    }

?>

<?php include "templates/header.php"; ?>

<h2 style="padding: 25px;">Add an Assignment</h2>

<?php if (isset($_POST['submit']) && $statement) { ?>

    <?php header("Location: update.php"); ?>

<?php } ?>

<form method="post" style="padding: 25px;">

    <div class="form-group">
        <label for="assignmentname">User</label>
        <input type="text" class="form-control" readonly name="username" id="username" value=<?php echo htmlspecialchars($_SESSION["username"]); ?>>
    </div>

    <div class="form-group">
        <label for="assignmentname">Assignment Name</label>
        <input type="text" class="form-control" name="assignmentname" id="assignmentname" placeholder="Enter the name of your assignment">
    </div>

    <div class="form-group">
        <label for="unit">Unit Name</label>
        <input type="text" class="form-control" name="unit" id="unit" placeholder="Enter the name of your unit/class">
    </div>

    <div class="form-group">
        <label for="duedate">Due Date</label>
        <input type="date" class="form-control" name="duedate" id="duedate">
    </div>

    <br>

    <input type="submit" name="submit" class="btn btn-primary">

    <br><br>

</form>

<?php include "templates/footer.php"; ?>