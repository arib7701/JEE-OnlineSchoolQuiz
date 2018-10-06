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
    private String theme;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTheme() { return theme; }

    public void setTheme(String theme) { this.theme = theme; }

    @Override
    public void doTag() throws JspException {

        List<Quiz> quizzes = null;
        //System.out.println("into tagHandler, userId " + userId + " - theme " + theme);

        quizzes = dao.getQuizByTeacherByTheme(userId, theme);

        if(quizzes != null){
            getJspContext().setAttribute("quizzes", quizzes);
        } else {
            getJspContext().setAttribute("noquiz", "You don't have quizzes yet. Time to take create one!");
        }
    }
}

