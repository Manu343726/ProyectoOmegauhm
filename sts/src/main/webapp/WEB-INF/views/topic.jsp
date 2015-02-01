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

<link href="${pageContext.request.contextPath}/resources/css/questions.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/activities.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/extra.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/post-view.css" rel="stylesheet">

</head>
<body>

	<%@ include file="../fragments/header.jspf"%>

	<div class="container">
		<div class="row row-offcanvas row-offcanvas-center">
			<div class="col-md-6">
				<c:forEach items="${topic_posts}" var="post">
					<%@ include file="../fragments/post_view.jspf"%>
				</c:forEach>
			</div>
		</div>
		
		<c:choose>
			<c:when test="${not empty user}">
				<a href="answer">
					<button type="button" class="btn btn-primary btn-lg btn-block">Responder</button>
				</a>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-primary btn-lg btn-block" disabled="disabled">Necesitas estas logueado para poder responder</button>
			</c:otherwise>
		</c:choose>
	</div>
	
	<script src="${pageContext.request.contextPath}/resources/js/split_tags.js"></script>

	<%@ include file="../fragments/footer.jspf"%>