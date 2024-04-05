package org.example.jsp_shopping_website.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/login.jsp"})
public class LoginPageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Object currentUser = req.getSession().getAttribute("currentUser");
        if (currentUser != null) {
            resp.sendRedirect("/404");
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
