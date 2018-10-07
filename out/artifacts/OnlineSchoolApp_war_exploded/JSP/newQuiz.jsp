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
<c:if test="${sessionScope.teacher != null}">
<div class="container" id="main">
    <div class="jumbotron">
        <h1 class="display-3">Welcome, <c:out value="${sessionScope.teacher.getFirstname()}"></c:out>!</h1>
        <p class="lead">Create a new Quiz!</p>
        <hr class="my-4">
    </div>

    <c:if test="${requestScope.chosenTheme == null}">
        <mtag:getTheme/>
        <c:if test="${themes != null}">
        <div class="row justify-content-md-center">
            <div class="col-lg-6">
                <div class="bs-component">
                    <form action="newQuiz" method="post">
                        <fieldset>
                            <div class="form-group">
                                <h6>Select a theme for the new quiz</h6>
                                <select class="custom-select" name="theme">
                                    <c:forEach items="${themes}" var="theme" varStatus="loop">
                                        <option value="${theme}">${theme}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <h6>Choose number of questions in quiz</h6>
                                <input type="number" name="nber_Q">
                            </div>
                            <button type="submit" name="setUpNewQuiz" class="btn btn-primary">Submit</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        </c:if>
    </c:if>
    <c:if test="${requestScope.chosenTheme != null}">
    <div class="row justify-content-md-center">
        <div class="col-lg-6">
            <div class="bs-component">
                <form action="newQuiz" method="post">
                    <fieldset>
                        <c:if test="${requestScope.nber_Q != null}">
                            <input type="text" hidden name="chosenTheme" value="${requestScope.chosenTheme}">
                            <input type="text" hidden name="nber_Q" value="${requestScope.nber_Q}">
                            <c:forEach begin="1" end="${requestScope.nber_Q}" varStatus="loop">
                                <h3 class="align-content-lg-center">Question ${loop.count}</h3>
                                <input type="text" hidden name="q_${loop.count}">
                                <fieldset class="form-group">
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="problem_${loop.count}" id="problem" placeholder="Problem">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="pos1_${loop.count}" id="pos1" placeholder="Possibility 1">
                                        <input type="text" class="form-control" name="pos2_${loop.count}" id="pos2" placeholder="Possibility 2">
                                        <input type="text" class="form-control" name="pos3_${loop.count}" id="pos3" placeholder="Possibility 3">
                                        <input type="text" class="form-control" name="pos4_${loop.count}" id="pos4" placeholder="Possibility 4">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="answer_${loop.count}" id="answer" placeholder="Answer(A,B,C,D)">
                                    </div>
                                </fieldset>
                            </c:forEach>
                            <button type="submit" name="registerNewQuiz" class="btn btn-primary">Submit</button>
                        </c:if>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
    </c:if>
</div>
</c:if>
</body>
</html>