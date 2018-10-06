package com.school.servlets;

import com.school.beans.User;
import com.school.dao.ApplicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/registerIntern")
public class RegisterIntern extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User intern = new User(firstname, lastname, email, username, password);
        ApplicationDAO dao = new ApplicationDAO();
        int row = dao.addNewUser(intern, "IN");

        if(row > 0){
            HttpSession session = req.getSession();
            session.setAttribute("intern", intern);
            //req.getRequestDispatcher("/JSP/login.jsp").forward(req,resp);
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            String error = "Make sure all your fields are filled or change your username";
            req.setAttribute("error", error);
            req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
        }
    }
}
