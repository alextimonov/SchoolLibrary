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
    <title>School library. Add school class</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        var ctxPath = "<%=request.getContextPath() %>";
        $(document).ready(function() {
            $('#form').submit(function(e) {
                e.preventDefault();
                var formData = {"course": $("#course").val(), "letter": $("#letter").val(),
                    "teacher": { "id": $("#teacher").val() } };
                $.ajax({
                    url: ctxPath + "/library/classes",
                    type: "POST",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    cache: false,
                    dataType: "json"
                })
                        .done(function(data, textStatus, jqXHR) {
                            alert("New class successfully added: " + data.course + "-" + data.letter);
                            window.location = ctxPath + "/library/classes";
                        })
                        .fail(function(jqXHR, textStatus, errorThrown) {
                            alert(jqXHR.responseText);
                        })
            });
        });
    </script>
</head>
<body>
<div class="container">
    <header>
        <h3><fmt:message key="allPages.header"/></h3>
        <h3><fmt:message key="class.adding"/></h3>
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
            <form id="form" class="form-horizontal" method="POST" action="/library/classes">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="course"><fmt:message key="class.course"/>:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="course" name="course" type="text" autofocus/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="letter"><fmt:message key="class.letter"/>:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="letter" name="letter" type="text"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="teacher"><fmt:message key="class.teacher"/>:</label>
                    </div>
                    <div class="col-sm-4">
                        <select id="teacher" name="teacher" class="form-control">
                            <option selected disabled hidden><fmt:message key="class.chooseEmployee"/>:</option>
                            <c:forEach var="employee" items="${it.teachers}">
                                <option value=${employee.id}>${employee.position} ${employee.name} ${employee.surname}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <button id="submit" class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-floppy-disk"></span> <fmt:message key="class.saveNew"/>
                </button>
            </form>
            <br>

            <form class="form-inline" action="/library/classes" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> <fmt:message key="link.back.classes"/></button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</div>
</body>
</html>


<%--function OnError(xhr, errorType, exception) {
var responseText;
$("#dialog").html("");
try {
responseText = jQuery.parseJSON(xhr.responseText);
$("#dialog").append("<div><b>" + errorType + " " + exception + "</b></div>");
$("#dialog").append("<div><p>Exception</p>:<br /><br />" + responseText.ExceptionType + "</div>");
$("#dialog").append("<div><p>StackTrace</p>:<br /><br />" + responseText.StackTrace + "</div>");
$("#dialog").append("<div><p>Message</p>:<br /><br />" + responseText.Message + "</div>");
} catch (e) {
responseText = xhr.responseText;
$("#dialog").html(responseText);
}
$("#dialog").dialog({
title: "jQuery Exception Details",
width: 700,
buttons: {
Close: function () {
$(this).dialog('close');
}
}
});
}--%>
