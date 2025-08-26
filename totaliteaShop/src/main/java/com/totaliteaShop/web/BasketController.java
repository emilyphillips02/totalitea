package com.totaliteaShop.web;

import com.totaliteaShop.model.BasketItem;
import com.totaliteaShop.service.ShippingService;
import com.totaliteaShop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private final ProductService productService;
    private final ShippingService shippingService; // add shippingService

    public BasketController(ProductService productService, ShippingService shippingService) {
        this.productService = productService;
        this.shippingService = shippingService;
    }

    @GetMapping
    public String viewBasket(HttpSession session, Model model) {
        List<BasketItem> basket = (List<BasketItem>) session.getAttribute("basket");
        if (basket == null) basket = new ArrayList<>();

        BigDecimal total = basket.stream()
                .map(BasketItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal shippingCost = shippingService.calculateShippingCost(basket, total);

        model.addAttribute("basketItems", basket);
        model.addAttribute("totalPrice", total);
        model.addAttribute("shippingCost", shippingCost); // add shippingCost for Thymeleaf

        return "basket";
    }

}