<%-- 
    Document   : livefeed
    Created on : 27-Nov-2015, 13:56:15
    Author     : john.bothwell1705
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- JSTL -->
        <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
        <!-- CSS -->
        <link rel="stylesheet" href="css/styles.css">

        <!-- Meta -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Scripts -->
        <link rel="stylesheet" type="text/css" href="resources/bootstrap-3.3.5-dist/bootstrap-3.3.5-dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resources\bootstrap-3.3.5-dist\css\bootstrap.css">
        <script src="resources/bootstrap-3.3.5-dist/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
        <script src="resources/jquery-2.1.4.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>Live Feed</title>
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
                <div class="col-md-4">
                    <h3>
                        Live Feed
                    </h3>
                    <div class="form-group">
                        <form method ="post" action="PostWall">
                            <textarea class="form-control" rows="3" id="newPost" name="postContent"></textarea>
                            <button type="submit" class="btn btn-default">Add Post</button>
                        </form>
                    </div>
                    <c:set var = "comments" value = "${sessionScope.NewsFeed}"/>
                    <c:forEach items="${comments}" var = "comments">
                        <div class="panel panel-default">


                            <div class="panel-body">
                                <c:out value="${comments}"/>
                            </div>

                        </div>
                    </c:forEach>
                </div>
                <div class="col-md-8">
                    <h3>
                        [Playlist Name]
                    </h3>
                    <div>
                        <div class="panel-group col-md-4" id="accordion">
                            <h4>
                                Filter songs
                            </h4>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse0">All</a>
                                    </h4>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Artist</a>
                                    </h4>
                                </div>
                                <div id="collapse1" class="panel-collapse collapse">
                                    <div class="list-group">
                                        <a href="#" class="list-group-item">First item</a>
                                        <a href="#" class="list-group-item">Second item</a>
                                        <a href="#" class="list-group-item">Third item</a>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Album</a>
                                    </h4>
                                </div>
                                <div id="collapse2" class="panel-collapse collapse">
                                    <div class="list-group">
                                        <a href="#" class="list-group-item">First item</a>
                                        <a href="#" class="list-group-item">Second item</a>
                                        <a href="#" class="list-group-item">Third item</a>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Genre</a>
                                    </h4>
                                </div>
                                <div id="collapse3" class="panel-collapse collapse">
                                    <div class="list-group">
                                        <a href="#" class="list-group-item">First item</a>
                                        <a href="#" class="list-group-item">Second item</a>
                                        <a href="#" class="list-group-item">Third item</a>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">Playlists</a>
                                    </h4>
                                </div>
                                <div id="collapse4" class="panel-collapse collapse">
                                    <div class="list-group">
                                        <a href="#" class="list-group-item">Add New Playlist</a>
                                        <a href="#" class="list-group-item">First item</a>
                                        <a href="#" class="list-group-item">Second item</a>
                                        <a href="#" class="list-group-item">Third item</a>
                                    </div>
                                </div>
                            </div>
                        </div> 
                        <div class="col-md-8">
                            <table class="table table-striped table table-hover">
                                <thead>
                                    <tr>
                                        <th>Song</th>
                                        <th>Artist</th>
                                        <th>Duration</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var = "songs" value = "${sessionScope.Songs}"/>
                                    <c:forEach items="${songs}" var = "songs">
                                        <tr>
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
    </body>
</html>
