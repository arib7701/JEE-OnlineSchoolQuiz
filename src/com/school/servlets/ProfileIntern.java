package com.school.servlets;

import com.school.beans.Question;
import com.school.beans.Quiz;
import com.school.dao.ApplicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profileIntern")
public class ProfileIntern extends HttpServlet {

    ApplicationDAO dao = new ApplicationDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if( req.getParameter("theme") != null){
            String theme = req.getParameter("theme");

            // Get Quiz By Theme and All Themes
            List<Quiz> quizzes = dao.getQuizByTHeme(theme);

            req.setAttribute("quizzes", quizzes);
            req.getRequestDispatcher("/JSP/profileIntern.jsp").forward(req,resp);
        }
        else if( req.getParameter("quiz") != null){

            int quizId = Integer.parseInt(req.getParameter("quiz"));
            List<Question> questions = dao.getQuestionsQuiz(quizId);
            req.setAttribute("questions", questions);
            req.setAttribute("quizId", quizId);
            req.getRequestDispatcher("/JSP/takeQuiz.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/JSP/profileIntern.jsp").forward(req,resp);
    }
}
