<?php 
// Source: https://www.androidhive.info/2014/12/android-uploading-camera-image-video-to-server-with-progress-bar/


$storage_path = "images/";
$response = array();

print_r($_FILES);
if (isset($_FILES['image']['name'])) {
    $storage_path .= basename($_FILES['image']['name']);

    try {
        if (!move_uploaded_file($_FILES['image']['tmp_name'], $storage_path)) {
            $response['error'] = true;
            $response['message'] = "Could not upload file.";
        } else {
            $response['message'] = "Upload complete.";
            $response['error'] = false;
        }
    } catch (Exception $e) {
        $response['error'] = true;
        $response['message'] = $e->getMessage();
    }
} else {
    $response['error'] = true;
    $response['message'] = "2No file uploaded.";
}

echo json_encode($response);

?>
