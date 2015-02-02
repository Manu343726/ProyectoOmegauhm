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

    <link href="resources/css/signin.css" rel="stylesheet">

  </head>
<body>

<div class="container">

      <form class="form-signin" role="form" action="signinUser" method="POST">
        <h2 class="form-signin-heading">Log in</h2>
        <input type="text" class="form-control" placeholder="Username" id="login" name="login" required autofocus>
        <input type="password" class="form-control" placeholder="Password" id="pass" name="pass" required>
        <button class="btn btn-lg btn-primary btn-block" name="submit" value="login" type="submit">Sign in</button>
      </form>
      
</div>

<%@ include file="../fragments/footer.jspf" %>
