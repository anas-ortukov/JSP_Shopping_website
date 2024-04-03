package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.repo.CategoryRepo;
import org.example.jsp_shopping_website.repo.ProductRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "deleteProduct", urlPatterns = "/product/delete")
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        ProductRepo.deleteById(id);
        resp.sendRedirect("/admin/product.jsp");
    }
}
