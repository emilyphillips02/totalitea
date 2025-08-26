package com.totaliteaShop.web;

import com.totaliteaShop.model.BasketItem;
import com.totaliteaShop.model.Product;
import com.totaliteaShop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CatalogController {

    private final ProductService productService;

    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/catalog")
    public String catalog(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String supplier,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String basketMessage,
            HttpSession session,
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

        List<BasketItem> basket = (List<BasketItem>) session.getAttribute("basket");
        int basketCount = (basket != null) ? basket.size() : 0;
        model.addAttribute("basketItemCount", basketCount);

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
                              HttpSession session) {

        List<BasketItem> basket = (List<BasketItem>) session.getAttribute("basket");
        if (basket == null) basket = new ArrayList<>();

        Product product = productService.findById(productId);

        BasketItem item = new BasketItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setSubTotal(product.getPriceGbp().multiply(BigDecimal.valueOf(quantity)));

        basket.add(item);
        session.setAttribute("basket", basket);


        String message = "Added " + quantity + " × " + product.getName() + " to your basket.";
        String redirectUrl = "/catalog?basketMessage=" + message;

        if (category != null) redirectUrl += "&category=" + category;
        if (type != null) redirectUrl += "&type=" + type;
        if (supplier != null) redirectUrl += "&supplier=" + supplier;
        if (name != null) redirectUrl += "&name=" + name;

        return "redirect:" + redirectUrl;
    }
}
