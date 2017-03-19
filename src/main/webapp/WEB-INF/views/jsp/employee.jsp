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
        <h1>School library Web application</h1>
        <h2>Found employee by ID:</h2>
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
                <c:url var="editUrl" value="/library/employees/editForm?id=${it.id}"/>
                <c:url var="deleteUrl" value="/library/employees/deleteForm?id=${it.id}"/>
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
            <br>
            <c:if test="${it.schoolClass.course > 0}">
                <table class="table table-striped">
                    <title>Teacher's books</title>
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
                            <td>${bookInClass.nBooksInClass}</td>
                            <td>${bookInClass.schoolbook.amountTotal}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>
</body>
</html>
