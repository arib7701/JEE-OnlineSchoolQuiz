<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mtag" uri="http://onlineschool.com/tags" %>
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
        <h1 class="display-3">Welcome, <c:out value="${sessionScope.teacher.getFirstname()}"></c:out>!</h1>
        <p class="lead">Monitor all our interns and their respective grades!</p>
        <hr class="my-4">
    </div>

    <div class="row justify-content-md-center">
        <div class="col-lg-1">
            <form action="newQuiz" method="post">
                <input type="text" name="newQuizBtn" hidden>
                <button type="submit" name="newQuizBtn" class="btn btn-success">Add New Quiz</button>
            </form>
        </div>
    </div>
    <br>
    <br>

    <mtag:getQuiz userId="${sessionScope.teacher.getId()}"/>
    <c:if test="${quizzes != null}">
        <h3>Quiz</h3>
        <br>
        <br>
        <div class="row justify-content-md-center">
            <div class="col-lg-8">
                <div class="bs-component">
                    <table class="table table-hover table-sm">
                        <thead>
                        <tr>
                            <th scope="col">Theme</th>
                            <th scope="col">Question</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${quizzes}" var="q" varStatus="loop">
                            <tr>
                                <td>${q.getTheme()}</td>
                                <td>${q.getNber_questions()}</td>
                                <td>
                                <form action="profileTeacher" method="post">
                                    <input hidden type="text" name="quizEdit" value="${q.getId()}">
                                    <button type="submit" name="quizEdit" class="btn btn-info btn-sm">Edit</button>
                                    <button type="submit" name="quizDelete" class="btn btn-danger btn-sm">Delete</button>
                                </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${quizzes == null}">
        <h3>Quiz</h3>
        <br>
        <br>
        <div class="row justify-content-md-center">
            <div class="col-lg-12">
                <div class="alert alert-dismissible alert-warning">
                    <strong>Heads up! </strong>${noquiz}
                </div>
            </div>
        </div>
    </c:if>

    <mtag:getIntern/>
    <c:if test="${interns != null}">
    <div class="row">
        <div class="col-lg-6">
            <div class="bs-component">
                <h4>School Interns</h4>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Firstname</th>
                        <th scope="col">Lastname</th>
                        <th scope="col">Email</th>
                        <th scope="col">Grades</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${interns}" var="i" varStatus="loop">
                        <tr>
                            <td>${i.getId()}</td>
                            <td>${i.getFirstname()}</td>
                            <td>${i.getLastname()}</td>
                            <td>${i.getEmail()}</td>
                            <td>
                                <form action="profileTeacher" method="post">
                                    <input hidden type="text" name="id" value="${i.getId()}">
                                    <button type="submit" name="id" class="btn btn-info">See</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </c:if>
        <div class="col-lg-5 offset-md-1">
            <div class="bs-component">
            <c:if test="${requestScope.grades != null}">
                <h3>Grades</h3>
                <div class="card border-info md-12" style="max-width: 30rem;">
                    <div class="card-header">Grades</div>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col>">Quiz</th>
                                <th scope="col">Grade</th>
                                <th scope="col">Date</th>
                                <th scope="col">Pass</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${grades}" var="g" varStatus="loop">
                                <tr>
                                    <td>${g.getQuiz_id()}</td>
                                    <td>${g.getGrade_value()}</td>
                                    <td>${g.getGrade_date()}</td>
                                    <td>
                                        <c:if test="${g.getGrade_value() > 50}">
                                            <span class="badge badge-pill badge-success">Success</span>
                                        </c:if>
                                        <c:if test="${g.getGrade_value() < 50}">
                                            <span class="badge badge-pill badge-danger">Danger</span>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${requestScope.nograde != null}">
                <h3>Grades</h3>
                <br>
                <br>
                <div class="row justify-content-md-center">
                    <div class="col-lg-12">
                        <div class="alert alert-dismissible alert-danger">
                            <strong>Heads up! </strong>${nograde}
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
</div>
</body>
</html>