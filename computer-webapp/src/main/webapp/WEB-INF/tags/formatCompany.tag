<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="company" type="com.formation.projet.business.beans.Company" required="true" %>

<c:choose>
    <c:when test="${company == null}">
        <em>-</em>
    </c:when>
    <c:otherwise>
        ${company.name}
    </c:otherwise>
</c:choose>
