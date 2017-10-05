<?php
$fname = $_POST['fname'];
$fage = $_POST['fage'];

include("dbconfig.php");

$result = mysql_query("INSERT INTO Users VALUES(null,'$fname','$fage')");	

$response = array();

 if($result){
 	$response['success']=1;
 	$response['message']="Record Inserted Sucessfully";
 }else{
 	$response['success']=0;
 	$response['message']="Insertion Failure";
 }
 
 echo json_encode($response);
?>