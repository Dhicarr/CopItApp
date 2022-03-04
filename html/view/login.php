<?php 
$_REQUEST['username'] = !empty($_REQUEST['username']) ? $_REQUEST['username'] : ''; //Refills username on login
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="style.css"/>
        <title>CopIt Admin Login</title>
    </head>
    <body style="background-color:#FC937D"> 

        <header>
            <h1 align="center">CopIt Admin Login</h1>
        </header>

        <main>
            <h2>Login</h2>
            <table>
                <form action="index.php", method="post">
                    <tr>
                        <th> <label for="username">User Name</label> </th>
                        <td> <input type="text" name="username" value=<?php echo $_REQUEST['username'] ?>> </td>
                    </tr>
                    <tr>
                        <th> <label for="password">Password</label> </th>
                        <td> <input type="password" name="password"> </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td> <input type="submit" name="submit" value="Login"> </td>
                    </tr>
                </form>
            </table>
        </main>
        
        <footer>&copy; Copyright 2020 BackHomeDiesel Inc. </footer>
    </body>
</html>

