package com.totaliteaShop.web;

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
    public String showCatalog(@RequestParam(required = false) String category, Model model) {
        List<Product> products;

        if (category != null && !category.isBlank()) {
            products = productService.getProductsByCategory(category);
        } else {
            products = productService.getAllProducts();
        }

        model.addAttribute("products", products);
        return "catalog";
    }
}
