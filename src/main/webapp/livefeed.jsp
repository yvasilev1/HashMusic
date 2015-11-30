<%-- 
    Document   : livefeed
    Created on : 27-Nov-2015, 13:56:15
    Author     : john.bothwell1705
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- CSS -->
        <link rel="stylesheet" href="css/styles.css">

        <!-- Meta -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- Scripts -->
        <link rel="stylesheet" type="text/css" href="resources/bootstrap-3.3.5-dist/bootstrap-3.3.5-dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resources\bootstrap-3.3.5-dist\css\bootstrap.css">
        <script src="resources/bootstrap-3.3.5-dist/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
        <script src="resources/jquery-2.1.4.js"></script>
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
                <div class="col-md-6">
                    <h3>
                        Live Feed
                    </h3>
                    <div class="form-group">
                        <form method ="post" action="PostWall">
                            <label for="newPost">Add a new post:</label>
                            <textarea class="form-control" rows="3" id="newPost" name="postContent"></textarea>
                            <button type="submit" class="btn btn-default">Add Post</button>
                        </form>
                    </div>
                    <div class="panel panel-default">
                        <c:set var = "comments" value = "${sessionScope.NewsFeed}"/>
                        <c:forEach items="${comments}" var = "comments">
                            <div class="panel-body">
                                <c:out value="${comments}"/>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-6">
                    <h3>
                        [Playlist Name]
                    </h3>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div>
                                [media player location]
                            </div>
                            <div>
                                <table class="table table-striped table table-hover">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Song</th>
                                            <th>Artist</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <a href="#" class="glyphicon glyphicon glyphicon-play "></a>
                                                <a href="#" class="no-underline glyphicon glyphicon glyphicon-pause"></a>
                                            </td>
                                            <td>Foo Fighters</td>
                                            <td>All My Life</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <a href="#" class="no-underline glyphicon glyphicon glyphicon-play"></a>
                                                <a href="#" class="no-underline glyphicon glyphicon glyphicon-pause"></a>
                                            </td>
                                            <td>Fleetwood Mac</td>
                                            <td>The Chain</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <a href="#" class="no-underline glyphicon glyphicon glyphicon-play"></a>
                                                <a href="#" class="no-underline glyphicon glyphicon glyphicon-pause"></a>
                                            </td>
                                            <td>Coldplay</td>
                                            <td>Yellow</td>
                                        </tr>
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
