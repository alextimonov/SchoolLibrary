<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page session="true"%>

<c:url var="loginUrl" value="/j_spring_security_check"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Signin page</title>
</head>
<body>

<div class="container">
    <div class="alert alert-success">
        <h3>Security Page: Sign in</h3>
        <%--<div style="color: red">${message}</div>--%>
    </div>

    <form:form  class="form-horizontal" method="POST" action="${loginUrl}">
        <div class="alert alert-success">
            <div class="form-group">
                <div class="col-sm-3">
                    <label for="j_username">Username: </label>
                </div>
                <div class="col-sm-4">
                    <input class="form-control" id="j_username" name="j_username" type="text" placeholder="Enter username"/>
                </div>
            </div>

            <div class="form-group" >
                <div class="col-sm-3">
                    <label for="j_password">Password: </label>
                </div>

                <div class="col-sm-4">
                    <input class="form-control" id="j_password" name="j_password" type="password" placeholder="Enter password"/>
                </div>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-hand-right"></span> Login</button>
    </form:form>

    <form class="form-inline" action="/index.jsp" method="GET">
        <button  class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-hand-left"></span> Return to main page</button>
    </form>
</div>

</body>
</html>
