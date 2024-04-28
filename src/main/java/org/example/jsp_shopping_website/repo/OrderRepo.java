package org.example.jsp_shopping_website.repo;

import org.example.jsp_shopping_website.entity.Order;
import org.example.jsp_shopping_website.entity.OrderProduct;
import org.example.jsp_shopping_website.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo extends BaseRepo<Order, Integer>{

    public static int getOrderSum(Order order) {
        int sum = 0;
        for (OrderProduct orderProduct : order.getOrderProducts()) {
            sum += orderProduct.getTotalPrice();
        }
        return sum;
    }

    public void changeStatus(String newStatus, int orderId, boolean completed) {
        Order order = em.find(Order.class, orderId);
        begin();
        order.setStatus(Status.valueOf(newStatus));
        if (completed) {
            order.setCompletedTime(LocalDateTime.now());
        }
        commit();
    }

    public List<Order> getOrderByStatus(List<Order> orders, Status status) {
        List<Order> ordersByStatus = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus().equals(status)) {
                ordersByStatus.add(order);
            }
        }
        return ordersByStatus;
    }
}
