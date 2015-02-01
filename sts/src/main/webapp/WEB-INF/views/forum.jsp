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
<link href="resources/css/extra.css" rel="stylesheet">
<link href="resources/css/forum.css" rel="stylesheet">




<!-- Script para los tabs -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#tabs').tab();
	});
</script>


</head>
<body>

	<%@ include file="../fragments/header.jspf"%>
	

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">

				<c:choose>
					<c:when test="${not empty user}">
						<a href="publication">
							<button type="button" class="btn btn-primary btn-lg btn-block">Haz una pregunta</button>
						</a>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-primary btn-lg btn-block" disabled="disabled">
							Necesitas estas logueado para hacer <br> una pregunta
						</button>
					</c:otherwise>
				</c:choose>

			</div>
		</div> <!-- Botón de la parte superior. Si el usuario no está logueado, el botón para hacer una pregunta aparece deshabilitado -->
		<br>
		<div id="forum-content">

			<ul id="forum-tab" class="nav nav-tabs" data-tabs="tabs">
				<li class="active"><a href="#new-questions" data-toggle="tab">New
						Questions</a></li>
				<li><a href="#top-questions" data-toggle="tab">Top
						Questions</a></li>
			</ul> <!-- tabs -->
			

			<div id="forum-tab-content" class="tab-content">
				<div class="tab-pane active" id="new-questions">
					<div class="panel panel-default">
						<div class="panel panel-body">
							<c:forEach items="${threadsOrderedByDate}" var="t">
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
					</div>
				</div>
				
				<div class="tab-pane active" id="top-questions">
					<div class="panel panel-default">
						<div class="panel panel-body">
							<c:forEach items="${threadsOrderedByViews}" var="t">
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
					</div>
				</div>

			</div>
			<!-- forum-tab-content -->
		</div>
		<!-- content -->

	</div>
	<!-- CONTAINER -->

	<%@ include file="../fragments/footer.jspf"%>