<%@ page import="java.util.UUID" %>
<%@ page import="org.example.jsp_shopping_website.entity.Category" %>
<%@ page import="org.example.jsp_shopping_website.repo.CategoryRepo" %>
<%@ page import="org.example.jsp_shopping_website.repo.ProductRepo" %>
<%@ page import="org.example.jsp_shopping_website.entity.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: azizo
  Date: 31/03/2024
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>

<%
    ProductRepo productRepo = new ProductRepo();
    CategoryRepo categoryRepo = new CategoryRepo();
    UUID id = UUID.fromString(request.getParameter("id"));
    Product product = productRepo.findById(id);
    List<Category> categories = categoryRepo.findAll();
%>

<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <h1>Edit Product</h1>
            <form method="post" action="/admin/product/edit" enctype="multipart/form-data">
                <input  type="hidden" value="<%= product.getId()%>" name="id">
                <input value="<%= product.getName()%>" name="name" class="form-control my-4" type="text" placeholder="Name" required>
                <input value="<%= product.getPrice()%>" name="price" class="form-control my-4" type="number" placeholder="Price" required>
                <select name="categoryId" class="form-control my-4">
                    <% for (Category category : categories) { %>
                    <option <%= product.getCategory().getId().equals(category.getId()) ? "selected":""%> value="<%=category.getId() %>"><%=category.getName()%>
                    </option>
                    <%}%>
                </select>
                <div class="mb-3">
                    <label for="formFile" class="form-label">Old Image: <img src="/file/download?productId=<%=product.getId()%>" width="60" alt=".."> </label>
                    <input class="form-control" type="file" id="formFile" name="image">
                </div>
                <div class="text-center">
                    <button class="btn btn-warning text-white mx-4 px-5" type="submit">Save</button>
                    <a class="btn btn-dark px-5" href="/admin/product.jsp">Back</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
