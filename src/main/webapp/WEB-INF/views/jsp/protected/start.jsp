<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>School library</title>
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
</head>

<body>
<div class="container">
    <header>
        <h2>School library Web application. Protected pages</h2>
        <h3>Hello, <sec:authentication property="principal.username"/>!</h3>
    </header>

    <nav>
        <ul>
            <li><a href="/index.jsp">Main page</a></li>
            <li><a href="/library/employees">Employees</a></li>
            <li><a href="/library/books">Books</a></li>
            <li><a href="/library/classes">Classes</a></li>
            <li><a href="/library/protected/protected">Protected</a></li>
        </ul>
    </nav>

    <article>
        <h2>START SECURITY PAGE</h2>
        <h3>${it}</h3>
        <sec:authorize access="isAuthenticated()">
            <form:form  class="form-horizontal" method="POST" action="/j_spring_security_logout">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-hand-left"></span> Logout POST</button>
            </form:form>
        </sec:authorize>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</div>
</body>
</html>