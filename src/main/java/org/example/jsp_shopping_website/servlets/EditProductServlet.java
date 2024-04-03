package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.jsp_shopping_website.entity.Category;
import org.example.jsp_shopping_website.entity.Product;
import org.example.jsp_shopping_website.repo.CategoryRepo;
import org.example.jsp_shopping_website.repo.ProductRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "editProduct", urlPatterns = "/product/edit")
public class EditProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        String name = req.getParameter("name");
        Integer price = Integer.parseInt(req.getParameter("price"));
        UUID categoryId = UUID.fromString(req.getParameter("categoryId"));
        Part image = req.getPart("image");
        ProductRepo.edit(new Product(id, name, price, categoryId, image.getInputStream().readAllBytes()));
        resp.sendRedirect("/admin/product.jsp");
    }
}
