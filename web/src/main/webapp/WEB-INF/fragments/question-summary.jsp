<div class="question-summary pull-left">
	<div class="question-stats pull-left">
		<div class="pull-left q-votes">
			<div class="mini-counts">
				<span title="votes">${param.votes}</span>
			</div>
			<div>votes</div>
		</div>
		<div class="pull-left q-answers">
			<div class="mini-counts">
				<span title="answers">${param.answers}</span>
			</div>
			<div>answers</div>
		</div>
		<div class="pull-left q-views">
			<div class="mini-counts">
				<span title="views">${param.views}</span>
			</div>
			<div>views</div>
		</div>
	</div> <!-- question-stats -->
	

	<div class="question-text pull-left">
		<h4>
			<a href="topic/${param.id}/${param.title}">${param.title}</a>
		</h4>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- lib para el forTokens -->
		<c:forTokens items="${param.tags}" delims=" " var="name">
			<span class="label label-default">${name}</span>
		</c:forTokens>
		<div class="started pull-right">
			<a>${param.date}</a>
		</div>
	</div> <!-- question-text -->
	
</div> <!-- question-summary -->
