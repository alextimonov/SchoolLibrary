<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>

<c:set var="NO_ITEM_IN_DB" value="-1"/>
<c:set var="FORBID_TO_DELETE" value="-2"/>

<html lang="${language}">
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Delete schoolbook</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        var ctxPath = "<%=request.getContextPath() %>";
        $(document).ready(function() {
            $('#form').submit(function(e) {
                e.preventDefault();
                var id = $("#id").val();
                var formData = {"id": id, "name": $("#name").val(), "course": $("#course").val(),
                    "amountTotal": $("#amount").val(), "librarian": { "id": $("#librarian").val() } };
                $.ajax({
                    url: ctxPath + "/library/books",
                    type: "DELETE",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    cache: false,
                    dataType: "json",
                    success: function(data, textStatus, jqXHR) {
                        alert("Schoolbook successfully deleted: #" + data.id + " " + data.name + " for " + data.course +
                                " course, amount = " + data.amountTotal);
                        window.location = ctxPath + "/library/books";
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if (jqXHR.status == 405) { // Method DELETE not allowed in JSP
                            alert("Schoolbook's data successfully deleted");
                            window.location = ctxPath + "/library/books";
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
        <h3><fmt:message key="allPages.header"/></h3>
        <h3><fmt:message key="schoolbook.deleting"/></h3>
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
                <c:when test="${it.errorId == NO_ITEM_IN_DB}">
                    <h4>${it.errorMessage}</h4>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${it.errorId == FORBID_TO_DELETE}">
                            <h4>${it.errorMessage}</h4>
                            <form class="form-inline" action="/library/books/${it.schoolbook.id}" method="GET">
                                <button class="btn btn-primary" type="submit">
                                    <span class="glyphicon glyphicon-triangle-right"></span> <fmt:message key="link.book"/></button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <c:set var="book" value="${it.schoolbook}"/>
                            <form id="form" class="form-horizontal" action="/library/books">
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label class="control-label"><fmt:message key="allPages.id"/>:</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="control-label">${book.id}</label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label class="control-label"><fmt:message key="schoolbook.name"/>:</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="control-label">${book.name}</label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label class="control-label"><fmt:message key="schoolbook.courseOfLearning"/>:</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="control-label">${book.course}</label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label class="control-label"><fmt:message key="schoolbook.author"/>:</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="control-label">${book.author}</label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label class="control-label"><fmt:message key="schoolbook.publisher"/>:</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="control-label">${book.publisher}</label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label class="control-label"><fmt:message key="schoolbook.amount"/>:</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="control-label">${book.amountTotal}</label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <label class="control-label"><fmt:message key="schoolbook.librarian"/>:</label>
                                    </div>
                                    <div class="col-sm-9">
                                        <label class="control-label">${book.librarian.position}
                                            ${book.librarian.name} ${book.librarian.surname}</label>
                                    </div>
                                </div>

                                <button id="submit" class="btn btn-primary" type="submit" autofocus>
                                    <span class="glyphicon glyphicon-trash"></span> <fmt:message key="schoolbook.delete"/>
                                </button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
            <br>
            <form class="form-inline" action="/library/books" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span> <fmt:message key="link.back.books"/></button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</div>
</body>
</html>