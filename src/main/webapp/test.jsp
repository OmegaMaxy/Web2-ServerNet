<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
    <c:set var="TestVar" scope="session" value='${request.getAttribute("test")}'/>
    ${test_str}
</body>
</html>
