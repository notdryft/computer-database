<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"/>
<body>

<jsp:include page="topbar.jsp"/>

<section id="main">

    <h1>Add a computer</h1>

    <form action="<c:url value="/computers/save"/>" method="POST">

        <fieldset>
            <helpers:inputText element="${form.name}" label="Computer name"/>
            <helpers:inputText element="${form.introduced}" label="Introduced date"/>
            <helpers:inputText element="${form.discontinued}" label="Discontinued date"/>

            <helpers:select element="${form.company}" label="Company"
                            defaultValue="-- Choose a company --"
                            companies="${companies}"/>
        </fieldset>

        <div class="actions">
            <input type="submit" value="Create this computer" class="btn primary"> or
            <a href="<c:url value="/computers"/>" class="btn">Cancel</a>
        </div>
    </form>
</section>

</body>
</html>
