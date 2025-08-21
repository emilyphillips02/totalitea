package com.totaliteaShop.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@SessionScope // Unique per user session
public class Basket {

    private List<BasketItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        for (BasketItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                item.setSubTotal(item.getProduct().getPriceGbp().multiply(BigDecimal.valueOf(item.getQuantity())));
                return;
            }
        }

        BasketItem newItem = new BasketItem();
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        newItem.setSubTotal(product.getPriceGbp().multiply(BigDecimal.valueOf(quantity)));
        items.add(newItem);
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(BasketItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clear() {
        items.clear();
    }
}