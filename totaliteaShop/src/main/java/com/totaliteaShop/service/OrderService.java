package com.totaliteaShop.service;

import com.totaliteaShop.model.Basket;
import com.totaliteaShop.model.BasketItem;
import com.totaliteaShop.model.Order;
import com.totaliteaShop.model.OrderItem;
import com.totaliteaShop.model.User;
import com.totaliteaShop.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final Basket basket;

    public OrderService(OrderRepository orderRepository, Basket basket) {
        this.orderRepository = orderRepository;
        this.basket = basket;
    }

    @Transactional
    public Order placeOrder(User user, List<BasketItem> basket) {
        if (this.basket.getItems().isEmpty()) {
            throw new IllegalStateException("Basket is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus("PENDING");
        order.setItems(new ArrayList<>());

        BigDecimal total = BigDecimal.ZERO;

        for (BasketItem basketItem : this.basket.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(basketItem.getProduct());
            orderItem.setQuantity(basketItem.getQuantity());
            orderItem.setSubTotal(basketItem.getSubTotal());

            order.getItems().add(orderItem);
            total = total.add(basketItem.getSubTotal());
        }

        order.setTotalPrice(total);
        order.setShippingCost(calculateShipping(total));

        // save order
        Order savedOrder = orderRepository.save(order);

        // empty basket after checkout
        this.basket.clear();

        return savedOrder;
    }

    public Order findByIdOrThrow(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    private BigDecimal calculateShipping(BigDecimal orderValue) {
        if (orderValue.compareTo(BigDecimal.valueOf(100)) > 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(5.00); // flat £5 for now
    }
}