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
				<div class="col-md-4 col-md-offset-4">
					<button type="button" class="btn btn-primary btn-lg btn-block ">Sube tus apuntes</button>
				</div>
			</div>	
			

			<br>
			<div id="repository-content">
				<div class="row">
					<div class=" col-md-offset-2 col-md-12">
						<a href="#" class="thumbnail col-md-3">
							<img src="resources/images/carpeta.png" alt="carpeta 1">
								<div class="caption">
									<h3>Grado en Ingeniería Informática</h3>
								</div>
						</a>
						<a href="#" class="thumbnail col-md-offset-2 col-md-3">
							<img src="resources/images/carpeta.png" alt="carpeta 2" class="img-rounded">
								<div class="caption">
									<h3>Grado en Ingeniería de Computadores</h3>
								</div>
						</a>
					</div>
				</div>
				<div class="row">
					<div class=" col-md-offset-2 col-md-12">
						<a href="#" class="thumbnail col-md-3">
							<img src="resources/images/carpeta.png" alt="carpeta 3">
								<div class="caption">
									<h3>Grado en Ingeniería del Software</h3>
								</div>
						</a>
						<a href="#" class="thumbnail col-md-offset-2 col-md-3">
							<img src="resources/images/carpeta.png" alt="carpeta 4" class="img-rounded">
								<div class="caption">
									<h3>Doble Grado en Ingeniería Informática y Matemáticas</h3>
								</div>
						</a>
								
					</div>
				</div>
			</div>
</div>

<%@ include file="../fragments/footer.jspf" %>
