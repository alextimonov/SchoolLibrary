<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>

<html lang="${language}">
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Signin Failure</title>
</head>
<body>

<div class="container">
    <header>
        <h3><fmt:message key="allPages.header"/></h3>
        <h3><fmt:message key="page.signinFailure.header"/></h3>
    </header>

    <nav>
        <div class="container">
            <ul>
                <li><a href="/index.jsp"><fmt:message key="link.mainPage"/></a></li>
                <li><a href="/library/employees"><fmt:message key="link.employees"/></a></li>
                <li><a href="/library/books"><fmt:message key="link.books"/></a></li>
                <li><a href="/library/classes"><fmt:message key="link.classes"/></a></li>
            </ul>
        </div>
    </nav>

    <article>
        <div class="form-group">
            <div class="alert alert-danger">
                <h3><fmt:message key="signin.failure.incorrect"/></h3>
            </div>
        </div>
        <form class="form-inline" action="/library/protected/signin" method="GET">
            <button class="btn btn-primary" type="submit" autofocus>
                <span class="glyphicon glyphicon-hand-right"></span> <fmt:message key="link.back.signin"/>
            </button>
        </form>

        <form class="form-inline" action="/index.jsp" method="GET">
            <button class="btn btn-primary" type="submit">
                <span class="glyphicon glyphicon-hand-left"></span> <fmt:message key="link.mainPage"/>
            </button>
        </form>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>

</body>
</html>
