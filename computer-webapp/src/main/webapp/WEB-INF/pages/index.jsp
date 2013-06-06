<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"/>
<body>

<jsp:include page="topbar.jsp"/>

<section id="main">

    <h1>${functions:title(state.total)}</h1>

    <c:if test="${not empty success}">
        <div class="alert-message warning">
            <strong>Done!</strong> ${success}
        </div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert-message error">
            <strong>Error!</strong> ${error}
        </div>
    </c:if>

    <div id="actions">

        <form target="<c:url value="/computers"/>">
            <input type="search" id="searchbox" name="f" value="${state.filter}"
                   placeholder="Filter by computer name...">
            <input type="submit" id="searchsubmit" value="Filter by name" class="btn primary">
        </form>

        <a class="btn success" id="add" href="<c:url value="/computers/create"/>">Add a new computer</a>
    </div>

    <c:choose>
        <c:when test="${not empty computers}">

            <table class="computers zebra-striped">
                <thead>
                <tr>
                    <helpers:header title="Computer name" state="${state}" orderBy="2"/>
                    <helpers:header title="Introduced" state="${state}" orderBy="3"/>
                    <helpers:header title="Discontinued" state="${state}" orderBy="4"/>
                    <helpers:header title="Company" state="${state}" orderBy="5"/>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="computer" items="${computers}">
                    <tr>
                        <td><a href="<c:url value="/computers/edit/${computer.id}"/>">${computer.name}</a></td>
                        <td><helpers:format date="${computer.introduced}"/></td>
                        <td><helpers:format date="${computer.discontinued}"/></td>
                        <td><helpers:formatCompany company="${computer.company}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <helpers:pagination state="${state}" nbItems="${computers.size()}"/>
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
