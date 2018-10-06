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

    <div class="jumbotron">
        <h1 class="display-3">Log In!</h1>
        <hr class="my-4">
    </div>

    <div class="row justify-content-md-center">
        <div class="col-lg-5">
            <div class="bs-component">
                <form action="login" method="post">
                    <fieldset>
                        <legend>LogIn</legend>
                        <div class="form-group">
                            <label for="status">Status</label>
                            <select type="text" class="form-control" name="status" id="status">
                                <option value="IN">Intern</option>
                                <option value="TE">Teacher</option>
                                <option value="AD">Admin</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" name="username" id="username" placeholder="Enter your username">
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
</div>
</body>
</html>