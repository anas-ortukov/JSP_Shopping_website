<%@ page import="org.example.jsp_shopping_website.entity.Category" %>
<%@ page import="org.example.jsp_shopping_website.repo.CategoryRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.jsp_shopping_website.entity.Product" %>
<%@ page import="org.example.jsp_shopping_website.repo.ProductRepo" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="org.example.jsp_shopping_website.service.SessionService" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mango Market</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>

        .card-img-top {
            width: 250px;
            height: 300px;
            object-fit: scale-down;
        }

        .card {
            transition: box-shadow 0.3s;
            border-radius: 10px;
        }

        .card:hover {
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
            border-radius: 10px;
        }
    </style>
</head>
<body>

<%
    ProductRepo productRepo = new ProductRepo();
    CategoryRepo categoryRepo = new CategoryRepo();
    UUID categoryId = SessionService.searchFilter(request.getSession());
    NumberFormat numberFormat = NumberFormat.getNumberInstance();
    List<Product> products = productRepo.findAll(); %>


<%@include file="navbar.jsp" %>

<% Object orderNotification = request.getSession().getAttribute("orderNotification");
    if (Objects.equals(orderNotification, true)) {
        request.getSession().removeAttribute("orderNotification"); %>
<div id="notification" class="notification col-4 offset-4 shadow p-2 rounded mt-4 bg-warning text-white text-center">
    Your order has been received, you can check it in "My Orders" page
</div>
<% }%>

<div class="row" style="width: 100%">
    <div class="col-2 p-4 mx-3">

        <ul class="list-group">
            <a href="/search" class="rounded"
               style="text-decoration: none; font-weight: bold; font-size: 18px">
                <li class="list-group-item m-2 rounded <%= categoryId == null ?"bg-dark text-white" : "" %>">
                    All
                </li>
            </a>
            <%for (Category category : categoryRepo.findAll()) { %>
            <a href="/search?categoryId=<%=category.getId()%>"
               class="rounded"
               style="text-decoration: none; font-weight: bold; font-size: 18px">
                <li class="list-group-item m-2 rounded <%= category.getId().equals(categoryId) ?"bg-dark text-white" : "" %>">
                    <%=category.getName()%>
                </li>
            </a>
            <% } %>
        </ul>
    </div>
    <div class="col-8 offset-1 pt-4">

        <div class="row row-cols-1 row-cols-md-3 g-4">
            <% for (Product product : products) {
                if (categoryId == null || categoryId.equals(product.getCategory().getId())) {%>
            <div class="col">
                <div class="card h-100">
                    <img src="/file/download?productId=<%=product.getId()%>"
                         class="card-img-top img-fluid mx-auto d-block" alt="...">
                    <div class="card-body text-center p-4">
                        <h5 class="card-title text-black"><%=product.getName()%>
                        </h5>
                        <p class="card-text text-black">Price: <%=numberFormat.format(product.getPrice())%> $</p>
                        <% if (SessionService.getBasketProductById(product.getId(), request.getSession()).isPresent()) { %>
                        <form action="/basket/remove" method="post">
                            <button class="btn  btn-secondary text-white" name="productId"
                                    value="<%= product.getId()%>">
                                Remove from Basket
                            </button>
                        </form>
                        <% } else { %>
                        <a class="btn btn-warning text-white" href="/basket/add?productId=<%= product.getId()%>">
                            Add to Basket</a>
                        <%}%>
                    </div>
                </div>
            </div>
            <% }
            } %>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
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
