package com.school.tags;

import com.school.beans.Question;
import com.school.dao.ApplicationDAO;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.List;

public class poolQuestionHandler extends SimpleTagSupport {

    ApplicationDAO dao = new ApplicationDAO();

    @Override
    public void doTag() throws JspException {

        // Pool questions are store in quiz id 2
        List<Question> questions = dao.getQuestionsQuiz(2);

        if(questions != null){
            getJspContext().setAttribute("questionsPool", questions);
        } else {
            getJspContext().setAttribute("poolEmpty", "All questions are assigned to a quiz!");
        }
    }

}
