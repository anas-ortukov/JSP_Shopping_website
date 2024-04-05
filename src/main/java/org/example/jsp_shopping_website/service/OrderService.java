package org.example.jsp_shopping_website.service;

import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.example.jsp_shopping_website.config.ConnectionPoolManager;
import org.example.jsp_shopping_website.entity.BasketProduct;
import org.example.jsp_shopping_website.entity.OrderProduct;
import org.example.jsp_shopping_website.entity.User;
import org.example.jsp_shopping_website.repo.UserRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    public static void createOrder(HttpSession session) {
        String orderQuery = "insert into orders(user_id) VALUES (?) returning id";
        try(
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(orderQuery);
        ){
            User user = UserRepo.getUserBySession(session).get();
            preparedStatement.setInt(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Integer orderId = resultSet.getInt("id");
            createOrderProducts(orderId, session);
            session.setAttribute("basket_product", null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createOrderProducts(Integer orderId, HttpSession session) {
        String insertQuery = "INSERT INTO order_product(order_id, product_id, amount) VALUES (?,?,?)";
        List<BasketProduct> basketProducts = BasketService.getListBasketProduct(session);
        try(
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                ) {
            for (BasketProduct basketProduct : basketProducts) {
                preparedStatement.setInt(1, orderId);
                preparedStatement.setObject(2, basketProduct.getProductId());
                preparedStatement.setInt(3, basketProduct.getAmount());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getOverallBasketPrice(List<OrderProduct> orderProducts) {
        Integer totalPrice = 0;
        for (OrderProduct orderProduct : orderProducts) {
            totalPrice += orderProduct.getTotalPrice();
        }
        return totalPrice;
    }
}
