package com.school.tags;

import com.school.beans.Quiz;
import com.school.dao.ApplicationDAO;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.List;

public class poolQuizHandler extends SimpleTagSupport {

    ApplicationDAO dao = new ApplicationDAO();

    @Override
    public void doTag() throws JspException {

        // Pool quiz are store in with teacher id 1
        List<Quiz> quizzes = dao.getQuizByTeacher(1);

        if(quizzes.size() > 0){
            getJspContext().setAttribute("quizzesPool", quizzes);
        } else {
            getJspContext().setAttribute("poolQuizEmpty", "All quizzes are assigned to a teacher!");
        }
    }

}
