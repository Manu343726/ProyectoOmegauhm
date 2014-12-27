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
								<jsp:param name="votes" value="${t.question.votes}" />
								<jsp:param name="answers" value="${t.answerscount}" />
								<jsp:param name="views" value="CER" />
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

						<div class="activity-summary pull-left">
							<div class="activity-stats pull-left">
								<div class="pull-left a-type">
									<div class="mini-counts">
										<span title="type">t</span>
									</div>
									<div>type</div>
								</div>
								<div class="pull-left a-votes">
									<div class="mini-counts">
										<span title="0 votes">0</span>
									</div>
									<div>answers</div>
								</div>
								<div class="pull-left a-views">
									<div class="mini-counts">
										<span title="0 views">0</span>
									</div>
									<div>views</div>
								</div>
							</div>
							<!-- activity-stats -->


							<div class="activity-text pull-left">
								<h4>Título Pregunta</h4>
								<div class="btn-group tags pull-left">
									<button type="button" class="btn btn-xs">Tag1</button>
									<button type="button" class="btn btn-xs">Tag2</button>
									<button type="button" class="btn btn-xs">Tag3</button>
									<button type="button" class="btn btn-xs">Tag4</button>
								</div>
								<div class="started pull-right">
									<a>Started</a>
								</div>
							</div>
							<!-- activity-text -->
						</div>
						<!-- activity-summary -->

					</div>
					<!-- panel-body -->
				</div>
				<!-- PANEL DERECHA(Actividad) -->
			</div>


		</div>
	</div>

	<%@ include file="../fragments/footer.jspf"%>