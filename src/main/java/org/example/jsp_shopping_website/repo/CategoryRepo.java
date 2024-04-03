package org.example.jsp_shopping_website.repo;

import org.example.jsp_shopping_website.config.ConnectionPoolManager;
import org.example.jsp_shopping_website.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryRepo {

    public static List<Category> findAll() {
        String query = "select * from category order by name";
        try(
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement =connection.prepareStatement(query)
                ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (resultSet.next()) {
                categories.add(new Category(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("name")
                ));
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(Category category) {
        String query = "insert into category(name) values (?)";
        try(
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement =connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteById(UUID id) {
        String query = "delete from category where id = ?";
        try(
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement =connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Category findById(UUID id) {
        String query = "select * from category where id = ?";
        try(
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement =connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Category(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void edit(Category category) {
        String query = "update category set name = ? where id = ?";
        try(
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement =connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setObject(2, category.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
