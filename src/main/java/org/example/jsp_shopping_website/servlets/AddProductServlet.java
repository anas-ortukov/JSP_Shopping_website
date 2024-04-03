package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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

@WebServlet(name = "addProduct", urlPatterns = "/product/add")
@MultipartConfig
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Integer price = Integer.parseInt(req.getParameter("price"));
        UUID categoryId = UUID.fromString(req.getParameter("categoryId"));
        Part imagePart = req.getPart("image");
        byte[] imageArray= imagePart.getInputStream().readAllBytes();
        Product product = new Product(null, name, price, categoryId, imageArray);
        ProductRepo.save(product);
        resp.sendRedirect("/admin/product.jsp");
    }
}
