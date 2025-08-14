package com.totaliteaShop.repository;

import com.totaliteaShop.model.Order;
import com.totaliteaShop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
    }