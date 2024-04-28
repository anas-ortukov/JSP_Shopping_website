package org.example.jsp_shopping_website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private Product product;
    @Check(constraints = "amount > 0")
    private Integer amount;

    public int getTotalPrice() {
        return amount * product.getPrice();
    }
}
