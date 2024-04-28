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
    ProductRepo productRepo = new ProductRepo();
    List<Product> products = productRepo.findAll();
    NumberFormat format = NumberFormat.getInstance();
%>

<%@include file="admin_navbar.jsp"%>

<div class="row">
    <div class="col-10 offset-1">
            <div class="d-flex justify-content-end my-5 me-5">
                <a href="addProduct.jsp" class="btn btn-success">Add Product</a>
            </div>

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
                    <td><%= format.format(product.getPrice())%> $
                    </td>
                    <td><%= product.getCategory().getName()%>
                    </td>
                    <td><img src="/file/download?productId=<%=product.getId()%>" width="60" alt=".."> </td>
                    <td>
                        <a href="editProduct.jsp?id=<%=product.getId()%>" class="btn btn-warning text-white">Edit</a>
                        <a href="/admin/product/delete?id=<%= product.getId()%>"
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
