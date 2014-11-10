<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Proyecto OHM</title>

    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


  </head>
<body>

<%@ include file="../fragments/header.jspf" %>

<div class="container">
  		<div class="row">
    		<div class="form col-md-8 col-md-offset-2">
    	
    			<div class="row">
    				<textarea class="form-control" rows="1">Titulo</textarea>
    			</div>
    			<br>
    	
    			<div class="row">
    				<textarea class="form-control" rows="5">Contenido</textarea>
    			</div>
    			<br>
    	
    			<div class="row">
    				<textarea class="form-control" rows="1">Tags</textarea>
    			</div>
    			<br>
    			
    			<div class="row">
    				<button type="button" class="btn btn-info col-md-1 col-md-offset-10">Enviar</button>
    			</div>
    		</div>
  			
  		</div>
</div>

<%@ include file="../fragments/footer.jspf" %>
