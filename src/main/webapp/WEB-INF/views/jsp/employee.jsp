<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>School library</title>
</head>
<body>
<h2>Employee by ID:</h2>
<h3>${path}</h3>
<h3>${it.message}</h3>
<p>${it.employee}</p>

</body>
</html>
