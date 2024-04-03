<%@ page import="java.util.List" %>
<%@ page import="org.example.jsp_shopping_website.entity.BasketProduct" %>
<%@ page import="org.example.jsp_shopping_website.service.BasketService" %>
<%@ page import="java.text.NumberFormat" %><%--
  Created by IntelliJ IDEA.
  User: azizo
  Date: 01/04/2024
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>

<%
    List<BasketProduct> basketProducts = BasketService.getBasketProducts();
    NumberFormat numberFormat = NumberFormat.getNumberInstance();
    if (basketProducts.isEmpty()) {
        response.sendRedirect("/client/empty_basket.jsp");
        return;
    }
%>

<div class="row">
    <div class="offset-4 col-6 py-3">
        <h1 class="offset-3">Basket</h1>
        <div class="card" style="width: 100%;">
            <div class="card-body">
                <% for (BasketProduct basketProduct : basketProducts) { %>
                <div class="row">
                    <div class="col-3 py-2">

                        <img src="/file/download?productId=<%=basketProduct.getProductId()%>" class="card-img-top img-fluid mx-auto d-block" alt="...">
                    </div>
                    <div class="col-7 py-5">
                        <h4><%=numberFormat.format(basketProduct.getProduct().getPrice())%> sum</h4>
                        <div style="display: flex; align-items: center;" class="my-3">
                            <a type="button" class="btn btn-light" style="font-weight: bold"
                               href="/basket/product/add/amount?basketProductId=<%= basketProduct.getProductId()%>">+</a>
                            <p style="margin: 10px;" ><%= basketProduct.getAmount() %> pieces</p>
                            <a type="button" class="btn btn-light" style="font-weight: bold"
                               href="/basket/product/remove/amount?basketProductId=<%=
                               basketProduct.getAmount()> 1 ? basketProduct.getProductId():"denied"%>">-</a>
                            <h4 class="px-4"><%=numberFormat.format(basketProduct.getTotalPrice()) %> sum</h4>
                        </div>
                        <a class="btn  text-white" style="background-color: navy;"
                           href="/basket/product/remove?productId=<%= basketProduct.getProduct().getId() %>&url=http://localhost:8080/client/basket.jsp"> Remove from Basket</a>
                    </div>
                </div>
                <% } %>
                <div style="display: flex;align-items: end">
                    <h3 class="mx-2" >Overall:</h3>
                    <h3>
                        <%=numberFormat.format(BasketService.getOverallBasketPrice(basketProducts))%> sum</h3>
                    <a class="btn btn-dark text-white offset-1 "
                       href="http://localhost:8080">Back</a>
                    <a class="btn btn-warning text-white mx-4"
                       href="/order/confirm?basketId=<%=basketProducts.getFirst().getBasketId()%>">Confirm Order</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
