<%@ page import="java.util.UUID" %>
<%@ page import="org.example.jsp_shopping_website.entity.Category" %>
<%@ page import="org.example.jsp_shopping_website.repo.CategoryRepo" %><%--
  Created by IntelliJ IDEA.
  User: azizo
  Date: 31/03/2024
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Category</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>

<%
    CategoryRepo categoryRepo = new CategoryRepo();
    UUID id = UUID.fromString(request.getParameter("id"));
    Category category = categoryRepo.findById(id);
%>

<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <h1>Edit Category</h1>
            <form method="post" action="/admin/category/edit">
                <input  type="hidden" value="<%= category.getId()%>" name="id">
                <input value="<%= category.getName()%>" name="name" class="form-control my-4" type="text" placeholder="Name" required>
                <div class="text-center">
                    <button class="btn btn-warning text-white mx-4 px-5" type="submit">Save</button>
                    <a class="btn btn-dark px-5" href="/admin/category.jsp">Back</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
