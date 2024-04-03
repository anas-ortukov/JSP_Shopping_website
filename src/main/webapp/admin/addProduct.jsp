<%@ page import="java.util.List" %>
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
    <title>Add Product</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>

<% List<Category> categories = CategoryRepo.findAll(); %>

<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <h1>Add Product</h1>
            <form method="post" action="http://localhost:8080/product/add" enctype="multipart/form-data">
                <input name="name" class="form-control my-3" type="text" placeholder="Name" required>
                <input name="price" class="form-control my-3" type="number" placeholder="Price" required>
                <div class="mb-3">
                    <label for="formFile" class="form-label">Choose Image for Product</label>
                    <input class="form-control" type="file" id="formFile" name="image" required>
                </div>
                <select name="categoryId" class="form-control my-3">
                    <option value="" selected disabled>Select Category</option>
                    <% for (Category category : categories) { %>
                    <option value="<%=category.getId() %>"><%=category.getName()%>
                    </option>
                    <%}%>
                </select>
                <div class="text-center">
                    <button class="btn btn-dark w-100">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
