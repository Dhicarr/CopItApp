<?php
    function showProducts(){
        $count = 0;
        $sqlcon = mysqli_connect("127.0.0.1", "copit", "309bhd", "csc301");
        if (mysqli_connect_errno($sqlcon)) {
            echo "Failed to Connect to DB" . mysqli_connect_error();
        }
        
        $sqlquery = "SELECT * FROM Products";
        if (!($statement = $sqlcon->prepare($sqlquery))) {
            echo "Prepare failed";
        }
        
        if (!$statement->execute()) {
            echo "Execute failed";
        }
        $result = $statement->get_result();
        if(mysqli_num_rows($result) > 0){
            while($row = mysqli_fetch_assoc($result)){
                echo "<tr><td>". $row["Id"] ."</td><td>". $row["Sid"] 
                ."</td><td>". $row["Title"] ."</td><td>". $row["Price"] 
                ."</td><td>". $row["Category"] ."</td><td>". $row["Description"]
                ."</td><td><image width=100 height=100 src=". $row["ImgSrc"] 
                ."/></td><td><form method=POST>Confirm<input type=checkbox id=deleteconfirm 
                name=deleteconfirm value=". $row["Id"] ." required /><input type=submit name=productconfirm value=confirm /></form></td><tr>";
                $count += 1;
            }
            echo $count. " results";
        }
        else{
            echo "0 result";
        }
        mysqli_close($sqlcon);
    }

    function deleteProduct($id){
        $sqlcon = mysqli_connect("127.0.0.1", "copit", "309bhd", "csc301");
        if (mysqli_connect_errno($sqlcon)) {
            echo "Failed to Connect to DB" . mysqli_connect_error();
        }
        
        $sqlquery = "DELETE FROM Products WHERE Id=$id";
        if (mysqli_query($sqlcon, $sqlquery)) {
            echo "Record deleted successfully";
        } else {
            echo "Error deleting record: " . mysqli_error($sqlcon);
        }
        mysqli_close($sqlcon);
    }

    function showUsers(){
        $count = 0;
        $sqlcon = mysqli_connect("127.0.0.1", "copit", "309bhd", "csc301");
        if (mysqli_connect_errno($sqlcon)) {
            echo "Failed to Connect to DB" . mysqli_connect_error();
        }
        
        $sqlquery = "SELECT * FROM Person";
        if (!($statement = $sqlcon->prepare($sqlquery))) {
            echo "Prepare failed";
        }
        
        if (!$statement->execute()) {
            echo "Execute failed";
        }
        $result = $statement->get_result();
        if(mysqli_num_rows($result) > 0){
            while($row = mysqli_fetch_assoc($result)){
                echo "<tr><td>". $row["Pid"] ."</td><td>". $row["Password"] 
                ."</td><td>". $row["Email"] ."</td><td>". $row["Phone"] 
                ."</td><td>". $row["name"] ."</td><td><form method=POST>Confirm<input type=checkbox id=deleteconfirm 
                name=deleteconfirm value=". $row["Pid"] ." required /><input type=submit name=userconfirm value=confirm /></form></td><tr>";
                $count += 1;
            }
            echo "<center>" .$count. " results</center>";
        }
        else{
            echo "<center>0 result</center>";
        }
        mysqli_close($sqlcon);
    }

    function deleteUser($id){
        $sqlcon = mysqli_connect("127.0.0.1", "copit", "309bhd", "csc301");
        if (mysqli_connect_errno($sqlcon)) {
            echo "Failed to Connect to DB" . mysqli_connect_error();
        }
        
        $sqlquery = "DELETE FROM Person WHERE Pid='$id'";
        if (mysqli_query($sqlcon, $sqlquery)) {
            echo "Record deleted successfully";
        } else {
            echo "Error deleting record: " . mysqli_error($sqlcon);
        }
        mysqli_close($sqlcon);
    }

    function showUser($userAttributes, $value){
        $count = 0;
        $sqlcon = mysqli_connect("127.0.0.1", "copit", "309bhd", "csc301");
        if (mysqli_connect_errno($sqlcon)) {
            echo "Failed to Connect to DB" . mysqli_connect_error();
        }
        
        $sqlquery = "SELECT * FROM Person WHERE ". $userAttributes ." LIKE \"%". $value ."%\"";
        if (!($statement = $sqlcon->prepare($sqlquery))) {
            echo "Prepare failed";
        }
        
        if (!$statement->execute()) {
            echo "Execute failed";
        }
        $result = $statement->get_result();
        if(mysqli_num_rows($result) > 0){
            while($row = mysqli_fetch_assoc($result)){
                echo "<tr><td>". $row["Pid"] ."</td><td>". $row["Password"] 
                ."</td><td>". $row["Email"] ."</td><td>". $row["Phone"] 
                ."</td><td>". $row["name"] ."</td><td><form method=POST>Confirm<input type=checkbox id=deleteconfirm 
                name=deleteconfirm value=". $row["Pid"] ." required /><input type=submit name=userconfirm value=confirm /></form></td><tr>";
                $count += 1;
            }
            echo "<center>" .$count. " results</center>";
        }
        else{
            echo "<center>0 result</center>";
        }
        mysqli_close($sqlcon);
    }
?>