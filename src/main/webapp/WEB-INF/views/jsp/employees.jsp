<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>

<h2>Employees</h2>
<h3>${pageContext.request.contextPath}</h3>
<h3>Welcome!</h3>
<p>
    Employees:<br />
    <c:forEach var="item" items="${it}">
        ${item}<br />
    </c:forEach>
</p>

</body>
</html>
