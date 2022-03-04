<?php
    require_once "DB.php";
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="style.css"/>
        <title>CopIt Admin Accounts</title>
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
            <h2>Products</h2>
        </main>
        <form>
            <table border="border">
                <tr>
                    <th>Id</th>
                    <th>Sid</th>
                    <th>Title</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Delete</th>
                </tr>
                <?php
                    showProducts();
                ?>
            </table>
        </form>
        
        <footer>&copy; Copyright 2020 BackHomeDiesel Inc. </footer>
    </body>
</html>