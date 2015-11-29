<%-- 
    Document   : viewFollowers
    Created on : 29-Nov-2015, 15:05:47
    Author     : Yulian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Followers</title>
    </head>
    <body>
        <p>Following</p>
        <c:set var = "usersForFollowers" value = "${sessionScope.Followers}"/>
        <c:forEach items="${usersForFollowers}" var = "usersForFollowers">
            <h2><c:out value = "${usersForFollowers}"/></h2></br>
  
            </br></br>
        </c:forEach>
          <p>Followers</p>
           <c:set var = "followersForUsers" value = "${sessionScope.Followers}"/>
        <c:forEach items="${followersForUsers}" var = "followersForUsers">
            <h2><c:out value = "${followersForUsers}"/></h2></br>
  
            </br></br>
        </c:forEach>
    </body>
</html>
