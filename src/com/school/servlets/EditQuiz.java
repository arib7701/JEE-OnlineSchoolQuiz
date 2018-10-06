package com.school.servlets;

import com.school.beans.Question;
import com.school.beans.Quiz;
import com.school.beans.User;
import com.school.dao.ApplicationDAO;
import javafx.application.Application;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/editQuiz")
public class EditQuiz extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationDAO dao = new ApplicationDAO();

        if(req.getParameter("edit") != null){

            int nber_Q = Integer.parseInt(req.getParameter("nber_Q"));
            int questionId;
            Question question;
            List<Question> questions = new ArrayList();

            for(int i = 1 ; i <= nber_Q; i++) {
                questionId = Integer.parseInt(req.getParameter("id_"+i));
                String problem = req.getParameter("problem_" + questionId);
                String pos1 = req.getParameter("pos1_" + questionId);
                String pos2 = req.getParameter("pos2_" + questionId);
                String pos3 = req.getParameter("pos3_" + questionId);
                String pos4 = req.getParameter("pos4_" + questionId);
                String answer = req.getParameter("answer_" + questionId);
                String theme = req.getParameter("theme_"+questionId);
                int quizId = Integer.parseInt(req.getParameter("quizId_"+i));

                question = new Question(questionId, problem, pos1, pos2, pos3, pos4, answer, quizId, theme);

                int editted = dao.updateQuestionById(question);
                if(editted < 1){
                    System.out.println("Error inserting new Q");
                    req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
                }
                else {
                    questions.add(question);
                }
            }

            req.setAttribute("questions", questions);
            req.getRequestDispatcher("/JSP/editQuiz.jsp").forward(req,resp);
        }
        else if(req.getParameter("return") != null){
            resp.sendRedirect(req.getContextPath() + "/profileTeacher");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/JSP/editQuiz.jsp").forward(req,resp);
    }
}
