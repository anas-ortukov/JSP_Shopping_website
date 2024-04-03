package org.example.jsp_shopping_website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jsp_shopping_website.repo.CategoryRepo;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private UUID id;
    private String name;
    private Integer price;
    private UUID categoryId;
    private byte[] image;

    public String getCategoryName() {
        Category category = CategoryRepo.findById(categoryId);
        return category.getName();
    }
}
