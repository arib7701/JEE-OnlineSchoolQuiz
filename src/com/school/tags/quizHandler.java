package com.school.tags;

import com.school.beans.Grade;
import com.school.beans.Quiz;
import com.school.dao.ApplicationDAO;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.List;

public class quizHandler extends SimpleTagSupport {

    ApplicationDAO dao = new ApplicationDAO();
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void doTag() throws JspException {

        List<Quiz> quizzes = null;
        quizzes = dao.getQuizByTeacher(userId);

        if(quizzes != null){
            getJspContext().setAttribute("quizzes", quizzes);
        } else {
            getJspContext().setAttribute("noquiz", "You don't have quizzes yet. Time to take create one!");
        }
    }
}

