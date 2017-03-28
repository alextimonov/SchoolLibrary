<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Return schoolbooks</title>
</head>
<body>
<header>
    <h2>School library Web application</h2>
    <h3>Return ${it.amountToCollect} schoolbooks "${it.bookInClass.schoolbook.name}" from
        ${it.bookInClass.schoolClassCourse}-${it.bookInClass.schoolClassLetter} class to library</h3>
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
            <c:when test="${it.errorId == -2}">
                <h4>${it.errorMessage}</h4>
                <form class="form-inline" action="/library/books/returnForm" method="GET">
                    <input name="bookId" value="${it.bookInClass.schoolbook.id}" type="hidden"/>
                    <input name="classId" value="${it.bookInClass.schoolClassId}" type="hidden"/>
                    <input name="booksAmount" value="${it.currentAmount}" type="hidden"/>
                    <button class="btn btn-primary" type="submit">
                        <span class="glyphicon glyphicon-triangle-right"></span> Return books</button>
                </form>
            </c:when>
            <c:otherwise>
                <h4>Return is successful. ${it.bookInClass.schoolClassCourse}-${it.bookInClass.schoolClassLetter} class
                    has returned ${it.amountToReturn} books "${it.bookInClass.schoolbook.name}", now there are
                        ${it.bookInClass.booksNumber} books in this class.</h4>
            </c:otherwise>
        </c:choose>
        <br>
        <form class="form-inline" action="/library/books/${it.bookInClass.schoolbook.id}" method="GET">
            <button class="btn btn-primary" type="submit">
                <span class="glyphicon glyphicon-triangle-left"></span> Return to schoolbook details</button>
        </form>
    </div>
</article>

<footer>Copyright &copy; Alexey Timonov</footer>

</body>
</html>