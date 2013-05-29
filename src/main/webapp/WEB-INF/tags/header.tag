<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="functions" uri="http://projet.formation.com/jsp/functions" %>

<%@ attribute name="title" type="java.lang.String" required="true" %>
<%@ attribute name="currentOrderBy" type="java.lang.Integer" required="true" %>
<%@ attribute name="orderBy" type="java.lang.Integer" required="true" %>
<%@ attribute name="filter" type="java.lang.String" required="true" %>

<c:if test="${functions:abs(currentOrderBy) == orderBy}">
    <c:set var="orderClass" value="${(currentOrderBy < 0) ? 'headerSortDown' : 'headerSortUp'}"/>
</c:if>

<th class="col${orderBy} header ${orderClass}">
    <c:set var="sortValue"
           value="${(functions:abs(currentOrderBy) == orderBy) ? -currentOrderBy : orderBy}"/>
    <a href="<c:url value="/computers${functions:linkSort(sortValue, filter)}"/>">${title}</a>
</th>
