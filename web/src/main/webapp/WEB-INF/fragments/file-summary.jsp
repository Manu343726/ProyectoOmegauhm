<div class="file-summary pull-left">
	
	<div class="file-text pull-left">
		<h4>
			<a href="${pageContext.request.contextPath}/file/download/${param.id}">${param.name}</a>
		</h4>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- lib para el forTokens -->
		<c:forTokens items="${param.tags}" delims=" " var="name">
			<span class="label label-default">${name}</span>
		</c:forTokens>
		<div class="started pull-right">
			<a>${param.date}</a>
		</div>
	</div> <!-- question-text -->
</div> <!-- file-summary -->
