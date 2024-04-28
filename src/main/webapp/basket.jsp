<%@ page import="java.text.NumberFormat" %>
<%@ page import="org.example.jsp_shopping_website.entity.OrderProduct" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.jsp_shopping_website.service.OrderService" %>
<%@ page import="org.example.jsp_shopping_website.service.SessionService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
</head>
<body>

<%
    List<OrderProduct> basketProducts = SessionService.getListBasketProducts(request.getSession());
    NumberFormat numberFormat = NumberFormat.getNumberInstance();
    if (basketProducts.isEmpty()) {
        response.sendRedirect("/empty-basket.jsp");
        return;
    }
%>

<div class="row">
    <div class="offset-4 col-6 py-3">
        <h1 class="offset-3">Basket</h1>
        <div class="card" style="width: 100%;">
            <div class="card-body">
                <% for (OrderProduct basketProduct : basketProducts) { %>
                <div class="row">
                    <div class="col-3 py-2">

                        <img src="/file/download?productId=<%=basketProduct.getProduct().getId()%>" class="card-img-top img-fluid mx-auto d-block" alt="...">
                    </div>
                    <div class="col-7 py-5">
                        <h4><%=numberFormat.format(basketProduct.getProduct().getPrice())%> $</h4>
                        <div style="display: flex; align-items: center;" class="my-3">
                            <a type="button" class="btn btn-light" style="font-weight: bold"
                               href="/basket/amount/add?productId=<%= basketProduct.getProduct().getId()%>">+</a>
                            <p style="margin: 10px;" ><%= basketProduct.getAmount() %> pieces</p>
                            <a type="button" class="btn btn-light" style="font-weight: bold"
                               href="/basket/amount/remove?productId=<%=basketProduct.getProduct().getId()%>">-</a>
                            <h4 class="px-4"><%=numberFormat.format(basketProduct.getTotalPrice()) %> $</h4>
                        </div>
                        <a class="btn btn-secondary text-white"
                           href="/basket/remove?productId=<%= basketProduct.getProduct().getId() %>"> Remove from Basket</a>
                    </div>
                </div>
                <% } %>
                <div style="display: flex;align-items: end">
                    <h3 class="mx-2" >Overall:</h3>
                    <h3>
                        <%=numberFormat.format(OrderService.getOverallBasketPrice(basketProducts))%> $</h3>
                    <a class="btn btn-dark text-white offset-1 "
                       href="/">Back</a>
                    <a class="btn btn-warning text-white mx-4"
                       href="/order/confirm">Confirm Order</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
