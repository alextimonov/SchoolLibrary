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
        <h2>Added employee:</h2>
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
        <p>Name: ${it.name}</p>
        <p>Surname: ${it.surname}</p>
        <p>Position: ${it.poosition}</p>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>
</body>
</html>
