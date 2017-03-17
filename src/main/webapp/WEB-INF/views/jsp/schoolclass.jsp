<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Class</title>
</head>
<body>
<div class="container">
    <header>
        <h1>School library Web application</h1>
        <h2>Found class by ID:</h2>
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
                    <th>Class</th>
                    <th>Teacher</th>
                </tr>
                <tr>
                    <td>${it.id}</td>
                    <td>${it.course}-${it.letter}</td>
                    <td>${it.teacher.name} ${it.teacher.surname} </td>
                </tr>
            </table>
            <hr>
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>School book</th>
                    <th>Amount (TOTAL)</th>
                </tr>
                <c:forEach var="schoolbook" items="${it.schoolbooks}">
                    <tr>
                        <td>${schoolbook.id}</td>
                        <td>${schoolbook.name}</td>
                        <td>${schoolbook.amountTotal}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>
</body>
</html>