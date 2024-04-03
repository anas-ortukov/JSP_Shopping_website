package org.example.jsp_shopping_website.repo;

import org.example.jsp_shopping_website.config.ConnectionPoolManager;
import org.example.jsp_shopping_website.entity.OrderProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderProductRepo {

    public static List<OrderProduct> findAll() {
        String query = "select * from orderproduct order by order_id";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderProduct> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(new OrderProduct(
                        resultSet.getInt("order_id"),
                        UUID.fromString(resultSet.getString("product_id")),
                        resultSet.getInt("amount")
                ));
            }
            return products;
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<OrderProduct> getOrderProductsByOrderId(Integer orderId) {
        String query = "select * from orderproduct where order_id = ? order by order_id ";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderProduct> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(new OrderProduct(
                        resultSet.getInt("order_id"),
                        UUID.fromString(resultSet.getString("product_id")),
                        resultSet.getInt("amount")
                ));
            }
            return products;
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
