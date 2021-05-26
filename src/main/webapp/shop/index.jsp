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
    <article class="card mb-4">
        <h1 class="card-header">Shop</h1>
        <div class="card-body">
            <a class="card-text" href="Controller?route=shop-deleteBasket">Refresh session here.</a>
            <p class="card-text">We <em>know</em> how busy you are. We <em>know</em> how important your server is for you.
                That's why we want to help you in making the right choice.</p>
            <p class="card-text">Scroll through our favourite servers below!</p>
            <a href="Controller?route=shop-basket" class="btn btn-primary">Shopping basket</a>
        </div>
    </article>
    <hr/>
    <section>
        <h1>Available items:</h1>
        <c:forEach var="server" items="${servers}">
            <article class="card card-enhanced mb-4">
                <h1 class="card-header"><b>${server.getName()}</b></h1>
                <div class="card-body">
                    <p class="card-text">A server situated in <b>${server.getLocation()}</b>, occupying <b>${server.getAmountOfServices()} Services</b>. This server is currently using <b>${server.getRamInMB()} MB</b> in RAM</p>
                    <p class="card-text">The <b>'${server.getName()}'</b> server has a lot to offer, consider buying with the button below.</p>
                    <section>
                        <a href="Controller?route=shop-view&id=${server.getId()}" class="btn btn-primary">View item</a>

                        <c:choose>
                            <c:when test="${not empty sessionScope.basket}">
                                <c:set var="isInBasket" scope="request" value="false"/>
                                <c:forEach var="basketServer" items="${sessionScope.basket}">
                                    <c:if test="${server.getId() == basketServer.getId()}">
                                        <c:set var="isInBasket" scope="request" value="true"/>
                                    </c:if>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${isInBasket}">
                                        <a href="Controller?route=shop-addToBasket&id=${server.getId()}" class="btn btn-danger">Remove from basket</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="Controller?route=shop-addToBasket&id=${server.getId()}" class="btn btn-success">Add to basket</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <a href="Controller?route=shop-addToBasket&id=${server.getId()}" class="btn btn-success">Add to basket</a>
                            </c:otherwise>
                        </c:choose>
                    </section>
                </div>
            </article>
        </c:forEach>
    </section>

</main>
</body>
</html>

