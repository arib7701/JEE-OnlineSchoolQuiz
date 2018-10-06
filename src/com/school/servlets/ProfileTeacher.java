package com.school.servlets;


import com.school.beans.Grade;
import com.school.beans.Question;
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

        if(req.getParameter("idIntern") != null){
            int internId = Integer.parseInt(req.getParameter("idIntern"));
            List<Grade> grades = dao.getGradeByIntern(internId);

            if (grades.size() > 0) {
                req.setAttribute("grades", grades);
            } else {
                String nograde = "This intern has not taken any quiz yet.";
                req.setAttribute("nograde", nograde);
            }

            req.getRequestDispatcher("/JSP/profileTeacher.jsp").forward(req,resp);
        }
        else if(req.getParameter("quizIdEdit") != null){

            int quizId = Integer.parseInt(req.getParameter("quizIdEdit"));
            List<Question> questions = dao.getQuestionsQuiz(quizId);
            req.setAttribute("questions", questions);
            req.getRequestDispatcher("/JSP/editQuiz.jsp").forward(req,resp);
        }
        else if(req.getParameter("quizIdDelete") != null){

            int quizId = Integer.parseInt(req.getParameter("quizIdDelete"));
            int delete = dao.deleteQuizById(quizId);
            System.out.println(delete);
            req.getRequestDispatcher("/JSP/profileTeacher.jsp").forward(req,resp);
        }

    }
}
