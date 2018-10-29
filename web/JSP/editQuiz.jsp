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
<c:if test="${sessionScope.teacher != null or sessionScope.admin != null or sessionScope.intern != null}">
<div class="container" id="main">

    <div class="jumbotron">
        <h1 class="display-3">Verify your quiz!</h1>
        <p class="lead">You can make edits if needed.<hr class="my-4">
    </div>

    <div class="row">
        <div class="col-lg-10 offset-md-2 ">
            <div class="bs-component">
                <mtag:getQuestionsQuiz quizId="${requestScope.quizId}"/>
                <form action="editQuiz" method="post">
                    <input type="text" hidden name="quizId" value="${requestScope.quizId}">
                    <c:if test="${showNewQuestionInputs == true}">
                        <fieldset class="form-group">
                            <div class="form-group">
                                <input type="text" class="form-control" name="newProblem" id="newProblem" placeholder="Problem">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="newPos1" id="newA" placeholder="Possibility 1">
                                <input type="text" class="form-control" name="newPos2" id="newB" placeholder="Possibility 2">
                                <input type="text" class="form-control" name="newPos3" id="newC" placeholder="Possibility 3">
                                <input type="text" class="form-control" name="newPos4" id="newD" placeholder="Possibility 4">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="newAnswer" id="newAnswer" placeholder="Answer">
                            </div>
                            <c:if test="${questions == null}">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="theme" id="theme" placeholder="Theme">
                                </div>
                            </c:if>
                        </fieldset>
                        <button type="submit" name="save" class="btn btn-success">Save New Question</button>
                    </c:if>
                    <br>
                    <br>
                    <c:if test="${questions != null}">
                        <input hidden name="type" value="quiz">
                        <input hidden name="theme" value="${questions.get(0).getTheme()}">
                        <fieldset>
                            <c:forEach items="${questions}" var="q" varStatus="loop">
                                <input type="text" hidden name="id_${loop.count}" value="${q.getId()}">
                                <h3 class="align-content-lg-center">Question ${loop.count}
                                    <span style="padding-left: 15px;">
                                        <button type="submit" name="questionIdUnassigned" value="${loop.count}" class="btn btn-warning btn-sm">Unassigned</button>
                                    </span>
                                    <span>
                                        <button type="submit" name="questionIdDelete" value="${loop.count}" class="btn btn-danger btn-sm">Delete</button>
                                    </span>
                                </h3>
                                <fieldset class="form-group">
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="problem_${q.getId()}" id="problem" value="${q.getProblem()}">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="pos1_${q.getId()}" id="A" value="${q.getPossibility_1()}">
                                        <input type="text" class="form-control" name="pos2_${q.getId()}" id="B" value="${q.getPossibility_2()}">
                                        <input type="text" class="form-control" name="pos3_${q.getId()}" id="C" value="${q.getPossibility_3()}">
                                        <input type="text" class="form-control" name="pos4_${q.getId()}" id="D" value="${q.getPossibility_4()}">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="answer_${q.getId()}" id="answer" value="${q.getCorrect_answer()}">
                                    </div>
                                </fieldset>
                            </c:forEach>
                            <input type="text" hidden name="nber_Q" value="${questions.size()}">
                            <button type="submit" name="add" class="btn btn-success">Add New Question</button>
                            <button type="submit" name="edit" class="btn btn-primary">Edit</button>
                            <button type="submit" name="return" class="btn btn-primary">Return</button>
                        </fieldset>
                    </c:if>
                    <c:if test="${noquestion != null}">
                        <h3>Quiz</h3>
                        <div class="alert alert-dismissible alert-info">
                            <strong>Heads up! </strong>${noquestion}
                        </div>
                        <button type="submit" name="add" class="btn btn-success">Add New Question</button>
                        <button type="submit" name="return" class="btn btn-primary">Return</button>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>
</c:if>
</body>
</html>