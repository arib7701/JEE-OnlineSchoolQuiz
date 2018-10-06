package com.school.tags;

import com.school.beans.Grade;
import com.school.beans.User;
import com.school.dao.ApplicationDAO;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.ArrayList;
import java.util.List;

public class gradeHandler extends SimpleTagSupport {

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

        List<Grade> grades = null;
        grades = dao.getGradeByIntern(userId);

        List<String> themes = new ArrayList<>();
        for(Grade g : grades){
            String theme = dao.getThemeByGradeId(g.getGrade_id());
            themes.add(theme);
        }

        if(grades.size() > 0){
            getJspContext().setAttribute("grades", grades);
            getJspContext().setAttribute("themes", themes);
        } else {
            getJspContext().setAttribute("nograde", "You don't have grade yet. Time to take a quiz!");
        }
    }
}
