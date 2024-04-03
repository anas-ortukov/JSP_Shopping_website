package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.entity.Order;
import org.example.jsp_shopping_website.repo.OrderRepo;

import java.io.IOException;

@WebServlet(name = "Update Order", value = "/admin/update/order")
public class UpdateOrderServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newStatus = req.getParameter("orderStatus");
        if (newStatus == null) {
            resp.sendRedirect("/admin/orders.jsp");
        }else {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            OrderRepo.changeStatus(newStatus, orderId);
            resp.sendRedirect("/admin/orders.jsp");
        }
    }
}
