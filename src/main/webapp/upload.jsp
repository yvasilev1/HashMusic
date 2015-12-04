<%-- 
    Document   : upload
    Created on : 27-Nov-2015, 14:04:46
    Author     : john.bothwell1705
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- JSTL -->
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <title>Upload</title>
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
                                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">My Account<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="EditProfile.html">Edit My Details</a></li>
                                        <li><a href="Index.html">Logout</a></li>
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
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-4">
                        <form role="form" method ="post" enctype="multipart/form-data" action ="Songs">
                            <div class="form-group">
                                <label for="title">
                                    Song Name
                                </label>
                                <input type="text" class="form-control" name="title" />
                            </div>
                            <div class="form-group">
                                <label for="artist">
                                    Artist
                                </label>
                                <input type="text" class="form-control" name="artist" />
                            </div>
                            <div class="form-group">
                                <label for="album">
                                    Album
                                </label>
                                <input type="text" class="form-control" name="album" />
                            </div>
                            <div class="form-group">
                                <label for="genre">
                                    Genre
                                </label>
                                <input type="text" class="form-control" name="genre" />
                            </div>
                            <div class="form-group">
                                <label for="duration">
                                    Duration (e.g. 1:30)
                                </label>
                                <input type="text" class="form-control" name="duration" />
                            </div>
                            <div class="form-group">
                                <label for="audioInputFile">
                                    File input
                                </label>
                                <input type="file" name="audioInputFile" accept="audio/*"/>
                                <p class="help-block">
                                    Select an audio file to upload.
                                </p>
                            </div>
                            <button type="submit" class="btn btn-default">
                                Add Song
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
