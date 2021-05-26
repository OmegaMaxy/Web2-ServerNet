<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
    <jsp:include page="../components/req/head.jsp" />
    <body>
        <jsp:include page="../components/ui/header_alt.jsp" />
        <main class="">
            <jsp:include page="../components/ui/success_messages.jsp" />
            <jsp:include page="../components/ui/error_messages.jsp" />
            <div class="card mb-2 p-3">
                <h1 class="card-header">Edit server</h1>
                <div class="card-body">
                    <form action="Controller?route=update&id=${server.getId()}" method="POST" novalidate>
                        <input type="hidden" class="form-control" name="serverid" id="serverid" value="${server.getId()}"/>
                        <div class="mb-3">
                            <label for="serverName" class="form-label">Server name</label>
                            <input type="text" class="form-control" name="serverName" id="serverName" value="${server.getName()}"/>
                        </div>
                        <div class="mb-3">
                            <label for="serverLocation" class="form-label">Server location</label>
                            <input type="text" class="form-control" name="serverLocation" id="serverLocation" value="${server.getLocation()}"/>
                        </div>
                        <div class="mb-3">
                            <label for="amountOfServices" class="form-label">Amount of services (max = 1000 services)</label>
                            <input type="number" class="form-control" name="amountOfServices" id="amountOfServices" value="${server.getAmountOfServices()}"/>
                        </div><div class="mb-3">
                        <label for="amountOfRAM" class="form-label">Amount of RAM in Megabytes (max = 64000mb)</label>
                        <input type="number" class="form-control" name="amountOfRAM" id="amountOfRAM" value="${server.getRamInMB()}"/>
                    </div>

                        <button type="submit" class="btn btn-primary">Save</button>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>

