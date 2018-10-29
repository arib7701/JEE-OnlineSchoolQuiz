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

@WebServlet("/editUser")
public class EditUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/JSP/registerUser.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session;
        session = req.getSession();
        User user = new User();

        if(req.getParameter("editUserForm") != null){

            if( session.getAttribute("intern") != null){
                user = (User) session.getAttribute("intern");
                req.setAttribute("status", "intern");
            }
            else if( session.getAttribute("teacher") != null){
                user = (User) session.getAttribute("teacher");
                req.setAttribute("status", "teacher");
            }
            else if( session.getAttribute("admin") != null){
                user = (User) session.getAttribute("admin");
                req.setAttribute("status", "admin");
            }

            req.setAttribute("user", user);
            req.getRequestDispatcher("/JSP/editUser.jsp").forward(req,resp);
        }

        // Return to Profile - No Change
        else if(req.getParameter("return") != null){

            if(req.getSession().getAttribute("teacher") != null){
                resp.sendRedirect(req.getContextPath() + "/profileTeacher");
            }
            if(req.getSession().getAttribute("admin") != null){
                resp.sendRedirect(req.getContextPath() + "/profileAdmin");
            }
            if(req.getSession().getAttribute("intern") != null){
                resp.sendRedirect(req.getContextPath() + "/profileIntern");
            }
        }

        else if(req.getParameter("saveEdits") != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            String email = req.getParameter("email");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String status = req.getParameter("status");

            user = new User(id, firstname, lastname, email, username, password);
            ApplicationDAO dao = new ApplicationDAO();
            int updated = 0;

            if (status.equals("IN")) {
                updated = dao.updateUserById(user, "IN");
                if(updated > 0){
                    session.setAttribute("intern", user);
                    resp.sendRedirect(req.getContextPath() + "/profileIntern");
                }
            } else if (status.equals("TE")) {
                updated = dao.updateUserById(user, "TE");
                if(updated > 0) {
                    session.setAttribute("teacher", user);
                    resp.sendRedirect(req.getContextPath() + "/profileTeacher");
                }
            } else if (status.equals("AD")) {
                updated = dao.updateUserById(user, "AD");
                if(updated > 0) {
                    session.setAttribute("admin", user);
                    resp.sendRedirect(req.getContextPath() + "/profileAdmin");
                }
            }

            if(updated < 1){
                String error = "Make sure all your fields are filled or change your username";
                req.setAttribute("error", error);
                req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req, resp);
            }
        }
    }
}
