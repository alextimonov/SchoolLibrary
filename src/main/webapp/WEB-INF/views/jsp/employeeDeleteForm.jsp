<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Delete Employee</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        var ctxPath = "<%=request.getContextPath() %>";
        $(document).ready(function() {
            $('#form').submit(function(e) {
                e.preventDefault();
                var id = $("#id").val();
                var formData = {"id": id, "name": $("#name").val(), "surname": $("#surname").val(), "position": $("#position").val()};
                $.ajax({
                    url: ctxPath + "/library/employees",
                    type: "DELETE",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    cache: false,
                    dataType: "json",
                    success: function(data, textStatus, jqXHR) {
                        alert("Employee successfully deleted: #" + data.id + " " + data.name + " " +
                                data.surname + ", " + data.position);
                        window.location = ctxPath + "/library/employees";
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if (jqXHR.status == 405) { // Method DELETE not allowed in JSP
                            alert("Employee's data successfully deleted");
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
        <h3>Are you sure to delete this employee:</h3>
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
                <c:when test="${it.id > 0}">
                    <form id="form" class="form-horizontal" action="/library/employees">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="control-label">ID:</label>
                            </div>
                            <div class="col-sm-9">
                                <label class="control-label">${it.id}</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="control-label">Name:</label>
                            </div>
                            <div class="col-sm-9">
                                <label class="control-label">${it.name}</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="control-label">Surname:</label>
                            </div>
                            <div class="col-sm-9">
                                <label class="control-label">${it.surname}</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="control-label">Position:</label>
                            </div>
                            <div class="col-sm-9">
                                <label class="control-label">${it.position}</label>
                            </div>
                        </div>

                        <button id="submit" class="btn btn-primary" type="submit">
                            <span class="glyphicon glyphicon-trash"></span> Delete employee
                        </button>
                    </form>
                </c:when>
                <c:otherwise>
                    <h3>Employee cannot be deleted while he(she) is a curator of some class:</h3>
                    <form id="form" class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="control-label">Name:</label>
                            </div>
                            <div class="col-sm-9">
                                <label class="control-label">${it.name}</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="control-label">Surname:</label>
                            </div>
                            <div class="col-sm-9">
                                <label class="control-label">${it.surname}</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="control-label">Position:</label>
                            </div>
                            <div class="col-sm-9">
                                <label class="control-label">${it.position}</label>
                            </div>
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
            <br>
            <form class="form-inline" action="/library/employees" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> Return to employees</button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</div>
</body>
</html>


