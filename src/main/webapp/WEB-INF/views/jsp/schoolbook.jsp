<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Schoolbook</title>
</head>
<body>
<div class="container">
    <header>
        <h3>School library Web application</h3>
        <h4>Schoolbook details</h4>
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
        <c:set var="book" value="${it.schoolbook}"/>
        <div class="container">
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Course</th>
                    <th>Total</th>
                    <th>Residue</th>
                    <th>Librarian</th>
                    <th>Add</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:url var="addUrl" value="/library/books/addForm"/>
                <c:url var="editUrl" value="/library/books/editForm?id=${book.id}"/>
                <c:url var="deleteUrl" value="/library/books/deleteForm?id=${book.id}"/>
                <tr>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.course}</td>
                    <td>${book.amountTotal}</td>
                    <td>${it.residue}</td>
                    <td>${book.librarian.name} ${book.librarian.surname}</td>
                    <td><a href="${addUrl}">Add</a></td>
                    <td><a href="${editUrl}">Edit</a></td>
                    <td><a href="${deleteUrl}">Delete</a></td>
                </tr>
            </table>

            <form class="form-horizontal" action="/library/books/collectForm" method="GET">
                <div class="form-group">
                    <div class="col-sm-0">
                        <input class="form-control" name="bookId" value="${book.id}" type="hidden"/>
                    </div>
                    <div class="col-sm-3">
                        <label class="control-label" for="classIdCollect">Collect schoolbooks:</label>
                    </div>
                    <div class="col-sm-3">
                        <select id="classIdCollect" class="form-control" name="classId">
                            <option selected disabled hidden>Choose class:</option>
                            <c:forEach var="schoolClass" items="${it.schoolClasses}">
                                <option value="${schoolClass.id}">${schoolClass.course}-${schoolClass.letter}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-2">
                        <label class="control-label" for="booksAmountCollect">Input amount:</label>
                    </div>
                    <div class="col-sm-2">
                        <input class="form-control" id="booksAmountCollect" type="number" name="booksAmount">
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-primary" type="submit">
                            <span class="glyphicon glyphicon-triangle-top"></span> Collect</button>
                    </div>
                </div>
            </form>

            <form class="form-horizontal" action="/library/books/handoutForm" method="GET">
                <div class="form-group">
                    <div class="col-sm-0">
                        <input class="form-control" name="bookId" value="${book.id}" type="hidden"/>
                    </div>
                    <div class="col-sm-3">
                        <label class="control-label" for="classId">Hand out schoolbooks:</label>
                    </div>
                    <div class="col-sm-3">
                        <select id="classId" class="form-control" name="classId">
                            <option selected disabled hidden>Choose class:</option>
                            <c:forEach var="schoolClass" items="${it.schoolClasses}">
                                <option value="${schoolClass.id}">${schoolClass.course}-${schoolClass.letter}</option>
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
                            <span class="glyphicon glyphicon-triangle-bottom"></span> Hand out</button>
                    </div>
                </div>
            </form>

            <table class="table table-striped">
                <caption>Book's amount in classes</caption>
                <tr>
                    <th>ID</th>
                    <th>Class</th>
                    <th>Teacher</th>
                    <th>Amount in class</th>
                </tr>
                <c:forEach var="bookInClass" items="${it.booksInClass}">
                    <tr>
                        <td>${bookInClass.schoolClassId}</td>
                        <td>${bookInClass.schoolClassCourse}-${bookInClass.schoolClassLetter}</td>
                        <td>${bookInClass.schoolClassTeacherName} ${bookInClass.schoolClassTeacherSurname}</td>
                        <td>${bookInClass.booksNumber}</td>
                    </tr>
                </c:forEach>
            </table>
            <br>

            <form class="form-inline" action="/library/books" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> Return to schoolbooks</button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>
</div>
</body>
</html>