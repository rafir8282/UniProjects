<?php

    session_start();
    
    if (isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] === true) {

        header("location: welcome.php");
        exit;

    }
    
    require_once "config.php";
    
    $username = $password = "";
    $username_err = $password_err = "";
    
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
    
        if (empty(trim($_POST["username"]))) {
            $username_err = "Please enter username.";
        } else {
            $username = trim($_POST["username"]);
        }
        
        if (empty(trim($_POST["password"]))) {
            $password_err = "Please enter your password.";
        } else {
            $password = trim($_POST["password"]);
        }
        
        if (empty($username_err) && empty($password_err)) {
            
            $sql = "SELECT id, username, password FROM users WHERE username = :username";
            
            if ($stmt = $pdo_connection->prepare($sql)) {
                
                $stmt->bindParam(":username", $param_username, PDO::PARAM_STR);
                
                $param_username = trim($_POST["username"]);
                
                if ($stmt->execute()) {
                    
                    if ($stmt->rowCount() == 1) {
                        
                        if ($row = $stmt->fetch()) {
                            
                            $id = $row["id"];
                            $username = $row["username"];
                            $hashed_password = $row["password"];

                            if (password_verify($password, $hashed_password)) {
                                
                                session_start();
                                
                                $_SESSION["loggedin"] = true;
                                $_SESSION["id"] = $id;
                                $_SESSION["username"] = $username;                            
                                
                                header("location: welcome.php");
                            
                            } else {
                                $password_err = "The password you entered was not valid.";
                            }

                        }

                    } else {
                        $username_err = "No account found with that username.";
                    }

                } else {
                    echo "Oops! Something went wrong. Please try again later.";
                }

            }
            
            unset($stmt);

        }
        
        unset($pdo_connection);
        
    }

?>

<?php include "templates/header.php"; ?>

<div class="wrapper">

    <h2 style="padding: 25px;">Log In</h2>

    <p style="padding-left: 25px;">Please fill in your credentials to login.</p>

    <form style="padding: 25px;" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">

        <div class="form-group <?php echo (!empty($username_err)) ? 'has-error' : ''; ?>">
            <label>Username</label>
            <input type="text" name="username" class="form-control" value="<?php echo $username; ?>">
            <span class="help-block"><?php echo $username_err; ?></span>
        </div>

        <div class="form-group <?php echo (!empty($password_err)) ? 'has-error' : ''; ?>">
            <label>Password</label>
            <input type="password" name="password" class="form-control">
            <span class="help-block"><?php echo $password_err; ?></span>
        </div>

        <br>

        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Login">
        </div>

    </form>

    <p style="padding-left: 25px;">Don't have an account? <a href="register.php">Sign up now</a>.</p>

    <br>

</div>

<?php include "templates/footer.php"; ?>