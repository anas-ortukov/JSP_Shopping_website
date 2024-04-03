package org.example.jsp_shopping_website.service;

import org.example.jsp_shopping_website.config.ConnectionPoolManager;
import org.example.jsp_shopping_website.entity.Basket;
import org.example.jsp_shopping_website.entity.BasketProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasketService {

    public static boolean checkBasketProduct(UUID productId) {
        String query = "select * from basketproduct where product_id = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setObject(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket getBasket() {
        String query = "select * from basket";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Basket(
                        UUID.fromString(resultSet.getString("id")),
                        null);
            } else {
                String insertQuery = "insert into basket(user_id) values (null) returning *";
                try (
                        PreparedStatement insertAndGet = connection.prepareStatement(insertQuery);
                ) {
                    ResultSet resultSet1 = insertAndGet.executeQuery();
                    resultSet1.next();
                    return new Basket(
                            UUID.fromString(resultSet1.getString("id")),
                            null);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addBasketProduct(Basket basket, UUID productId) {
        String query = "insert into basketproduct(basket_id, product_id) values (?,?)";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setObject(1, basket.getId());
            preparedStatement.setObject(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeBasketProduct(UUID productId) {
        String query = "delete from basketproduct where product_id = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setObject(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getBasketProductAmount() {
        String query = "select * from basketproduct";
        int counter = 0;
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                counter++;
            }
            return counter;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<BasketProduct> getBasketProducts() {
        String query = "select * from basketproduct order by product_id";
        List<BasketProduct> basketProducts = new ArrayList<>();
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                basketProducts.add(new BasketProduct(
                        UUID.fromString(resultSet.getString("product_id")),
                        UUID.fromString(resultSet.getString("basket_id")),
                        resultSet.getInt("amount")
                ));
            }
            return basketProducts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addAmountBasketProduct(UUID productId) {
        String query = "update basketproduct set amount = amount + 1 where product_id = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setObject(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeAmountBasketProduct(UUID productId) {
        String query = "update basketproduct set amount = amount - 1 where product_id = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setObject(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getOverallBasketPrice(List<BasketProduct> basketProducts) {
        Integer totalPrice = 0;
        for (BasketProduct basketProduct : basketProducts) {
            totalPrice += basketProduct.getTotalPrice();
        }
        return totalPrice;
    }

    public static List<BasketProduct> getBasketProductsByBasketId(UUID basketId) {
        String query = "select * from basketproduct where basket_id = ? order by product_id";
        List<BasketProduct> basketProducts = new ArrayList<>();
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setObject(1, basketId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                basketProducts.add(new BasketProduct(
                        UUID.fromString(resultSet.getString("product_id")),
                        UUID.fromString(resultSet.getString("basket_id")),
                        resultSet.getInt("amount")
                ));
            }
            return basketProducts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
