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
    <title>School library. Class</title>
</head>
<body>
<div class="container">
    <header>
        <h3><fmt:message key="allPages.header"/></h3>
        <h3>${it.schoolClass.course}-${it.schoolClass.letter} <fmt:message key="page.class.header"/></h3>
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
        <div class="container">
            <table class="table table-striped">
                <c:url var="editUrl" value="/library/classes/editForm?id=${it.schoolClass.id}"/>
                <c:url var="deleteUrl" value="/library/classes/deleteForm?id=${it.schoolClass.id}"/>
                <tr>
                    <th><fmt:message key="allPages.id"/></th>
                    <th><fmt:message key="allPages.class"/></th>
                    <th><fmt:message key="class.teacher"/></th>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <th><fmt:message key="allPages.edit"/></th>
                        <th><fmt:message key="allPages.delete"/></th>
                    </sec:authorize>
                </tr>
                <tr>
                    <td>${it.schoolClass.id}</td>
                    <td>${it.schoolClass.course}-${it.schoolClass.letter}</td>
                    <td>${it.schoolClass.teacher.name} ${it.schoolClass.teacher.surname} </td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td><a href="${editUrl}"><fmt:message key="allPages.edit"/></a></td>
                        <td><a href="${deleteUrl}"><fmt:message key="allPages.delete"/></a></td>
                    </sec:authorize>
                </tr>
            </table>
            <table class="table table-striped">
                <caption><fmt:message key="class.books"/>:</caption>
                <tr>
                    <th><fmt:message key="allPages.id"/></th>
                    <th><fmt:message key="schoolbook.course"/></th>
                    <th><fmt:message key="schoolbook.name"/></th>
                    <th><fmt:message key="schoolbook.author"/></th>
                    <th><fmt:message key="schoolbook.amount"/></th>
                    <th><fmt:message key="schoolbook.total.upperCase"/></th>
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

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <form class="form-horizontal" action="/library/books/returnForm" method="GET">
                    <div class="form-group">
                        <div class="col-sm-0">
                            <input class="form-control" name="classId" value="${it.schoolClass.id}" type="hidden"/>
                        </div>
                        <div class="col-sm-3">
                            <label class="control-label" for="bookIdReturn"><fmt:message key="schoolbook.returnBooks"/>:</label>
                        </div>
                        <div class="col-sm-3">
                            <select id="bookIdReturn" class="form-control" name="bookId">
                                <option selected disabled hidden><fmt:message key="class.chooseBook"/>:</option>
                                <c:forEach var="bookInClass" items="${it.schoolClass.booksInClass}">
                                    <option value="${bookInClass.schoolbook.id}">
                                            ${bookInClass.schoolbook.course} course, ${bookInClass.schoolbook.name}, author ${bookInClass.schoolbook.author}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label class="control-label" for="booksAmountReturn"><fmt:message key="allPages.inputAmount"/>:</label>
                        </div>
                        <div class="col-sm-2">
                            <input class="form-control" id="booksAmountReturn" type="number" name="booksAmount">
                        </div>
                        <div class="col-sm-2">
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-triangle-right"> </span><fmt:message key="allPages.return"/></button>
                        </div>
                    </div>
                </form>

                <form class="form-horizontal" action="/library/books/handoutForm" method="GET">
                    <div class="form-group">
                        <div class="col-sm-0">
                            <input class="form-control" name="classId" value="${it.schoolClass.id}" type="hidden"/>
                        </div>
                        <div class="col-sm-3">
                            <label class="control-label" for="bookId"><fmt:message key="schoolbook.handoutBooks"/>:</label>
                        </div>
                        <div class="col-sm-3">
                            <select id="bookId" class="form-control" name="bookId">
                                <option selected disabled hidden><fmt:message key="class.chooseBook"/>:</option>
                                <c:forEach var="schoolbook" items="${it.schoolbooks}">
                                    <option value="${schoolbook.id}">
                                        ${schoolbook.course} course, ${schoolbook.name}, author ${schoolbook.author}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label class="control-label" for="booksAmount"><fmt:message key="allPages.inputAmount"/>:</label>
                        </div>
                        <div class="col-sm-2">
                            <input class="form-control" id="booksAmount" type="number" name="booksAmount">
                        </div>
                        <div class="col-sm-2">
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-triangle-left"></span> <fmt:message key="allPages.toHandout"/></button>
                        </div>
                    </div>
                </form>

                <form class="form-horizontal" action="/library/classes/${it.schoolClass.id}" method="GET">
                    <div class="col-sm-3">
                        <label class="control-label"><fmt:message key="class.selection"/>:</label>
                    </div>
                    <div class="col-sm-2">
                        <label>
                            <input type="radio" name="booksSelection" value="byCourse"
                                   <c:if test="${it.booksSelectionAll == false}">checked</c:if> > <fmt:message key="allPages.byCourse"/>
                        </label>
                    </div>
                    <div class="col-sm-2">
                        <label>
                            <input type="radio" name="booksSelection" value="all"
                                   <c:if test="${it.booksSelectionAll == true}">checked</c:if> > <fmt:message key="allPages.allСourses"/>
                        </label>
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-primary" type="submit">
                            <span class="glyphicon glyphicon-triangle-right"></span> <fmt:message key="class.changeSelection"/></button>
                    </div>
                </form>
                <br>
            </sec:authorize>
            <br>
            <form class="form-inline" action="/library/classes" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> <fmt:message key="link.back.classes"/></button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>
</body>
</html>