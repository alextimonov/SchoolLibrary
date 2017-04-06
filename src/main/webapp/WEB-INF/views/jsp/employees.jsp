<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Employees</title>
</head>
<body>
<div class="container">
    <header>
        <h3>School library Web application. Employees page</h3>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <h3>You signed in as <sec:authentication property="principal.username"/></h3>
        </sec:authorize>
    </header>

    <nav>
        <div class="container">
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
        </div>
    </nav>

    <article>
        <div class="container">
            <div class="table">
                <h3>${it.message}</h3>
                <table class="table table-striped">
                    <tr>
                        <th>ID</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Position</th>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <th>Add</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </sec:authorize>
                    </tr>
                    <c:forEach var="employee" items="${it.employees}">
                        <c:url var="employeeUrl" value="/library/employees/${employee.id}"/>
                        <c:url var="addUrl" value="/library/employees/addForm"/>
                        <c:url var="editUrl" value="/library/employees/editForm?id=${employee.id}"/>
                        <c:url var="deleteUrl" value="/library/employees/deleteForm?id=${employee.id}"/>
                        <tr>
                            <td>${employee.id}</td>
                            <td><a href="${employeeUrl}">${employee.name}</a></td>
                            <td><a href="${employeeUrl}">${employee.surname}</a></td>
                            <td>${employee.position}</td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td><a href="${addUrl}">Add</a></td>
                                <td><a href="${editUrl}">Edit</a></td>
                                <td><a href="${deleteUrl}">Delete</a></td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="buttons">
                    <form class="form-horizontal" action="/library/employees/addForm" method="GET">
                        <div class="form-group">
                            <div class="col-sm-5">
                                <label class="control-label">Add new employee:</label>
                            </div>
                            <div class="col-sm-4">
                            </div>
                            <div class="col-sm-3">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-plus-sign"></span> Add new employee</button>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" action="/library/employees/editForm" method="GET">
                        <div class="form-group">
                            <div class="col-sm-5">
                                <label class="control-label">Edit employee. Input ID:</label>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" type="number" name="id" title="id">
                            </div>
                            <div class="col-sm-3">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-edit"></span> Edit by id</button>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" action="/library/employees/deleteForm" method="GET">
                        <div class="form-group">
                            <div class="col-sm-5">
                                <label class="control-label"> Delete employee. Input ID:</label>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" type="number" name="id" title="id">
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
