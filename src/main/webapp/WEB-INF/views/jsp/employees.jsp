<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>School library. Employees</title>
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
</head>
<body>
<div class="container">
    <header>
        <h1>School library Web application</h1>
        <h2>Employees page</h2>
    </header>

    <nav>
        <ul>
            <li><a href="/index.jsp">Main page</a></li>
            <li><a href="/library/employees">Employees</a></li>
            <li><a href="/library/books">Books</a></li>
            <li><a href="/library/classes">Classes</a></li>
        </ul>
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
                    </tr>
                    <c:forEach var="employee" items="${it.employees}">
                        <tr>
                            <td>${employee.id}</td>
                            <td>${employee.name}</td>
                            <td>${employee.surname}</td>
                            <td>${employee.position}</td>
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
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-plus-sign"></span>Add new employee</button>
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
                        <div class="col-sm-2">
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-edit"></span>Edit by id</button>
                        </div>
                    </div>
                </form>

                <form class="form-horizontal" action="/library/employees/deleteForm" method="GET">
                    <div class="form-group">
                        <div class="col-sm-5">
                            <label class="control-label">Delete employee. Input ID:</label>
                        </div>
                        <div class="col-sm-4">
                            <input class="form-control" type="number" name="id" title="id">
                        </div>
                        <div class="col-sm-2">
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-edit"></span>Delete by id</button>
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
