<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>School library. Add Employee</title>
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        var ctxPath = "<%=request.getContextPath() %>";
        $(document).ready(function() {
            $('#form').submit(function(e) {
                e.preventDefault();
                var formData = {"name": $("#name").val(), "surname": $("#surname").val(), "position": $("#position").val()};
                $.ajax({
                    url: ctxPath + "/library/employees",
                    type: "POST",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    cache: false,
                    dataType: "json",
                    success: function(data, textStatus, jqXHR) {
                        alert("Employee successfully added: " + data.name + " " + data.surname + ", " + data.position);
                        window.location = ctxPath + "/library/employees";
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
        <h3>Create new employee:</h3>
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
            <form id="form" class="form-horizontal" method="POST" action="/library/employees">
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
                        <label class="control-label" for="surname">Surname:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="surname" name="surname" type="text"/>
                    </div>
                    <%--<div class="col-sm-4">
                        <label class="label-info">${employeeValidate.surnameLabel}</label>
                    </div>--%>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="position">Position:</label>
                    </div>
                    <div class="col-sm-4">
                        <select id="position" name="position" class="form-control">
                            <option selected disabled hidden>Choose from available positions</option>
                            <option value="director">director</option>
                            <option value="deputy_director">deputy director</option>
                            <option value="librarian">librarian</option>
                            <option value="teacher">teacher</option>
                            <option value="tutor">tutor</option>
                            <option value="security">security</option>
                            <option value="cleaner">cleaner</option>
                        </select>
                    </div>
                    <%--<div class="col-sm-4">
                        <label class="label-info">${employeeValidate.positionLabel}</label>
                    </div>--%>
                </div>

                <button id="submit" class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-floppy-disk"></span>Save new employee
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


