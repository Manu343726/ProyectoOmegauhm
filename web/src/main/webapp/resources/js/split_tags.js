$.each($(".thread_tags"), function(i, o) {
	var tags = $(o).text().split(" ");
	console.log(tags);

	$(o).text("");
	for (var i = 0; i < tags.length; ++i) {
		var b = $("<button type=\"button\" class=\"btn btn-xs\">" + tags[i]
				+ "</button>");
		$(o).append(b);
	}
});