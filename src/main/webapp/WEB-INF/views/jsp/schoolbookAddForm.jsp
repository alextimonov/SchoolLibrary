<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>

<html lang="${language}">
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
                var formData = {"name": $("#name").val(), "course": $("#course").val(), "author": $("#author").val(),
                    "publisher": $("#publisher").val(), "amountTotal": $("#amount").val(),
                    "librarian": { "id": $("#librarian").val() } };
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
        <h3><fmt:message key="allPages.header"/></h3>
        <h3><fmt:message key="schoolbook.adding"/>
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
        <div class="container">
            <form id="form" class="form-horizontal" method="POST" action="/library/books">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="name"><fmt:message key="schoolbook.name"/>:</label>
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
                        <label class="control-label" for="course"><fmt:message key="schoolbook.courseOfLearning"/>:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="course" name="course" type="text"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="author"><fmt:message key="schoolbook.author"/>:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="author" name="author" type="text"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="publisher"><fmt:message key="schoolbook.publisher"/>:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="publisher" name="publisher" type="text"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="amount"><fmt:message key="schoolbook.amount"/>:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="amount" name="amount" type="text"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="librarian"><fmt:message key="schoolbook.librarian"/>:</label>
                    </div>
                    <div class="col-sm-4">
                        <select id="librarian" name="librarian" class="form-control">
                            <option selected disabled hidden><fmt:message key="schoolbook.chooseEmployee"/>:</option>
                            <c:forEach var="employee" items="${it.librarians}">
                                <option value=${employee.id}>${employee.position} ${employee.name} ${employee.surname}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <button id="submit" class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-floppy-disk"></span> <fmt:message key="schoolbook.save.new"/>
                </button>
            </form>
            <br>
            <form class="form-inline" action="/library/books" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> <fmt:message key="link.back.books"/>
                </button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</div>
</body>
</html>


