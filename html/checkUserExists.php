<?php

$username = $_REQUEST['username'];

$sqlcon = mysqli_connect("127.0.0.1", "copit", "309bhd", "csc301");
if (mysqli_connect_errno($sqlcon)) {
    echo "Failed to Connect to DB" . mysqli_connect_error();
}

$sqlquery = "SELECT EXISTS(SELECT * FROM Person WHERE Pid = ?)";

if (!($statement = $sqlcon->prepare($sqlquery))) {
    echo "Prepare failed";
}

if (!$statement->bind_param("s", $username)) {
    echo "Binding failed";
}

if (!$statement->execute()) {
    echo "Execute failed";
}
$result = $statement->get_result();
if ($result->fetch_array()[0] == "0") {
    echo "FALSE"; //User does not exist
} else {
    echo "TRUE"; //User exists
}

?>
