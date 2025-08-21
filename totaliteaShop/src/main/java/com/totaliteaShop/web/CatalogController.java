package com.totaliteaShop.web;

import com.totaliteaShop.model.Basket;
import com.totaliteaShop.model.Product;
import com.totaliteaShop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CatalogController {

    private final ProductService productService;
    private final Basket basket;

    public CatalogController(ProductService productService, Basket basket) {
        this.productService = productService;
        this.basket = basket;
    }

    @GetMapping("/catalog")
    public String catalog(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String supplier,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String basketMessage,
            Model model) {

        List<Product> products = productService.getAllProducts();

        if (category != null && !category.isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }

        if (type != null && !type.isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getType().equalsIgnoreCase(type))
                    .collect(Collectors.toList());
        }

        if (supplier != null && !supplier.isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getSupplier().equalsIgnoreCase(supplier))
                    .collect(Collectors.toList());
        }

        if (name != null && !name.isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("products", products);


        model.addAttribute("categories", productService.getAllProducts().stream()
                .map(Product::getCategory).distinct().sorted().collect(Collectors.toList()));
        model.addAttribute("types", productService.getAllProducts().stream()
                .map(Product::getType).distinct().sorted().collect(Collectors.toList()));
        model.addAttribute("suppliers", productService.getAllProducts().stream()
                .map(Product::getSupplier).distinct().sorted().collect(Collectors.toList()));

        model.addAttribute("category", category);
        model.addAttribute("type", type);
        model.addAttribute("supplier", supplier);
        model.addAttribute("name", name);

        model.addAttribute("basketItemCount", basket.getItems().size());

        model.addAttribute("basketMessage", basketMessage);

        return "catalog";
    }

    @PostMapping("/catalog/add-to-basket")
    public String addToBasket(@RequestParam Long productId,
                              @RequestParam(defaultValue = "1") int quantity,
                              @RequestParam(required = false) String category,
                              @RequestParam(required = false) String type,
                              @RequestParam(required = false) String supplier,
                              @RequestParam(required = false) String name,
                              Model model) {

        Product product = productService.getAllProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        basket.addProduct(product, quantity);

        String message = "Added " + quantity + " × " + product.getName() + " to your basket.";


        String redirectUrl = "/catalog?basketMessage=" + message;

        if (category != null) redirectUrl += "&category=" + category;
        if (type != null) redirectUrl += "&type=" + type;
        if (supplier != null) redirectUrl += "&supplier=" + supplier;
        if (name != null) redirectUrl += "&name=" + name;

        return "redirect:" + redirectUrl;
    }
}