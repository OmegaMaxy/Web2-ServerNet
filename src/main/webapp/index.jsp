<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
    <jsp:include page="components/req/head.jsp" />
    <body>
        <jsp:include page="components/ui/header.jsp" />
        <main>
            <jsp:include page="components/ui/success_messages.jsp" />
            <jsp:include page="components/ui/error_messages.jsp" />
            <article class="card mb-4">
                <h1 class="card-header">ServerNet</h1>
                <div class="card-body">
                    <h1 class="card-title">By OmegaUna</h1>
                    <p class="card-text">Initially started from a school assignment, but quickly grew to something bigger; ServerNet.
                        That's why we want to help you in making the right choice.</p>
                    <p class="card-text">This site offers you information about servers.
                        To make sure you choose your ideal server.</p>
                    <a href="Controller?route=overview" class="btn btn-primary">Manage servers</a>
                </div>
            </article>
            <div class="card mb-4">
                <h1 class="card-header" style="font-size: unset;">RAM Usage</h1>
                <p class="card-title" style="color: green;padding: .5rem 1rem;">
                    Ram usage over the ${db.getServers().size()}
                    available servers is ${Math.round(db.getMeanMBOverServers())} MB</p>
            </div>
        </main>
    </body>
</html>