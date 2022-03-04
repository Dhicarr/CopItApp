<?php

$username = $_REQUEST['username'];
$password = $_REQUEST['password'];

$response = array();

$sqlcon = mysqli_connect("127.0.0.1", "copit", "309bhd", "csc301");
if (mysqli_connect_errno($sqlcon)) {
    echo "Failed to Connect to DB" . mysqli_connect_error();
}

$sqlquery = "SELECT EXISTS(SELECT * FROM Person WHERE Pid = ? AND Password = ?)";

if (!($statement = $sqlcon->prepare($sqlquery))) {
    $response['error'] = true;
    $response['message'] = "Prepare Failed";
}

if (!$statement->bind_param("ss", $username, $password)) {
    $response['error'] = true;
    $response['message'] = "Binding Failed";
}

if (!$statement->execute()) {
    $response['error'] = true;
    $response['message'] = "Execute Failed";
}
$result = $statement->get_result();
if ($result->fetch_array()[0] == "0") {
    $response['error'] = false;
    $response['message'] = "User logged in.";
} else {
    $response['error'] = true;
    $response['message'] = "User already exists";
}

echo json_encode($response);

?>