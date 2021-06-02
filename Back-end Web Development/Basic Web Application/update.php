<?php

    session_start();
    
    if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {

        header("location: index.php");
        exit;
        
    }

?>

<?php 
    
    require "config.php"; 
    
    try {
        
        $connection = new PDO($dsn, $username, $password, $options);

        $user = htmlspecialchars($_SESSION["username"]);

        $sql = "SELECT * FROM assignments WHERE username='" . $user . "'";
        
        $statement = $connection->prepare($sql);
        $statement->execute();
        
        $result = $statement->fetchAll();

	} catch(PDOException $error) {
        echo $sql . "<br>" . $error->getMessage();
	}	

?>

<?php include "templates/header.php"; ?>

<h2 style="padding: 25px;">Your Assignments</h2>

<?php foreach($result as $row) { ?>

    <hr>

    <h3 style="padding: 25px;"><?php echo $row['assignmentname']; ?> (<?php echo $row['unit']; ?>)</h3>

    <h4 style="padding-left: 25px; padding-bottom: 25px;">Due: <?php echo $row['duedate']; ?></h4>

    <h4 style="padding-left: 25px; padding-bottom: 25px; color: yellow;">
        <?php

            $cdate = strtotime($row["duedate"]);
            $today = time();
            $difference = $cdate - $today;

            if ($difference < 0) {
                $difference = 0;
            }

            echo "Days remaining: " . round($difference/60/60/24);

        ?>
    </h4>

    <a href='update-work.php?id=<?php echo $row['id']; ?>' style="padding: 25px;"><input type="submit" value="Edit" class="btn btn-primary"></a>
    <a href='delete.php?id=<?php echo $row['id']; ?>'><input type="submit" value="Delete" class="btn btn-danger"></a>

    <br><br>

<?php }; ?>

<br>

<?php include "templates/footer.php"; ?>