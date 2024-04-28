<%@ page import="org.example.jsp_shopping_website.repo.OrderRepo" %>
<%@ page import="org.example.jsp_shopping_website.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.jsp_shopping_website.entity.enums.Status" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="org.example.jsp_shopping_website.entity.OrderProduct" %>
<%@ page import="org.example.jsp_shopping_website.repo.OrderProductRepo" %><%--
  Created by IntelliJ IDEA.
  User: azizo
  Date: 04/04/2024
  Time: 09:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Archive</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<%
    OrderRepo orderRepo = new OrderRepo();
    List<Order> orders = orderRepo.findAll();
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
                    <th>Completed at</th>
                    <th>Status</th>
                    <th>Order Details</th>
                </tr>
                </thead>
                <tbody>
                <% for (Order order : orders) {
                    if (order.getStatus().equals(Status.ARCHIVED)) {
                %>
                <tr>
                    <p class="d-inline-flex gap-1">
                        <td><%= order.getId()%>
                        </td>
                        <td><%= order.showDateTime(order.getDateTime())%>
                        </td>
                        <td><%= order.showDateTime(order.getCompletedTime())%>
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
                    <td colspan="5">
                        <div class="collapse" id="<%="Order"+order.getId()%>">
                            <div class="card card-body">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>Product</th>
                                        <th>Amount</th>
                                        <th>Product price</th>
                                        <th>Total product price</th>
                                        <th>Total order price</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        int orderTotalPrice = 0;
                                        for (OrderProduct orderProduct : order.getOrderProducts()) {
                                            orderTotalPrice += orderProduct.getTotalPrice(); %>
                                    <tr>

                                        <td><%= orderProduct.getProduct().getName() %> </td>
                                        <td><%= orderProduct.getAmount() %> </td>
                                        <td><%= numberFormat.format(orderProduct.getProduct().getPrice()) + " $"  %> </td>
                                        <td><%= numberFormat.format(orderProduct.getTotalPrice()) + " $"  %> </td>
                                        <td></td>
                                    </tr>
                                    <%    } %>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><%= numberFormat.format(orderTotalPrice) + " $" %> </td>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </td>
                </tr>
                <%
                        }} %>
                </tbody>
            </table>
        </div>

        <a class="btn btn-dark text-white text-center mt-2" href="/admin/orders.jsp">Back</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>
