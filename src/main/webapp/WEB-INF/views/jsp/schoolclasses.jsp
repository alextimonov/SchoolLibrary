<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>

<html lang="${language}">
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Classes</title>
</head>
<body>

<div class="container">
    <header>
        <h3><fmt:message key="allPages.header"/></h3>
        <h3><fmt:message key="page.classes.header"/></h3>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <h3><fmt:message key="allPages.signedin"/> <sec:authentication property="principal.username"/></h3>
        </sec:authorize>

    </header>

    <nav>
        <ul>
            <li><a href="/index.jsp"><fmt:message key="link.mainPage"/></a></li>
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
        <div class="container">
            <div class="table">
                <table class="table table-striped">
                    <tr>
                        <th><fmt:message key="allPages.id"/></th>
                        <th><fmt:message key="allPages.class"/></th>
                        <th><fmt:message key="class.teacher"/></th>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <th><fmt:message key="allPages.add"/></th>
                            <th><fmt:message key="allPages.edit"/></th>
                            <th><fmt:message key="allPages.delete"/></th>
                        </sec:authorize>
                    </tr>
                    <c:forEach var="schoolClass" items="${it.schoolClasses}">
                        <c:url var="classUrl" value="/library/classes/${schoolClass.id}"/>
                        <c:url var="employeeUrl" value="/library/employees/${schoolClass.teacher.id}"/>
                        <c:url var="addUrl" value="/library/classes/addForm"/>
                        <c:url var="editUrl" value="/library/classes/editForm?id=${schoolClass.id}"/>
                        <c:url var="deleteUrl" value="/library/classes/deleteForm?id=${schoolClass.id}"/>
                        <tr>
                            <td>${schoolClass.id}</td>
                            <td><a href="${classUrl}">${schoolClass.course}-${schoolClass.letter}</a></td>
                            <td><a href="${employeeUrl}">${schoolClass.teacher.name} ${schoolClass.teacher.surname}</a></td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td><a href="${addUrl}"><fmt:message key="allPages.add"/></a></td>
                                <td><a href="${editUrl}"><fmt:message key="allPages.edit"/></a></td>
                                <td><a href="${deleteUrl}"><fmt:message key="allPages.delete"/></a></td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="buttons">
                    <form class="form-horizontal" action="/library/classes/addForm" method="GET">
                        <div class="form-group">
                            <div class="col-sm-5">
                                <label class="control-label"> <fmt:message key="class.add.new"/>:</label>
                            </div>
                            <div class="col-sm-4">
                            </div>
                            <div class="col-sm-3">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-plus-sign"></span> <fmt:message key="class.add.new"/></button>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" action="/library/classes/editForm" method="GET">
                        <div class="form-group">
                            <div class="col-sm-5">
                                <label class="control-label"><fmt:message key="class.editById"/>:</label>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" type="number" name="id" title="id">
                            </div>
                            <div class="col-sm-2">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-edit"></span> <fmt:message key="class.editById"/></button>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" action="/library/classes/deleteForm" method="GET">
                        <div class="form-group">
                            <div class="col-sm-5">
                                <label class="control-label"><fmt:message key="class.deleteById"/>:</label>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" type="number" name="id" title="id">
                            </div>
                            <div class="col-sm-2">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-trash"></span> <fmt:message key="class.deleteById"/></button>
                            </div>
                        </div>
                    </form>
                </div>
            </sec:authorize>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</div>
</body>
</html>