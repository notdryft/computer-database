<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="date" type="java.util.Date" required="true" %>

<c:choose>
    <c:when test="${date == null}">
        <em>-</em>
    </c:when>
    <c:otherwise>
        <fmt:formatDate value="${date}" pattern="dd MMM yyyy"/>
    </c:otherwise>
</c:choose>
