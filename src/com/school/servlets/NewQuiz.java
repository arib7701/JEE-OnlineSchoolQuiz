package com.school.servlets;

import com.school.beans.Question;
import com.school.beans.Quiz;
import com.school.beans.Teacher;
import com.school.beans.User;
import com.school.dao.ApplicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/newQuiz")
public class NewQuiz extends HttpServlet {

    ApplicationDAO dao = new ApplicationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String chosenTheme;
        int nber_Q;
        Quiz quiz;
        int quizId;
        int qId = 0;
        User teacher;
        HttpSession session = req.getSession();
        List<Question> questions = new ArrayList();

        if(req.getParameter("newQuizBtn") != null){
            req.getRequestDispatcher("/JSP/newQuiz.jsp").forward(req,resp);
        }
        else if(req.getParameter("setUpNewQuiz") != null){

            chosenTheme = req.getParameter("theme");
            nber_Q = Integer.parseInt(req.getParameter("nber_Q"));

            req.setAttribute("chosenTheme", chosenTheme);
            req.setAttribute("nber_Q", nber_Q);
            req.getRequestDispatcher("/JSP/newQuiz.jsp").forward(req,resp);
        }
        else if(req.getParameter("registerNewQuiz") != null){

            chosenTheme = req.getParameter("chosenTheme");
            nber_Q = Integer.parseInt(req.getParameter("nber_Q"));
            teacher = (User) session.getAttribute("teacher");
            quiz = new Quiz(chosenTheme, nber_Q,teacher.getId());
            quizId = dao.addNewQuiz(quiz);

            for(int i = 1 ; i <= nber_Q; i++) {
                String problem = req.getParameter("problem_" + i);
                String pos1 = req.getParameter("pos1_" + i);
                String pos2 = req.getParameter("pos2_" + i);
                String pos3 = req.getParameter("pos3_" + i);
                String pos4 = req.getParameter("pos4_" + i);
                String answer = req.getParameter("answer_" + i);

                Question question = new Question(problem, pos1, pos2, pos3, pos4, answer, quizId);
                qId = dao.addNewQuestion(question);
                if(qId < 1){
                    System.out.println("Error inserting new Q");
                    req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
                }
                else {
                    question.setId(qId);
                    questions.add(question);
                }
            }

            req.setAttribute("questions", questions);
            req.getRequestDispatcher("/JSP/editQuiz.jsp").forward(req,resp);
        }
    }
}
