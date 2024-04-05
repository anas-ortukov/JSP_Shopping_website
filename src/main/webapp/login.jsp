<%--
  Created by IntelliJ IDEA.
  User: azizo
  Date: 05/04/2024
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
</head>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background-color: #f6f6f6;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .login-container {
        width: 300px;
        padding: 20px;
        background-color: #f9cc44;
        border-radius: 8px;
        box-shadow: 0 0 10px #e17630;
    }

    .login-container h2 {
        text-align: center;
        margin-bottom: 20px;
    }

    .login-form .form-group {
        margin-bottom: 20px;
    }

    .login-form .form-group label {
        display: block;
        margin-bottom: 5px;
    }

    .login-form .form-group input {
        width: 100%;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ffffff;
        border-radius: 5px;
    }

    .login-form button {
        width: 100%;
        padding: 10px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .notification {
        display: none;
        position: fixed;
        top: 15%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: #fdbf07;
        color: white;
        padding: 15px 20px;
        border-radius: 5px;
        box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
        text-align: center;
    }

</style>

<body>

<%
    String nextUrl = request.getParameter("nextUrl");
    String manyTimes = request.getParameter("multiple");
    if (manyTimes != null) { %>
<div id="notification" class="notification">
    Email or password is wrong. Please, try again!
</div>
<% } %>
<div class="login-container">
    <h2 class="text-white">Login</h2>
    <form class="login-form" method="post" action="/login/check?nextUrl=<%=nextUrl%>">
        <div class="form-group text-white">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group text-white">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-light fw-bold mt-4" style="color: #fdbf07">Login</button>
    </form>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        showNotification();
    });

    function showNotification() {
        var notification = document.getElementById("notification");
        notification.style.display = "block";
        setTimeout(function () {
            notification.style.display = "none";
        }, 3000);
    }

</script>
</body>
</html>
