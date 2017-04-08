<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page session="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>
<c:url var="loginUrl" value="/j_spring_security_check"/>

<html lang="${language}">
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Signin page</title>
</head>
<body>

<div class="container">
    <header>
        <h3><fmt:message key="allPages.header"/></h3>
        <h3><fmt:message key="page.signin.header"/></h3>
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
        <form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ua" ${language == 'ua' ? 'selected' : ''}>Ukrainian</option>
            </select>
        </form>
        <div class="container">
            <div class="alert alert-success">
                <h4><fmt:message key="signin.pleaseInput"/></h4>
                <%--<div style="color: red">${message}</div>--%>
            </div>

            <form:form  class="form-horizontal" method="POST" action="${loginUrl}">
                <div class="alert alert-success">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="j_username"><fmt:message key="signin.login"/>: </label>
                        </div>
                        <div class="col-sm-4">
                            <input class="form-control" id="j_username" name="j_username" type="text" placeholder="login" autofocus/>
                        </div>
                    </div>

                    <div class="form-group" >
                        <div class="col-sm-3">
                            <label for="j_password"><fmt:message key="signin.password"/>: </label>
                        </div>

                        <div class="col-sm-4">
                            <input class="form-control" id="j_password" name="j_password" type="password" placeholder="password"/>
                        </div>
                    </div>
                </div>

                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-hand-right"></span> <fmt:message key="signin.enter"/></button>
            </form:form>

            <form class="form-inline" action="/index.jsp" method="GET">
                <button  class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-hand-left"></span> <fmt:message key="link.mainPage"/></button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>

</body>
</html>
