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
        <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value = "${sessionScope.user}"/></title>

        <script type="text/javascript">

            function getXMLObject()  //XML OBJECT
            {
                var xmlHttp = false;
                try {
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
                } catch (e) {
                    try {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
                    } catch (e2) {
                        xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
                    }
                }
                if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
                    xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
                }
                return xmlHttp;  // Mandatory Statement returning the ajax object created
            }

            var xmlhttp = new getXMLObject();	//xmlhttp holds the ajax object

            function AjaxPostComment() {
                var getdate = new Date();  //Used to prevent caching during ajax call
                if (xmlhttp) {
                    var data = document.getElementById("postContent").value;

                    xmlhttp.open("POST", "PostWall?postContent="+ data ,true); //gettime will be the servlet name
                    xmlhttp.onreadystatechange = handleServerResponse;
                    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xmlhttp.send();
                }
            }

            function handleServerResponse() {
                if (xmlhttp.readyState == 4) {
                    if (xmlhttp.status == 200) {
                      
                                
                         // $("#comments").prepend(xmlhttp.responseText);
                       document.getElementById("comments").innerHTML = xmlhttp.responseText; //Update the HTML Form element
                        //alert(xmlhttp.responseText);
                    } else {
                        alert("Error during AJAX call. Please try again");
                    }
                }
            }
        </script>
    </head>
    <body>
        <!-- <form method ="post" action ="PostWall"> -->
        <input type ="text" id ="postContent">
        <button onclick="javascript: AjaxPostComment();" value ="Post">Post</button>
        <!-- </form>-->
         <div id="comments">
                
            </div>
        
        <c:if test="${sessionScope.userWall == true}">
           
        </c:if>
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
