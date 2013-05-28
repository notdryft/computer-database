<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="helpers" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Computers database</title>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/stylesheets/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/stylesheets/main.css"/>">
</head>
<body>

<header class="topbar">
    <h1 class="fill">
        <a href="<c:url value="/computers"/>">
            Jdk sample application &mdash; Computer database
        </a>
    </h1>
</header>

<section id="main">

    <h1>Add a computer</h1>

    <form action="<c:url value="/computers"/>" method="POST">

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
