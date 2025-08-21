package com.totaliteaShop.web;

import com.totaliteaShop.model.Basket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class BasketController {

    private final Basket basket;

    public BasketController(Basket basket) {
        this.basket = basket;
    }

    @GetMapping("/basket")
    public String viewBasket(Model model) {
        model.addAttribute("basketItems", basket.getItems());
        model.addAttribute("totalPrice", basket.getTotal());
        model.addAttribute("basketItemCount", basket.getItems().size());
        return "basket";
    }

    @PostMapping("/basket/update")
    public String updateQuantity(@RequestParam Long productId, @RequestParam int quantity) {
        basket.getItems().forEach(item -> {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(quantity);
                item.setSubTotal(item.getProduct().getPriceGbp().multiply(BigDecimal.valueOf(quantity)));
            }
        });
        return "redirect:/basket";
    }

    @PostMapping("/basket/remove")
    public String removeItem(@RequestParam Long productId) {
        basket.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return "redirect:/basket";
    }

    @PostMapping("/basket/clear")
    public String clearBasket() {
        basket.clear();
        return "redirect:/basket";
    }
}