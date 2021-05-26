<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="components/req/head.jsp" />
    <body>
        <jsp:include page="components/ui/header.jsp" />
        <main class="">
            <jsp:include page="components/ui/success_messages.jsp" />
            <jsp:include page="components/ui/error_messages.jsp" />
            <article class="card mb-4">
                <h1 class="card-header">Explore</h1>
                <div class="card-body">
                    <form action="Controller" method="GET" novalidate>
                        <label class="card-title" for="searchbar">Search servers</label>
                        <input type="hidden" name="route" value="search"/>
                        <input type="hidden" name="is_searching" value="1"/>
                        <input type="search" name="query" id="searchbar" class="form-control mb-3" placeholder="Search server by name, location, # services, and RAM"/>
                        <button class="btn btn-primary" type="submit">Search</button>
                    </form>
                    <c:if test='${cookie.latest_query != null && cookie.latest_query.value.length() > 0}'>
                        <section>
                            <p>Your latest search: "${cookie.latest_query.value}"</p>
                        </section>
                    </c:if>
                </div>
            </article>
            <c:if test='${results != null}'>
                <c:choose>
                    <c:when test="${not empty results}">
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
                                <c:forEach var="server" items="${results}">
                                    <tr>
                                        <td> #${server.getId()} </td>
                                        <td> <a href="Controller?route=view&id=${server.getId()}" class="link-text">${server.getName()}</a></td>
                                        <td> ${server.getLocation()} </td>
                                        <td> ${server.getAmountOfServices()} Services</td>
                                        <td> ${server.getRamInMB()}MB</td>
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
                    </c:when>
                    <c:otherwise>
                        <p>Query returned no results.</p>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </main>
    </body>
</html>

