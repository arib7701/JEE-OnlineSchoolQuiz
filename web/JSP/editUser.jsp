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

    <div class="jumbotron">
        <h1 class="display-3">Make Changes!</h1>
        <p class="lead">Update your information!</p>
        <hr class="my-4">
    </div>

    <div class="row">
        <div class="col-lg-10">
            <div class="bs-component">
                <form action="editUser" method="post">
                    <input type="text" hidden name="id" value="${requestScope.user.getId()}">
                    <c:if test="${requestScope.status == 'intern'}">
                        <input type="text" hidden name="status" value="IN">
                    </c:if>
                    <fieldset>
                        <legend>Edit Info</legend>
                        <c:if test="${requestScope.status != 'intern'}">
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
                            <input type="text" class="form-control" id="firstname" name="firstname" value="${requestScope.user.getFirstname()}">
                        </div>
                        <div class="form-group">
                            <label for="lastname">Lasttname</label>
                            <input type="text" class="form-control" id="lastname" name="lastname" value="${requestScope.user.getLastname()}">
                        </div>
                        <div class="form-group">
                            <label for="email">Email address</label>
                            <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" value="${requestScope.user.getEmail()}">
                            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                        </div>
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" name="username" id="username" aria-describedby="usernameHelp" value="${requestScope.user.getUsername()}">
                            <small id="usernameHelp" class="form-text text-muted">It must be unique.</small>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" disabled class="form-control" name="password" id="password" value="${requestScope.user.getPassword()}">
                        </div>
                        <button type="submit" name="saveEdits" class="btn btn-primary">Submit</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>