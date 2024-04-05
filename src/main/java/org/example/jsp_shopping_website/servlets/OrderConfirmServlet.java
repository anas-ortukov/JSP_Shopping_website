package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.service.BasketService;
import org.example.jsp_shopping_website.service.OrderService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "ConfirmationOrder", urlPatterns = "/order/confirm")
public class OrderConfirmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService.createOrder(req.getSession());
        resp.sendRedirect("http://localhost:8080");
    }
}
