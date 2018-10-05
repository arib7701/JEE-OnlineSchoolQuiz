package com.school.servlets;

import com.school.beans.Question;
import com.school.dao.ApplicationDAO;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getHome")
public class GetHomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationDAO dao = new ApplicationDAO();
        List<Question> questions = dao.getQuestionsQuiz(1);

        req.setAttribute("questions", questions);
        req.getRequestDispatcher("/JSP/home.jsp").forward(req,resp);
    }
}
