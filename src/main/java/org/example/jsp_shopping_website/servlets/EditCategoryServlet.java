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

@WebServlet(name = "editCategory", urlPatterns = "/category/edit")
public class EditCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        String name = req.getParameter("name");
        CategoryRepo.edit(new Category(id, name));
        resp.sendRedirect("/admin/category.jsp");
    }
}
