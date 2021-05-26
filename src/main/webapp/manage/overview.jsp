<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../components/req/head.jsp" />
    <body>
        <jsp:include page="../components/ui/header.jsp" />
        <main class="">
            <jsp:include page="../components/ui/success_messages.jsp" />
            <jsp:include page="../components/ui/error_messages.jsp" />
            <article class="card mb-4">
                <h1 class="card-header">Featured</h1>
                <div class="card-body">
                    <h1 class="card-title">Powerful servers</h1>
                    <p class="card-text">We <em>know</em> how busy you are. We <em>know</em> how important your server is for you.
                        That's why we want to help you in making the right choice.</p>
                    <p class="card-text">This site offers you information about servers.
                        To make sure you choose your ideal server.</p>
                    <a href="Controller?route=create" class="btn btn-primary">Add server</a>
                </div>
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
                    <c:forEach var="server" items="${servers}">
                        <tr>
                            <td> Nr. ${server.getId()} </td>
                            <td> <a href="Controller?route=view&id=${server.getId()}" class="link-text">${server.getName()}</a></td>
                            <td> ${server.getLocation() } </td>
                            <td> ${server.getAmountOfServices() } Services</td>
                            <td> ${server.getRamInMB() } MB</td>
                            <td><a href="Controller?route=edit&id=${server.getId()}" class="btn btn-primary">Edit</a></td>
                            <td><a href="Controller?route=delete&id=${server.getId()}" class="btn btn-danger">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td><a href="Controller?route=create" class="btn btn-primary">Add server</a></td>
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

