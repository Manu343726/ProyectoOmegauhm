<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Proyecto OHM</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<link href="${pageContext.request.contextPath}/resources/css/questions.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/extra.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/forum.css" rel="stylesheet">

</head>
<body>

	<%@ include file="../fragments/header.jspf"%>


	<div class="container">
		<form method="POST" enctype="multipart/form-data" action="fileload">
			<div class="form-group">
				<label for="exampleInputFile">File input</label> <input type="file"
					class="form-control" id="file" name="file" required>
				<p class="help-block">Select your file.</p>
				<input type="text" class="form-control" id="tags" name="tags"/>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
	<!-- CONTAINER -->

	<%@ include file="../fragments/footer.jspf"%>