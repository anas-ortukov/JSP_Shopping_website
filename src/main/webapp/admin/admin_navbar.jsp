<%@ page import="org.example.jsp_shopping_website.entity.User" %>
<%@ page import="org.example.jsp_shopping_website.repo.UserRepo" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Navbar</title>
</head>
<body>

<%
    Optional<User> currentUser = UserRepo.getUserBySession(request.getSession());
    String uri = request.getRequestURI();
%>

<div class="col-10 offset-1">
<nav class="navbar navbar-expand bg-body-tertiary m-4 shadow rounded">
    <div class="container-fluid">
        <a class="navbar-brand" href="/"><img src="/photos/mango-logo.png" alt="Logo"
                                                   height="50"
                                                   class="d-inline-block align-top mx-3"></a>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-5">
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%= uri.contains("category")?"active bg-secondary text-white rounded":""%>" href="/admin/category.jsp">Category</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%= uri.contains("product")?"active bg-secondary text-white rounded":""%>" href="/admin/product.jsp">Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%= uri.contains("order")?"active bg-secondary text-white rounded":""%>" href="/admin/orders.jsp">Orders</a>
            </li>
        </ul>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle me-3" type="button" data-bs-toggle="dropdown"
                    aria-expanded="false">
                <%= currentUser.get().getLastName() + " " + currentUser.get().getFirstName()%>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/client/my-orders.jsp">My Orders</a></li>
                <li><a class="dropdown-item" href="/">Main Page</a></li>
                <li><a class="dropdown-item" href="/auth/logout">Log out</a></li>
            </ul>
        </div>
    </div>
</nav>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
