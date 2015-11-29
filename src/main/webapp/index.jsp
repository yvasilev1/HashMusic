<%-- 
    Document   : index
    Created on : 24-Nov-2015, 16:15:41
    Author     : Yulian
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
        <h1>Hash Music </h1>
       
            <h2><li><a href = "Register.jsp">Register</a></li></h2>
            <h2><li><a href = "Login.jsp">Login</a></li></h2>
          

        <c:if test="${sessionScope.userStatus == true}">
            <h2><li><a href = "UserView.jsp">Feed</a></li></h2>
                </c:if>
              <form method ="post" enctype="multipart/form-data" action ="Songs">
                <input type ="file" name ="song">
                <input type ="text" name ="title" placeholder ="title">
                <input type ="text" name ="artist" placeholder ="artist">
                <input type ="text" name ="genre" placeholder ="genre">
                <input type ="text" name ="duration" placeholder="duration (seconds)">
                <input type ="text" name ="album" placeholder ="album">
                
                <input type ="submit" value ="Add Song">
            </form>

            <a href ="Songs">Get Da Songz</a>


    </body>
</html>
