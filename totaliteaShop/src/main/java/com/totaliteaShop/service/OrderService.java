package com.totaliteaShop.service;

import com.totaliteaShop.model.OrderModel;
import com.totaliteaShop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderModel placeOrder(OrderModel orderModel) {
        return orderRepository.save(orderModel);
    }
    public Optional<OrderModel> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    public List<OrderModel> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
    public List<OrderModel> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
