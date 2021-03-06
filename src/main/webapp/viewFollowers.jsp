<%-- 
    Document   : viewFollowers
    Created on : 29-Nov-2015, 15:05:47
    Author     : Yulian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <title>View Followers</title>
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
        <div class="container-fluid">
            <div class="row">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <a class="navbar-brand" href="index.jsp">#Music</a>
                        </div>
                        <div>
                            <ul class="nav navbar-nav">
                                <li><a href="populateUserView">Live Feed</a></li>               
                                <li><a href="Songs">Upload Music</a></li>
                                <li><a href="Followers">Followers</a></li>
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">My Account<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="editprofile.jsp">Edit My Details</a></li>
                                        <li><a href="index.jsp">Logout</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="nav navbar-nav">
                            <form method="GET" class="navbar-form" action="Search">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search user" name="user" id="srch-term">
                                    <div class="input-group-btn">
                                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <h3>
                        Following
                    </h3>
                    <div class="panel panel-default">
                        <c:set var = "followers" value = "${sessionScope.Followers}"/>
                        <c:forEach items="${followers}" var = "followers">
                            <div class="panel-body">


                                <a href="populateUserView?profileOf=<c:out value = "${followers.getFollowingUName() }"/>"><c:out value = "${followers.getFollowingUName() }"/></a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-6">
                    <h3>
                        Followers
                    </h3>
                    <div class="panel panel-default">
                        <c:set var = "following" value = "${sessionScope.Followers}"/>
                        <c:forEach items="${following}" var = "following">
                            <div class="panel-body">
                                <div>


                                    <a href="populateUserView?profileOf=<c:out value = "${following.getFollowerUName()}"/>"><c:out value = "${following.getFollowerUName()}"/></a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>
