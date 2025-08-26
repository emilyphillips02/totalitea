package com.totaliteaShop.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BasketItem {

    private Product product;
    private int quantity;
    private BigDecimal subTotal;

    // ✅ Helper method for shipping calculation
    public BigDecimal getTotalWeight() {
        if (product == null || product.getWeightGrams() == null) {
            return BigDecimal.ZERO;
        }
        return product.getWeightGrams().multiply(BigDecimal.valueOf(quantity));
    }
}