package com.totaliteaShop.service;

import com.totaliteaShop.model.Order;
import com.totaliteaShop.model.OrderItem;
import com.totaliteaShop.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }
    public List<OrderItem> getItemByOrderId(Long orderId){
        return orderItemRepository.findByOrderId(orderId);
    }
    public List<OrderItem> getItemsByOrderIdAndProductId(Long orderId, Long productId){
        return orderItemRepository.findByOrderIdAndProductId(orderId, productId);
    }
    public List<OrderItem>getAllItemByOrderId(Long orderId){
        return orderItemRepository.findByOrderId(orderId);
    }
    public void deleteOrderItemById(Long id){
        orderItemRepository.deleteById(id);
    }
}
