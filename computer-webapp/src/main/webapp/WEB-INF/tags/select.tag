<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="helpers" tagdir="/WEB-INF/tags" %>

<%@ attribute name="element" type="com.formation.projet.core.forms.FormElement" required="true" %>
<%@ attribute name="label" type="java.lang.String" required="true" %>
<%@ attribute name="defaultValue" type="java.lang.String" required="true" %>
<%@ attribute name="companies" type="java.util.List" required="true" %>

<c:set var="input">
    <select id="${element.name}" name="${element.name}">
        <option class="blank" value="">${defaultValue}</option>
        <c:forEach var="company" items="${companies}">
            <c:choose>
                <c:when test="${company.id.toString().equals(element.value)}">
                    <option value="${company.id}" selected>${company.name}</option>
                </c:when>
                <c:otherwise>
                    <option value="${company.id}">${company.name}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
</c:set>

<helpers:twitterBootstrapInput element="${element}" label="${label}" input="${input}"/>
