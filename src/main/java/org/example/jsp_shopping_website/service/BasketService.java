package org.example.jsp_shopping_website.service;

import jakarta.servlet.http.HttpSession;
import org.example.jsp_shopping_website.config.ConnectionPoolManager;
import org.example.jsp_shopping_website.entity.BasketProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@SuppressWarnings("unchecked")
public class BasketService {
    public static List<BasketProduct> getListBasketProduct(HttpSession session) {
        Object object = session.getAttribute("basket_product");
        if (object == null) {
            List<BasketProduct> newBasketProductList = new ArrayList<>();
            session.setAttribute("basket_product", newBasketProductList);
            return newBasketProductList;
        }else {
            return (List<BasketProduct>) object;
        }
    }

    public static Optional<BasketProduct> getBasketProductById(UUID productId, HttpSession session) {
        List<BasketProduct> basketProducts = getListBasketProduct(session);
        for (BasketProduct basketProduct : basketProducts) {
            if (basketProduct.getProductId().equals(productId)) {
                return Optional.of(basketProduct);
            }
        }
        return Optional.empty();
    }

    public static void addBasketProduct(UUID productId, HttpSession session) {
        List<BasketProduct> listBasketProduct = getListBasketProduct(session);
        listBasketProduct.add(new BasketProduct(productId, 1));
        session.setAttribute("basket_product", listBasketProduct);
    }

    public static void removeBasketProduct(UUID productId, HttpSession session) {
        List<BasketProduct> listBasketProduct = getListBasketProduct(session);
        listBasketProduct.removeIf(basketProduct -> basketProduct.getProductId().equals(productId));
        session.setAttribute("basket_product", listBasketProduct);
    }

    public static Integer getBasketProductAmount(HttpSession session) {
        return getListBasketProduct(session).size();
    }

    public static void addAmountBasketProduct(UUID productId, HttpSession session) {
        getBasketProductById(productId, session).
                ifPresent(basketProduct -> basketProduct.setAmount(basketProduct.getAmount() + 1));
    }

    public static void removeAmountBasketProduct(UUID productId, HttpSession session) {
        getBasketProductById(productId, session).
                ifPresent(basketProduct -> basketProduct.setAmount(basketProduct.getAmount() - 1));
    }

    public static Integer getOverallBasketPrice(List<BasketProduct> basketProducts) {
        Integer totalPrice = 0;
        for (BasketProduct basketProduct : basketProducts) {
            totalPrice += basketProduct.getTotalPrice();
        }
        return totalPrice;
    }
}
