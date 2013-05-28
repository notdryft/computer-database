<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="functions" uri="http://projet.formation.com/jsp/functions" %>

<%@ attribute name="name" type="java.lang.String" required="true" %>
<%@ attribute name="label" type="java.lang.String" required="true" %>
<%@ attribute name="input" type="java.lang.String" required="true" %>
<%@ attribute name="hasErrors" type="java.lang.Boolean" required="true" %>
<%@ attribute name="help" type="java.util.List" required="true" %>

<c:if test="${hasErrors}">
    <c:set var="errorClass" value="error"/>
</c:if>

<div class="clearfix ${errorClass}">
    <label for="${name}">${label}</label>

    <div class="input">
        ${input}
        <span class="help-inline">${functions:join(help, ', ')}</span>
    </div>
</div>
