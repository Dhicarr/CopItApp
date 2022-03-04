<?php

/*
* Call this given a Buyer ID, returns Item a given buyer has liked in the past.
*/

$bid = $_REQUEST['bid']; //Buyer ID

$response = array(); 
$response['error'] = false;

$sqlcon = mysqli_connect("127.0.0.1", "copit", "309bhd", "csc301");
if (mysqli_connect_errno($sqlcon)) {
    $response['message'] = "Failed to Connect to DB" . mysqli_connect_error();
    $response['error'] = true;
}

$sqlquery = "SELECT P.Id, P.Sid, P.Title, P.Price, P.Category, P.Description, P.ImgSrc FROM Products P INNER JOIN likes ON (P.Id = likes.Id) WHERE likes.Bid = ? ORDER BY likes.Date DESC";

if (!($statement = $sqlcon->prepare($sqlquery))) {
    $response['message'] = "Prepare failed";
    $response['error'] = true;
}

if (!$statement->bind_param("s", $bid)) {
    $response['message'] = "Binding parameters failed: (" . $stmt->errno . ") " . $stmt->error;
    $response['error'] = true;
}

if (!$statement->execute()) {
    $response['message'] = "Execute failed";
    $response['error'] = true;
}

$statement->bind_result($id, $sid, $title, $price, $category, $description, $imgsrc);
$response['items'] = array();

while ($statement->fetch()) {
    $temp = ['id'=>$id, 'sid'=>$sid, 'title'=>$title, 'price'=>$price, 'category'=>$category, 'description'=>$description, 'imgsrc'=>$imgsrc];
    array_push($response['items'], $temp);
}

echo json_encode($response);


?>
