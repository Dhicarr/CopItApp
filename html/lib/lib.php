<?php 

    //Returns true iff. username and password given are correct
    function verifyPassword($username, $password) {
        if($username === "admin" && $password === "admin1234")
            return TRUE;
        return FALSE;
    }

    //Retuns page state to use on the FSM of controller based on login state of use.
    function getPage($state) {
        if (isset($_SESSION['user'])) { //Logged in users
            switch ($state) {
                case "login":
                    return "login";
                break;

                case "mainmenu":
                    return "mainmenu";
                break;

                case "logout":
                    return "logout";
                break;

                case "setting":
                    return "setting";
                break;

                case "products":
                    return "products";
                break;

                case "users":
                    return "users";
                break;

                default:
                    return "login";
                break;
            }
        } else {
            switch ($state) { //Not logged in users.
                case "login":
                    return "login";
                break;

                case "mainmenu":
                    return "mainmenu";
                break;

                case "logout":
                    return "logout";
                break;

                case "setting":
                    return "setting";
                break;

                case "products":
                    return "products";
                break;

                case "users":
                    return "users";
                break;

                default:
                    return "login";
                break;
            }
        }
    }
?>