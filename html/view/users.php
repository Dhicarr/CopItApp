<?php
    require_once "DB.php";
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="style.css"/>
        <title>CopIt Admin Users</title>
    </head>
    <body style="background-color:#FC937D"> 

        <header>
            <h1 align="center">CopIt Admin Main Menu</h1>
        </header>

        <main>
            <ul>
                <li><a href="index.php?a=mainmenu">mainmenu</a></li>
                <li><a href="index.php?a=products">products</a></li>
                <li><a href="index.php?a=users">users</a></li>
                <li><a href="index.php?a=mainmenu">feature3</a></li>
                <li><a href="index.php?a=setting">setting</a></li>
                <li><a href="index.php?a=logout">logout</a></li>
            </ul>
            <h2>Users</h2>
            <form align="center" method="POST">
                <label for="userAttributes">Choose an attribute:</label>
                <select name="userAttributes">
                    <option value="default">--option--</option>
                    <option value="Pid">Pid</option>
                    <option value="Email">Email</option>
                    <option value="Phone">Phone</option>
                    <option value="name">name</option>
                </select>
                <label for="search">Contains:</label>
                <input type=text name=search placeholder="Search.." />
                <input type=submit name=searchconfirm value=search />
            </form>
        </main>
        <form>
            <table border="border" align="center">
                <tr>
                    <th>Pid</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>name</th>
                    <th>Delete</th>
                </tr>
                <?php
                    if($_REQUEST['userAttributes'] !== "default" && isset($_REQUEST['search'])){
                        showUser($_REQUEST['userAttributes'], $_REQUEST['search']);
                    }else if ($_REQUEST['userAttributes'] === "default" && $_REQUEST['search'] !== ""){
                        echo "<center><h3>Please pick an attribute!</h3></center>";
                    }else{
                        showUsers();
                    }
                ?>
            </table>
        </form>
        
        <footer>&copy; Copyright 2020 BackHomeDiesel Inc. </footer>
    </body>
</html>