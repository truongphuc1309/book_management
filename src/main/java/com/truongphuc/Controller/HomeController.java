package com.truongphuc.Controller;

import com.truongphuc.Model.Book;
import com.truongphuc.Service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        BookService bookService = new BookService();
        if (action == null) {
            List<Book> bookList = bookService.getAll();
            req.setAttribute("list", bookList);
            RequestDispatcher rd = req.getRequestDispatcher("/View/Main.jsp");
            rd.forward(req, res);
        }else if (action.equals("delete")){
            String id = req.getParameter("id");
            bookService.delete(id);
            res.sendRedirect("/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        BookService bookService = new BookService();
        if (action.equals("create")) {
            String name = req.getParameter("name");
            String author = req.getParameter("author");
            String type = req.getParameter("type");

            bookService.create(name, author, type);
            res.sendRedirect("/home");
        }else if (action.equals("update")) {
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String author = req.getParameter("author");
            String type = req.getParameter("type");

            bookService.update(id, name, author, type);
            res.sendRedirect("/home");
        }
    }
}
