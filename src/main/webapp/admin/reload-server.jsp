<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<jsp:include page="../components/req/head.jsp" />
<body>
<jsp:include page="../components/ui/header.jsp" />
<main>
    <article class="card mb-4">
        <h1 class="card-header">Admin options</h1>
        <div class="card-body">
            <c:if test="${status != null}">
                <p style="color: green;"> ${status} </p>
            </c:if>

            <p> <b>${amountOfServers}</b> Server instances were found.</p>
            <p> <b>${amountOfMBInUse}</b> MB in use.</p>
            <form action="Controller?route=admin-reload-server" method="POST">
                <input class="btn btn-primary" type="submit" name="command" value="reload-server">
            </form>
        </div>
    </article>
</main>
</body>
</html>