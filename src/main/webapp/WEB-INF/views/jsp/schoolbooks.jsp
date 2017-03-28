<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <th>Name</th>
                        <th>Course</th>
                        <th>Amount</th>
                        <th>Responsible</th>
                        <th>Employee</th>
                        <th>Details</th>
                        <th>Add</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
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
                            <td><a href="${addUrl}">Add</a></td>
                            <td><a href="${editUrl}">Edit</a></td>
                            <td><a href="${deleteUrl}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

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
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</div>
</body>
</html>
