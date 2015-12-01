<%-- 
    Document   : login
    Created on : 27-Nov-2015, 13:24:01
    Author     : john.bothwell1705
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <!-- CSS -->
        <link rel="stylesheet" href="css/styles.css">

        <!-- Meta -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- Scripts -->
        <link rel="stylesheet" type="text/css" href="resources/bootstrap-3.3.5-dist/bootstrap-3.3.5-dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resources\bootstrap-3.3.5-dist\css\bootstrap.css">
        <script src="resources/bootstrap-3.3.5-dist/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
        <script src="resources/jquery-2.1.4.js"></script>
    </head>
    <body class="background">
        <div class="alert alert-danger text-center" role="alert">
            WARNING! Login details used incorrect
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4">
                    <h1 class="text-center">#Music</h1>
                    <div>
                        <form class="form-signin" method="POST"  action="Login">
                            <input id="inputField" class="form-control" name="username" class="u-full-width" type="text" placeholder="username" id="registerInput" pattern="[a-z0-9._%+-]+" title="Characters or numbers only" required><br>
                            <input id="inputField" class="form-control" name="password" class="u-full-width" type="password" placeholder="password" id="registerInput" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="At least one number, upper and lower case letter, and minimum eight characters" required>
                            <input type="Submit" class="btn btn-lg btn-primary btn-block" value="Login" class="button"/>
                        </form>
                    </div>
                    <a href="Register.jsp" class="text-center new-account">Create an account </a>
                </div>
            </div>
        </div>
    </body>
</html>
