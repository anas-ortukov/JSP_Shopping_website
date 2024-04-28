package org.example.jsp_shopping_website.repo;

import org.example.jsp_shopping_website.entity.Product;

import java.util.UUID;

public class ProductRepo extends BaseRepo<Product, UUID>{


    public void update(Product product) {
        begin();
        em.merge(product);
        commit();
    }
}
