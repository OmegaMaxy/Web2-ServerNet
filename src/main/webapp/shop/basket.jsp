<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../components/req/head.jsp" />
    <body>
        <jsp:include page="../components/ui/header_shop.jsp" />
        <main class="">
            <jsp:include page="../components/ui/success_messages.jsp" />
            <jsp:include page="../components/ui/error_messages.jsp" />
            <c:choose>
                <c:when test="${not empty sessionScope.basket}">
                    <table class="table table-dark table-striped">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Server</th>
                            <th>Location</th>
                            <th># of Services</th>
                            <th>RAM usage</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="server" items="${sessionScope.basket}">
                                <tr>
                                    <td title="Server number"> Nr. ${server.getId()} </td>
                                    <td> <a href="Controller?route=shop-view&id=${server.getId() }" class="link-text">${server.getName()}</a></td>
                                    <td> ${server.getLocation() } </td>
                                    <td> ${server.getAmountOfServices() } Services</td>
                                    <td> ${server.getRamInMB() } MB</td>
                                    <td><a href="Controller?route=shop-removeFromBasket&id=${server.getId()}" class="btn btn-danger">Remove</a></td>
                                </tr>
                            </c:forEach>
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
                </c:when>
                <c:otherwise>
                    <p>Your shopping cart is empty at the moment. Add some items <a href="Controller?route=shop">here.</a></p>
                </c:otherwise>
            </c:choose>
        </main>
    </body>
</html>

