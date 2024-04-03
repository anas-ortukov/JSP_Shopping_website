<%@ page import="org.example.jsp_shopping_website.entity.Category" %>
<%@ page import="org.example.jsp_shopping_website.repo.CategoryRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.jsp_shopping_website.entity.Product" %>
<%@ page import="org.example.jsp_shopping_website.repo.ProductRepo" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.Objects" %>
<%@ page import="org.example.jsp_shopping_website.service.BasketService" %>
<%@ page import="java.text.NumberFormat" %><%--
  Created by IntelliJ IDEA.
  User: azizo
  Date: 31/03/2024
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mango Market</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
    <style>

        .card-img-top {
            width: 250px;
            height: 300px;
            object-fit: scale-down;
        }

        .card {
            transition: box-shadow 0.3s;
            border-radius: 10px;
        }

        .card:hover {
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
            border-radius: 10px;
        }
    </style>
</head>
<body>

<% UUID categoryId = null;
    if (request.getParameter("categoryId") != null) {
        categoryId = UUID.fromString(request.getParameter("categoryId"));
    }
    NumberFormat numberFormat = NumberFormat.getNumberInstance();
    List<Product> products = ProductRepo.findAll();
%>


<nav class="navbar bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand mx-3" href="http://localhost:8080"><img src="photos/mango_logo.png" alt="Logo" height="80"
                                                                  class="d-inline-block align-top"></a>
        <a class="text-white offset-9" href="client/basket.jsp">
            <button type="button" class="btn btn-warning position-relative text-white">Basket</button>
            <span class="top-0 start-100 translate-middle badge rounded-pill bg-danger">
            <%= BasketService.getBasketProductAmount()%>
            </span>
        </a>
        <a class="btn btn-dark text-white mx-3" href="admin/admin.jsp">Admin</a>
    </div>
</nav>
<div class="row" style="width: 100%">
    <div class="col-2 p-4 mx-3">

        <ul class="list-group">
            <%for (Category category : CategoryRepo.findAll()) { %>
            <a href="http://localhost:8080?categoryId=<%=category.getId()%>"
               class="<%= category.getId().equals(categoryId) ?"bg-warning rounded" : "" %>"
               style="text-decoration: none; font-weight: bold; font-size: 18px">
                <li class="list-group-item m-2 rounded">
                    <%=category.getName()%>
                </li>
            </a>
            <% } %>
            <a href="http://localhost:8080" class="<%= categoryId == null ?"bg-warning rounded" : "" %>"
               style="text-decoration: none; font-weight: bold; font-size: 18px">
                <li class="list-group-item m-2 rounded">
                    All
                </li>
            </a>
        </ul>
    </div>
    <div class="col-8 offset-1 pt-4">

        <div class="row row-cols-1 row-cols-md-3 g-4">
            <% for (Product product : products) {
                if (categoryId == null || categoryId.equals(product.getCategoryId())) {%>
            <div class="col">
                <div class="card h-100">
                    <img src="/file/download?productId=<%=product.getId()%>" class="card-img-top img-fluid mx-auto d-block" alt="...">
                    <div class="card-body text-center p-4">
                        <h5 class="card-title text-black"><%=product.getName()%>
                        </h5>
                        <p class="card-text text-black">Narxi: <%=numberFormat.format(product.getPrice())%> sum</p>
                        <% if (BasketService.checkBasketProduct(product.getId())) { %>
                        <a class="btn  text-white" style="background-color: navy;"
                           href="/basket/product/remove?productId=<%= product.getId() %>&url=http://localhost:8080">
                            Remove from Basket</a>
                        <% } else { %>
                        <a class="btn btn-warning text-white" href="/basket/add?productId=<%= product.getId() %>">
                            Add to Basket</a>
                        <%}%>
                    </div>
                </div>
            </div>
            <% }
            } %>
        </div>
    </div>
</div>
</body>
</html>
