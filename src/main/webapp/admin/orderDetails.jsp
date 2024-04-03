<%@ page import="java.util.UUID" %>
<%@ page import="org.example.jsp_shopping_website.repo.OrderRepo" %>
<%@ page import="org.example.jsp_shopping_website.entity.Order" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="org.example.jsp_shopping_website.repo.OrderProductRepo" %>
<%@ page import="org.example.jsp_shopping_website.repo.ProductRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.jsp_shopping_website.entity.OrderProduct" %>
<%@ page import="org.example.jsp_shopping_website.service.BasketService" %>
<%@ page import="org.example.jsp_shopping_website.entity.Product" %>
<%@ page import="org.example.jsp_shopping_website.service.OrderService" %>
<%@ page import="org.example.jsp_shopping_website.entity.enums.Status" %><%--
  Created by IntelliJ IDEA.
  User: azizo
  Date: 03/04/2024
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Details</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>

<%
    Integer orderId = Integer.parseInt(request.getParameter("orderId"));
    Order order = OrderRepo.getOrderById(orderId);
    List<OrderProduct> orderProducts = OrderProductRepo.getOrderProductsByOrderId(orderId);
    NumberFormat numberFormat = NumberFormat.getNumberInstance();
%>

<div class="row">
    <div class="offset-4 col-4 py-3">
        <h1 class="offset-3">Order Details</h1>
        <div class="card" style="width: 100%;">
            <div class="card-body">
                <% for (OrderProduct orderProduct : orderProducts) {
                    Product productById = ProductRepo.findById(orderProduct.getProductId());
                %>
                <div class="row bg-light rounded my-5">
                    <div class="col-3 py-2">

                        <img src="/file/download?productId=<%=orderProduct.getProductId()%>"
                             class="card-img-bottom img-fluid mx-auto d-block" alt="...">
                    </div>
                    <div class="col-7 py-5 ">
                        <h5 class="my-3">Product price: <span style="font-weight: normal"><%=numberFormat.format(productById.getPrice())%> sum</span></h5>
                        <h5 class="my-3">Product count: <span style="font-weight: normal"><%= orderProduct.getAmount() %> pieces</span></h5>
                        <h5 class="my-3">Total order's price: <span style="font-weight: normal"><%=numberFormat.format(orderProduct.getTotalPrice()) %>sum</span></h5>
                    </div>
                </div>
                <% } %>
                <div class="card bg-light rounded py-3 mb-3 text-center">
                    <h5>Order created at: <span style="font-weight: normal"> <%= order.showDateTime()%></span></h5>
                    <h5 class="">Order status: <span style="font-weight: normal"> <%= order.getStatus()%></span></h5>
                    <h5>Overall: <span style="font-weight: normal"> <%=numberFormat.format(OrderService.getOverallBasketPrice(orderProducts))%>sum</span></h5>
                </div>
                <form action="update/order" method="post">
                    <input name="orderId" hidden="hidden" value="<%=orderId%>">
                    <div style="display: flex; align-items: center;" class="mt-3">
                        <select name="orderStatus" class="form-select" style="width: 40%">
                            <%
                                for (Status status : Status.values()) {
                                    if (status.equals(order.getStatus())) {
                            %>
                            <option selected disabled><%=status%>
                            </option>
                            <% } else { %>
                            <option><%=status%>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                        <div class="offset-3">
                            <button class="btn btn-warning text-white">Change status</button>
                            <a class="btn btn-dark text-white ms-4" href="orders.jsp">Back</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
