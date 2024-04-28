package org.example.jsp_shopping_website.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.example.jsp_shopping_website.entity.User;
import org.example.jsp_shopping_website.entity.enums.Role;
import org.example.jsp_shopping_website.repo.UserRepo;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Auth servlet", urlPatterns = "/auth/*")
public class AuthServlet extends HttpServlet {

    UserRepo userRepo = new UserRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        //check for request API
        if (req.getRequestURI().equals("/auth/login")) {
            Optional<User> user = userRepo.getUser(email, password);
            //check if user details are right
            if (user.isPresent()) {
                saveUserIntoSession(user.get(), req, resp);
                return;
            }
            resp.sendRedirect("/login.jsp");
            req.getSession().setAttribute("multiple", true);
        } else if (req.getRequestURI().equals("/auth/sign-up")) {
            if (userRepo.registeredEmail(email)) {
                req.getSession().setAttribute("inUse", true);
                resp.sendRedirect("/sign-up.jsp");
                return;
            }
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = User.builder().email(email).password(hashpw).firstName(firstName).lastName(lastName).role(Role.USER).build();
            userRepo.save(user);
            saveUserIntoSession(user, req, resp);
        }

    }

    @SneakyThrows
    private void saveUserIntoSession(User user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("currentUser", user);
        Object object = req.getSession().getAttribute("nextUrl");
        if (object != null) {
            resp.sendRedirect(object.toString());
        }else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("currentUser", null);
        resp.sendRedirect("/");
    }
}
