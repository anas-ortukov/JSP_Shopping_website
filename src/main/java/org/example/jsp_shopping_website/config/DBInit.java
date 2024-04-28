package org.example.jsp_shopping_website.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import org.example.jsp_shopping_website.entity.Category;
import org.example.jsp_shopping_website.entity.User;
import org.example.jsp_shopping_website.entity.enums.Role;
import org.example.jsp_shopping_website.repo.CategoryRepo;
import org.example.jsp_shopping_website.repo.UserRepo;
import org.mindrot.jbcrypt.BCrypt;

@WebListener
public class DBInit implements ServletContextListener {

    UserRepo userRepo = new UserRepo();
    CategoryRepo categoryRepo = new CategoryRepo();


    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
        if (userRepo.findAll().isEmpty()) {
            User user = User.builder()
                    .email("mango@gmail.com")
                    .firstName("Mango")
                    .lastName("Galen")
                    .role(Role.ADMIN)
                    .password(hashpw)
                    .build();
            userRepo.save(user);
        }
        if (categoryRepo.findAll().isEmpty()) {
            Category category1 = Category.builder()
                    .name("Foods")
                    .build();
            Category category2 = Category.builder()
                    .name("Clothes")
                    .build();
            Category category3 = Category.builder()
                    .name("Technology")
                    .build();
            categoryRepo.save(category1);
            categoryRepo.save(category2);
            categoryRepo.save(category3);
        }
    }
}
