<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="helper" uri="http://projet.formation.com/jsp/helpers" %>
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
    <%--@(currentPage: Page[(Computer, Option[Company])], currentOrderBy: Int, currentFilter: String)(implicit flash:--%>
    <%--play.api.mvc.Flash)--%>

    <%--@****************************************--%>
    <%--* Helper generating navigation links *--%>
    <%--****************************************@--%>
    <%--@link(newPage: Int, newOrderBy: Option[Int] = None) = @{--%>
    <%--routes.Application.list(newPage, newOrderBy.map { orderBy =>--%>
    <%--if(orderBy == scala.math.abs(currentOrderBy)) -currentOrderBy else orderBy--%>
    <%--}.getOrElse(currentOrderBy), currentFilter)--%>

    <%--}--%>


    <%--<h1>@Messages("computers.list.title", currentPage.total)</h1>--%>

    <%--@flash.get("success").map { message =>--%>
    <%--<div class="alert-message warning">--%>
    <%--<strong>Done!</strong> @message--%>
    <%--</div>--%>
    <%--}--%>

    <%--<div id="actions">--%>

    <%--@helper.form(action=routes.Application.list()) {--%>
    <%--<input type="search" id="searchbox" name="f" value="@currentFilter" placeholder="Filter by computer name...">--%>
    <%--<input type="submit" id="searchsubmit" value="Filter by name" class="btn primary">--%>
    <%--}--%>

    <%--<a class="btn success" id="add" href="@routes.Application.create()">Add a new computer</a>--%>
    <%--</div>--%>

    <c:choose>
        <c:when test="${not empty computers}">

            <table class="computers zebra-striped">
                <thead>
                <tr>
                        ${helper:header(2, 2, "Computer name")}
                        ${helper:header(2, 3, "Introduced")}
                        ${helper:header(2, 4, "Discontinued")}
                        ${helper:header(2, 5, "Company") }
                </tr>
                </thead>
                <tbody>
                <c:forEach var="computer" items="${computers}">
                    <tr>
                        <td><a href="#">${computer.name}</a></td>
                            <%--<td><a href="@routes.Application.edit(computer.id.get)">@computer.name</a></td>--%>
                        <td>${helper:format(computer.introduced)}</td>
                        <td>${helper:format(computer.discontinued)}</td>
                        <td>${helper:formatCompany(computer.company)}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <%--<div id="pagination" class="pagination">--%>
            <%--<ul>--%>
            <%--@currentPage.prev.map { page =>--%>
            <%--<li class="prev">--%>
            <%--<a href="@link(page)">&larr; Previous</a>--%>
            <%--</li>--%>
            <%--}.getOrElse {--%>
            <%--<li class="prev disabled">--%>
            <%--<a>&larr; Previous</a>--%>
            <%--</li>--%>
            <%--}--%>
            <%--<li class="current">--%>
            <%--<a>Displaying @(currentPage.offset + 1) to @(currentPage.offset + computers.size) of @currentPage.total</a>--%>
            <%--</li>--%>
            <%--@currentPage.next.map { page =>--%>
            <%--<li class="next">--%>
            <%--<a href="@link(page)">Next &rarr;</a>--%>
            <%--</li>--%>
            <%--}.getOrElse {--%>
            <%--<li class="next disabled">--%>
            <%--<a>Next &rarr;</a>--%>
            <%--</li>--%>
            <%--}--%>
            <%--</ul>--%>
            <%--</div>--%>
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