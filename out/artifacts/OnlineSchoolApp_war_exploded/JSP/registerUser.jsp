<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

    <c:if test="${requestScope.pass == false}">
        <div class="jumbotron">
            <h1 class="display-3">Sorry, it's not for this time!</h1>
            <p class="lead">Study a bit more and retake the test anytime!</p>
            <hr class="my-4">
            <a href="getHome">Retake the Test</a>
        </div>
    </c:if>

    <c:if test="${requestScope.pass == true}">
        <div class="jumbotron">
            <h1 class="display-3">Congratulation!</h1>
            <p class="lead">Now you can join the school by registering below!</p>
            <hr class="my-4">
        </div>

        <div class="row">
            <div class="col-lg-10">
                <div class="bs-component">
                    <form action="registerUser" method="post">
                        <c:if test="${requestScope.status != 'notIntern'}">
                            <input type="text" hidden name="status" value="IN">
                        </c:if>
                        <fieldset>
                            <legend>Register</legend>
                            <c:if test="${requestScope.status == 'notIntern'}">
                            <div class="form-group">
                                <label for="status">Status</label>
                                <select class="form-control" type="text" id="status" name="status">
                                    <option value="TE">Teacher</option>
                                    <option value="AD">Admin</option>
                                </select>
                            </div>
                            </c:if>
                            <div class="form-group">
                                <label for="firstname">Firstname</label>
                                <input type="text" class="form-control" id="firstname" name="firstname" placeholder="Enter your firstname">
                            </div>
                            <div class="form-group">
                                <label for="lastname">Lasttname</label>
                                <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Enter your lastname">
                            </div>
                            <div class="form-group">
                                <label for="email">Email address</label>
                                <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Enter your email">
                                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                            </div>
                            <div class="form-group">
                                <label for="username">Username</label>
                                <input type="text" class="form-control" name="username" id="username" aria-describedby="usernameHelp" placeholder="Enter your username">
                                <small id="usernameHelp" class="form-text text-muted">It must be unique.</small>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </c:if>
    </div>
</div>
</body>
</html>