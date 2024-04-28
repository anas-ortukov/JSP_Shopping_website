package org.example.jsp_shopping_website.repo;

import org.example.jsp_shopping_website.entity.OrderProduct;

import java.util.List;
import java.util.UUID;

public class OrderProductRepo extends BaseRepo<OrderProduct, UUID>{

    public void saveAll(List<OrderProduct> listBasketProduct) {
        listBasketProduct.forEach(this::save);
    }
}
