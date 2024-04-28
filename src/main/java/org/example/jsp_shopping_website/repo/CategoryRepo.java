package org.example.jsp_shopping_website.repo;

import org.example.jsp_shopping_website.entity.Category;

import java.util.UUID;

public class CategoryRepo extends BaseRepo<Category, UUID> {

    public void update(Category category) {
        begin();
        em.merge(category);
        commit();
    }
}
