package org.example.jsp_shopping_website.repo;

import jakarta.servlet.http.HttpSession;
import org.example.jsp_shopping_website.config.ConnectionPoolManager;
import org.example.jsp_shopping_website.entity.User;
import org.example.jsp_shopping_website.entity.enums.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepo {

    public static List<User> findAll() {
        String query = "SELECT * FROM users";
        try(
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
                ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while(resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        Role.valueOf(resultSet.getString("role")),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                        )
                );
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Optional<User> getUser(String email, String password) {
        for (User user : findAll()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public static Optional<User> getUserBySession(HttpSession session) {
        Object currentUser = session.getAttribute("currentUser");
        if (currentUser != null) {
            return Optional.of((User) currentUser);
        }else {
            return Optional.empty();
        }
    }
}
