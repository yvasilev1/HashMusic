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
        <c:set var = "uNamesOfFollowing" value = "${sessionScope.Followers}"/>
        <c:forEach items="${uNamesOfFollowing}" var = "uNamesOfFollowing">
            <h2><c:out value = "${uNamesOfFollowing}"/></h2></br>
  
            </br></br>
        </c:forEach>
          <p>Followers</p>
           <c:set var = "uNamesofFollowers" value = "${sessionScope.Followers}"/>
        <c:forEach items="${uNamesofFollowers}" var = "uNamesofFollowers">
            <h2><c:out value = "${uNamesofFollowers}"/></h2></br>
  
            </br></br>
        </c:forEach>
    </body>
</html>
