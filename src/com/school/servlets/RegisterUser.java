package com.school.servlets;

import com.school.beans.User;
import com.school.dao.ApplicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerUser")
public class RegisterUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/JSP/registerUser.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String status = req.getParameter("status");

        User user = new User(firstname, lastname, email, username, password);
        ApplicationDAO dao = new ApplicationDAO();
        int added = 0;

        if(status.equals("IN")){
            added = dao.addNewUser(user, "IN");
        }
        else if(status.equals("TE")){
            added = dao.addNewUser(user, "TE");
        }
        else if(status.equals("AD")){
            added = dao.addNewUser(user, "AD");
        }

        if(added > 0){
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            String error = "Make sure all your fields are filled or change your username";
            req.setAttribute("error", error);
            req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req,resp);
        }
    }
}
