<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.school.beans.User" %>
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
   <div class="jumbotron">
        <h1 class="display-3">Welcome, <c:out value="${sessionScope.admin}"></c:out>!</h1>
        <p class="lead">Start learning and improving by taking quizzes!</p>
        <hr class="my-4">
    </div>

    <div class="row">
        <div class="col-lg-10">
            <div class="bs-component">
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>