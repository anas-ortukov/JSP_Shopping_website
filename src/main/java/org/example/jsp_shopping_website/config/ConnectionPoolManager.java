package org.example.jsp_shopping_website.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

public class ConnectionPoolManager {

    @Getter
    private static final HikariDataSource dataSource;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/ecommerce");
        config.setConnectionTimeout(30000);
        config.setUsername("postgres");
        config.setPassword("MrAnas123$");
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(30);
        dataSource = new HikariDataSource(config);
    }

}
