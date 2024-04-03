<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="org.example.jsp_shopping_website.entity.Category" %>
<%@ page import="org.example.jsp_shopping_website.repo.CategoryRepo" %>
<%@ page import="org.example.jsp_shopping_website.entity.Product" %>
<%@ page import="org.example.jsp_shopping_website.repo.ProductRepo" %>
<%@ page import="javax.swing.text.NumberFormatter" %>
<%@ page import="java.text.NumberFormat" %><%--
  Created by IntelliJ IDEA.
  User: azizo
  Date: 31/03/2024
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>

<%
    List<Product> products = ProductRepo.findAll();
    NumberFormat format = NumberFormat.getInstance();
%>

<div class="row">
    <div class="col-2 border-right p-4">
        <ul class="list-group">
            <a href="category.jsp" style="text-decoration: none">
                <li class="list-group-item ">Category</li>
            </a>
            <a href="product.jsp" style="text-decoration: none">
                <li class="list-group-item bg-dark text-white">Product</li>
            </a>
            <a href="orders.jsp" style="text-decoration: none">
                <li class="list-group-item">Orders</li>
            </a>
            
            <a href="http://localhost:8080" style="text-decoration: none">
                <li class="list-group-item">Go back to Menu</li>
            </a>
        </ul>
    </div>
    <div class="col-9">

        <div class="row">
            <div class="col-2 offset-10 p-4">
                <a href="addProduct.jsp" class="btn btn-dark">Add Product</a>
            </div>
        </div>
        <hr>

        <div class="p-4">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Image</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                <% for (Product product : products) {%>
                <tr style="vertical-align: middle">
                    <td><%= product.getId()%>
                    </td>
                    <td><%= product.getName()%>
                    </td>
                    <td><%= format.format(product.getPrice())%> sum
                    </td>
                    <td><%= product.getCategoryName()%>
                    </td>
                    <td><img src="/file/download?productId=<%=product.getId()%>" width="60" alt=".."> </td>
                    <td>
                        <a href="editProduct.jsp?id=<%=product.getId()%>" class="btn btn-warning text-white">Edit</a>
                        <a href="http://localhost:8080/product/delete?id=<%= product.getId()%>"
                           class="btn btn-dark text-white">Delete</a>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
