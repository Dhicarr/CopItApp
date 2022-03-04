<?php

$sid = $_REQUEST['sid'];
$title = $_REQUEST['title'];
$price = $_REQUEST['price'];
$category = $_REQUEST['category'];
$description = $_REQUEST['description'];
$imgsrc = $_REQUEST['imgsrc'];

echo $sid . $title . $price . $category . $description . $imgsrc;

$sqlcon = mysqli_connect("127.0.0.1", "copit", "309bhd", "csc301");
if (mysqli_connect_errno($sqlcon)) {
    echo "Failed to Connect to DB" . mysqli_connect_error();
}

$sqlquery = "INSERT INTO Products (Sid, Title, Price, Category, Description, ImgSrc) VALUES (?, ?, ?, ?, ?, ?)";

if (!($statement = $sqlcon->prepare($sqlquery))) {
    echo "Prepare failed: (" . $mysqli->errno . ") " . $mysqli->error;
    //http_response_code(403);
}

if (!$statement->bind_param("ssssss", $sid, $title, $price, $category, $description, $imgsrc)) {
    echo "Binding parameters failed: (" . $stmt->errno . ") " . $stmt->error;
    //http_response_code(403);
}

//if (!$statement->bind_param("i", $_REQUEST['username'], $_REQUEST['passhash'], $_REQUEST['email'], $_REQUEST['phone'], $_REQUEST['name'])) {
 //   echo "Binding parameters failed: (" . $stmt->errno . ") " . $stmt->error;
//}
if (!$statement->execute()) {
    echo "Execute failed: (" . $stmt->errno . ") " . $stmt->error;
    //http_response_code(403);
}
if ($statement->affected_rows == 1) {
    echo "TRUE";
} else {
    echo "FALSE";
}

?>