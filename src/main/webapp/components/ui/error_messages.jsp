<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty errorMessages}">
    <section class="errorMessages">
        <c:forEach var="message" items="${errorMessages}">
            <p>${message}</p>
        </c:forEach>
    </section>
</c:if>