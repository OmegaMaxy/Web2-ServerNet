<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sorry for the inconvenience!</title>
    <link rel="stylesheet" href="../assets/css/master.css">
</head>
<body>
<jsp:include page="../components/ui/header.jsp" />
<main class="">
    <article class="card mb-4">
        <h1 class="card-header">Sorry for the inconvenience!</h1>
        <div class="card-body">
            <p class="card-text">Something went terribly wrong.</p>
            <a href="Controller?route=home" class="btn btn-primary">Home</a>
        </div>
    </article>
</main>
</body>
</html>

