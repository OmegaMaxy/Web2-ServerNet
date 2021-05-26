<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
    <jsp:include page="../components/req/head.jsp" />
    <body>
        <jsp:include page="../components/ui/header.jsp" />
        <main class="">
            <jsp:include page="../components/ui/success_messages.jsp" />
            <jsp:include page="../components/ui/error_messages.jsp" />
            <article class="card mb-4">
                <h1 class="card-header">Deleting server: ${server_name}</h1>
                <div class="card-body">
                    <h1 class="card-title">Are you sure you want to delete this server?</h1>
                    <form action="Controller?route=deleteConfirm" method="POST" novalidate>
                        <input type="hidden" name="id" value="${serverid}">
                        <button class="btn btn-danger" type="submit">Yes, delete</button>
                    </form>
                </div>
            </article>
        </main>
    </body>
</html>