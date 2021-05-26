<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>ServerNET</title>
    <link rel="icon" href="../../assets/img/OmegaUnaIcon.png">
    <link rel="stylesheet" href="../../assets/css/master.css">
    <c:if test="${!param.noCookiesOnThisPage}">
        <c:if test="${cookie.darkmode_option != null}">


            <c:if test="${cookie.previousRoute.value != currentRoute && currentRoute == 'darkmode' && previousRoute != 'darkmode'}">
                <script>
                    alert("Changing window to: ${cookie.previousRoute.value}");
                    window.location.assign("Controller?route=${cookie.previousRoute.value}");
                </script>
            </c:if>
            <c:if test="${currentRoute == 'darkmode' && previousRoute == 'darkmode'}">
                <script>
                    window.location.assign("Controller?route=welcome");
                </script>
            </c:if>

            <c:if test="${cookie.darkmode_option.value == 'dark'}">
                <script>
                    function docReady(fn) {
                        // see if DOM is already available
                        if (document.readyState === "complete" || document.readyState === "interactive") {
                            // call on next available tick
                            setTimeout(fn, 1);
                        } else {
                            document.addEventListener("DOMContentLoaded", fn);
                        }
                    }
                    docReady(function() {
                        document.getElementsByTagName("body")[0].classList.add("darkmode");
                    });

                </script>
            </c:if>
        </c:if>
    </c:if>
</head>