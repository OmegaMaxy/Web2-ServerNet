<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Item not found!</title>
        <link rel="stylesheet" href="../assets/css/master.css">
    </head>
    <body>
        <jsp:include page="../components/ui/header.jsp" />
        <main class="">
            <article class="card mb-4">
                <h1 class="card-header">Item not found!</h1>
                <div class="card-body">
                    <p class="card-text">Sorry! Are you sure this item exists?</p>
                    <a href="#" class="btn btn-primary" onclick="window.history.back()">Home</a>
                </div>
            </article>
        </main>
    </body>
</html>

