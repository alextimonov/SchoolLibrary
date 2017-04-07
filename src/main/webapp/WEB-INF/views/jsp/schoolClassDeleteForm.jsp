<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>

<c:set var="NO_SCHOOLCLASS_IN_DB" value="-1"/>
<c:set var="FORBID_TO_DELETE" value="-2"/>

<html lang="${language}">
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Delete school class</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        var ctxPath = "<%=request.getContextPath() %>";
        $(document).ready(function() {
            $('#form').submit(function(e) {
                e.preventDefault();
                var id = $("#id").val();
                var formData = {"id": id, "course": $("#course").val(), "letter": $("#letter").val(),
                    "teacher": { "id": $("#teacher").val() } };
                $.ajax({
                    url: ctxPath + "/library/classes",
                    type: "DELETE",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    cache: false,
                    dataType: "json",
                    success: function(data, textStatus, jqXHR) {
                        alert("Class successfully deleted: #" + data.id + ", class " + data.course + "-" +
                                data.letter);
                        window.location = ctxPath + "/library/classes";
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if (jqXHR.status == 405) { // Method DELETE not allowed in JSP
                            alert("Class successfully deleted");
                            window.location = ctxPath + "/library/classes";
                        }
                        else {
                            alert(jqXHR.responseText);
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
        <h3><fmt:message key="class.delete.areYouSure"/></h3>
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
            <c:choose>
                <c:when test="${it.errorId == NO_SCHOOLCLASS_IN_DB}">
                    <h4>${it.errorMessage}</h4>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${it.errorId == FORBID_TO_DELETE}">
                            <h4>${it.errorMessage}</h4>
                            <form class="form-inline" action="/library/classes/${it.schoolClass.id}" method="GET">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-triangle-right"></span> <fmt:message key="link.class"/></button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form id="form" class="form-horizontal" action="/library/classes">
                                <c:set var="schoolClass" value="${it.schoolClass}"/>
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label class="control-label"><fmt:message key="allPages.id"/>:</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="control-label">${schoolClass.id}</label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label class="control-label"><fmt:message key="class.course"/>:</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="control-label">${schoolClass.course}</label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label class="control-label"><fmt:message key="class.letter"/>:</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="control-label">${schoolClass.letter}</label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label class="control-label"><fmt:message key="class.teacher"/>:</label>
                                    </div>
                                    <div class="col-sm-9">
                                        <label class="control-label">${schoolClass.teacher.position}
                                                ${schoolClass.teacher.name} ${schoolClass.teacher.surname}</label>
                                    </div>
                                </div>

                                <button id="submit" class="btn btn-primary" type="submit" autofocus>
                                    <span class="glyphicon glyphicon-trash"></span> <fmt:message key="class.delete"/>
                                </button>
                            </form>
                            <br>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
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