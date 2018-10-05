package com.school.servlets;

import com.school.beans.*;
import com.school.dao.ApplicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static javax.swing.UIManager.getString;

@WebServlet("/login")
public class LogIn extends HttpServlet {

    HttpSession session;
    ApplicationDAO dao = new ApplicationDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String status = req.getParameter("status");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("Password " + password);

        session = req.getSession();

        User user = dao.logInUser(status, username);
        boolean passwordOk = checkPassword(password, user.getPassword());

        if(passwordOk){
            System.out.println("User recognized in DB");

            if (status.equals("IN")) {
                session.setAttribute("intern", user);
                resp.sendRedirect(req.getContextPath() + "/profileIntern");
            } else if (status.equals("TE")) {
                session.setAttribute("teacher", user);
                resp.sendRedirect(req.getContextPath() + "/profileTeacher");
            } else if (status.equals("AD")) {
                session.setAttribute("admin", user);
                resp.sendRedirect(req.getContextPath() + "/profileAdmin");
            }
        }
        else {
            System.out.println("User NOT recognized in DB");
            req.getRequestDispatcher("/JSP/errorPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/JSP/login.jsp").forward(req, resp);
    }

    public boolean checkPassword(String passwordInput, String passwordDB) {

        boolean same = false;

        System.out.println("hash pass from DB " + passwordInput);

        String hashText = dao.getMd5(passwordInput);

        System.out.println("hash pass from hash " + hashText);

        if(hashText.equals(passwordDB)){
            same = true;
        }
        return same ;
    }
}
