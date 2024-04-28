package org.example.jsp_shopping_website.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jsp_shopping_website.entity.User;
import org.example.jsp_shopping_website.entity.enums.Role;

import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*")
public class AdminPageFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Object currentUser = req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("/login.jsp");
            req.getSession().setAttribute("nextUrl", req.getRequestURI());
        } else if (((User)currentUser).getRole().equals(Role.USER)) {
            resp.sendRedirect("/");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
