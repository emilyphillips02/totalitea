package com.totaliteaShop.controller;

import com.totaliteaShop.model.Basket;
import com.totaliteaShop.model.Product;
import com.totaliteaShop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogController {

    private final ProductService productService;
    private final Basket basket;

    public CatalogController(ProductService productService, Basket basket) {
        this.productService = productService;
        this.basket = basket;
    }

    @GetMapping("/catalog")
    public String catalog(@RequestParam(required = false) String category, Model model) {
        List<Product> products;
        if (category != null && !category.isEmpty()) {
            products = productService.getProductsByCategory(category);
        } else {
            products = productService.getAllProducts();
        }
        model.addAttribute("products", products);
        model.addAttribute("basketItemCount", basket.getItems().size());
        return "catalog";
    }

    @PostMapping("/catalog/add-to-basket")
    public String addToBasket(@RequestParam Long productId, @RequestParam(defaultValue = "1") int quantity) {
        Product product = productService.getAllProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        basket.addProduct(product, quantity);
        return "redirect:/basket";
    }
}