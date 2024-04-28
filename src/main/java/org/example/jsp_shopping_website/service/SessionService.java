package org.example.jsp_shopping_website.service;

import jakarta.servlet.http.HttpSession;
import org.example.jsp_shopping_website.entity.Order;
import org.example.jsp_shopping_website.entity.OrderProduct;
import org.example.jsp_shopping_website.entity.enums.Status;
import org.example.jsp_shopping_website.repo.OrderProductRepo;
import org.example.jsp_shopping_website.repo.OrderRepo;
import org.example.jsp_shopping_website.repo.ProductRepo;
import org.example.jsp_shopping_website.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SessionService {


    static OrderRepo orderRepo = new OrderRepo();
    static OrderProductRepo orderProductRepo = new OrderProductRepo();
    static ProductRepo productRepo = new ProductRepo();

    public static UUID searchFilter(HttpSession session) {
        if (session.getAttribute("categoryId") != null) {
            return UUID.fromString(session.getAttribute("categoryId").toString());
        }else {
            return null;
        }
    }

    public static void createOrder(HttpSession session) {
        List<OrderProduct> listBasketProduct = getListBasketProducts(session);
        session.removeAttribute("basket_product");
        orderProductRepo.saveAll(listBasketProduct);
        Order order = Order.builder()
                .user(UserRepo.getUserBySession(session).get())
                .status(Status.NEW)
                .orderProducts(listBasketProduct)
                .build();
        orderRepo.save(order);
    }

    @SuppressWarnings("unchecked")
    public static List<OrderProduct> getListBasketProducts(HttpSession session) {
        Object object = session.getAttribute("basket_product");
        if (object == null) {
            List<OrderProduct> newBasketProductList = new ArrayList<>();
            session.setAttribute("basket_product", newBasketProductList);
            return newBasketProductList;
        }else {
            return (List<OrderProduct>) object;
        }
    }

    public static Optional<OrderProduct> getBasketProductById(UUID productId, HttpSession session) {
        List<OrderProduct> basketProducts = getListBasketProducts(session);
        for (OrderProduct basketProduct : basketProducts) {
            if (basketProduct.getProduct().getId().equals(productId)) {
                return Optional.of(basketProduct);
            }
        }
        return Optional.empty();
    }

    public static void addBasketProduct(UUID productId, HttpSession session) {
        List<OrderProduct> listBasketProduct = getListBasketProducts(session);
        OrderProduct basketProduct = new OrderProduct();
        basketProduct.setProduct(productRepo.findById(productId));
        basketProduct.setAmount(1);
        listBasketProduct.add(basketProduct);
        session.setAttribute("basket_product", listBasketProduct);
    }

    public static void removeBasketProduct(UUID productId, HttpSession session) {
        List<OrderProduct> listBasketProduct = getListBasketProducts(session);
        listBasketProduct.removeIf(basketProduct -> basketProduct.getProduct().getId().equals(productId));
        session.setAttribute("basket_product", listBasketProduct);
    }
}
