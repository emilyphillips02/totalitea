package com.totaliteaShop.repository;

import com.totaliteaShop.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    List<OrderModel> findByUserId(Long userId);
    List<OrderModel> findByStatus(String status);
}
