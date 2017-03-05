<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>School library. Schoolbook</title>
</head>
<body>
<h2>Schoolbook by ID:</h2>
<h3>${path}</h3>
<h3>${it.message}</h3>
<p>${it.schoolbook}</p>

</body>
</html>
