<%@ page import="org.example.jsp_shopping_website.repo.OrderRepo" %>
<%@ page import="org.example.jsp_shopping_website.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.jsp_shopping_website.repo.OrderProductRepo" %>
<%@ page import="org.example.jsp_shopping_website.entity.OrderProduct" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="org.example.jsp_shopping_website.repo.ProductRepo" %><%--
  Created by IntelliJ IDEA.
  User: azizo
  Date: 05/04/2024
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>My Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<%@include file="../navbar.jsp" %>
<%
    List<Order> orders = OrderRepo.findAll();
    NumberFormat numberFormat = NumberFormat.getNumberInstance();
%>

<div class="row">
    <div class="col-6 offset-3 mt-5 text-center">
        <div class="dropdown">

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Created at</th>
                    <th>Status</th>
                    <th>Order Details</th>
                </tr>
                </thead>
                <tbody>
                <% for (Order order : orders) {
                    if (order.getUserId().equals(currentUser.get().getId())) {
                %>
                <tr>
                    <p class="d-inline-flex gap-1">
                        <td><%= order.getId()%>
                        </td>
                        <td><%= order.showDateTime()%>
                        </td>
                        <td><%= order.getStatus()%>
                        </td>
                        <td>
                    <p class="d-inline-flex gap-1">
                        <button class="btn btn-light" type="button" data-bs-toggle="collapse"
                                data-bs-target="#<%="Order"+order.getId()%>" aria-expanded="false"
                                aria-controls="<%="Order"+order.getId()%>">
                            View order detail
                        </button>
                    </p>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <div class="collapse" id="<%="Order"+order.getId()%>">
                            <div class="card card-body">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>Product</th>
                                        <th>Amount</th>
                                        <th>Product price</th>
                                        <th>Order product price</th>
                                        <th>Total order price</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        int orderTotalPrice = 0;
                                        for (OrderProduct orderProduct : OrderProductRepo.getOrderProductsByOrderId(order.getId())) {
                                            orderTotalPrice += orderProduct.getTotalPrice(); %>
                                        <tr>

                                        <td><%= orderProduct.getProduct().getName() %> </td>
                                        <td><%= orderProduct.getAmount() %> </td>
                                        <td><%= numberFormat.format(orderProduct.getProduct().getPrice()) + " sum"  %> </td>
                                        <td><%= numberFormat.format(orderProduct.getTotalPrice()) + " sum"  %> </td>
                                            <td></td>
                                        </tr>
                                        <%    } %>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><%= numberFormat.format(orderTotalPrice) + " sum" %> </td>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </td>
                </tr>
                <% }
                } %>
                </tbody>
            </table>
        </div>

        <a class="btn btn-dark text-white text-center mt-2" href="/index.jsp">Back</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
