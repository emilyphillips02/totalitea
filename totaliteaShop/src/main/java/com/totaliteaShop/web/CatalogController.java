package com.totaliteaShop.web;

import com.totaliteaShop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatalogController {
    private final ProductService productService;

    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/catalog")
    public String showCatalog(
            @RequestParam(required = false) String category,
            Model model) {
        if (category != null && !category.isEmpty()) {
            model.addAttribute("products", productService.getProductsByCategory(category));
        } else {
            model.addAttribute("products", productService.getAllProducts());
        }
        model.addAttribute("activePage", "catalog");
        model.addAttribute("selectedCategory", category);
        return "catalog"; //
    }

}

