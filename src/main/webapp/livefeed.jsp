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
                    </div>
                </nav>
                <form method ="GET" action ="Search" style="float:right">
                    <input type ="text" name ="user" placeholder="Search User">
                    <button type="submit" class="btn btn-sm">Search</button>
                </form>
                <h3>${sessionScope.user}'s Music</h3>
            </div>
            <div class="row">


                <div class="col-md-4">


                    <form name="input" action="Followers" method="POST">
                        <input type="text" name="user" value="${sessionScope.user}" hidden>
                        <input type="text" name="user1" value="${sessionScope.user}" hidden>
                        <button type="submit" class="btn btn-sm">Follow ${sessionScope.user} </button></br>
                    </form></br>
                    <div class="form-group">
                        <form method ="post" action="PostWall">
                            <textarea class="form-control" rows="3" id="newPost" name="postContent" placeholder="Add Post"></textarea>
                            <button type="submit" class="btn btn-default ">Add Post</button>
                        </form>
                    </div>

                    <c:set var = "commentNo" value = "0"/>
                    <c:set var = "details"  value = "${sessionScope.NewsFeed}"/>
                    <c:forEach items="${details}" var = "details">
                        <a href="#" ><c:out value="${details.getPostedByUName()}"/></a></br>
                        <c:out value="${details.getDatePosted()}"/>

                        <div class="panel panel-default">
                            
                            <div class="panel-body" id = "{$iteration}">

                                   <h6 id = "${commentNo}">Test</h6>
                                
                                <script>
                                   
                                  comment = "${details.getPostContent()}";
                                  
                                  //http://stackoverflow.com/questions/4913555/find-twitter-hashtags-using-jquery-and-apply-a-link
                                  //Above URL used as source for following 2 lines of code. Finds and attaches links to music hashtags.
                                  //Accessed: 03/12/2015 19:41, Post Author: Reiner Gerecke
                                  hashtag_regexp = /#!([a-zA-Z0-9]+)/g;
                                  comment =  comment.replace(hashtag_regexp, '<a class="hashtag" href="#" value = "$1" onclick = "callHash(\'$1\',\'${commentNo}\');">#!$1</a>');
                                  //--
                                  document.getElementById("${commentNo}").innerHTML = comment;
                                </script>
                            </div>

                        </div>
                       <c:set var = "commentNo" value = "${commentNo + 1}"/>
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
                                        <a href ="populateUserView">All</a>
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
                                        <c:set var = "playLists" value = "${sessionScope.playlists}"/>
                                        <c:forEach items="${playLists}" var = "playLists">        
                                            <a href="PlayList?id=<c:out value = "${playLists}"/>" class="list-group-item"><c:out value = "${playLists}"/></a>
                                        </c:forEach>
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

                            <source src="aud.wav" type="audio/mp3">
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
                                    <form method ="post" action ="PlayList?type=create" id = "playListForm">
                                    <input type ="text" name ="playlist" placeholder ="New PlayList">
                                    <select name ="playListSelected" id = "playListSelected">
                                    <c:set var = "playLists" value = "${sessionScope.playlists}"/>
                                    <c:forEach items="${playLists}" var = "playLists">        
                                   <option value ="<c:out value = "${playLists}"/>"><c:out value = "${playLists}"/></option>
                                     </c:forEach>
                                    </select>

                                    <input type ="submit" value ="Continue">
                                </form> 
                                
                           <form method ="post" action ="PlayList?type=add" id = "updateList">
                           </form>       
                           
                                
                                <c:set var = "index" value = "0"/>
                                <c:forEach items="${songs}" var = "songs">
                              

                                    <tr>
                                        <td>
                                            <input type ="checkBox" form = "playListForm" name = "song" value ="<c:out value = "${index}"/>">
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

                                            <c:choose>
                                                <c:when test ="${sessionScope.listType == 'user'}">

                                                    <form method ="post" action ="userHashTag">
                                                        <input type ="text" name ="hashvalue" placeholder = "<c:out value = "${songs.getHashTag()}"/>">
                                                        <input type ="hidden" name ="songID" value ="<c:out value = "${songs.getSongID()}"/>">
                                                        <input type ="submit" style ="visibility:hidden">
                                                    </form>
                                                </c:when>
                                                <c:otherwise>

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
                                        </c:otherwise>

                                    </c:choose>
                                        <c:set var = "index" value = "${index + 1}"/>
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
            
            function callHash(hashtag, commentNo)
            {
                divID = hashtag.concat(commentNo);
                $("#"+commentNo).append('<div id = '+divID+'>\n\
                                        <audio controls>\n\
                                        <source src ="PlayHashed?id='+hashtag+'" type="audio/wav">\n\
                                        </audio>\n\
                                        <button onclick = removeSongPlayer("'+divID+'")>Close</button>\n\
                                        </div>'
                                        );
              
            }
            
            function removeSongPlayer(divID)
            {
                $("#"+divID).remove();
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
