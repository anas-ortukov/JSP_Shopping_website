<%@ page import="java.util.Objects" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<%
    Object manyTimes = request.getSession().getAttribute("multiple");
    if (Objects.equals(manyTimes, true)) {
        request.getSession().removeAttribute("multiple");%>
<div id="notification" class="notification col-4 offset-4 shadow p-2 rounded mt-4 bg-warning text-white text-center">
    Email or password is wrong. Please, try again!
</div>
<% } %>

<div class="container offset-4 col-4">
    <div class="row justify-content-center mt-5">
        <div class="col-md-9">
            <div class="card shadow-sm">
                <div class=" bg-white text-center text-warning mt-4"><h4>Login
                </h4></div>
                <div class="card-body shadow-sm">
                    <form method="post" action="/auth/login">
                        <div class="mb-3">
                            <label for="loginEmail" class="form-label">Email address</label>
                            <input name="email" type="email" class="form-control" id="loginEmail" required="">
                        </div>
                        <div class="mb-3">
                            <label for="loginPassword" class="form-label">Password</label>
                            <input name="password" type="password" class="form-control" id="loginPassword" required="">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-warning text-white px-4">Login</button>
                        </div>
                    </form>
                    <p class="mt-3">Don't have an account? <a href="/sign-up.jsp">Sign Up</a></p>
                </div>
            </div>
        </div>
    </div>
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
