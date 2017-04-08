<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<c:set var="NO_SCHOOLCLASS_IN_DB" value="-1"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>

<html lang="${language}">
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Edit class</title>
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
                    url: ctxPath + "/library/classes/" + id,
                    type: "PUT",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    cache: false,
                    dataType: "json"
                })
                        .done(function(data, textStatus, jqXHR) {
                            alert("Class's data successfully changed: #" + data.id + ", class " + data.course + "-" +
                                    data.letter);
                            window.location = ctxPath + "/library/classes";
                        })
                        .fail(function(jqXHR, textStatus, errorThrown) {
                            if (jqXHR.status == 405) {  // Method PUT not allowed in JSP
                                alert("There is the same class already in school or teacher is already a curator of another class.");
                                window.location = ctxPath + "/library/classes";
                            }
                            else {
                                alert(jqXHR.responseText);
                            }
                        })
            });
        });
    </script>
</head>
<body>
<div class="container">
    <header>
        <h3><fmt:message key="allPages.header"/></h3>
        <h3><fmt:message key="class.editing"/></h3>
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
        <c:set var="schoolClass" value="${it.schoolClass}"/>
        <div class="container">
            <c:choose>
                <c:when test="${it.errorId == NO_SCHOOLCLASS_IN_DB}">
                    <%--<h3>There is no class with id = ${schoolClass.id} in database. You cannot edit it.</h3>--%>
                    <h3>${it.errorMessage}</h3>
                </c:when>
                <c:otherwise> <%--test="${schoolClass.course > 0}"--%>
                    <form id="form" class="form-horizontal" action="/library/classes">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label" for="id"><fmt:message key="allPages.id"/>:</label>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" id="id" name="id" value="${schoolClass.id}" disabled="true" type="text"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label" for="course"><fmt:message key="class.course"/>:</label>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" id="course" name="course" value="${schoolClass.course}" type="text" autofocus/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label" for="letter"><fmt:message key="class.letter"/>:</label>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" id="letter" name="letter" value="${schoolClass.letter}" type="text"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label" for="teacher"><fmt:message key="class.teacher"/>:</label>
                            </div>
                            <div class="col-sm-4">
                                <select id="teacher" name="teacher" class="form-control">
                                    <option disabled><fmt:message key="class.chooseTeachers"/>:</option>
                                    <c:forEach var="employee" items="${it.teachers}">
                                        <option <c:if test="${employee.id == schoolClass.teacher.id}">selected</c:if>
                                                value=${employee.id}>${employee.position} ${employee.name} ${employee.surname}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <button id="submit" class="btn btn-primary" type="submit">
                            <span class="glyphicon glyphicon-floppy-disk"></span> <fmt:message key="class.saveEdited"/>
                        </button>
                    </form>
                    <br>
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