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
        <h1 class="display-3">Welcome, <c:out value="${sessionScope.admin.getFirstname()}"></c:out>!</h1>
        <p class="lead">Manage interns, teachers, quizzes and questions.</p>
        <hr class="my-4">
    </div>

    <div class="row justify-content-md-center">
        <div class="col-lg-1">
            <form action="profileAdmin" method="post">
                <input type="text" name="newUser" hidden>
                <button type="submit" name="newUser" class="btn btn-success">Add New User</button>
            </form>
        </div>
    </div>

    <br>
    <br>
    <hr style="background-color: white">
    <br>
    <br>

    <mtag:getIntern/>
    <div class="row">
        <c:if test="${interns != null}">
            <div class="col-lg-6">
                <div class="bs-component">
                    <h2>School Interns</h2>
                    <table class="table table-hover table-sm">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Firstname</th>
                            <th scope="col">Lastname</th>
                            <th scope="col">Email</th>
                            <th scope="col">Grades</th>
                            <th scope="col">Average</th>
                            <th></th>
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
                                    <form action="profileAdmin" method="post">
                                        <input hidden type="text" name="idInternGrade" value="${i.getId()}">
                                        <button type="submit" name="idInternGrade" class="btn btn-info btn-sm">See</button>
                                    </form>
                                </td>
                                <td>${averages[loop.index]}%</td>
                                <td>
                                    <form action="profileAdmin" method="post">
                                        <input hidden type="text" name="idInternDelete" value="${i.getId()}">
                                        <button type="submit" name="idInternDelete" class="btn btn-danger btn-sm">Unregister</button>
                                    </form>
                                </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
        <c:if test="${requestScope.grades != null}">
            <div class="col-lg-5 offset-md-1">
                <div class="bs-component">
                    <h3>Grades</h3>
                    <div class="card border-info md-12" style="max-width: 30rem;">
                        <div class="card-header">Grades</div>
                        <div class="card-body">
                            <table class="table table-hover table-sm">
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
                </div>
            </div>
        </c:if>
        <c:if test="${requestScope.nograde != null}">
            <br>
            <br>
            <div class="col-lg-5 offset-md-1">
                <div class="bs-component">
                    <h3>Grades</h3>
                    <div class="alert alert-dismissible alert-danger">
                        <strong>Heads up! </strong>${nograde}
                    </div>
                </div>
            </div>
        </c:if>
    </div>

    <br>
    <br>
    <hr style="background-color: white">
    <br>
    <br>

    <mtag:getTeacher/>
    <div class="row">
        <c:if test="${teachers != null}">
            <div class="col-lg-6">
                <div class="bs-component">
                    <h2>School Teachers</h2>
                    <table class="table table-hover table-sm">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Firstname</th>
                            <th scope="col">Lastname</th>
                            <th scope="col">Email</th>
                            <th scope="col">Quiz</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${teachers}" var="t" varStatus="loop">
                            <tr>
                                <td>${t.getId()}</td>
                                <td>${t.getFirstname()}</td>
                                <td>${t.getLastname()}</td>
                                <td>${t.getEmail()}</td>
                                <td>
                                    <form action="profileAdmin" method="post">
                                        <input hidden type="text" name="idTeacherQuiz" value="${t.getId()}">
                                        <button type="submit" name="idTeacherQuiz" class="btn btn-info btn-sm">See</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="profileAdmin" method="post">
                                        <input hidden type="text" name="idTeacherDelete" value="${t.getId()}">
                                        <button type="submit" name="idTeacherDelete" class="btn btn-danger btn-sm">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
        <c:if test="${requestScope.quizzes != null}">
            <div class="col-lg-5 offset-md-1">
                <div class="bs-component">
                    <h3>Quizzes</h3>
                    <div class="card border-info md-12" style="max-width: 30rem;">
                        <div class="card-header">Quizzes</div>
                        <div class="card-body">
                            <table class="table table-hover table-sm">
                                <thead>
                                <tr>
                                    <th scope="col>">Id</th>
                                    <th scope="col">Theme</th>
                                    <th scope="col">Questions</th>
                                    <th scope="col">Average Grade</th>
                                    <th scope="col">Count</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${quizzes}" var="q" varStatus="loop">
                                    <tr>
                                        <td>${q.getId()}</td>
                                        <td>${q.getTheme()}</td>
                                        <td><span class="badge badge-primary badge-pill">${q.getNber_questions()} questions</span></td>
                                        <td>${averagesQuiz[loop.index]}%</td>
                                        <td>${counts[loop.index]}x</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${requestScope.noquiz != null}">
            <br>
            <br>
            <div class="col-lg-5 offset-md-1">
                <div class="bs-component">
                    <h3>Quizzes</h3>
                    <div class="alert alert-dismissible alert-danger">
                        <strong>Heads up! </strong>${noquiz}
                    </div>
                </div>
            </div>
        </c:if>
    </div>

    <br>
    <br>
    <hr style="background-color: white">
    <br>
    <br>

    <mtag:getPoolQuestion/>
    <div class="row">
        <c:if test="${questionsPool != null}">
            <div class="col-lg-6">
                <div class="bs-component">
                    <h2>Pool Questions</h2>
                    <table class="table table-hover table-sm">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Problem</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${questionsPool}" var="qp" varStatus="loop">
                            <tr>
                                <td>${qp.getId()}</td>
                                <td>${qp.getProblem()}</td>
                                <td>
                                    <form action="profileAdmin" method="post">
                                        <input hidden type="text" name="assignQuestion" value="${qp.getId()}">
                                        <button type="submit" name="assignQuestion" class="btn btn-info btn-sm">Assign</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="profileAdmin" method="post">
                                        <input hidden type="text" name="questionDelete" value="${qp.getId()}">
                                        <button type="submit" name="questionDelete" class="btn btn-danger btn-sm">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
        <c:if test="${poolEmpty != null}">
            <h3>Pool Questions</h3>
            <br>
            <br>
            <div class="row justify-content-md-center">
                <div class="col-lg-12">
                    <div class="alert alert-dismissible alert-info">
                        <strong>Heads up! </strong>${poolEmpty}
                    </div>
                </div>
            </div>
        </c:if>
    </div>

    <br>
    <br>
    <hr style="background-color: white">
    <br>
    <br>

    <mtag:getPoolQuiz/>
    <div class="row">
        <c:if test="${quizzesPool != null}">
            <div class="col-lg-6">
                <div class="bs-component">
                    <h2>Pool Quiz</h2>
                    <table class="table table-hover table-sm">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Problem</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${quizzesPool}" var="quizzp" varStatus="loop">
                            <tr>
                                <td>${quizzp.getId()}</td>
                                <td>${quizzp.getTheme()}</td>
                                <td>
                                    <form action="profileAdmin" method="post">
                                        <input hidden type="text" name="assignQuiz" value="${quizzp.getId()}">
                                        <button type="submit" name="assignQuiz" class="btn btn-info btn-sm">Assign</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="profileAdmin" method="post">
                                        <input hidden type="text" name="quizDelete" value="${quizp.getId()}">
                                        <button type="submit" name="quizDelete" class="btn btn-danger btn-sm">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
        <c:if test="${poolQuizEmpty != null}">
            <h3>Pool Quiz</h3>
            <br>
            <br>
            <div class="row justify-content-md-center">
                <div class="col-lg-12">
                    <div class="alert alert-dismissible alert-info">
                        <strong>Heads up! </strong>${poolQuizEmpty}
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $(".nav-tabs a").click(function(){
            $(".tab-pane").hide();
            var id = $(this).attr('href');
            $(id).show();
            $(id).css("opacity", 1);
        });
    });
</script>

</body>
</html>