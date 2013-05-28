<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="helpers" tagdir="/WEB-INF/tags" %>

<%@ attribute name="name" type="java.lang.String" required="true" %>
<%@ attribute name="label" type="java.lang.String" required="true" %>
<%@ attribute name="defaultValue" type="java.lang.String" required="true" %>
<%@ attribute name="companies" type="java.util.List" required="true" %>
<%@ attribute name="hasErrors" type="java.lang.Boolean" required="true" %>
<%@ attribute name="help" type="java.util.List" required="true" %>

<c:set var="input">
    <select id="${name}" name="${name}">
        <option class="blank" value="">${defaultValue}</option>
        <c:forEach var="company" items="${companies}">
            <option value="${company.id}">${company.name}</option>
        </c:forEach>
    </select>
</c:set>

<helpers:twitterBootstrapInput name="${name}"
                               label="${label}" input="${input}"
                               hasErrors="${hasErrors}" help="${help}"/>