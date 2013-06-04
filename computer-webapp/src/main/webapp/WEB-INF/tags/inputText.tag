<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="helpers" tagdir="/WEB-INF/tags" %>

<%@ attribute name="element" type="com.formation.projet.core.forms.FormElement" required="true" %>
<%@ attribute name="label" type="java.lang.String" required="true" %>

<c:set var="input">
    <input type="text" id="${element.name}" name="${element.name}" value="${element.value}">
</c:set>

<helpers:twitterBootstrapInput element="${element}" label="${label}" input="${input}"/>
