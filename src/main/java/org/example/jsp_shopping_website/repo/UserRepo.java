package org.example.jsp_shopping_website.repo;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpSession;
import org.example.jsp_shopping_website.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class UserRepo extends BaseRepo<User, Integer>{

    public static Optional<User> getUserBySession(HttpSession session) {
        Object currentUser = session.getAttribute("currentUser");
        if (currentUser != null) {
            return Optional.of((User) currentUser);
        }else {
            return Optional.empty();
        }
    }

    public Optional<User> getUser(String email, String password) {
        TypedQuery<User> query = em.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        try {
            User user = query.getSingleResult();
            if (BCrypt.checkpw(password, user.getPassword())) {
                return Optional.of(user);
            }else {
                return Optional.empty();
            }
        } catch (NoResultException e) {
            return Optional.empty();
        }

    }

    public boolean registeredEmail(String email) {
        TypedQuery<Long> query = em.createQuery("select count(*) from User where email = :email", Long.class);
        query.setParameter("email", email);
        return query.getSingleResult() > 0;
    }
}
