<?php

if ($_SERVER['REQUEST_METHOD']=='POST'){

require_once 'connect.php';

$nome      = $_POST['nome'];
$fone	   = $_POST['fone'];
$email	   = $_POST['email'];
$detalhes  = $_POST['detalhes'];
$cep	   = $_POST['cep'];
$cidadeUf  = $_POST['cidadeUf'];
$endereco  = $_POST['endereco'];
$sexo	   = $_POST['sexo'];
$fisica    = $_POST['fisica'];
$auditiva  = $_POST['auditiva'];
$visual	   = $_POST['visual'];
$mental	   = $_POST['mental'];

$sql          = "INSERT INTO cadastro(nome, fone, email, detalhes, cep, cidadeUf, endereco, sexo, fisica, auditiva, visual, mental)VALUES ('$nome', '$fone', '$email', '$detalhes', '$cep', '$cidadeUf', '$endereco', '$sexo', '$fisica', '$auditiva', '$visual', '$mental')";


if(mysqli_query($conn, $sql)){
	$result["sucess"] = "1";
	$result["message"] = "Sucess";
	echo json_encode($result);
} else {
	$result["sucess"] = "0";
	$result["message"] = "error";

	echo json_encode($result);
	mysql_close($conn);
}


}