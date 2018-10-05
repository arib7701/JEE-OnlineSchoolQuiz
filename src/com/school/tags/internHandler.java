package com.school.tags;

import com.school.beans.User;
import com.school.dao.ApplicationDAO;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.List;

public class internHandler extends SimpleTagSupport {

    ApplicationDAO dao = new ApplicationDAO();

    public void doTag() throws JspException {
        List<User> users = null;
        users = dao.getAllInterns();
        getJspContext().setAttribute("interns", users);
    }
}
