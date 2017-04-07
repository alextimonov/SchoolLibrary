<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>

<html lang="${language}">
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
                            alert("Server error. HTTP status: " + jqXHR.status + ", response: " + jqXHR.responseText);
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
        <h3><fmt:message key="allPages.header"/></h3>
        <h3><fmt:message key="employee.adding"/></h3>
    </header>

    <nav>
        <ul>
            <li><a href="/index.jsp"><fmt:message key="link.mainPage"/></a></li>
            <li><a href="/library/employees"><fmt:message key="link.employees"/></a></li>
            <li><a href="/library/books"><fmt:message key="link.books"/></a></li>
            <li><a href="/library/classes"><fmt:message key="link.classes"/></a></li>
        </ul>
    </nav>

    <article>
        <form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ua" ${language == 'ua' ? 'selected' : ''}>Ukrainian</option>
            </select>
        </form>
        <div class="container">
            <form id="form" class="form-horizontal" method="POST" action="/library/employees">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="name"><fmt:message key="employee.firstName"/>:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="name" name="name" type="text" autofocus/>
                    </div>
                    <%--<div class="col-sm-4">
                        <label class="label-info">${employeeValidate.nameLabel}</label>
                    </div>--%>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="surname"><fmt:message key="employee.lastName"/>:</label>
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
                        <label class="control-label" for="position"><fmt:message key="employee.position"/>:</label>
                    </div>
                    <div class="col-sm-4">
                        <select id="position" class="form-control" name="position">
                            <option selected disabled hidden><fmt:message key="employee.position.choose"/>:</option>
                            <c:forEach var="position" items="${it.positions}">
                                <option value="${position}">${position}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <%--<div class="col-sm-4">
                        <label class="label-info">${employeeValidate.positionLabel}</label>
                    </div>--%>
                </div>

                <button id="submit" class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-floppy-disk"></span> <fmt:message key="employee.save.new"/>
                </button>
            </form>
            <br>
            <form class="form-inline" action="/library/employees" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> <fmt:message key="link.back.employees"/></button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</div>
</body>
</html>