package com.school.tags;

import com.school.beans.User;
import com.school.dao.ApplicationDAO;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.List;

public class teacherHandler extends SimpleTagSupport {

    ApplicationDAO dao = new ApplicationDAO();

    @Override
    public void doTag() throws JspException {
        List<User> users = null;
        users = dao.getAllTeachers();
        getJspContext().setAttribute("teachers", users);
    }
}