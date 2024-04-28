package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.entity.Product;
import org.example.jsp_shopping_website.repo.ProductRepo;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "File Download", value = "/file/download")
public class FileDownloadServlet extends HttpServlet {

    ProductRepo productRepo = new ProductRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID productId = UUID.fromString(req.getParameter("productId"));
        Product product = productRepo.findById(productId);
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(product.getImage());
    }
}
