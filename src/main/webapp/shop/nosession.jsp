<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../components/req/head.jsp" />
<body>
<jsp:include page="../components/ui/header_shop.jsp" />
<main>
    <jsp:include page="../components/ui/success_messages.jsp" />
    <jsp:include page="../components/ui/error_messages.jsp" />
    <article class="card mb-4">
        <h1 class="card-header">Shop</h1>
        <div class="card-body">
            <h1 class="card-title">No session</h1>
            <p class="card-text">Please visit the <b>shop</b> to refresh your session.</p>
            <a href="Controller?route=shop" class="btn btn-primary">Shop</a>
        </div>
    </article>
</main>
</body>
</html>