<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="functions" uri="http://projet.formation.com/jsp/functions" %>

<%@ attribute name="title" type="java.lang.String" required="true" %>
<%@ attribute name="state" type="com.formation.projet.business.beans.PageState" required="true" %>
<%@ attribute name="orderBy" type="java.lang.Integer" required="true" %>

<c:if test="${functions:abs(state.sortColumn) == orderBy}">
    <c:set var="orderClass" value="${(state.sortColumn < 0) ? 'headerSortDown' : 'headerSortUp'}"/>
</c:if>

<th class="col${orderBy} header ${orderClass}">
    <c:set var="sortValue"
           value="${(functions:abs(state.sortColumn) == orderBy) ? -state.sortColumn : orderBy}"/>
    <a href="<c:url value="/computers${functions:linkSort(sortValue, state.filter)}"/>">${title}</a>
</th>
