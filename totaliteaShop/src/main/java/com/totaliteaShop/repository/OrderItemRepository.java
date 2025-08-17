package com.totaliteaShop.repository;

import com.totaliteaShop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByOrderIdAndProductId(Long orderId, Long productId);
}
