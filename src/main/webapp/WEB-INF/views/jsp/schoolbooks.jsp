<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>School library. Schoolbooks</title>
</head>
<body>

<h2>Schoolbooks</h2>
<h3>${pageContext.request.contextPath}</h3>
<h3>${it.message}</h3>
<p>
    <c:forEach var="schoolbook" items="${it.schoolbooks}">
        ${schoolbook}<br />
    </c:forEach>
</p>

</body>
</html>
