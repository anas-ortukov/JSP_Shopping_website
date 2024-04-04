<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="org.example.jsp_shopping_website.entity.Category" %>
<%@ page import="org.example.jsp_shopping_website.repo.CategoryRepo" %>
<%@ page import="org.example.jsp_shopping_website.entity.OrderProduct" %>
<%@ page import="org.example.jsp_shopping_website.repo.OrderRepo" %>
<%@ page import="org.example.jsp_shopping_website.entity.Order" %>
<%@ page import="org.example.jsp_shopping_website.entity.enums.Status" %>
<%@ page import="java.text.NumberFormat" %><%--
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
    List<Order> orders = OrderRepo.findAll();
    NumberFormat numberFormat = NumberFormat.getNumberInstance();
    List<Order> newOrders = OrderRepo.getOrderByStatus(orders, Status.NEW);
    List<Order> inProgressOrders = OrderRepo.getOrderByStatus(orders, Status.IN_PROGRESS);
    List<Order> completedOrders = OrderRepo.getOrderByStatus(orders, Status.COMPLETED);
%>

<div class="row">

    <div class="col-2 border-right p-4">
        <ul class="list-group">
            <a href="category.jsp" style="text-decoration: none">
                <li class="list-group-item ">Category</li>
            </a>
            <a href="product.jsp" style="text-decoration: none">
                <li class="list-group-item">Product</li>
            </a>
            <a href="orders.jsp" style="text-decoration: none">
                <li class="list-group-item bg-dark text-white">Orders</li>
            </a>
            <a href="http://localhost:8080" style="text-decoration: none">
                <li class="list-group-item">Go back to Menu</li>
            </a>
        </ul>
    </div>
    <div class="col-9">

        <div class="row">
            <div class="col-2 offset-10 p-4">
                <%--                TODO create Archive Page--%>
                <a href="archive.jsp" class="btn btn-dark">Go to Archieve</a>
            </div>
        </div>

        <h2 class="text-center">Orders</h2>

        <hr>
    </div>
    <div class="col-3 offset-2">
        <h3 class="text-center">New</h3>
        <%
            for (Order order : newOrders) {
        %>
            <div class="card bg-light rounded my-3 py-4">
                <div class="card offset-1">
                    <div class="card-body">
                        <h5 class="card-text">Order ID: <%= order.getId()%></h5>
                        <p class="card-title">Products: <%= OrderRepo.getProductAmount(order)%></p>
                        <p class="card-title">Order's Total Price: <%= numberFormat.format(OrderRepo.getOrderSum(order))%></p>
                        <a class="btn btn-warning text-white my-3" href="orderDetails.jsp?orderId=<%=order.getId()%>">Order Details</a>
                    </div>
                </div>
            </div>
        <%
            }
        %>
    </div>
    <div class="col-3">
        <h3 class="text-center">In Progress</h3>
        <%
            for (Order order : inProgressOrders) {
        %>
        <div class="card bg-light rounded my-3 py-4">
            <div class="card offset-1">
                <div class="card-body">
                    <h5 class="card-text">Order ID: <%= order.getId()%></h5>
                    <p class="card-title">Products: <%= OrderRepo.getProductAmount(order)%></p>
                    <p class="card-title">Order's Total Price: <%= numberFormat.format(OrderRepo.getOrderSum(order))%></p>
                    <a class="btn btn-warning text-white my-3" href="orderDetails.jsp?orderId=<%=order.getId()%>">Order Details</a>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <div class="col-3">
        <h3 class="text-center">Completed</h3>
        <%
            for (Order order : completedOrders) {
        %>
        <div class="card bg-light rounded my-3 py-4">
            <div class="card offset-1">
                <div class="card-body">
                    <h5 class="card-text">Order ID: <%= order.getId()%></h5>
                    <p class="card-title">Products: <%= OrderRepo.getProductAmount(order)%></p>
                    <p class="card-title">Order's Total Price: <%= numberFormat.format(OrderRepo.getOrderSum(order))%></p>
                    <a class="btn btn-warning text-white my-3" href="orderDetails.jsp?orderId=<%=order.getId()%>">Order Details</a>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>

</body>
</html>
