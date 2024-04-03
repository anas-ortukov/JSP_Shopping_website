package org.example.jsp_shopping_website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jsp_shopping_website.config.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketProduct {

    private UUID productId;
    private UUID basketId;
    private Integer amount;

    public Product getProduct() {
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement("select * from product where id = ?");
        ) {
            preparedStatement.setObject(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Product(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    UUID.fromString(resultSet.getString("category_id")),
                    resultSet.getBytes("image")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getTotalPrice() {
        return getProduct().getPrice() * amount;
    }
}
