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

@WebServlet(name = "Admin product servlet", urlPatterns = "/admin/product/*")
@MultipartConfig
public class ProductServlet extends HttpServlet {

    CategoryRepo categoryRepo = new CategoryRepo();
    ProductRepo productRepo = new ProductRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/admin/product/add")) {
            String name = req.getParameter("name");
            Integer price = Integer.parseInt(req.getParameter("price"));
            UUID categoryId = UUID.fromString(req.getParameter("categoryId"));
            Category category = categoryRepo.findById(categoryId);
            Part imagePart = req.getPart("image");
            byte[] imageArray= imagePart.getInputStream().readAllBytes();
            Product product = Product.builder()
                    .category(category)
                    .name(name)
                    .price(price)
                    .image(imageArray)
                    .build();
            productRepo.save(product);
        }else if (req.getRequestURI().equals("/admin/product/edit")) {
            UUID id = UUID.fromString(req.getParameter("id"));
            Product product = productRepo.findById(id);
            String name = req.getParameter("name");
            Integer price = Integer.parseInt(req.getParameter("price"));
            UUID categoryId = UUID.fromString(req.getParameter("categoryId"));
            Part image = req.getPart("image");
            byte[] imageArray= image.getInputStream().readAllBytes();
            if (imageArray.length == 0) {
                imageArray = product.getImage();
            }
            productRepo.update(new Product(id, name, price, categoryRepo.findById(categoryId), imageArray));
        }
        resp.sendRedirect("/admin/product.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        productRepo.deleteById(id);
        resp.sendRedirect("/admin/product.jsp");
    }
}
