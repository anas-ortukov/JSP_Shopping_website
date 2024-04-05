package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.entity.User;
import org.example.jsp_shopping_website.repo.UserRepo;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Login check", urlPatterns = "/login/check")
public class LoginCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String nextUrl = req.getParameter("nextUrl");
        Optional<User> user = UserRepo.getUser(email, password);
        if (user.isPresent()) {
            req.getSession().setAttribute("currentUser", user.get());
            if (nextUrl.equals("null")) {
                resp.sendRedirect("/");
            }else {
                resp.sendRedirect(nextUrl);
            }
        } else {
            if (nextUrl == null) {
                resp.sendRedirect("/login.jsp?multiple=true");
            } else {
                resp.sendRedirect("/login.jsp?nextUrl=" + nextUrl+"&multiple=true");
            }
        }
    }
}
