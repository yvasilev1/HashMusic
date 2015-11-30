<%-- 
    Document   : viewSongs
    Created on : 29-Nov-2015, 02:29:23
    Author     : Connor
--%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
 
        
        <!--http://www.coderanch.com/t/293396/JSP/java/Iterating-list-session for this loopz incase any of ya'll interested-->
        <c:set var = "songs" value = "${sessionScope.Songs}"/>
        <c:forEach items="${songs}" var = "songs">
            <h2><c:out value = "${songs.getSongID()}"/></h2></br>
            <h2><c:out value = "${songs.getTitle()}"/></h2></br>
            <h2><c:out value = "${songs.getAlbum()}"/></h2></br>
            <h2><c:out value = "${songs.getArtist()}"/></h2></br>
            <h2><c:out value = "${songs.getGenre()}"/></h2></br>
            <h2><c:out value = "${songs.getDuration()}"/></h2></br>
            </br></br>
            
             
            <audio controls>
            <source src="PlaySong?id=<c:out value = "${songs.getSongID()}"/>" type="audio/wav">
            </audio>
            
            
          
        </c:forEach>
            
       
                 <h2> Create a Playlist </h2>
            
          
            <form method ="post" action ="PlayList">
                <input type ="text" name ="playlist" placeholder ="PlayList Name">
                <input type ="submit" value ="Create">
            </form>
            
            <h1><a href ="PlayList">Get PlayLists</a></h1>
    </body>
</html>
