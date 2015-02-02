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
        <div class="col-md-10">

          <div class="panel panel-default">

            <div class="panel-heading">
              <h3 class="panel-title">Moderación del repositorio</h3>
            </div>

            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-md-offset-0">
                  <a class="btn btn-primary" type="button">
                    <span class="badge">79</span> elementos a revisar
                  </a>
                </div>

                <div class="col-md-6">
                  <span class="label label-default">63 new files</span>
                  <span class="label label-default">12 modified files</span>
                  <span class="label label-default">4 deleted files</span>
                </div>
              </div>
            </div>
          </div>

          <div class="panel panel-default">

            <div class="panel-heading">
              <h3 class="panel-title">Moderación del forum</h3>
            </div>

            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-md-offset-0">

                  <a class="btn btn-primary" type="button">
                    <span class="badge">20</span> elementos a revisar
                  </a>

                </div>

                <div class="col-md-6">
                  <span class="label label-default">12 new questions</span>
                  <span class="label label-default">5 suggested edits</span>
                  <span class="label label-default">3 flagged questions/answers</span>
                </div>
              </div>
            </div>  
          </div>

          <div class="panel panel-default">

            <div class="panel-heading">
              <h3 class="panel-title">Moderación de usuarios</h3>
            </div>

            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-md-offset-0">

                  <a class="btn btn-primary" type="button">
                    <span class="badge">17</span> elementos a revisar
                  </a>

                </div>

                <div class="col-md-6">
                  <span class="label label-default">12 new users</span>
                  <span class="label label-default">5 flagged users</span>
                </div>
              </div>
            </div> 
          </div>
        </div>
      </div>
</div>

<%@ include file="../fragments/footer.jspf" %>
