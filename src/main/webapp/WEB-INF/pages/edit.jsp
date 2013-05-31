<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"/>
<body>

<jsp:include page="topbar.jsp"/>

<section id="main">

    <h1>Edit computer</h1>

    <form action="<c:url value="/computers/update?id=${form.id}"/>" method="POST">

        <fieldset>
            <helpers:inputText element="${form.name}" label="Computer name"/>
            <helpers:inputText element="${form.introduced}" label="Introduced date"/>
            <helpers:inputText element="${form.discontinued}" label="Discontinued date"/>

            <helpers:select element="${form.company}" label="Company"
                            defaultValue="-- Choose a company --"
                            companies="${companies}"/>
        </fieldset>

        <div class="actions">
            <input type="submit" value="Save this computer" class="btn primary"> or
            <a href="<c:url value="/computers"/>" class="btn">Cancel</a>
        </div>
    </form>

    <form action="<c:url value="/computers/delete?id=${form.id}"/>" method="POST" class="topRight">
        <input type="submit" value="Delete this computer" class="btn danger">
    </form>
</section>

</body>
</html>
