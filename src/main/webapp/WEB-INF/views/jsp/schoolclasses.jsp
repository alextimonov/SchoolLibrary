<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Classes</title>
</head>
<body>

<div class="container">
    <header>
        <h1>School library Web application</h1>
        <h2>School classes page</h2>
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
                        <th>Class</th>
                        <th>Teacher</th>
                        <th>Details</th>
                        <th>Add</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="schoolClass" items="${it.schoolClasses}">
                        <c:url var="detailsUrl" value="/library/classes/${schoolClass.id}"/>
                        <c:url var="addUrl" value="/library/classes/addForm"/>
                        <c:url var="editUrl" value="/library/classes/editForm?id=${schoolClass.id}"/>
                        <c:url var="deleteUrl" value="/library/classes/deleteForm?id=${schoolClass.id}"/>
                        <tr>
                            <td>${schoolClass.id}</td>
                            <td>${schoolClass.course}-${schoolClass.letter}</td>
                            <td>${schoolClass.teacher.name} ${schoolClass.teacher.surname} </td>
                            <td><a href="${detailsUrl}">Details</a></td>
                            <td><a href="${addUrl}">Add</a></td>
                            <td><a href="${editUrl}">Edit</a></td>
                            <td><a href="${deleteUrl}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div class="buttons">
                <form class="form-horizontal" action="/library/classes/addForm" method="GET">
                    <div class="form-group">
                        <div class="col-sm-5">
                            <label class="control-label"> Add new class:</label>
                        </div>
                        <div class="col-sm-4">
                        </div>
                        <div class="col-sm-3">
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-plus-sign"></span> Add new class</button>
                        </div>
                    </div>
                </form>

                <form class="form-horizontal" action="/library/classes/editForm" method="GET">
                    <div class="form-group">
                        <div class="col-sm-5">
                            <label class="control-label">Edit class. Input ID:</label>
                        </div>
                        <div class="col-sm-4">
                            <input class="form-control" type="number" name="id" title="id">
                        </div>
                        <div class="col-sm-2">
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-edit"></span> Edit by id</button>
                        </div>
                    </div>
                </form>

                <form class="form-horizontal" action="/library/classes/deleteForm" method="GET">
                    <div class="form-group">
                        <div class="col-sm-5">
                            <label class="control-label">Delete class. Input ID:</label>
                        </div>
                        <div class="col-sm-4">
                            <input class="form-control" type="number" name="id" title="id">
                        </div>
                        <div class="col-sm-2">
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