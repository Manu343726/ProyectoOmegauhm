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
					<c:choose>
					<c:when test="${not empty user}">
						<a href="${pageContext.request.contextPath}/file/select">
							<button type="button" class="btn btn-primary btn-lg btn-block">Sube tus apuntes</button>
						</a>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-primary btn-lg btn-block" disabled="disabled">Necesitas estas logueado para poder <br> subir archivos</button>
					</c:otherwise>
				</c:choose>
				</div>
			</div>	
			

			<br>
			<div id="repository-content">	
				<ul class="list-group">
					<c:forEach items="${filesOrderedByDate}" var="f">
						<jsp:include page="../fragments/file-summary.jsp">
							<jsp:param name="id" value="${f.id}" />
							<jsp:param name="name" value="${f.name}"/>
							<jsp:param name="date" value="${f.timeStamp}" />
							<jsp:param name="tags" value="${f.tags}" />
							<jsp:param name="owner" value="${f.owner}" />
						</jsp:include>
					</c:forEach>
				</ul>
			</div>
</div>

<%@ include file="../fragments/footer.jspf" %>
