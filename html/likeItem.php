<?php

$bid = $_REQUEST['bid'];
$id = $_REQUEST['id'];

//echo $bid . $id . $date;

$sqlcon = mysqli_connect("127.0.0.1", "copit", "309bhd", "csc301");
if (mysqli_connect_errno($sqlcon)) {
    echo "Failed to Connect to DB" . mysqli_connect_error();
}

$sqlquery = "INSERT INTO likes (Bid, Id, Date) VALUES (?, ?, curdate())";

if (!($statement = $sqlcon->prepare($sqlquery))) {
    echo "Prepare failed: (" . $mysqli->errno . ") " . $mysqli->error;
    //http_response_code(403);
}

if (!$statement->bind_param("ss", $bid, $id)) {
    echo "Binding parameters failed: (" . $statement->errno . ") " . $statement->error;
    //http_response_code(403);
}

if (!$statement->execute()) {
    echo "Execute failed: (" . $statement->errno . ") " . $statement->error;
}
if ($statement->affected_rows == 1) {
    echo "TRUE";
} else {
    echo "FALSE";
}

?>