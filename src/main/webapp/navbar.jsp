<%@ page import="org.example.jsp_shopping_website.entity.enums.Role" %>
<%@ page import="org.example.jsp_shopping_website.entity.User" %>
<%@ page import="java.util.Optional" %>
<%@ page import="org.example.jsp_shopping_website.repo.UserRepo" %>
<%@ page import="static org.example.jsp_shopping_website.service.SessionService.getListBasketProducts" %><%--
  Created by IntelliJ IDEA.
  User: azizo
  Date: 05/04/2024
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Navbar</title>
</head>
<body>

<%
    Optional<User> currentUser = UserRepo.getUserBySession(request.getSession());
%>

<nav class="navbar bg-body-tertiary m-4 shadow rounded">
    <div class="container-fluid">
        <a class="navbar-brand " href="/"><img src="/photos/mango-logo.png" alt="Logo"
                                                   height="50"
                                                   class="d-inline-block align-top mx-3"></a>
        <a class="text-white offset-9" href="/basket.jsp">
            <button type="button" class="btn btn-warning position-relative text-white">Basket</button>
            <span class="top-0 start-100 translate-middle badge rounded-pill bg-danger">
            <%= getListBasketProducts(request.getSession()).size()%>
            </span>
        </a>
        <% if (currentUser.isEmpty()) { %>
        <a class="btn btn-dark text-white me-4 px-3" href="/login.jsp">Login</a>
        <% } else if (currentUser.get().getRole().equals(Role.USER)) { %>
        <div class="dropdown bg-light-subtle">
            <button class="btn btn-secondary dropdown-toggle me-3" type="button" data-bs-toggle="dropdown"
                    aria-expanded="false">
                <%= currentUser.get().getLastName() + " " + currentUser.get().getFirstName()%>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item " href="/client/my-orders.jsp">My Orders</a></li>
                <li><a class="dropdown-item" href="/auth/logout">Log out</a></li>
            </ul>
        </div>
        <% } else { %>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle me-3" type="button" data-bs-toggle="dropdown"
                    aria-expanded="false">
                <%= currentUser.get().getLastName() + " " + currentUser.get().getFirstName()%>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/client/my-orders.jsp">My Orders</a></li>
                <li><a class="dropdown-item" href="/admin/category.jsp">Admin Page</a></li>
                <li><a class="dropdown-item" href="/auth/logout">Log out</a></li>
            </ul>
        </div>
        <% } %>
    </div>
</nav>

</body>
</html>
