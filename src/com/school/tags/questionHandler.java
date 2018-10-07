package com.school.tags;

import com.school.beans.Question;
import com.school.beans.Quiz;
import com.school.dao.ApplicationDAO;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.ArrayList;
import java.util.List;

public class questionHandler extends SimpleTagSupport {

    ApplicationDAO dao = new ApplicationDAO();
    private int quizId;

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    @Override
    public void doTag() throws JspException {

        List<Question> questions = null;
        questions = dao.getQuestionsQuiz(quizId);

        if (questions.size() > 0) {
            getJspContext().setAttribute("questions", questions);
        } else {
            getJspContext().setAttribute("noquestion", "You don't have any questions for this quiz yet. Time to take create one!");
        }
    }
}
