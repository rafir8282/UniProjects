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

    if (isset($_POST['submit'])) {
        
        try {
            
            $connection = new PDO($dsn, $username, $password, $options);  
            
            $work = [
                "id" => $_POST['id'],
                "assignmentname" => $_POST['assignmentname'],
                "unit" => $_POST['unit'],
                "duedate" => $_POST['duedate']
            ];
            
            $sql = "UPDATE `assignments` 
                    SET id = :id, 
                        assignmentname = :assignmentname, 
                        unit = :unit, 
                        duedate = :duedate
                    WHERE id = :id";

            $statement = $connection->prepare($sql);
            $statement->execute($work);
            
        } catch(PDOException $error) {
            echo $sql . "<br>" . $error->getMessage();
        }
    }

    if (isset($_GET['id'])) {
        
        try {
            
            $connection = new PDO($dsn, $username, $password, $options);
            
            $id = $_GET['id'];
            
            $sql = "SELECT * FROM assignments WHERE id = :id";
            
            $statement = $connection->prepare($sql);
            $statement->bindValue(':id', $id);
            $statement->execute();
            
            $work = $statement->fetch(PDO::FETCH_ASSOC); 

        } catch(PDOExcEption $error) {
            echo $sql . "<br>" . $error->getMessage();
        }

    } else {
        echo "No ID - Something went wrong.";
    };

?>

<?php include "templates/header.php"; ?>

<h2 style="padding: 25px;">Edit Your Assignment</h2>

<?php if (isset($_POST['submit']) && $statement) : ?>

    <?php header("Location: update.php"); ?>

<?php endif; ?>

<form method="post" style="padding: 25px;">

    <div class="form-group">
        <label for="id">ID</label>
        <input type="text" readonly class="form-control" name="id" id="id" value="<?php echo escape($work['id']); ?>" >
    </div>

    <div class="form-group">
        <label for="artistname">Assignment Name</label>
        <input type="text" class="form-control" name="assignmentname" id="assignmentname" value="<?php echo escape($work['assignmentname']); ?>">
    </div>

    <div class="form-group">
        <label for="worktitle">Unit Name</label>
        <input type="text" class="form-control" name="unit" id="unit" value="<?php echo escape($work['unit']); ?>">
    </div>

    <div class="form-group">
        <label for="workdate">Due Date</label>
        <input type="date" class="form-control" name="duedate" id="duedate" value="<?php echo escape($work['duedate']); ?>">
    </div>
    
    <br>

    <input type="submit" name="submit" value="Save" class="btn btn-primary">

    <br><br>

</form>

<?php include "templates/footer.php"; ?>