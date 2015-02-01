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

	<link href="resources/css/extra.css" rel="stylesheet">
	
  </head>
<body>

<%@ include file="../fragments/header.jspf" %>

<div class="container">

  		<div class="row">
    		<div class="form col-md-8 col-md-offset-2">
    		
    			<form role="form" action="${pageContext.request.contextPath}/newanswer/${topic_id}" id="formularioNewAnswer" method="POST">
	    			<h2 class="form-heading">Responder</h2>
	    			<textarea type="text" class="form-control" rows="5" placeholder="Texto" id="text" name="text" required autofocus></textarea><br>
	    			<button class="btn btn-info pull-right" name="submit" value="${pageContext.request.contextPath}/newanswer/${topic_id}" type="submit">Enviar</button>
    			</form>
    			
    		</div>
  			
  		</div>
  		
</div>

<%@ include file="../fragments/footer.jspf" %>
