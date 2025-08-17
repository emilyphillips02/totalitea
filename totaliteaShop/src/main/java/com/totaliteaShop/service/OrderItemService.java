package com.totaliteaShop.service;

import com.totaliteaShop.model.OrderItemModel;
import com.totaliteaShop.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemModel addOrderItem(OrderItemModel orderItemModel) {
        return orderItemRepository.save(orderItemModel);
    }
    public Optional<OrderItemModel> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }
    public List<OrderItemModel> getItemByOrderId(Long orderId){
        return orderItemRepository.findByOrderId(orderId);
    }
    public List<OrderItemModel> getItemsByOrderIdAndProductId(Long orderId, Long productId){
        return orderItemRepository.findByOrderIdAndProductId(orderId, productId);
    }
    public List<OrderItemModel>getAllItemByOrderId(Long orderId){
        return orderItemRepository.findByOrderId(orderId);
    }
    public void deleteOrderItemById(Long id){
        orderItemRepository.deleteById(id);
    }
}
