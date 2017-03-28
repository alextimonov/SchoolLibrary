<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Employee</title>
</head>
<body>
<div class="container">
    <header>
        <h3>School library Web application. Employee details</h3>
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
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Position</th>
                    <th>Class</th>
                    <th>Add</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:url var="addUrl" value="/library/employees/addForm"/>
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
                    <td><a href="${addUrl}">Add</a></td>
                    <td><a href="${editUrl}">Edit</a></td>
                    <td><a href="${deleteUrl}">Delete</a></td>
                </tr>
            </table>
            <c:if test="${it.schoolClass.course > 0}">
                <table class="table table-striped">
                    <caption>Books in class</caption>
                    <tr>
                        <th>ID</th>
                        <th>School book</th>
                        <th>Amount in class</th>
                        <th>TOTAL</th>
                    </tr>
                    <c:forEach var="bookInClass" items="${it.schoolClass.booksInClass}">
                        <tr>
                            <td>${bookInClass.schoolbook.id}</td>
                            <td>${bookInClass.schoolbook.name}</td>
                            <td>${bookInClass.booksNumber}</td>
                            <td>${bookInClass.schoolbook.amountTotal}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${!empty it.schoolbooks}">
                <table class="table table-striped">
                    <caption>Responsible as librarian for books:</caption>
                    <tr>
                        <th>ID</th>
                        <th>School book</th>
                        <th>Course</th>
                        <th>Amount</th>
                        <th>Details</th>
                    </tr>
                    <c:forEach var="book" items="${it.schoolbooks}">
                        <c:url var="detailsUrl" value="/library/books/${book.id}"/>
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.name}</td>
                            <td>${book.course}</td>
                            <td>${book.amountTotal}</td>
                            <td><a href="${detailsUrl}">Details</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <br>
            <form class="form-inline" action="/library/employees" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> Return to employees</button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>
</body>
</html>
