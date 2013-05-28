<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="helper" uri="http://projet.formation.com/jsp/helpers" %>
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
            ${helper:inputText("name", "Computer name", false, null)}
            ${helper:inputText("introduced", "Introduced date", false, null)}
            ${helper:inputText("discontinued", "Discontinued date", false, null)}

            ${helper:select("company","Company","-- Choose a company --", companies,false,null)}
        </fieldset>

        <div class="actions">
            <input type="submit" value="Create this computer" class="btn primary"> or
            <a href="<c:url value="/computers"/>" class="btn">Cancel</a>
        </div>
    </form>
</section>

</body>
</html>