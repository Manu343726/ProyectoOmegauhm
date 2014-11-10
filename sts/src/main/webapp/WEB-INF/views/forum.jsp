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

    <link href="resources/css/questions.css" rel="stylesheet">
    <link href="resources/css/extra.css" rel="stylesheet">
    <link href="resources/css/forum.css" rel="stylesheet">

  </head>
<body>

<%@ include file="../fragments/header.jspf" %>

<div class="container">
	  <div class="row">
				<div class="col-md-4 col-md-offset-4">
					<a href="publication">
						<button type="button" class="btn btn-primary btn-lg btn-block ">Haz una pregunta</button>
					</a>
				</div>
	  </div>
	  <br>
      <div id="forum-content">

        <ul id="forum-tab" class="nav nav-tabs" data-tabs="tabs">
          <li class="active"><a href="#new-questions" data-toggle="tab">New Questions</a></li>
          <li><a href="#top-questions" data-toggle="tab">Top Questions</a></li>
        </ul> <!-- forum-tab -->

        <div id="forum-tab-content" class="tab-content">
          <div class="tab-pane active" id="new-questions">
            <h1>New Questions</h1>
          </div>
          <div class="tab-pane" id="top-questions">
            <h1>Top Questions</h1>
          </div>

        </div> <!-- forum-tab-content -->
      </div> <!-- content -->



      <script type="text/javascript">
          jQuery(document).ready(function ($) {
              $('#tabs').tab();
          });
      </script>
    </div> <!-- CONTAINER -->

<%@ include file="../fragments/footer.jspf" %>