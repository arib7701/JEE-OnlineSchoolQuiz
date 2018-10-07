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

<c:if test="${sessionScope.intern != null}">
<div class="container" id="main">

    <div class="jumbotron">
        <h1 class="display-3">Take the Quiz below!</h1>
        <p class="lead">Your score will be available on your profile page once submitted.<hr class="my-4">
    </div>

    <div class="row">
        <div class="col-lg-10 offset-md-2 ">
            <div class="bs-component">
                <form action="checkQuiz" method="post">
                    <input hidden name="type" value="quiz">
                    <fieldset>
                        <c:forEach items="${requestScope.questions}" var="q" varStatus="loop">
                        <fieldset class="form-group">
                            <input hidden name="quizId" value=<c:out value="${requestScope.quizId}"></c:out>>
                            <legend>Question ${loop.count}: ${q.getProblem()}</legend>
                            <input type="text" hidden name="qId_${loop.count}" value="${q.getId()}">
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="radio" class="form-check-input" name="${q.getId()}" id="A" value="A">
                                    ${q.getPossibility_1()}
                                </label>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="radio" class="form-check-input" name="${q.getId()}" id="B" value="B">
                                    ${q.getPossibility_2()}
                                </label>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="radio" class="form-check-input" name="${q.getId()}" id="C" value="C">
                                    ${q.getPossibility_3()}
                                </label>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="radio" class="form-check-input" name="${q.getId()}" id="D" value="D">
                                    ${q.getPossibility_4()}
                                </label>
                            </div>
                        </fieldset>
                        </c:forEach>
                        <input type="text" hidden name="nberQ" value=${requestScope.questions.size()}>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</c:if>
</body>
</html>