package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.entity.Category;
import org.example.jsp_shopping_website.repo.CategoryRepo;

import java.io.IOException;

@WebServlet(name = "addCategory", urlPatterns = "/category/add")
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Category category = new Category();
        category.setName(name);
        CategoryRepo.save(category);
        resp.sendRedirect("/admin/category.jsp");
    }
}
