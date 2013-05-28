<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="functions" uri="http://projet.formation.com/jsp/functions" %>

<%@ attribute name="element" type="com.formation.projet.business.forms.FormElement" required="true" %>
<%@ attribute name="label" type="java.lang.String" required="true" %>
<%@ attribute name="input" type="java.lang.String" required="true" %>

<c:if test="${not element.valid}">
    <c:set var="errorClass" value="error"/>
</c:if>

<div class="clearfix ${errorClass}">
    <label for="${element.name}">${label}</label>

    <div class="input">
        ${input}
        <span class="help-inline">${functions:join(element.messages, ', ')}</span>
    </div>
</div>
