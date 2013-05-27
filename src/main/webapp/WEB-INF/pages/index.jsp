<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Computer database</title>
</head>
<body>
<div class="container">

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
</div>
</body>
</html>