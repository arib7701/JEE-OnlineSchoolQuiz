<%@ page import="com.school.beans.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://onlineschool.com/tags" prefix="mtag" %>

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
        <h1 class="display-3">Welcome, <c:out value="${sessionScope.intern.getFirstname()}"></c:out>!</h1>
        <p class="lead">Start learning and improving by taking quizzes!</p>
        <hr class="my-4">
    </div>
    <mtag:getTheme/>
    <c:if test="${themes != null}">
    <div class="row justify-content-md-center">
        <div class="col-lg-6">
            <div class="bs-component">
                <h4>Select a theme to access quizzes</h4>
                <form action="profileIntern" method="post">
                    <div class="form-group">
                        <select class="custom-select" name="theme">
                            <c:forEach items="${themes}" var="theme" varStatus="loop">
                                <option value="${theme}">${theme}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" name="theme" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
        </c:if>
        <c:if test="${requestScope.quizzes != null}">
            <div class="col-lg-4 align-self-center">
                <div class="bs-component">
                    <h4>Select a quiz</h4>
                    <form action="profileIntern" method="post">
                        <fieldset class="form-group">
                            <div class="form-group">
                                <c:forEach items="${requestScope.quizzes}" var="q" varStatus="loop">
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" name="quiz" id="${q.getId()}" value="${q.getId()}">
                                        <label class="custom-control-label" for="${q.getId()}">Quiz number ${q.getId()} - ${q.getNber_questions()} Questions</label>
                                    </div>
                                </c:forEach>
                            </div>
                        </fieldset>
                        <button type="submit" name="quiz" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </c:if>
    </div>
    <mtag:getGrade userId="${sessionScope.intern.getId()}"/>
    <c:if test="${grades != null}">
        <br>
        <br>
        <div class="row justify-content-md-center">
            <div class="col-lg-8">
                <div class="bs-component">
                    <h2>Your Grades</h2>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">Quiz Number</th>
                            <th scope="col">Theme</th>
                            <th scope="col">Date</th>
                            <th scope="col">Grade</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${grades}" var="g" varStatus="loop">
                            <tr>
                                <td>${g.getQuiz_id()}</td>
                                <td>${themes[loop.index]}</td>
                                <td>${g.getGrade_date()}</td>
                                <td>${g.getGrade_value()}%</td>
                                <td>
                                    <c:if test="${g.getGrade_value() <= 50}">
                                        <span class="badge badge-pill badge-danger">Danger</span>
                                    </c:if>
                                    <c:if test="${g.getGrade_value() <= 70 and g.getGrade_value() > 50 }">
                                        <span class="badge badge-pill badge-warning">Warning</span>
                                    </c:if>
                                    <c:if test="${g.getGrade_value() <= 100 and g.getGrade_value() > 70}">
                                        <span class="badge badge-pill badge-success">Success</span>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${nograde != null}">
            <br>
            <br>
            <div class="row justify-content-md-center">
                <div class="col-lg-6">
                    <div class="alert alert-dismissible alert-info">
                        <strong>Heads up! </strong>${nograde}
                    </div>
                </div>
            </div>
    </c:if>
</div>
</div>
</body>
</html>