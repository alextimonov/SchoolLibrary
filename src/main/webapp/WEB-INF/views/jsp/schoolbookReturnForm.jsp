<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>
<c:set var="FORBID_TO_RETURN" value="-2"/>

<html lang="${language}">
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>School library. Return schoolbooks</title>
</head>
<body>
<header>
    <h2>School library Web application</h2>
    <h3>Return ${it.amountToCollect} schoolbooks "${it.bookInClass.schoolbook.name}" from
        ${it.bookInClass.schoolClassCourse}-${it.bookInClass.schoolClassLetter} class to library</h3>

    <h3><fmt:message key="allPages.header"/></h3>
    <h3><fmt:message key="allPages.return"/> ${it.amountToHandout} <fmt:message key="schoolbook.plural"/>
        "${it.bookInClass.schoolbook.name}" <fmt:message key="allPages.from"/>
        ${it.bookInClass.schoolClassCourse}-${it.bookInClass.schoolClassLetter} <fmt:message key="allPages.class"/>
    </h3>
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
            <c:when test="${it.errorId == FORBID_TO_RETURN}">
                <h4>${it.errorMessage}</h4>
                <form class="form-inline" action="/library/books/returnForm" method="GET">
                    <input name="bookId" value="${it.bookInClass.schoolbook.id}" type="hidden"/>
                    <input name="classId" value="${it.bookInClass.schoolClassId}" type="hidden"/>
                    <input name="booksAmount" value="${it.currentAmount}" type="hidden"/>
                    <button class="btn btn-primary" type="submit" autofocus>
                        <span class="glyphicon glyphicon-triangle-right"></span> <fmt:message key="schoolbook.returnBooks"/></button>
                </form>
            </c:when>
            <c:otherwise>
                <h4><fmt:message key="message.successReturn"/></h4>
                <h4>${it.bookInClass.schoolClassCourse}-${it.bookInClass.schoolClassLetter} <fmt:message key="message.classHasReturned"/>
                        ${it.amountToReturn} <fmt:message key="schoolbook.plural"/> "${it.bookInClass.schoolbook.name}".</h4>
                <h4><fmt:message key="message.nowThereAre"/> ${it.bookInClass.booksNumber} <fmt:message key="message.booksInClass"/>.</h4>
            </c:otherwise>
        </c:choose>
        <br>
        <form class="form-inline" action="/library/books/${it.bookInClass.schoolbook.id}" method="GET">
            <button class="btn btn-primary" type="submit">
                <span class="glyphicon glyphicon-triangle-left"></span> <fmt:message key="link.back.bookDetail"/></button>
        </form>
        <br>
        <form class="form-inline" action="/library/classes/${it.bookInClass.schoolClassId}" method="GET">
            <button class="btn btn-primary" type="submit">
                <span class="glyphicon glyphicon-triangle-left"></span> <fmt:message key="link.back.classDetail"/></button>
        </form>
    </div>
</article>

<footer>Copyright &copy; Alexey Timonov</footer>

</body>
</html>