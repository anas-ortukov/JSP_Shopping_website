package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.service.BasketService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "BasketProductAmountRemove", urlPatterns = "/basket/product/remove/amount")
public class RemoveAmountBasketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("basketProductId").equals("denied")) {
            resp.sendRedirect("/client/basket.jsp");
            return;
        }
        UUID productId = UUID.fromString(req.getParameter("basketProductId"));
        BasketService.removeAmountBasketProduct(productId);
        resp.sendRedirect("/client/basket.jsp");
    }
}
