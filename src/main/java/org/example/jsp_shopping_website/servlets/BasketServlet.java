package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.service.SessionService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "Basket servlet", urlPatterns = "/basket/*")
public class BasketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/basket/add")) {
            UUID productId = UUID.fromString(req.getParameter("productId"));
            SessionService.addBasketProduct(productId, req.getSession());
            String categoryId = req.getParameter("categoryId");
            if (categoryId == null) {
                resp.sendRedirect("/");
            } else {
                resp.sendRedirect("/?categoryId=" + categoryId);
            }
        }else if (req.getRequestURI().equals("/basket/remove")) {
            UUID productId = UUID.fromString(req.getParameter("productId"));
            SessionService.removeBasketProduct(productId, req.getSession());
            resp.sendRedirect("/basket.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID productId = UUID.fromString(req.getParameter("productId"));
        SessionService.removeBasketProduct(productId, req.getSession());
        resp.sendRedirect("/");
    }
}
