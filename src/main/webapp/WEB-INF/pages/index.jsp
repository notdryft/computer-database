<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Introduced Date</th>
            <th>Discontinued Date</th>
            <th>Company Id</th>
        </tr>
        </thead>
        <c:forEach var="computer" items="${computers}">
            <tr>
                <td>${computer.id}</td>
                <td>${computer.name}</td>
                <td>${computer.introducedDate}</td>
                <td>${computer.discontinuedDate}</td>
                <td>${computer.companyId}</td>
            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>