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
            resp.sendRedirect(req.getContextPath() + "/login.jsp?nextUrl=/admin/admin.jsp");
        } else if (((User)currentUser).getRole().equals(Role.USER)) {
            resp.sendRedirect("/404");
        } else {
            req.getSession().setAttribute("currentUser", currentUser);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
