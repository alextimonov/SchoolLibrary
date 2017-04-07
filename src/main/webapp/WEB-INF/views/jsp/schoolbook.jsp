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
    <title>School library. Schoolbook</title>
</head>
<body>
<div class="container">
    <header>
        <h3><fmt:message key="allPages.header"/></h3>
        <h3><fmt:message key="page.schoolbook.header"/>: ${it.schoolbook.name} <fmt:message key="allPages.for"/> ${it.schoolbook.course} <fmt:message key="allPages.course"/></h3>
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
        <c:set var="book" value="${it.schoolbook}"/>
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
                    <th><fmt:message key="schoolbook.course"/></th>
                    <th><fmt:message key="schoolbook.name"/></th>
                    <th><fmt:message key="schoolbook.author"/></th>
                    <th><fmt:message key="schoolbook.publisher"/></th>
                    <th><fmt:message key="schoolbook.total.lowerCase"/></th>
                    <th><fmt:message key="schoolbook.balance"/></th>
                    <th><fmt:message key="schoolbook.librarian"/></th>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <th><fmt:message key="allPages.edit"/></th>
                        <th><fmt:message key="allPages.delete"/></th>
                    </sec:authorize>
                </tr>
                <c:url var="editUrl" value="/library/books/editForm?id=${book.id}"/>
                <c:url var="deleteUrl" value="/library/books/deleteForm?id=${book.id}"/>
                <tr>
                    <td>${book.id}</td>
                    <td>${book.course}</td>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.publisher}</td>
                    <td>${book.amountTotal}</td>
                    <td>${it.residue}</td>
                    <td>${book.librarian.name} ${book.librarian.surname}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td><a href="${editUrl}"><fmt:message key="allPages.edit"/></a></td>
                        <td><a href="${deleteUrl}"><fmt:message key="allPages.delete"/></a></td>
                    </sec:authorize>
                </tr>
            </table>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <form class="form-horizontal" action="/library/books/returnForm" method="GET">
                    <div class="form-group">
                        <div class="col-sm-0">
                            <input class="form-control" name="bookId" value="${book.id}" type="hidden"/>
                        </div>
                        <div class="col-sm-3">
                            <label class="control-label" for="classIdReturn"><fmt:message key="schoolbook.returnBooks"/>:</label>
                        </div>
                        <div class="col-sm-3">
                            <select id="classIdReturn" class="form-control" name="classId">
                                <option selected disabled hidden><fmt:message key="class.choose"/>:</option>
                                <c:forEach var="bookInClass" items="${it.booksInClass}">
                                    <option value="${bookInClass.schoolClassId}">${bookInClass.schoolClassCourse}-${bookInClass.schoolClassLetter}</option>
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
                                <span class="glyphicon glyphicon-triangle-top"></span> <fmt:message key="allPages.return"/></button>
                        </div>
                    </div>
                </form>

                <form class="form-horizontal" action="/library/books/handoutForm" method="GET">
                    <div class="form-group">
                        <div class="col-sm-0">
                            <input class="form-control" name="bookId" value="${book.id}" type="hidden"/>
                        </div>
                        <div class="col-sm-3">
                            <label class="control-label" for="classId"><fmt:message key="schoolbook.handoutBooks"/>:</label>
                        </div>

                        <div class="col-sm-3">
                            <select id="classId" class="form-control" name="classId">
                                <option selected disabled hidden><fmt:message key="class.choose"/>:</option>
                                <c:forEach var="schoolClass" items="${it.schoolClasses}">
                                    <option value="${schoolClass.id}">${schoolClass.course}-${schoolClass.letter}</option>
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
                                <span class="glyphicon glyphicon-triangle-bottom"></span> <fmt:message key="allPages.toHandout"/></button>
                        </div>
                    </div>
                </form>

                <form class="form-horizontal" action="/library/books/${book.id}" method="GET">
                    <div class="col-sm-3">
                        <label class="control-label"><fmt:message key="class.selection"/>:</label>
                    </div>
                    <div class="col-sm-2">
                        <label>
                            <input type="radio" name="classesSelection" value="byCourse"
                                   <c:if test="${it.classesSelectionAll == false}">checked</c:if> > <fmt:message key="allPages.byCourse"/>
                        </label>
                    </div>
                    <div class="col-sm-2">
                        <label>
                            <input type="radio" name="classesSelection" value="all"
                               <c:if test="${it.classesSelectionAll == true}">checked</c:if> > <fmt:message key="allPages.allСourses"/>
                        </label>
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-primary" type="submit">
                            <span class="glyphicon glyphicon-triangle-right"></span> <fmt:message key="allPages.allСourses"/></button>
                    </div>
                </form>
                <br>
            </sec:authorize>

            <br>
            <table class="table table-striped">
                <caption><fmt:message key="schoolbook.amountInClasses"/></caption>
                <tr>
                    <th><fmt:message key="allPages.id"/></th>
                    <th><fmt:message key="allPages.class"/></th>
                    <th><fmt:message key="class.teacher"/></th>
                    <th><fmt:message key="schoolbook.amount"/></th>
                </tr>
                <c:forEach var="bookInClass" items="${it.booksInClass}">
                    <c:url var="classUrl" value="/library/classes/${bookInClass.schoolClassId}"/>
                    <tr>
                        <td>${bookInClass.schoolClassId}</td>
                        <td><a href="${classUrl}">${bookInClass.schoolClassCourse}-${bookInClass.schoolClassLetter}</a></td>
                        <td>${bookInClass.schoolClassTeacherName} ${bookInClass.schoolClassTeacherSurname}</td>
                        <td>${bookInClass.booksNumber}</td>
                    </tr>
                </c:forEach>
            </table>
            <br>

            <form class="form-inline" action="/library/books" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> <fmt:message key="link.back.books"/></button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>
</body>
</html>