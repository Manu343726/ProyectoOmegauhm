<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Proyecto OHM</title>

<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<link
	href="${pageContext.request.contextPath}/resources/css/questions.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/activities.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/extra.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="../fragments/header.jspf"%>

	<script
		src="${pageContext.request.contextPath}/resources/js/split_tags.js"></script>
	<script>
		$("#voting_button").prop('disabled', true);
	</script>

	<div class="container">
		<div class="jumbotron" id=${topic_question.id}>
			<!-- ancla al post-pregunta -->
			<h1>${topic.title}</h1>

			<div class="row">
				<div class="col-md-1">
					<div class="row">
						<a
							href="${pageContext.request.contextPath}/vote/${topic_question.id}/1">
							<button type="button" class="btn btn-default btn-sl"
								id="voting_button">
								<span class="glyphicon glyphicon-chevron-up" aria-hidden="true"></span>
							</button>
						</a>
					</div>
					<div class="row">
						<a
							href="${pageContext.request.contextPath}/vote/${topic_question.id}/-1">
							<button type="button" class="btn btn-default btn-sl"
								id="voting_button">
								<span class="glyphicon glyphicon-chevron-down"
									aria-hidden="true"></span>
							</button>
						</a>
					</div>
					<div class="row">
						<span class="label label-default">${topic_question.votes}
							points</span>
					</div>
				</div>

				By ${topic_asker.login}

				<div class="thread_tags">${topic.tags}</div>
			</div>
		</div>
		<div class="row row-offcanvas row-offcanvas-center">
			<div class="col-md-6">
				<c:forEach items="${topic_answers}" var="post">
					<%@ include file="../fragments/post_view.jspf"%>
				</c:forEach>
			</div>
		</div>
	</div>

	<%@ include file="../fragments/footer.jspf"%>