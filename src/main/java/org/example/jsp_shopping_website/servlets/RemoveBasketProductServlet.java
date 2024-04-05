package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.service.BasketService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "RemoveBasketProduct", urlPatterns = "/basket/product/remove")
public class RemoveBasketProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID productId = UUID.fromString(req.getParameter("productId"));
        BasketService.removeBasketProduct(productId, req.getSession());
        String categoryId = req.getParameter("categoryId");
        if (categoryId.equals("null")) {
            resp.sendRedirect("http://localhost:8080");
        }else {
            resp.sendRedirect("http://localhost:8080?categoryId=" + categoryId);
        }
    }
}
