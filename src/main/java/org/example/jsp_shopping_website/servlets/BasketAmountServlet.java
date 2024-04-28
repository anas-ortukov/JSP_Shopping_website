package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.entity.OrderProduct;
import org.example.jsp_shopping_website.service.SessionService;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "Basket Amount servlet", urlPatterns = "/basket/amount/*")
public class BasketAmountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/basket/amount/add")) {
            UUID productId = UUID.fromString(req.getParameter("productId"));
            SessionService.getBasketProductById(productId, req.getSession()).
                    ifPresent(basketProduct -> basketProduct.setAmount(basketProduct.getAmount() + 1));
            resp.sendRedirect("/basket.jsp");
        }else if (req.getRequestURI().equals("/basket/amount/remove")) {
            UUID productId = UUID.fromString(req.getParameter("productId"));
            Optional<OrderProduct> basketProductById = SessionService.getBasketProductById(productId, req.getSession());
            if (basketProductById.isPresent()) {
                Integer originAmount = basketProductById.get().getAmount();
                if (originAmount > 1) {
                    basketProductById.get().setAmount(originAmount - 1);
                }
            }
            resp.sendRedirect("/basket.jsp");
        }
    }
}
