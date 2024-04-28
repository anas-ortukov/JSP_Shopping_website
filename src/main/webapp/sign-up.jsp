<%@ page import="java.util.Objects" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<%
    Object emailStatus = request.getSession().getAttribute("inUse");
    if (Objects.equals(emailStatus, true)) {
    request.getSession().removeAttribute("inUse");%>
<div id="notification" class="notification col-4 offset-4 shadow p-2 rounded mt-4 bg-warning text-white text-center">
    Email is already in use. Please, Log in
</div>
<% } %>


<div class="container offset-4 col-4">
    <div class="row justify-content-center mt-5">
        <div class="col-md-9">
            <div class="card shadow-sm">
                <div class=" bg-white text-center text-warning mt-4"><h4>Sign up
                </h4></div>
                <div class="card-body shadow-sm">
                    <form method="post" action="/auth/sign-up">
                        <div class="mb-3">
                            <label for="firstName" class="form-label">First name</label>
                            <input name="firstName" type="text" class="form-control" id="firstName" required>
                        </div>
                        <div class="mb-3">
                            <label for="lastName" class="form-label">Last name</label>
                            <input name="lastName" type="text" class="form-control" id="lastName" required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email address</label>
                            <input name="email" type="email" class="form-control" id="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input name="password" type="password" class="form-control" id="password" required>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-warning text-white px-4">Sign up</button>
                        </div>
                    </form>
                    <p class="mt-3">Already have an account? <a href="/login.jsp">Login</a></p>
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