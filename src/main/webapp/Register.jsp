<%-- 
    Document   : Register
    Created on : 24-Nov-2015, 16:28:14
    Author     : Connor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
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
        <div>
            <div class="alert alert-danger text-center" role="alert">
                WARNING! Login details used incorrect
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-md-4 col-md-offset-4">
                        <h1 class="text-center">#Music</h1>
                        <div>
                            <form class="form-group" method="POST"  action="Register">
                                <table>
                                    <tr>
                                        <td colspan="2">
                                            <input id="inputField" class="form-control" name="uname" class="u-full-width" type="text" placeholder="username" id="loginInput" pattern="[a-z0-9._%+-]+" title="Characters or numbers only" required><br>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <input id="inputField" class="form-control" name="email" class="u-full-width" type="email" placeholder="email address" id="registerInput" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" title="Email address" required><br>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input id="inputField" class="form-control" name="password" class="u-full-width" type="password" placeholder="password" id="loginInput" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="At least one number, upper and lower case letter, and minimum eight characters" required>
                                        </td>
                                        <td>
                                            <input id="inputField" class="form-control" name="confirmPassword" class="u-full-width" type="password" onkeyup="checkPasswordMatch();" placeholder="confirm password" id="loginInput" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="At least one number, upper and lower case letter, and minimum eight characters" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <br><input type="Submit" class="btn btn-lg btn-primary btn-block" value="Register" class="button"/>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                        <div class="text-center registrationFormAlert" id="divCheckPasswordMatch">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
