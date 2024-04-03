package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.service.BasketService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "BasketProductAmountAdd", urlPatterns = "/basket/product/add/amount")
public class AddAmountBasketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID productId = UUID.fromString(req.getParameter("basketProductId"));
        BasketService.addAmountBasketProduct(productId);
        resp.sendRedirect("/client/basket.jsp");
    }
}
