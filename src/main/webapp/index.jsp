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
         <h2><li><a href = "index.jsp">Logout</a></li></h2>  
        </c:if>



    </body>
</html>
