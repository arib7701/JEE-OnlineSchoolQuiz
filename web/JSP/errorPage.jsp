<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Full Stack Dev Online School</title>
    <link rel="stylesheet" href="CSS/bootstrap.min.css">
    <link rel="stylesheet" href="CSS/styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<%@ include file="header.jsp"%>

<div class="container" id="main">

    <% String error = (String) request.getAttribute("error"); %>
    <div class="jumbotron">
        <h1 class="display-3">Sorry, an error occured!</h1>
        <p class="lead"><c:out value="${requestScope.error}" ></c:out> and please try again!</p>
        <hr class="my-4">
    </div>
</div>
</body>
</html>