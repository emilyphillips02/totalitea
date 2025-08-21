package com.totaliteaShop.controller;

import com.totaliteaShop.model.Product;
import com.totaliteaShop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogController {

    private final ProductService productService;

    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/catalog")
    public String catalog(@RequestParam(required = false) String category, Model model) {
        List<Product> products;

        if (category != null && !category.isEmpty()) {
            // Filter products by category
            products = productService.getProductsByCategory(category);
        } else {
            // Show all products
            products = productService.getAllProducts();
        }

        // Add products to the model for Thymeleaf
        model.addAttribute("products", products);

        return "catalog"; // renders catalog.html
    }
}