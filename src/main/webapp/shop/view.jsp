<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<jsp:include page="../components/req/head.jsp" />
<body>
<jsp:include page="../components/ui/header_shop.jsp" />
<main class="">
    <jsp:include page="../components/ui/success_messages.jsp" />
    <jsp:include page="../components/ui/error_messages.jsp" />
    <article>
        <h1 class="card-header">View server</h1>
    </article>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>#</th>
            <th>Server</th>
            <th>Location</th>
            <th># of Services</th>
            <th>RAM usage</th>
            <th>Actions</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td> Nr. ${server.getId()} </td>
            <td> ${server.getName()}</td>
            <td> ${server.getLocation()}</td>
            <td> ${server.getAmountOfServices()} Services</td>
            <td> ${server.getRamInMB()}MB</td>
            <td><a href="Controller?route=shop-addToBasket&id=${server.getId()}" class="btn btn-primary">Add to basket</a></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </tfoot>
    </table>
</main>
</body>
</html>