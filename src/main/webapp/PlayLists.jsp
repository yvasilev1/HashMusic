<%-- 
    Document   : PlayLists
    Created on : 30-Nov-2015, 12:03:24
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
        <h1>PlayLists</h1>
        
           <c:set var = "playlist" value = "${sessionScope.playLists}"/>
        <c:forEach items="${playlist}" var = "playlist">
            <h2><c:out value = "${playlist.getPlayListID()}"/></h2></br>
            <h2><c:out value = "${playlist.getPlayListName()}"/></h2></br>
        </c:forEach>
    </body>
</html>
