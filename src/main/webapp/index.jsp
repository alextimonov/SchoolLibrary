<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>

<html lang="${language}">
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library</title>
</head>

<body>
    <div class="container">
        <header>

            <h1><fmt:message key="allPages.header"/></h1>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <h3><fmt:message key="allPages.signedin"/> <sec:authentication property="principal.username"/></h3>
            </sec:authorize>

        </header>

        <nav>
            <ul>
                <li><a href="/library/employees"><fmt:message key="link.employees"/></a></li>
                <li><a href="/library/books"><fmt:message key="link.books"/></a></li>
                <li><a href="/library/classes"><fmt:message key="link.classes"/></a></li>
                <li>
                    <sec:authorize access="isAnonymous()">
                        <a href="/library/protected/login"><fmt:message key="link.signin"/></a>
                    </sec:authorize>
                </li>
                <li>
                    <sec:authorize access="isAuthenticated()">
                        <form:form  class="form-horizontal" method="POST" action="/j_spring_security_logout">
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-hand-left"></span> <fmt:message key="link.signout"/></button>
                        </form:form>
                    </sec:authorize>
                </li>
            </ul>
        </nav>

        <article>
            <form>
                <select id="language" name="language" onchange="submit()">
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                    <option value="ua" ${language == 'ua' ? 'selected' : ''}>Ukrainian</option>
                </select>
            </form>
            <h2><fmt:message key="mainPage.article.header"/></h2>
            <p><fmt:message key="mainPage.article.p1"/></p>
            <p><fmt:message key="mainPage.article.p2"/></p>
        </article>

        <footer>Copyright &copy; Alexey Timonov</footer>

    </div>
</body>
</html>