package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "Search servlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("categoryId");
        if (id != null) {
            UUID categoryId = UUID.fromString(id);
            req.getSession().setAttribute("categoryId", categoryId);
        }else {
            req.getSession().removeAttribute("categoryId");
        }
        resp.sendRedirect("/");
    }
}
