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
        <h3>School library Web application. School class details</h3>
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
                <c:url var="addUrl" value="/library/classes/addForm"/>
                <c:url var="editUrl" value="/library/classes/editForm?id=${it.schoolClass.id}"/>
                <c:url var="deleteUrl" value="/library/classes/deleteForm?id=${it.schoolClass.id}"/>
                <tr>
                    <th>ID</th>
                    <th>Class</th>
                    <th>Teacher</th>
                    <th>Add</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <tr>
                    <td>${it.schoolClass.id}</td>
                    <td>${it.schoolClass.course}-${it.schoolClass.letter}</td>
                    <td>${it.schoolClass.teacher.name} ${it.schoolClass.teacher.surname} </td>
                    <td><a href="${addUrl}">Add</a></td>
                    <td><a href="${editUrl}">Edit</a></td>
                    <td><a href="${deleteUrl}">Delete</a></td>
                </tr>
            </table>
            <table class="table table-striped">
                <caption>School books in class:</caption>
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

            <form class="form-horizontal" action="/library/books/handoutForm" method="GET">
                <div class="form-group">
                    <div class="col-sm-0">
                        <input class="form-control" name="classId" value="${it.schoolClass.id}" type="hidden"/>
                    </div>
                    <div class="col-sm-3">
                        <label class="control-label" for="bookId">Hand out schoolbooks:</label>
                    </div>
                    <div class="col-sm-3">
                        <select id="bookId" class="form-control" name="bookId">
                            <option selected disabled hidden>Choose schoolbook:</option>
                            <c:forEach var="bookInClass" items="${it.schoolClass.booksInClass}">
                                <option value="${bookInClass.schoolbook.id}">
                                    ${bookInClass.schoolbook.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-2">
                        <label class="control-label" for="booksAmount">Input amount:</label>
                    </div>
                    <div class="col-sm-2">
                        <input class="form-control" id="booksAmount" type="number" name="booksAmount">
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-primary" type="submit">
                            <span class="glyphicon glyphicon-triangle-left"></span> Hand out</button>
                    </div>
                </div>
            </form>

            <form class="form-horizontal" action="/library/books/returnForm" method="GET">
                <div class="form-group">
                    <div class="col-sm-0">
                        <input class="form-control" name="classId" value="${it.schoolClass.id}" type="hidden"/>
                    </div>
                    <div class="col-sm-3">
                        <label class="control-label" for="bookIdReturn">Return schoolbooks:</label>
                    </div>
                    <div class="col-sm-3">
                        <select id="bookIdReturn" class="form-control" name="bookId">
                            <option selected disabled hidden>Choose schoolbook:</option>
                            <c:forEach var="bookInClass" items="${it.schoolClass.booksInClass}">
                                <option value="${bookInClass.schoolbook.id}">
                                        ${bookInClass.schoolbook.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-2">
                        <label class="control-label" for="booksAmountReturn">Input amount:</label>
                    </div>
                    <div class="col-sm-2">
                        <input class="form-control" id="booksAmountReturn" type="number" name="booksAmount">
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-primary" type="submit">
                            <span class="glyphicon glyphicon-triangle-right"></span> Return</button>
                    </div>
                </div>
            </form>

            <form class="form-inline" action="/library/classes" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> Return to classes</button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>
</body>
</html>