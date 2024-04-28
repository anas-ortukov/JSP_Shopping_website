package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.entity.Category;
import org.example.jsp_shopping_website.repo.CategoryRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "Admin category servlet", urlPatterns = "/admin/category/*")
public class CategoryServlet extends HttpServlet {
    CategoryRepo categoryRepo = new CategoryRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/admin/category/add")) {
            String name = req.getParameter("name");
            Category category = new Category();
            category.setName(name);
            categoryRepo.save(category);
        }else if (req.getRequestURI().equals("/admin/category/edit")) {
            UUID id = UUID.fromString(req.getParameter("id"));
            String name = req.getParameter("name");
            categoryRepo.update(new Category(id, name));
        }
        resp.sendRedirect("/admin/category.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        categoryRepo.deleteById(id);
        resp.sendRedirect("/admin/category.jsp");
    }
}
