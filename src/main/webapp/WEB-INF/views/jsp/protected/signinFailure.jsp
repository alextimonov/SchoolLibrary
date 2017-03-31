<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 31.03.2017
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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

    <form class="form-inline" action="/library/signin" method="GET">
        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-hand-right"></span> Return to sign in</button>
    </form>

    <form class="form-inline" action="/" method="GET">
        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-hand-left"></span> Return to main page</button>
    </form>
</div>

</body>
</html>
