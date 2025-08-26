package com.totaliteaShop.service;

import com.totaliteaShop.model.BasketItem;
import com.totaliteaShop.repository.ShippingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;

    public ShippingService(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    public BigDecimal calculateShippingCost(List<BasketItem> basket, BigDecimal basketTotal) {
        if (basket == null || basket.isEmpty()) {
            return BigDecimal.ZERO;
        }


        BigDecimal totalWeight = basket.stream()
                .map(item -> item.getProduct().getWeightGrams()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return shippingRepository
                .findFirstByMinWeightLessThanEqualAndMaxWeightGreaterThanEqual(totalWeight, totalWeight)
                .map(rule -> {
                    if (rule.getFreeShippingThreshold() != null &&
                            basketTotal.compareTo(rule.getFreeShippingThreshold()) >= 0) {
                        return BigDecimal.ZERO;
                    }
                    return rule.getCost();
                })
                .orElse(BigDecimal.ZERO);
    }
}