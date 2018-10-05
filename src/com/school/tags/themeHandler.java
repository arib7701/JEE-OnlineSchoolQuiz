package com.school.tags;

import com.school.dao.ApplicationDAO;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.List;

public class themeHandler extends SimpleTagSupport {

    ApplicationDAO dao = new ApplicationDAO();

    @Override
    public void doTag() throws JspException {
        List<String> themes = null;
        themes = dao.getThemes();
        getJspContext().setAttribute("themes", themes);
    }
}
