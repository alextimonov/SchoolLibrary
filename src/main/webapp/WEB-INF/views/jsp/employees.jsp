<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </header>

    <nav>
        <div class="container">
            <ul>
                <li><a href="/index.jsp">Main page</a></li>
                <li><a href="/library/employees">Employees</a></li>
                <li><a href="/library/books">Books</a></li>
                <li><a href="/library/classes">Classes</a></li>
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
                        <th>Details</th>
                        <th>Add</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="employee" items="${it.employees}">
                        <c:url var="detailsUrl" value="/library/employees/${employee.id}"/>
                        <c:url var="addUrl" value="/library/employees/addForm"/>
                        <c:url var="editUrl" value="/library/employees/editForm?id=${employee.id}"/>
                        <c:url var="deleteUrl" value="/library/employees/deleteForm?id=${employee.id}"/>
                        <tr>
                            <td>${employee.id}</td>
                            <td>${employee.name}</td>
                            <td>${employee.surname}</td>
                            <td>${employee.position}</td>
                            <td><a href="${detailsUrl}">Details</a></td>
                            <td><a href="${addUrl}">Add</a></td>
                            <td><a href="${editUrl}">Edit</a></td>
                            <td><a href="${deleteUrl}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

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
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</div>
</body>
</html>
