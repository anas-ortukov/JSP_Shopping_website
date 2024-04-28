package org.example.jsp_shopping_website.service;

import org.example.jsp_shopping_website.entity.OrderProduct;

import java.util.List;

public class OrderService {

    public static Integer getOverallBasketPrice(List<OrderProduct> orderProducts) {
        int totalPrice = 0;
        for (OrderProduct orderProduct : orderProducts) {
            totalPrice += orderProduct.getTotalPrice();
        }
        return totalPrice;
    }
}
