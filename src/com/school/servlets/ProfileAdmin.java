package com.school.servlets;

import com.school.beans.Grade;
import com.school.beans.Question;
import com.school.beans.Quiz;
import com.school.dao.ApplicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profileAdmin")
public class ProfileAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationDAO dao = new ApplicationDAO();

        // See Intern Grades Details
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

        // Delete Intern from DB
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

        // See Teacher's Quiz Details
        else if(req.getParameter("idTeacherQuiz") != null){
            int teacherId = Integer.parseInt(req.getParameter("idTeacherQuiz"));

            List<Quiz> quizzes = dao.getQuizByTeacher(teacherId);

            if (quizzes.size() > 0) {

                List<Long> averages = new ArrayList<>();
                List<Integer> counts = new ArrayList<>();

                for(Quiz q : quizzes){
                    double avg = dao.getAverageGradeByQuizId(q.getId());
                    int count = dao.getCountByQuizId(q.getId());
                    counts.add(count);
                    averages.add(Math.round(avg));
                }

                req.setAttribute("counts", counts);
                req.setAttribute("averagesQuiz", averages);
                req.setAttribute("quizzes", quizzes);
            } else {
                String noquiz = "This teacher has made no quiz yet.";
                req.setAttribute("noquiz", noquiz);
            }

            req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);
        }

        // Delete Teacher from DB
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

        // Edit Quiz
        else if(req.getParameter("quizIdEdit") != null){

            int quizId = Integer.parseInt(req.getParameter("quizIdEdit"));
            List<Question> questions = dao.getQuestionsQuiz(quizId);
            req.setAttribute("quizId", quizId);
            req.getRequestDispatcher("/JSP/editQuiz.jsp").forward(req,resp);
        }

        // Delete Quiz
        else if(req.getParameter("quizIdDelete") != null){

            int quizId = Integer.parseInt(req.getParameter("quizIdDelete"));
            int delete = dao.deleteQuizById(quizId);
            System.out.println(delete);
            req.getRequestDispatcher("/JSP/profileTeacher.jsp").forward(req,resp);
        }

        // Add New User
        else if(req.getParameter("newUser") != null){
             req.setAttribute("status", "notIntern");
             req.setAttribute("pass", true);
             req.getRequestDispatcher("/JSP/registerUser.jsp").forward(req,resp);
        }

        // Reassign Questions to Quiz - show correct Theme Quiz
        else if (req.getParameter("assignQuestion") != null){
            int questionId = Integer.parseInt(req.getParameter("assignQuestion"));
            String questionTheme = req.getParameter("assignQuestionTheme");
            req.setAttribute("assignQuestionToQuiz", questionId);
            req.setAttribute("assignTheme", questionTheme);
            req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);
        }

        // Reassign to Picked Quiz
        else if(req.getParameter("idQuizQuestionReassign") != null){
            int questionId = Integer.parseInt(req.getParameter("questionIdReassign"));
            int quizId = Integer.parseInt(req.getParameter("idQuizQuestionReassign"));
            int reassigned = dao.reassignQuestions(questionId, quizId, 2);
            if(reassigned > 0){
                req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);
            }
            else {
                req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
            }
        }

        // Delete Question from DB
        else if (req.getParameter("questionDelete") != null){
            int questionId = Integer.parseInt(req.getParameter("questionDelete"));
            int deleted = dao.deleteQuestionById(questionId, 1);
            if(deleted > 0){
                req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);
            }
            else {
                req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
            }
        }

        // Reassign Quiz to Teacher - show Teacher to Pick
        else if (req.getParameter("assignQuiz") != null){
            int quizId = Integer.parseInt(req.getParameter("assignQuiz"));
            req.setAttribute("assignQuizToTeacher", quizId);
            req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);

        }

        // Reassign to Picked Teacher
        else if(req.getParameter("idTeacherQuizReassign") != null){
            int quizId = Integer.parseInt(req.getParameter("quizIdReassign"));
            int teacherId = Integer.parseInt(req.getParameter("idTeacherQuizReassign"));
            int reassigned = dao.reassignQuiz(quizId, teacherId);
            if(reassigned > 0){
                req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);
            }
            else {
                req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
            }
        }

        // Delete Quiz from DB
        else if (req.getParameter("quizDelete") != null){
            int quizId = Integer.parseInt(req.getParameter("quizDelete"));
            int deleted = dao.deleteQuizById(quizId);
            if(deleted > 0){
                req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);
            }
            else {
                req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/JSP/profileAdmin.jsp").forward(req,resp);
    }
}
