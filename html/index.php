<?php
    session_save_path("sess");
    session_start(); //Starts session

    require_once "lib/lib.php";
    require_once "DB.php";
    $errors = array();
    $view="";

    if(isset($_GET['a'])) $_SESSION['state'] = $_GET['a'];
    if(isset($_REQUEST['productconfirm'])) $_SESSION['state'] = "products";
    if(isset($_REQUEST['userconfirm'])) $_SESSION['state'] = "users";
    if(isset($_REQUEST['searchconfirm'])) $_SESSION['state'] = "users";
    if (!isset($_SESSION['state'])) $_SESSION['state'] = 'login';
    $_SESSION['PAGE'] = getPage($_SESSION['state']); //Gets page to display (prevents forced login)

    switch ($_SESSION['PAGE']) {
        case "login":
            $view="login.php";
            if (!isset($_REQUEST['submit']) || $_REQUEST['submit']!="Login") break; //Check if login button pressed
            if (empty($_REQUEST['username']) || empty($_REQUEST['password'])){
                if (empty($_REQUEST['username']))
                    $errors[] = "User Name required to login.";
                if(empty($_REQUEST['password']))
                    $errors[] = "Password required to login.";
                break;
            }
            if (verifyPassword($_REQUEST['username'], $_REQUEST['password'])) { //Check if password is correct
                $_SESSION['user'] = 'loggedin';
                $_SESSION['state'] = "mainmenu";
                $view = "mainmenu.php";
            }else{
                $errors[] = "invalid username or password";
            }
        break;

        case "mainmenu":
            $view="mainmenu.php";
        break;

        case "setting":
            $view="setting.php";
        break;

        case "products":
            $view="products.php";
            if (!isset($_REQUEST['productconfirm']) || $_REQUEST['productconfirm']!="confirm") break; //Check if delete button pressed
            if(isset($_REQUEST['deleteconfirm'])){
                deleteProduct($_REQUEST['deleteconfirm']);
            }
        break;

        case "users":
            $view="users.php";
            if (!isset($_REQUEST['userconfirm']) || $_REQUEST['userconfirm']!="confirm") break; //Check if delete button pressed
            if(isset($_REQUEST['deleteconfirm'])){
                deleteUser($_REQUEST['deleteconfirm']);
            }
        break;

        case "logout":
            $_SESSION['state'] = 'login';
            $view="login.php";

        break;

        default:
            $view="login.php";
        break;
    }

    require_once "view/" . $view;
    echo implode("<br>", $errors); //Displays errors on each page.
?>