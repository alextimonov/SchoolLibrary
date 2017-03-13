<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>School library. Employee</title>
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
</head>
<body>
<div class="container">
    <header>
        <h1>School library Web application</h1>
        <h2>${it.message}</h2>
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
        <p>${it.employee}</p>
        <c:set var="employee" value="${it.employee}"/>
        <div class="container">
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Position</th>
                </tr>
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.name}</td>
                    <td>${employee.surname}</td>
                    <td>${employee.position}</td>
                </tr>
            </table>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>
</body>
</html>
