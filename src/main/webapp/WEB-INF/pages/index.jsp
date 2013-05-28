<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="functions" uri="http://projet.formation.com/jsp/functions" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="helpers" %>
<!DOCTYPE html>
<html>
<head>
    <title>Computers database</title>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/stylesheets/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/stylesheets/main.css"/>">
</head>
<body>

<header class="topbar">
    <h1 class="fill">
        <a href="<c:url value="/computers"/>">
            Jdk sample application &mdash; Computer database
        </a>
    </h1>
</header>

<section id="main">

    <h1>${functions:title(total)}</h1>

    <%--@flash.get("success").map { message =>--%>
    <%--<div class="alert-message warning">--%>
    <%--<strong>Done!</strong> @message--%>
    <%--</div>--%>
    <%--}--%>

    <div id="actions">

        <form target="<c:url value="/computers"/>">
            <input type="search" id="searchbox" name="f" value="${filter}" placeholder="Filter by computer name...">
            <input type="submit" id="searchsubmit" value="Filter by name" class="btn primary">
        </form>

        <a class="btn success" id="add" href="<c:url value="/computers/new"/>">Add a new computer</a>
    </div>

    <c:choose>
        <c:when test="${not empty computers}">

            <table class="computers zebra-striped">
                <thead>
                <tr>
                    <helpers:header title="Computer name" currentOrderBy="${sortColumn}" orderBy="2"
                                    filter="${filter}"/>
                    <helpers:header title="Introduced" currentOrderBy="${sortColumn}" orderBy="3" filter="${filter}"/>
                    <helpers:header title="Discontinued" currentOrderBy="${sortColumn}" orderBy="4" filter="${filter}"/>
                    <helpers:header title="Company" currentOrderBy="${sortColumn}" orderBy="5" filter="${filter}"/>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="computer" items="${computers}">
                    <tr>
                        <td><a href="#">${computer.name}</a></td>
                            <%--<td><a href="@routes.Application.edit(computer.id.get)">@computer.name</a></td>--%>
                        <td>${functions:format(computer.introduced)}</td>
                        <td>${functions:format(computer.discontinued)}</td>
                        <td>${functions:formatCompany(computer.company)}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div id="pagination" class="pagination">
                <ul>
                    <c:choose>
                    <c:when test="${page <= 0}">
                    <li class="prev disabled">
                        <a>&larr; Previous</a>
                    </li>
                    </c:when>
                    <c:otherwise>
                    <li class="prev">
                        <a href="<c:url value="/computers?p=${page - 1}&s=${sortColumn}&f=${filter}"/>">&larr;
                            Previous</a>
                    </li>
                    </c:otherwise>
                    </c:choose>
                    <li class="current">
                        <a>Displaying ${offset + 1} to ${offset + computers.size()} of ${total}</a>
                    </li>
                    <c:choose>
                    <c:when test="${page >= maxPages}">
                    <li class="next disabled">
                        <a>Next &rarr;</a>
                    </li>
                    </c:when>
                    <c:otherwise>
                    <li class="next">
                        <a href="<c:url value="/computers?p=${page + 1}&s=${sortColumn}&f=${filter}"/>">Next &rarr;</a>
                    </li>
                    </c:otherwise>
                    </c:choose>
            </div>
        </c:when>
        <c:otherwise>
            <div class="well">
                <em>Nothing to display</em>
            </div>
        </c:otherwise>
    </c:choose>
</section>

</body>
</html>