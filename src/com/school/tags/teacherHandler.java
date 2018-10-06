package com.school.tags;

import com.school.beans.User;
import com.school.dao.ApplicationDAO;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.ArrayList;
import java.util.List;

public class teacherHandler extends SimpleTagSupport {

    ApplicationDAO dao = new ApplicationDAO();

    @Override
    public void doTag() throws JspException {
        List<User> users = null;
        users = dao.getAllTeachers();

        List<Integer> counts = new ArrayList<>();
        int count = 0;
        for(User u : users ){
            count = dao.getCountByTeacherId(u.getId());
            counts.add(count);
        }

        getJspContext().setAttribute("countsQuiz", counts);
        getJspContext().setAttribute("teachers", users);
    }
}