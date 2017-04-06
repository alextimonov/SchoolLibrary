<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true"%>

<c:set var="path" value="${pageContext.request.contextPath}/Restaurant"/>
<c:url value="/j_spring_security_check" var="loginUrl" />
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Signin Failure</title>
</head>
<body>

<div class="container">
    <div class="form-group">
        <div class="alert alert-danger">
            <h2>Security page: Sign in failure</h2>
            <h3>Incorrect username or password!</h3>
        </div>
    </div>

    <form class="form-inline" action="/library/protected/signin" method="GET">
        <button class="btn btn-primary" type="submit" autofocus>
            <span class="glyphicon glyphicon-hand-right"></span> Return to sign in</button>
    </form>

    <form class="form-inline" action="/index.jsp" method="GET">
        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-hand-left"></span> Return to main page</button>
    </form>
</div>

</body>
</html>
