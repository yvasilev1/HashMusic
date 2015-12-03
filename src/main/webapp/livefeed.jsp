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
        <title>Home</title>
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
                    <form method ="GET" action ="Search">
                        <input type ="text" name ="user">
                        <button type="submit" class="btn btn-sm">Search for User</button></br>
                        </br>
                    </form>
                    <div class="form-group">
                        <form method ="post" action="PostWall">
                            <textarea class="form-control" rows="3" id="newPost" name="postContent"></textarea>
                            <button type="submit" class="btn btn-default ">Add Post</button>
                        </form>
                    </div>
                    
                    <c:set var = "details"  value = "${sessionScope.NewsFeed}"/>
                    <c:forEach items="${details}" var = "details">

                         
                        <div class="panel panel-default">


                            <div class="panel-body">
                                <c:out value="${details}"/>
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
                                        <c:set var = "songLibrary" value = "${sessionScope.SongLibrary.getArtists()}"/>
                                        <c:forEach items="${songLibrary}" var = "songLibrary">

                                            <a href="filterSongs?type=artist&id=<c:out value = "${songLibrary}"/>" class="list-group-item"><c:out value = "${songLibrary}"/></a>
                                        </c:forEach>
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
                                        <c:set var = "songLibrary" value = "${sessionScope.SongLibrary.getAlbums()}"/>
                                        <c:forEach items="${songLibrary}" var = "songLibrary">        
                                            <a href="filterSongs?type=album&id=<c:out value = "${songLibrary}"/>" class="list-group-item"><c:out value = "${songLibrary}"/></a>
                                        </c:forEach>
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
                                        <c:set var = "songLibrary" value = "${sessionScope.SongLibrary.getGenres()}"/>
                                        <c:forEach items="${songLibrary}" var = "songLibrary">        
                                            <a href="filterSongs?type=genre&id=<c:out value = "${songLibrary}"/>" class="list-group-item"><c:out value = "${songLibrary}"/></a>
                                        </c:forEach>
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
                                        <form method ="post" class ="list-group-item" action ="PlayList">
                                            <input type ="text" name ="playlist" placeholder ="Create New" style = "width: 100%">
                                        </form>
                                        <a href="#" class="list-group-item">Add New Playlist</a>
                                        
                                    </div>
                                </div>
                            </div>
                        </div> 

                        <form method ="GET" action ="SearchSong">
                            <input type ="text" name ="song" placeholder = "Search Song">
                            <button type="submit" class="btn btn-sm">Search</button></br>
                            </br>
                        </form>

                                       
                                            

                        <h6 style = "margin-left: 30%" id = "songPlayTitle">No Song Selected</h6>                                
                        <audio controls style = "margin-left: 20%" id = "songPlayer">

                            <source src="#" type="audio/mp3">
                        </audio>


                        <div class="col-md-8">
                            <table class="table table-striped table table-hover">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th></th>
                                        <th>Song</th>
                                        <th>Artist</th>
                                        <th>Album</th>
                                        <th>Genre</th>
                                        <th>Duration</th>
                                      
                                        <th>

                                            <c:choose>
                                                <c:when test ="${sessionScope.listType == 'user'}">

                                                    <c:out value = "HashTag"/>
                                                </c:when>
                                            </c:choose>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var = "songs" value = "${sessionScope.Songs}"/>
                                     <form method ="post" action ="PlayList" id = "playListForm">
                                         <input type ="text" name ="playlist" placeholder ="New PlayList">
                                         
                                         <input type ="submit" value ="test">
                                      </form> 
                                    <c:forEach items="${songs}" var = "songs">
                                        
                                        <tr>
                                            <td>
                                                <input type ="checkBox" form = "playListForm" name = "song" value ="<c:out value = "${songs.getSongID()}"/>">
                                            </td>
                                       
                                            <td>
                                                <!-- http://stackoverflow.com/questions/18014639/passing-jstl-value-to-javascript  if interested in how this parameter pass works... 03/12/2015 00:00 -->
                                                <button onclick = "playSong('${songs.getSongID()}', '${songs.getTitle()}')">Play</button>
                                            </td>
                                            
                                            <td><c:out value = "${songs.getTitle()}"/></td>
                                            
                                            
                                            
                                            <td><c:out value = "${songs.getArtist()}"/></td>
                                            <td><c:out value = "${songs.getAlbum()}"/></td>
                                            <td><c:out value = "${songs.getGenre()}"/></td>
                                            <td><c:out value = "${songs.getDuration()}"/></td>
                                         
                                            <td>

                                                <form method ="post" action ="addUserSong">
                                                    <input type ="hidden" name ="id" value = "<c:out value = "${songs.getSongID()}"/>">
                                                    <input type ="hidden" name ="artist" value = "<c:out value = "${songs.getArtist()}"/>">
                                                    <input type ="hidden" name ="genre" value = "<c:out value = "${songs.getGenre()}"/>">
                                                    <input type ="hidden" name ="album" value = "<c:out value = "${songs.getAlbum()}"/>">
                                                    <input type ="hidden" name ="duration" value = "<c:out value = "${songs.getDuration()}"/>">
                                                    <input type ="hidden" name ="title" value = "<c:out value = "${songs.getTitle()}"/>">
                                                    <input type ="submit" value ="Get Song">
                                                </form>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <h1 id = "test">

        </h1>

        <script>
            function playSong(songID, songTitle)
            {
                var audio = document.getElementById("songPlayer");
                document.getElementById("songPlayTitle").innerHTML = "Now Playing: " + songTitle;

                audio.src = "PlaySong?id=" + songID;
                audio.load();
                audio.play();

            }
        </script>

        <style>
            audio
            {
                width: 400px;
            }
        </style>
    </body>


</html>