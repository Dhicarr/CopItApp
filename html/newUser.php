<?php

$username = $_REQUEST['username'];
$passhash = $_REQUEST['passhash'];
$email = $_REQUEST['email'];
$phone= $_REQUEST['phone'];
$name = $_REQUEST['name'];

#echo ($username. " " . $passhash . " " . $email . " " . $phone . " " . $name);

$sqlcon = mysqli_connect("127.0.0.1", "copit", "309bhd", "csc301");
if (mysqli_connect_errno($sqlcon)) {
    echo "Failed to Connect to DB" . mysqli_connect_error();
}

$sqlquery = "INSERT INTO Person (Pid, Password, Email, Phone, Name) VALUES (?, ?, ?, ?, ?)";

if (!($statement = $sqlcon->prepare($sqlquery))) {
    echo "Prepare failed: (" . $mysqli->errno . ") " . $mysqli->error;
    http_response_code(403);
}

if (!$statement->bind_param("sssss", $username, $passhash, $email, $phone, $name)) {
    echo "Binding parameters failed: (" . $stmt->errno . ") " . $stmt->error;
    http_response_code(403);
}

//if (!$statement->bind_param("i", $_REQUEST['username'], $_REQUEST['passhash'], $_REQUEST['email'], $_REQUEST['phone'], $_REQUEST['name'])) {
 //   echo "Binding parameters failed: (" . $stmt->errno . ") " . $stmt->error;
//}
if (!$statement->execute()) {
    echo "Execute failed: (" . $stmt->errno . ") " . $stmt->error;
    http_response_code(403);
}
echo $statement->affected_rows;

?>

</html>