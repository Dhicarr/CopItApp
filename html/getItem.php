<?php

$sid = $_REQUEST['sid']; //Seller-ID (if specified).
$category = $_REQUEST['category']; //Category (if specified).
$id = $_REQUEST['id']; //Product-ID (if specified).

$response = array(); 
$response['error'] = false; 

$sqlcon = mysqli_connect("127.0.0.1", "copit", "309bhd", "csc301");
if (mysqli_connect_errno($sqlcon)) {
    $response['message'] =  "Failed to Connect to DB" . mysqli_connect_error();
    $response['error'] = true;
}

if (isset($_REQUEST['category'])) { //Products of specific category
    $sqlquery = "SELECT * FROM Products WHERE Category = ?";
    if (!($statement = $sqlcon->prepare($sqlquery))) {
        $response['message'] = "Prepare failed";
        $response['error'] = true; 
    }
} else if (isset($_REQUEST['sid'])) { //Products with seller id (from given username)
    $sqlquery = "SELECT * FROM Products WHERE sid = ?"; 
    if (!($statement = $sqlcon->prepare($sqlquery))) {
        $response['message'] = "Prepare failed";
        $response['error'] = true; 
    }
} else if (isset($_REQUEST['id'])) { //Products with given ID
    $sqlquery = "SELECT * FROM Products WHERE id = ?";
    if (!($statement = $sqlcon->prepare($sqlquery))) {
        $response['message'] = "Prepare failed";
        $response['error'] = true; 
    }
} else { //All Products
    $sqlquery = "SELECT * From Products";
}

if (!($statement = $sqlcon->prepare($sqlquery))) {
    $response['message'] = "Prepare failed";
    $response['error'] = true; 
}

if (isset($_REQUEST['category'])) {
    if (!$statement->bind_param("s", $category)) {
        $response['message'] = "Binding parameters failed: (" . $stmt->errno . ") " . $stmt->error;
        $response['error'] = true; 
    }
} else if (isset($_REQUEST['sid'])) {
    if (!$statement->bind_param("s", $sid)) {
        $response['message'] = "Binding parameters failed: (" . $stmt->errno . ") " . $stmt->error;
        $response['error'] = true; 
    }
} else if (isset($_REQUEST['id'])) {
    if (!$statement->bind_param("s", $id)) {
        $response['message'] = "Binding parameters failed: (" . $stmt->errno . ") " . $stmt->error;
        $response['error'] = true; 
    }
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
