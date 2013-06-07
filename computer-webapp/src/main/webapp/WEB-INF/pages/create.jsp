<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"/>
<body>

<jsp:include page="topbar.jsp"/>

<section id="main">

    <h1>Add a computer</h1>

    <form:form action="${pageContext.request.contextPath}/computers/save" method="POST" commandName="computer">

        <fieldset>
            <!-- computer.name -->
            <div class="clearfix <c:if test="${not empty result.getFieldError('name')}">error</c:if>">
                <label for="name">Computer name</label>

                <div class="input">
                    <form:input type="text" id="name" name="name" path="name" value="${computer.name}"/>
                    <span class="help-inline">Required</span>
                </div>
            </div>

            <!-- computer.introduced -->
            <div class="clearfix <c:if test="${not empty result.getFieldError('introduced')}">error</c:if>">
                <label for="introduced">Introduced date</label>

                <div class="input">
                    <form:input type="text" id="introduced" name="introduced" path="introduced"/>
                    <span class="help-inline">Date ('yyyy-MM-dd')</span>
                </div>
            </div>

            <!-- computer.discontinued -->
            <div class="clearfix <c:if test="${not empty result.getFieldError('discontinued')}">error</c:if>">
                <label for="discontinued">Discontinued date</label>

                <div class="input">
                    <form:input type="text" id="discontinued" name="discontinued" path="discontinued"/>
                    <span class="help-inline">Date ('yyyy-MM-dd')</span>
                </div>
            </div>

            <!-- computer.company -->
            <div class="clearfix <c:if test="${not empty result.getFieldError('company')}">error</c:if>">
                <label for="company">Company</label>

                <div class="input">
                    <form:select id="company" name="company" path="company">
                        <form:option class="blank" value="">-- Choose a company --</form:option>
                        <c:forEach var="company" items="${companies}">
                            <c:choose>
                                <c:when test="${company.id.equals(computer.company.id)}">
                                    <form:option value="${company.id}" selected="selected">${company.name}</form:option>
                                </c:when>
                                <c:otherwise>
                                    <form:option value="${company.id}">${company.name}</form:option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>

                    <span class="help-inline"></span>
                </div>
            </div>
        </fieldset>

        <div class="actions">
            <input type="submit" value="Create this computer" class="btn primary"> or
            <a href="<c:url value="/computers"/>" class="btn">Cancel</a>
        </div>
    </form:form>
</section>

</body>
</html>
