<%@ page import="org.example.jsp_shopping_website.repo.OrderRepo" %>
<%@ page import="org.example.jsp_shopping_website.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.jsp_shopping_website.entity.enums.Status" %><%--
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
    <link rel="stylesheet" href="../static/bootstrap.min.css">

</head>
<body>

<%
    List<Order> orders = OrderRepo.findAll();
%>

<div class="row">
    <div class="offset-3 col-6">
        <div class="p-4">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Order created at</th>
                    <th>User ID</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <% for (Order order : orders) {
                   if (order.getStatus().equals(Status.ARCHIVED)) {%>
                <tr>
                    <td><%= order.getId()%></td>
                    <td><%= order.showDateTime()%></td>
                    <td><%= order.getUserId()%></td>
                    <td><%= order.getStatus()%></td>
                </tr>
                <% }} %>
                </tbody>
            </table>
            <a class="btn btn-dark text-white offset-5" href="orders.jsp">Back</a>
        </div>
    </div>
</div>

</body>
</html>
