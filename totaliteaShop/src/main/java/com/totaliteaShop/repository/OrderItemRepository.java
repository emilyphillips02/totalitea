package com.totaliteaShop.repository;

import com.totaliteaShop.model.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemModel, Long> {
    List<OrderItemModel> findByOrderId(Long orderId);
    List<OrderItemModel> findByOrderIdAndProductId(Long orderId, Long productId);
}
