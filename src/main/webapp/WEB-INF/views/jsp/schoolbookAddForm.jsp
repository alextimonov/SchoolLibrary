<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Add schoolbook</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        var ctxPath = "<%=request.getContextPath() %>";
        $(document).ready(function() {
            $('#form').submit(function(e) {
                e.preventDefault();
                var formData = {"name": $("#name").val(), "course": $("#course").val(),
                    "amountTotal": $("#amount").val(), "librarian": { "id": $("#librarian").val() } };
                $.ajax({
                    url: ctxPath + "/library/books",
                    type: "POST",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    cache: false,
                    dataType: "json",
                    success: function(data, textStatus, jqXHR) {
                        alert("Schoolbook successfully added: " + data.name + " for " + data.course + " course, amount = " +
                                data.amountTotal);
                        window.location = ctxPath + "/library/books";
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if(jqXHR.status == 400) {
                            var messages = JSON.parse(jqXHR.responseText);
                            $('#messages').empty();
                            $.each(messages, function(i, v) {
                                var item = $('<li>').append(v);
                                $('#messages').append(item);
                            });
                        }
                        else {
                            alert('Server error. HTTP status: ' + jqXHR.status);
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="container">
    <header>
        <h1>School library Web application</h1>
        <h3>Create new schoolbook:</h3>
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
            <form id="form" class="form-horizontal" method="POST" action="/library/books">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="name">Name:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="name" name="name" type="text"/>
                    </div>
                    <%--<div class="col-sm-4">
                        <label class="label-info">${employeeValidate.nameLabel}</label>
                    </div>--%>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="course">Course:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="course" name="course" type="text"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="amount">Amount:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="amount" name="amount" type="text"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="librarian">Librarian:</label>
                    </div>
                    <div class="col-sm-4">
                        <select id="librarian" name="librarian" class="form-control">
                            <option selected disabled hidden>Choose from employees:</option>
                            <c:forEach var="employee" items="${it.librarians}">
                                <option value=${employee.id}>${employee.position} ${employee.name} ${employee.surname}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <button id="submit" class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-floppy-disk"></span>Save new schoolbook
                </button>
            </form>

            <form class="form-inline" action="/library/books" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span>Return to schoolbooks</button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</div>
</body>
</html>


