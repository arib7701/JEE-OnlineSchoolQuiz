package com.school.tags;

import com.school.beans.Quiz;
import com.school.dao.ApplicationDAO;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.ArrayList;
import java.util.List;

public class quizThemeHandler extends SimpleTagSupport {

    ApplicationDAO dao = new ApplicationDAO();

    private String theme;

    public String getTheme() { return theme; }

    public void setTheme(String theme) { this.theme = theme; }

    @Override
    public void doTag() throws JspException {

        List<Quiz> quizzes;

        quizzes = dao.getQuizByTheme(theme);

        if(quizzes.size() > 0){
            getJspContext().setAttribute("quizzes", quizzes);
        } else {
            getJspContext().setAttribute("noquiz", "There is no quiz with that theme yet!");
        }
    }
}
