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
        <form method ="post" action ="PostWall"> 
            <input type ="text" name ="postContent">
            <input type="submit" value ="Post">
        </form>
     
        <c:set var = "comments" value = "${sessionScope.NewsFeed}"/>
        <c:forEach items="${comments}" var = "comments">
            <p><c:out value = "${comments}"/></p>

        </c:forEach>




        <h1>User Stuff</h1>

        <h2>Make a post...</h2>


        <form name="input" action="Followers" method="POST">
            <input type="text" name="user" value="${sessionScope.userID}" hidden>
            <input type="text" name="user1" value="" hidden>
            <input type="submit" value="Follow">
        </form>



        <c:if test="${sessionScope.userStatus == true}">
            <h2><li><a href = "LogoutServlet">Logout</a></li></h2>
            <h2><li><a href = "Followers">Followers</a></li></h2>  
                </c:if>


        <h2>Wall..</h2></br>

        <h3>Post..</h3></br>





        <form method ="GET" action ="Search">
            <input type ="text" name ="user">
            <input type ="submit" value ="Search">
        </form>
        <p>Search</p>
        <c:set var = "searchedUName" value = "${sessionScope.Search}"/>
        <c:forEach items="${searchedUName}" var = "searchedUName">
            <h2><c:out value = "${searchedUName}"/></h2></br>

            </br></br>
        </c:forEach>
    </body>
</html>
