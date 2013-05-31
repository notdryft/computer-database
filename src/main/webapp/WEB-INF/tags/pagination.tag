<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="functions" uri="http://projet.formation.com/jsp/functions" %>

<%@ attribute name="state" type="com.formation.projet.business.beans.PageState" required="true" %>
<%@ attribute name="nbItems" type="java.lang.Integer" required="true" %>

<div id="pagination" class="pagination">
    <ul>
        <c:choose>
        <c:when test="${state.page <= 0}">
        <li class="prev disabled">
            <a>&larr; Previous</a>
        </li>
        </c:when>
        <c:otherwise>
        <li class="prev">
            <a href="<c:url value="/computers${functions:linkAttributes(state.page - 1, state.sortColumn, state.filter)}"/>">&larr;
                Previous</a>
        </li>
        </c:otherwise>
        </c:choose>
        <li class="current">
            <a>Displaying ${state.offset + 1} to ${state.offset + nbItems} of ${state.total}</a>
        </li>
        <c:choose>
        <c:when test="${state.page >= state.maxPages}">
        <li class="next disabled">
            <a>Next &rarr;</a>
        </li>
        </c:when>
        <c:otherwise>
        <li class="next">
            <a href="<c:url value="/computers${functions:linkAttributes(state.page + 1, state.sortColumn, state.filter)}"/>">Next &rarr;</a>
        </li>
        </c:otherwise>
        </c:choose>
</div>
