<%-- 
    Document   : createplaylist
    Created on : 27-Nov-2015, 13:59:50
    Author     : john.bothwell1705
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Playlist</title>
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
                                <li><a href="livefeed.jsp">Live Feed</a></li>
                                <li><a href="createplaylist.jsp">Create Playlist</a></li>
                                <li><a href="upload.jsp">Upload Music</a></li>
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
                    </div>
                </nav>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form role="form" class="form-inline">
                        <input id="inputField" type="text" class="form-control" name="playlistName" placeholder="playlist name"/>
                        <button type="submit" class="btn btn-default">Save Playlist</button>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-md-7">
                    <h3>
                        [new playlist]
                    </h3>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <table class="table table-striped table table-hover">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>Song</th>
                                        <th>Artist</th>
                                        <th>Length</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <a href="#" class="no-underline glyphicon glyphicon-minus"></a>
                                        </td>
                                        <td>[artist name]</td>
                                        <td>[song name]</td>
                                        <td>[length]</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <h3>
                        All Songs
                    </h3>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div>
                                <table class="table table-striped table table-hover">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Song</th>
                                            <th>Artist</th>
                                            <th>Duration</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var = "songs" value = "${sessionScope.Songs}"/>
                                    <c:forEach items="${songs}" var = "songs">
                                        <tr>
                                            <td><a href="#" class="glyphicon glyphicon glyphicon-plus"></a></td>
                                            <td><c:out value = "${songs.getArtist()}"/></td>
                                            <td><c:out value = "${songs.getTitle()}"/></td>
                                            <td><c:out value = "${songs.getDuration()}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
