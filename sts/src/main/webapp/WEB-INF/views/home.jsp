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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<link href="resources/css/questions.css" rel="stylesheet">
<link href="resources/css/files.css" rel="stylesheet">
<link href="resources/css/activities.css" rel="stylesheet">
<link href="resources/css/extra.css" rel="stylesheet">

</head>
<body>

	<%@ include file="../fragments/header.jspf"%>

	<div class="container">

		<div class="row row-offcanvas row-offcanvas-right">

			<div class="col-md-12">
				<div class="jumbotron">
					<p>Hay tres usuarios para hacer login: user, admin y test. La
						contraseña es la misma para los tres: hola</p>
				</div>
				<!-- jumbotron -->
			</div>


			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">Últimas Preguntas</h1>
					</div>
					<div class="panel panel-body">
						<c:forEach items="${threads}" var="t">
							<jsp:include page="../fragments/question-summary.jsp">
								<jsp:param name="title" value="${t.title}" />
								<jsp:param name="id" value="${t.id}"/>
								<jsp:param name="tags" value="${t.tags}" />
								<jsp:param name="votes" value="${t.question.votesCount}" />
								<jsp:param name="answers" value="${t.answersCount}" />
								<jsp:param name="views" value="${t.viewsCount}" />
								<jsp:param name="date" value="${t.question.timeStamp}" />
							</jsp:include>
						</c:forEach>
					</div>
					<!-- panel-body -->
				</div>
				<!-- PANEL IZQUIERDA(Preguntas) -->
			</div>

			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">Última Actividad</h1>
					</div>
					<div class="panel panel-body">
						<c:forEach items="${filesOrderedByDate}" var="f">
							<jsp:include page="../fragments/file-summary.jsp">
								<jsp:param name="id" value="${f.id}" />
								<jsp:param name="name" value="${f.name}"/>
								<jsp:param name="date" value="${f.timeStamp}" />
								<jsp:param name="tags" value="${f.tags}" />
								<jsp:param name="owner" value="${f.owner}" />
							</jsp:include>
						</c:forEach>
					</div>
					<!-- panel-body -->
				</div>
				<!-- PANEL DERECHA(Actividad) -->
			</div>


		</div>
	</div>

	<%@ include file="../fragments/footer.jspf"%>