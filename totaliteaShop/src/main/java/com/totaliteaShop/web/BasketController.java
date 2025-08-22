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

@Controller
@RequestMapping("/basket")
public class BasketController {

    private final ProductService productService;

    public BasketController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String viewBasket(HttpSession session, Model model) {
        List<BasketItem> basket = (List<BasketItem>) session.getAttribute("basket");
        if (basket == null) {
            basket = new ArrayList<>();
        }

        BigDecimal total = basket.stream()
                .map(BasketItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("basketItems", basket);
        model.addAttribute("totalPrice", total);

        return "basket"; // basket.html
    }

    @PostMapping("/add")
    public String addToBasket(@RequestParam Long productId,
                              @RequestParam int quantity,
                              HttpSession session) {

        List<BasketItem> basket = (List<BasketItem>) session.getAttribute("basket");
        if (basket == null) {
            basket = new ArrayList<>();
        }

        Product product = productService.findById(productId);

        BasketItem item = new BasketItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setSubTotal(product.getPriceGbp().multiply(BigDecimal.valueOf(quantity)));

        basket.add(item);

        session.setAttribute("basket", basket);

        return "redirect:/basket";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam Long productId,
                                 @RequestParam int quantity,
                                 HttpSession session) {
        List<BasketItem> basket = (List<BasketItem>) session.getAttribute("basket");
        if (basket != null) {
            basket.forEach(item -> {
                if (item.getProduct().getId().equals(productId)) {
                    item.setQuantity(quantity);
                    item.setSubTotal(item.getProduct().getPriceGbp()
                            .multiply(BigDecimal.valueOf(quantity)));
                }
            });
            session.setAttribute("basket", basket);
        }
        return "redirect:/basket";
    }

    @PostMapping("/remove")
    public String removeFromBasket(@RequestParam Long productId, HttpSession session) {
        List<BasketItem> basket = (List<BasketItem>) session.getAttribute("basket");
        if (basket != null) {
            basket.removeIf(item -> item.getProduct().getId().equals(productId));
            session.setAttribute("basket", basket);
        }
        return "redirect:/basket";
    }

    @PostMapping("/clear")
    public String clearBasket(HttpSession session) {
        session.removeAttribute("basket");
        return "redirect:/basket";
    }
}
