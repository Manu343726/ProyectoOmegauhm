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
<link href="${pageContext.request.contextPath}/resources/css/activities.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/extra.css" rel="stylesheet">

</head>
<body>

	<%@ include file="../fragments/header.jspf"%>

	<div class="container">
	
	<div class="row row-offcanvas row-offcanvas-right">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">${topic.title}</h1>
					
					${topic_question.text}
				</div>

				<c:forEach items="${topic_answers}" var="answer">
					<div class="panel panel-body">
						${answer.text}
					</div>
				</c:forEach>
			</div>
		</div>
		</div>
	</div>

	<%@ include file="../fragments/footer.jspf"%>