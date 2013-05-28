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

    <form target="<c:url value="/computers/save"/>" method="POST">

        <fieldset>
            <helpers:inputText name="name" label="Computer name" hasErrors="${false}" help="${null}"/>
            <helpers:inputText name="introduced" label="Introduced date" hasErrors="${false}" help="${null}"/>
            <helpers:inputText name="discontinued" label="Discontinued date" hasErrors="${false}" help="${null}"/>

            <helpers:select name="company" label="Company" defaultValue="-- Choose a company --"
                            companies="${companies}" hasErrors="${false}" help="${null}"/>
        </fieldset>

        <div class="actions">
            <input type="submit" value="Create this computer" class="btn primary"> or
            <a href="<c:url value="/computers"/>" class="btn">Cancel</a>
        </div>
    </form>
</section>

</body>
</html>
