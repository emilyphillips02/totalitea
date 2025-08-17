package com.totaliteaShop.service;

import com.totaliteaShop.model.Order;
import com.totaliteaShop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class OrderService {
    private final OrderRepository orderRepository;

    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
