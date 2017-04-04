<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library</title>
</head>

<body>
    <div class="container">
        <header>
            <h1>School library Web application</h1>
        </header>

        <nav>
            <ul>
                <li><a href="/library/employees">Employees</a></li>
                <li><a href="/library/books">Books</a></li>
                <li><a href="/library/classes">Classes</a></li>
                <li><a href="/library/protected/start">Protected</a></li>
            </ul>
        </nav>

        <article>
            <h2>Features</h2>
            <p>This application can be used for storing data about school library</p>
            <p>Web application supports RESTful web service</p>
        </article>

        <footer>Copyright &copy; Alexey Timonov</footer>

    </div>
</body>
</html>