package org.example.jsp_shopping_website.service;

import lombok.SneakyThrows;
import org.example.jsp_shopping_website.config.ConnectionPoolManager;
import org.example.jsp_shopping_website.entity.BasketProduct;
import org.example.jsp_shopping_website.entity.OrderProduct;
import org.example.jsp_shopping_website.entity.enums.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class OrderService {
    public static void createOrder(UUID basketId) {
        String orderQuery = "insert into orders(user_id, status) VALUES (?,?) returning id";
        try(
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(orderQuery);
        ){
            preparedStatement.setObject(1,null);
            preparedStatement.setString(2, Status.NEW.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Integer orderId = resultSet.getInt("id");
            createOrderProducts(basketId, orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private static void createOrderProducts(UUID basketId, Integer orderId) {
        List<BasketProduct> basketProducts = BasketService.getBasketProductsByBasketId(basketId);
        Connection connection = ConnectionPoolManager.getDataSource().getConnection();
        String insertQuery = "INSERT INTO orderproduct(order_id, product_id, amount) VALUES (?,?,?)";
        for (BasketProduct basketProduct : basketProducts) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setObject(2, basketProduct.getProductId());
            preparedStatement.setInt(3, basketProduct.getAmount());
            preparedStatement.executeUpdate();
        }
        String dropQuery = "DELETE FROM basket where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(dropQuery);
        preparedStatement.setObject(1,basketId);
        preparedStatement.executeUpdate();
        connection.close();
    }

    public static Integer getOverallBasketPrice(List<OrderProduct> orderProducts) {
        Integer totalPrice = 0;
        for (OrderProduct orderProduct : orderProducts) {
            totalPrice += orderProduct.getTotalPrice();
        }
        return totalPrice;
    }
}
