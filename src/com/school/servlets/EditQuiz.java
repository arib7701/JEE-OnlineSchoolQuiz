package com.school.servlets;

import com.school.beans.Question;
import com.school.dao.ApplicationDAO;

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

        // Edit All Quiz Questions
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

        // Return to Profile - No Change
        else if(req.getParameter("return") != null){

            if(req.getSession().getAttribute("teacher") != null){
                resp.sendRedirect(req.getContextPath() + "/profileTeacher");
            }
            if(req.getSession().getAttribute("admin") != null){
                resp.sendRedirect(req.getContextPath() + "/profileAdmin");
            }
        }

        // Unassigned Chosen Question
        else if(req.getParameter("questionIdUnassigned") != null){
            int i = Integer.parseInt((req.getParameter("questionIdUnassigned")));
            System.out.println("i is " + i);
            int questionId = Integer.parseInt(req.getParameter("id_"+i));
            System.out.println("question Id i is " + questionId);
            int quizId = Integer.parseInt(req.getParameter("quizId"));

            int reassigned = dao.reassignQuestions(questionId, 2, quizId);
            if(reassigned > 0){
                req.setAttribute("quizId", quizId);
                req.getRequestDispatcher("/JSP/editQuiz.jsp").forward(req,resp);
            }
            else {
                System.out.println("Error reassign question");
                req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
            }
        }

        // Delete Chosen Question
        else if(req.getParameter("questionIdDelete") != null){
            int i = Integer.parseInt((req.getParameter("questionIdDelete")));
            int questionId = Integer.parseInt(req.getParameter("id_"+i));
            int quizId = Integer.parseInt(req.getParameter("quizId"));
            int deleted = dao.deleteQuestionById(questionId, quizId);
            if(deleted > 0){
                req.setAttribute("quizId", quizId);
                req.getRequestDispatcher("/JSP/editQuiz.jsp").forward(req,resp);
            }
            else {
                System.out.println("Error delete question");
                req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
            }
        }

        // Add New Question Inputs in Form
        else if(req.getParameter("add") != null){
            int quizId = Integer.parseInt(req.getParameter("quizId"));
            req.setAttribute("quizId", quizId);
            req.setAttribute("showNewQuestionInputs", true);
            req.getRequestDispatcher("/JSP/editQuiz.jsp").forward(req,resp);
        }

        else if(req.getParameter("save") != null){
            String problem = req.getParameter("newProblem");
            String pos1 = req.getParameter("newPos1");
            String pos2 = req.getParameter("newPos2");
            String pos3 = req.getParameter("newPos3");
            String pos4 = req.getParameter("newPos4");
            String answer = req.getParameter("newAnswer");
            String theme = req.getParameter("theme");
            int quizId = Integer.parseInt(req.getParameter("quizId"));

            Question question = new Question(problem, pos1, pos2, pos3, pos4, answer, quizId, theme);
            int added = dao.addNewQuestion(question);
            if(added > 0){
                req.setAttribute("quizId", quizId);
                req.getRequestDispatcher("/JSP/editQuiz.jsp").forward(req,resp);
            }
            else {
                System.out.println("Error adding question");
                req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/JSP/editQuiz.jsp").forward(req,resp);
    }
}
