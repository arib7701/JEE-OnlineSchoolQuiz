package com.school.servlets;


import com.school.beans.Grade;
import com.school.dao.ApplicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profileTeacher")
public class ProfileTeacher extends HttpServlet {

    ApplicationDAO dao = new ApplicationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/JSP/profileTeacher.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("into profileTeacher post, click on btn");

        int internId = Integer.parseInt(req.getParameter("id"));
        List<Grade> grades = dao.getGradeByIntern(internId);

        if (grades.size() > 0) {
            req.setAttribute("grades", grades);
        } else {
            String nograde = "This intern has not taken any quiz yet.";
            req.setAttribute("nograde", nograde);
        }

        req.getRequestDispatcher("/JSP/profileTeacher.jsp").forward(req,resp);
    }
}
