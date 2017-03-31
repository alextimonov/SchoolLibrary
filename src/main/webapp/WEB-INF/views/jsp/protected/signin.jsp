
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>School library. Signin page</title>
</head>
<body>

<div class="container">
    <div class="alert alert-success">
        <h3>Security Page: Sign in</h3>
        <div style="color: red">${message}</div>
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

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-hand-right"></span> Login</button>
    </form:form>

    <form class="form-inline" action="/" method="GET">
        <button  class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-hand-left"></span> Return to main page</button>
    </form>
</div>

</body>
</html>
