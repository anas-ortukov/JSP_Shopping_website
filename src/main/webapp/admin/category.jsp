<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="org.example.jsp_shopping_website.entity.Category" %>
<%@ page import="org.example.jsp_shopping_website.repo.CategoryRepo" %><%--
  Created by IntelliJ IDEA.
  User: azizo
  Date: 31/03/2024
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>

<%
    List<Category> categories = CategoryRepo.findAll();
%>

<div class="row">
    <div class="col-2 border-right p-4">
        <ul class="list-group">
            <a href="category.jsp" style="text-decoration: none">
                <li class="list-group-item bg-dark text-white">Category</li>
            </a>
            <a href="product.jsp" style="text-decoration: none">
                <li class="list-group-item">Product</li>
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
                <a href="addCategory.jsp" class="btn btn-dark">Add Category</a>
            </div>
        </div>
        <hr>

        <div class="p-4">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                <% for (Category category : categories) {%>
                <tr>
                    <td><%= category.getId()%>
                    </td>
                    <td><%= category.getName()%>
                    </td>
                    <td>
                        <a href="editCategory.jsp?id=<%=category.getId()%>" class="btn btn-warning text-white">Edit</a>
                        <a href="http://localhost:8080/category/delete?id=<%= category.getId()%>" class="btn btn-dark text-white">Delete</a>
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
