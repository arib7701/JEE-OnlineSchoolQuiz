package com.school.servlets;

import com.school.beans.Grade;
import com.school.beans.Quiz;
import com.school.dao.ApplicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profileAdmin")
public class ProfileAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationDAO dao = new ApplicationDAO();

        if(req.getParameter("idInternGrade") != null){
            int internId = Integer.parseInt(req.getParameter("idInternGrade"));
            List<Grade> grades = dao.getGradeByIntern(internId);

            if (grades.size() > 0) {
                req.setAttribute("grades", grades);
            } else {
                String nograde = "This intern has not taken any quiz yet.";
                req.setAttribute("nograde", nograde);
            }

            req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);
        }
        else if(req.getParameter("idInternDelete") != null){
            int internId = Integer.parseInt(req.getParameter("idInternDelete"));
            int deleted = dao.deleteUserById(internId, "IN");
            if(deleted > 0){
                req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);
            }
            else {
                req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
            }
        }
        else if(req.getParameter("idTeacherQuiz") != null){
            int teacherId = Integer.parseInt(req.getParameter("idTeacherQuiz"));
            List<Quiz> quizzes = dao.getQuizByTeacher(teacherId);

            if (quizzes.size() > 0) {
                req.setAttribute("quizzes", quizzes);
            } else {
                String noquiz = "This intern has not make any quiz yet.";
                req.setAttribute("noqiz", noquiz);
            }

            req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);
        }
        else if(req.getParameter("idTeacherDelete") != null){
            int teacherId = Integer.parseInt(req.getParameter("idTeacherDelete"));
            int deleted = dao.deleteUserById(teacherId, "TE");
            if(deleted > 0){
                req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);
            }
            else {
                req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
            }
        }
         else if(req.getParameter("newUser") != null){
             req.setAttribute("status", "notIntern");
             req.setAttribute("pass", true);
             req.getRequestDispatcher("/JSP/registerUser.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);
    }
}
