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
                </tr>
                <tr>
                    <td>${it.id}</td>
                    <td>${it.name}</td>
                    <td>${it.surname}</td>
                    <td>${it.position}</td>
                </tr>
            </table>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>
</body>
</html>
