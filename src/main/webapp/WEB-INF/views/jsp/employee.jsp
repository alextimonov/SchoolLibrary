<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>

<html lang="${language}">
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Employee</title>
</head>
<body>
<div class="container">
    <header>
        <h3><fmt:message key="allPages.header"/></h3>
        <h3><fmt:message key="page.employee.header"/>: ${it.employee.name} ${it.employee.surname}</h3>
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
            <table class="table table-striped">
                <tr>
                    <th><fmt:message key="allPages.id"/></th>
                    <th><fmt:message key="employee.firstName"/></th>
                    <th><fmt:message key="employee.lastName"/></th>
                    <th><fmt:message key="employee.position"/></th>
                    <th><fmt:message key="allPages.class"/></th>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <th><fmt:message key="allPages.edit"/></th>
                        <th><fmt:message key="allPages.delete"/></th>
                    </sec:authorize>
                </tr>
                <c:url var="editUrl" value="/library/employees/editForm?id=${it.employee.id}"/>
                <c:url var="deleteUrl" value="/library/employees/deleteForm?id=${it.employee.id}"/>
                <tr>
                    <td>${it.employee.id}</td>
                    <td>${it.employee.name}</td>
                    <td>${it.employee.surname}</td>
                    <td>${it.employee.position}</td>
                    <td>
                        <c:if test="${it.schoolClass.course > 0}">${it.schoolClass.course}-${it.schoolClass.letter}</c:if>
                    </td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td><a href="${editUrl}"><fmt:message key="allPages.edit"/></a></td>
                        <td><a href="${deleteUrl}"><fmt:message key="allPages.delete"/></a></td>
                    </sec:authorize>
                </tr>
            </table>
            <c:if test="${it.schoolClass.course > 0}">
                <table class="table table-striped">
                    <caption><fmt:message key="allPages.booksInClass"/></caption>
                    <tr>
                        <th><fmt:message key="allPages.id"/></th>
                        <th><fmt:message key="schoolbook.course"/></th>
                        <th><fmt:message key="schoolbook.name"/></th>
                        <th><fmt:message key="schoolbook.author"/></th>
                        <th><fmt:message key="schoolbook.amount"/></th>
                        <th><fmt:message key="schoolbook.total"/></th>
                    </tr>
                    <c:forEach var="bookInClass" items="${it.schoolClass.booksInClass}">
                        <c:url var="bookUrl" value="/library/books/${bookInClass.schoolbook.id}"/>
                        <tr>
                            <td>${bookInClass.schoolbook.id}</td>
                            <td>${bookInClass.schoolbook.course}</td>
                            <td><a href="${bookUrl}">${bookInClass.schoolbook.name}</a></td>
                            <td>${bookInClass.schoolbook.author}</td>
                            <td>${bookInClass.booksNumber}</td>
                            <td>${bookInClass.schoolbook.amountTotal}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${!empty it.schoolbooks}">
                <table class="table table-striped">
                    <caption><fmt:message key="schoolbook.responsible"/>:</caption>
                    <tr>
                        <th><fmt:message key="allPages.id"/></th>
                        <th><fmt:message key="schoolbook.course"/></th>
                        <th><fmt:message key="schoolbook.name"/></th>
                        <th><fmt:message key="schoolbook.author"/></th>
                        <th><fmt:message key="schoolbook.amount"/></th>
                    </tr>
                    <c:forEach var="book" items="${it.schoolbooks}">
                        <c:url var="bookUrl" value="/library/books/${book.id}"/>
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.course}</td>
                            <td><a href="${bookUrl}">${book.name}</a></td>
                            <td>${book.author}</td>
                            <td>${book.amountTotal}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <br>
            <form class="form-inline" action="/library/employees" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> <fmt:message key="link.back.employees"/></button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>
</body>
</html>
