<%-- 
    Document   : UserView
    Created on : 25-Nov-2015, 12:33:15
    Author     : Connor
--%>
 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value = "${sessionScope.user}"/></title>
    </head>
    <body>
        <h1>User Stuff</h1>
        
        <h2>Make a post...</h2>
        
        <form method ="post" action ="PostWall">
            <input type ="text" name ="postContent">
            <input type ="submit" value ="Search">
        </form>
        
         <c:if test="${sessionScope.userStatus == true}">
         <h2><li><a href = "LogoutServlet">Logout</a></li></h2>  
        </c:if>
        
    </body>
</html>
