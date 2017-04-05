<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Hand out schoolbooks</title>
</head>
<body>
    <header>
        <h2>School library Web application</h2>
        <h3>Hand out ${it.amountToHandout} schoolbooks "${it.bookInClass.schoolbook.name}" to
            ${it.bookInClass.schoolClassCourse}-${it.bookInClass.schoolClassLetter} class</h3>
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
            <c:choose>
                <c:when test="${it.errorId == -1}">
                    <h4>${it.errorMessage}</h4>
                    <form class="form-inline" action="/library/books/handoutForm" method="GET">
                        <input name="bookId" value="${it.bookInClass.schoolbook.id}" type="hidden"/>
                        <input name="classId" value="${it.bookInClass.schoolClassId}" type="hidden"/>
                        <input name="booksAmount" value="${it.residue}" type="hidden"/>
                        <button class="btn btn-primary" type="submit" autofocus>
                            <span class="glyphicon glyphicon-triangle-right"></span> Hand out schoolbooks</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <h4>Hand out is successful. ${it.bookInClass.schoolClassCourse}-${it.bookInClass.schoolClassLetter} class
                        has received ${it.amountToHandout} books "${it.bookInClass.schoolbook.name}", now there are
                        ${it.bookInClass.booksNumber} books in this class.</h4>
                </c:otherwise>
            </c:choose>
            <br>
            <form class="form-inline" action="/library/books/${it.bookInClass.schoolbook.id}" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> Back to schoolbook details</button>
            </form>
            <br>
            <form class="form-inline" action="/library/classes/${it.bookInClass.schoolClassId}" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> Back to class details</button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</body>
</html>
