<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty successMessages}">
    <section class="successMessages">
        <c:forEach var="message" items="${successMessages}">
            <p>${message}</p>
        </c:forEach>
    </section>
</c:if>