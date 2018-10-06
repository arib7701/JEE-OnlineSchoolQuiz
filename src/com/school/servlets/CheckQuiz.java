package com.school.servlets;

import com.school.beans.Grade;
import com.school.beans.User;
import com.school.dao.ApplicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/checkQuiz")
public class CheckQuiz extends HttpServlet {

    HttpSession session;
    ApplicationDAO dao = new ApplicationDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Integer> questionsId = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        int nberQ = Integer.parseInt(req.getParameter("nberQ"));

        for(int i = 1; i <= nberQ; i++){
            String queryName = "qId_"+i;
            String qId = req.getParameter(queryName);
            String answer = req.getParameter(qId);
            questionsId.add(Integer.parseInt(qId));
            answers.add(answer);
        }

        double result = checkResult(questionsId, answers);

        String type = req.getParameter("type");

        if(type.equals("entry")){
            boolean pass;
            if(result == 100){
                pass = true;
                req.setAttribute("pass", pass);
                req.getRequestDispatcher("/JSP/entry_result.jsp").forward(req,resp);
                //resp.sendRedirect(req.getContextPath() + "/entry_result");
            }
            else {
                req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
            }

        }
        else if(type.equals("quiz")){

            // Add grade to DB
            session = req.getSession();
            User intern = (User) session.getAttribute("intern");
            int quizId = Integer.parseInt(req.getParameter("quizId"));

            LocalDate today = LocalDate.now();
            Date date = Date.valueOf(today);

            Grade grade = new Grade(intern.getId(), quizId, result, date);
            int rowAffected = dao.addNewGrade(grade);
            if(rowAffected > 0){
                //req.getRequestDispatcher("/JSP/profileIntern.jsp").forward(req,resp);
                resp.sendRedirect(req.getContextPath() + "/profileIntern");
            }
            else {
                req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    public double checkResult(List<Integer> questionsId, List<String> answers){

        int i = 0;
        int countTrue = 0;
        boolean pass = false;

        for(Integer q: questionsId){
            String correct_answer = dao.getAnswerOfQuestion(q);
            if(correct_answer.equals(answers.get(i))){
                i++;
                countTrue++;
            }
            else {
                i++;
            }
        }

        if(countTrue == i){
            pass = true;
        }

        double percent = (countTrue*100.0) / (double) i;
        return percent;
    }
}
