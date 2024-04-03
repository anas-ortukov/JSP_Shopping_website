<%--
  Created by IntelliJ IDEA.
  User: azizo
  Date: 31/03/2024
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Category</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>
<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <h1>Add Category</h1>
            <form method="post" action="http://localhost:8080/category/add">
                <input name="name" class="form-control my-3" type="text" placeholder="Name" required>
                <div class="text-center">
                    <button class="btn btn-dark w-100">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
