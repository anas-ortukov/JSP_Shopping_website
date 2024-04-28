<%@ page import="java.util.List" %>
<%@ page import="org.example.jsp_shopping_website.entity.Category" %>
<%@ page import="org.example.jsp_shopping_website.repo.CategoryRepo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>

<%
    CategoryRepo categoryRepo = new CategoryRepo();
    List<Category> categories = categoryRepo.findAll(); %>

<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <h1>Add Product</h1>
            <form method="post" action="/admin/product/add" enctype="multipart/form-data">
                <input name="name" class="form-control my-4" type="text" placeholder="Name" required="">
                <input name="price" class="form-control my-4" type="number" placeholder="Price" required="">
                <select name="categoryId" class="form-control my-4">
                    <option value="" selected disabled>Select Category</option>
                    <% for (Category category : categories) { %>
                    <option value="<%=category.getId() %>"><%=category.getName()%>
                    </option>
                    <%}%>
                </select>
                <div class="mb-3">
                    <label for="formFile" class="form-label">Choose Image for Product:</label>
                    <input class="form-control" type="file" id="formFile" name="image" required="">
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
