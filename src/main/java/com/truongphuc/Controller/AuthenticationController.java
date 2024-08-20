package com.truongphuc.Controller;

import com.truongphuc.Model.User;
import com.truongphuc.Service.AuthenticationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/auth" })
public class AuthenticationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            RequestDispatcher rd = req.getRequestDispatcher("/View/Authentication.jsp");
            rd.forward(req, res);
        }
        else if (action.equals("logout")) {
            req.getSession().invalidate();
            RequestDispatcher rd = req.getRequestDispatcher("/View/Main.jsp");
            rd.forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");

        String action = req.getParameter("action");
        AuthenticationService authenticationService = new AuthenticationService();

        if (action.equals("register")) {
            String userName = req.getParameter("username");
            String fullName = req.getParameter("fullname");
            String password = req.getParameter("password");

            try {
                User result = authenticationService.register(userName, fullName, password);
                if (result != null)
                    req.getRequestDispatcher("/View/Authentication.jsp").forward(req, res);

            }catch (Exception e){
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/View/Authentication.jsp").forward(req, res);
            }

        }else if (action.equals("login")){
            String userName = req.getParameter("username");
            String password = req.getParameter("password");

            User result = authenticationService.logIn(userName, password);
            if (result == null){
                req.setAttribute("error", "Invalid username or password");
                System.out.println("Invalid username or password");
            }
            else {
                req.getSession().setAttribute("user", result);
                System.out.println("Successfully logged in");
            }

            req.getRequestDispatcher("/View/Authentication.jsp").forward(req, res);
        }
    }
}
