<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Schoolbooks</title>
</head>
<body>

<div class="container">
    <header>
        <h3>School library Web application. Schoolbooks page</h3>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <h3>You signed in as <sec:authentication property="principal.username"/></h3>
        </sec:authorize>
    </header>

    <nav>
        <ul>
            <li><a href="/index.jsp">Main page</a></li>
            <li><a href="/library/employees">Employees</a></li>
            <li><a href="/library/books">Books</a></li>
            <li><a href="/library/classes">Classes</a></li>
            <li>
                <sec:authorize access="isAnonymous()">
                    <a href="/library/protected/login">Sign in</a>
                </sec:authorize>
            </li>
            <li>
                <sec:authorize access="isAuthenticated()">
                    <form:form  class="form-horizontal" method="POST" action="/j_spring_security_logout">
                        <button class="btn btn-primary" type="submit">
                            <span class="glyphicon glyphicon-hand-left"></span> Sign out</button>
                    </form:form>
                </sec:authorize>
            </li>
        </ul>
    </nav>

    <article>
        <div class="container">
            <div class="table">
                <h3>${it.message}</h3>
                <table class="table table-striped">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Course</th>
                        <th>Amount</th>
                        <th>Responsible</th>
                        <th>Employee</th>
                        <th>Details</th>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <th>Add</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </sec:authorize>
                    </tr>
                    <c:set var="totalNumber" value="0"/>
                    <c:forEach var="book" items="${it.schoolbooks}">
                        <c:url var="detailsUrl" value="/library/books/${book.id}"/>
                        <c:url var="addUrl" value="/library/books/addForm"/>
                        <c:url var="editUrl" value="/library/books/editForm?id=${book.id}"/>
                        <c:url var="deleteUrl" value="/library/books/deleteForm?id=${book.id}"/>
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.name}</td>
                            <td>${book.course}</td>
                            <td>${book.amountTotal}</td>
                            <td>${book.librarian.position}</td>
                            <td>${book.librarian.name} ${book.librarian.surname} </td>
                            <td><a href="${detailsUrl}">Details</a></td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td><a href="${addUrl}">Add</a></td>
                                <td><a href="${editUrl}">Edit</a></td>
                                <td><a href="${deleteUrl}">Delete</a></td>
                            </sec:authorize>
                        </tr>
                        <c:set var="totalNumber" value="${totalNumber + book.amountTotal}"/>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td>TOTAL</td>
                        <td></td>
                        <td>${totalNumber}</td>
                        <td></td>
                        <td></td>
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
                                <label class="control-label">Add new schoolbook:</label>
                            </div>
                            <div class="col-sm-4">
                            </div>
                            <div class="col-sm-3">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-plus-sign"></span> Add new schoolbook</button>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" action="/library/books/editForm" method="GET">
                        <div class="form-group">
                            <div class="col-sm-5">
                                <label class="control-label">Edit schoolbook. Input ID:</label>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" type="number" name="id">
                            </div>
                            <div class="col-sm-3">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-edit"></span> Edit by id</button>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" action="/library/books/deleteForm" method="GET">
                        <div class="form-group">
                            <div class="col-sm-5">
                                <label class="control-label">Delete schoolbook. Input ID:</label>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" type="number" name="id">
                            </div>
                            <div class="col-sm-3">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-trash"></span> Delete by id</button>
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
