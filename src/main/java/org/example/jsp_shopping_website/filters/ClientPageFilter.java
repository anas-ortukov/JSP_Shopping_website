package org.example.jsp_shopping_website.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/order/confirm", "/client/*"})
public class ClientPageFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Object currentUser = req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("/login.jsp");
            req.getSession().setAttribute("nextUrl", req.getRequestURI());
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
