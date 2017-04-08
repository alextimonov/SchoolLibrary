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
    <title>School library. Schoolbooks</title>
</head>
<body>

<div class="container">
    <header>
        <h3><fmt:message key="allPages.header"/></h3>
        <h3><fmt:message key="page.books.header"/></h3>
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
                        <th><fmt:message key="schoolbook.course"/></th>
                        <th><fmt:message key="schoolbook.name"/></th>
                        <th><fmt:message key="schoolbook.author"/></th>
                        <th><fmt:message key="schoolbook.amount"/></th>
                        <th><fmt:message key="schoolbook.librarian"/></th>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <th><fmt:message key="allPages.add"/></th>
                            <th><fmt:message key="allPages.edit"/></th>
                            <th><fmt:message key="allPages.delete"/></th>
                        </sec:authorize>
                    </tr>
                    <c:set var="totalNumber" value="0"/>
                    <c:forEach var="book" items="${it.schoolbooks}">
                        <c:url var="bookUrl" value="/library/books/${book.id}"/>
                        <c:url var="addUrl" value="/library/books/addForm"/>
                        <c:url var="editUrl" value="/library/books/editForm?id=${book.id}"/>
                        <c:url var="deleteUrl" value="/library/books/deleteForm?id=${book.id}"/>
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.course}</td>
                            <td><a href="${bookUrl}">${book.name}</a></td>
                            <td>${book.author}</td>
                            <td>${book.amountTotal}</td>
                            <td>${book.librarian.name} ${book.librarian.surname} </td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td><a href="${addUrl}"><fmt:message key="allPages.add"/></a></td>
                                <td><a href="${editUrl}"><fmt:message key="allPages.edit"/></a></td>
                                <td><a href="${deleteUrl}"><fmt:message key="allPages.delete"/></a></td>
                            </sec:authorize>
                        </tr>
                        <c:set var="totalNumber" value="${totalNumber + book.amountTotal}"/>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td><fmt:message key="schoolbook.total.upperCase"/></td>
                        <td></td>
                        <td>${totalNumber}</td>
                        <td></td>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <td></td>
                            <td></td>
                            <td></td>
                        </sec:authorize>
                    </tr>
                </table>
            </div>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="buttons">
                    <form class="form-horizontal" action="/library/books/addForm" method="GET">
                        <div class="form-group">
                            <div class="col-sm-5">
                                <label class="control-label"><fmt:message key="schoolbook.addNew"/>:</label>
                            </div>
                            <div class="col-sm-4">
                            </div>
                            <div class="col-sm-3">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-plus-sign"></span> <fmt:message key="schoolbook.addNew"/>
                                </button>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" action="/library/books/editForm" method="GET">
                        <div class="form-group">
                            <div class="col-sm-5">
                                <label class="control-label"><fmt:message key="schoolbook.editById"/>:</label>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" type="number" name="id">
                            </div>
                            <div class="col-sm-3">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-edit"></span> <fmt:message key="schoolbook.editById"/></button>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" action="/library/books/deleteForm" method="GET">
                        <div class="form-group">
                            <div class="col-sm-5">
                                <label class="control-label"><fmt:message key="schoolbook.editById"/>:</label>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" type="number" name="id">
                            </div>
                            <div class="col-sm-3">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-trash"></span> <fmt:message key="schoolbook.editById"/>:
                                </button>
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
