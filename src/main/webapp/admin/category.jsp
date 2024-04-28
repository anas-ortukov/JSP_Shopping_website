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
    CategoryRepo categoryRepo = new CategoryRepo();
    List<Category> categories = categoryRepo.findAll();
%>

<%@include file="admin_navbar.jsp" %>

<div class="row">
    <div class="col-10 offset-1">
        <div class="d-flex justify-content-end my-5 me-5">
            <a href="addCategory.jsp" class="btn btn-success">Add Category</a>
        </div>

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
                <tr style="vertical-align: middle">
                    <td><%= category.getId()%>
                    </td>
                    <td><%= category.getName()%>
                    </td>
                    <td>
                        <a href="editCategory.jsp?id=<%=category.getId()%>" class="btn btn-warning text-white">Edit</a>
                        <a href="/admin/category/delete?id=<%= category.getId()%>" class="btn btn-dark text-white">Delete</a>
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
