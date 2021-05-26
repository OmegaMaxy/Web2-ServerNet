<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error found!</title>
    </head>
    <!-- String scopeTitle = "Error found!"; -->

    <jsp:include page="../req/head.jsp" />
    <body>
        <jsp:include page="../ui/header.jsp" />
        <c:set scope="request" var="isErrorPage" value="true"/>
        <main class="">
            <h3>Sorry a problem has occured!</h3>

            <p>We found the following error:</p>
            <pre>${pageContext.exception}</pre>
            <a href="${pageContext.errorData.requestURI}">Try again or visit navigation above.</a>
        </main>
    </body>
</html>
