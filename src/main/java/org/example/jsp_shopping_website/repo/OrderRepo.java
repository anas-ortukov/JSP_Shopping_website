package org.example.jsp_shopping_website.repo;

import org.example.jsp_shopping_website.config.ConnectionPoolManager;
import org.example.jsp_shopping_website.entity.Order;
import org.example.jsp_shopping_website.entity.OrderProduct;
import org.example.jsp_shopping_website.entity.Product;
import org.example.jsp_shopping_website.entity.enums.Status;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo {

    public static List<Order> findAll() {
        String query = "select * from orders order by status desc";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> products = new ArrayList<>();
            while (resultSet.next()) {
                LocalDateTime completedTime = null;
                Timestamp completedTimestamp = resultSet.getTimestamp("completed_time");
                if (completedTimestamp != null) {
                    completedTime = completedTimestamp.toLocalDateTime();
                }
                products.add(new Order(
                                resultSet.getInt("id"),
                                resultSet.getTimestamp("date_time").toLocalDateTime(),
                                resultSet.getInt("user_id"),
                                Status.valueOf(resultSet.getString("status")),
                                completedTime
                        )
                );
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getProductAmount(Order order) {
        int counter = 0;
        for (OrderProduct orderProduct : OrderProductRepo.findAll()) {
            if (orderProduct.getOrderId().equals(order.getId())) {
                counter++;
            }
        }
        return counter;
    }

    public static int getOrderSum(Order order) {
        int sum = 0;
        for (OrderProduct orderProduct : OrderProductRepo.findAll()) {
            if (orderProduct.getOrderId().equals(order.getId())) {
                Product ordersProduct = ProductRepo.findById(orderProduct.getProductId());
                sum += orderProduct.getAmount() * ordersProduct.getPrice();
            }
        }
        return sum;
    }

    public static Order getOrderById(Integer orderId) {
        String query = "select * from orders where id = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSS");
            LocalDateTime completedTime = null;
            Timestamp completedTimestamp = resultSet.getTimestamp("completed_time");
            if (completedTimestamp != null) {
                completedTime = completedTimestamp.toLocalDateTime();
            }
            return new Order(
                    resultSet.getInt("id"),
                    resultSet.getTimestamp("date_time").toLocalDateTime(),
                    resultSet.getInt("user_id"),
                    Status.valueOf(resultSet.getString("status")),
                    completedTime);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void changeStatus(String newStatus, int orderId, boolean completed) {
        String query = "UPDATE orders SET status = ? WHERE id = ?";
        if (completed) {
            query = "UPDATE orders SET status = ?, completed_time = now() WHERE id = ?";
        }
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Order> getOrderByStatus(List<Order> orders, Status status) {
        List<Order> ordersByStatus = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus().equals(status)) {
                ordersByStatus.add(order);
            }
        }
        return ordersByStatus;
    }
}
