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
	</div>
	<!-- questions-stats -->

	<div class="question-text pull-left">
		<h4>
			<a href="topic/${param.id}/${param.title}">${param.title}</a>
		</h4>
		<div class="btn-group tags pull-left">
			<div class="thread_tags">${param.tags}</div>
		</div>
		<div class="started pull-right">
			<a>N/A</a>
		</div>
	</div>
	<!-- question-text -->
</div>
<!-- question-summary -->

<script>
	$.each($(".thread_tags"), function(i, o) {
		var tags = $(o).text().split(" ");
	    console.log(tags);
	    
	    $(o).text("");
	    for (var i = 0; i < tags.length; ++i) {
	        var b = $("<button type=\"button\" class=\"btn btn-xs\">" + tags[i] + "</button>");
	        $(o).append(b);
	    }       
	});
</script>
