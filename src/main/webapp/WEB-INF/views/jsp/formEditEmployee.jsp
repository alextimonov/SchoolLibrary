<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Add Employee</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        var ctxPath = "<%=request.getContextPath() %>";
        $(document).ready(function() {
            $('#form').submit(function(e) {
                e.preventDefault();
                var id = $("#id").val();
                var formData = {"id": id, "name": $("#name").val(), "surname": $("#surname").val(), "position": $("#position").val()};
                $.ajax({
                    url: ctxPath + "/library/employees/" + id,
                    method: "PUT",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    cache: false,
                    dataType: "json",
                    success: function(data, textStatus, jqXHR) {
                        alert("Employee's data successfully changed: #" + data.id + " " + data.name + " " +
                                data.surname + ", " + data.position);
                        window.location = ctxPath + "/library/employees";
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if (jqXHR.status == 405) { // Method POST not allowed in JSP
                            alert("Employee's data successfully changed");
                            window.location = ctxPath + "/library/employees";
                        }
                        else {
                            if (jqXHR.status == 400) {
                                var messages = JSON.parse(jqXHR.responseText);
                                $('#messages').empty();
                                $.each(messages, function (i, v) {
                                    var item = $('<li>').append(v);
                                    $('#messages').append(item);
                                });
                            }
                            else {
                                alert('Server error. HTTP status: ' + jqXHR.status);
                            }
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
        <h3>Edit employee:</h3>
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
            <form id="form" class="form-horizontal" action="/library/employees">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="id">ID:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="id" name="id" value="${it.employee.id}" disabled="true" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="name">Name:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="name" name="name" value="${it.employee.name}" type="text"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="surname">Surname:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="surname" name="surname" value="${it.employee.surname}" type="text"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="position">Position:</label>
                    </div>
                    <div class="col-sm-4">
                        <select id="position" class="form-control" name="position">
                            <option disabled>Choose from positions:</option>
                            <c:forEach var="position" items="${it.positions}">
                                <option <c:if test="${position == it.employee.position}">selected</c:if>
                                        value="${position}">${position}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <button id="submit" class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-floppy-disk"></span>Save edited employee
                </button>
            </form>

            <form class="form-inline" action="/library/employees" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span>Return to employees</button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</div>
</body>
</html>

