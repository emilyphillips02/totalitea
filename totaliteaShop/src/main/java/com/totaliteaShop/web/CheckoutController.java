package com.totaliteaShop.web;

import com.totaliteaShop.model.BasketItem;
import com.totaliteaShop.model.Order;
import com.totaliteaShop.model.User;
import com.totaliteaShop.repository.UserRepository;
import com.totaliteaShop.service.OrderService;
import com.totaliteaShop.service.ShippingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final OrderService orderService;
    private final UserRepository userRepository;
    private final ShippingService shippingService;

    public CheckoutController(OrderService orderService,
                              UserRepository userRepository,
                              ShippingService shippingService) {
        this.orderService = orderService;
        this.userRepository = userRepository;
        this.shippingService = shippingService;
    }

    @GetMapping
    public String viewCheckout(HttpSession session, Model model) {
        List<BasketItem> basket = (List<BasketItem>) session.getAttribute("basket");
        if (basket == null || basket.isEmpty()) {
            model.addAttribute("error", "Your basket is empty.");
            return "basket";
        }

        BigDecimal total = basket.stream()
                .map(BasketItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal shippingCost = shippingService.calculateShippingCost(basket, total);
        BigDecimal finalTotal = total.add(shippingCost);

        model.addAttribute("basketItems", basket);
        model.addAttribute("totalPrice", total);
        model.addAttribute("shippingCost", shippingCost);
        model.addAttribute("finalTotal", finalTotal);

        return "checkout";
    }

    @PostMapping
    public String startCheckout(@AuthenticationPrincipal UserDetails principal,
                                HttpSession session,
                                Model model) {

        List<BasketItem> basket = (List<BasketItem>) session.getAttribute("basket");
        if (basket == null || basket.isEmpty()) {
            model.addAttribute("error", "Your basket is empty.");
            return "basket";
        }

        User user = null;
        if (principal != null) {
            String email = principal.getUsername();
            user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found: " + email));
        }

        BigDecimal total = basket.stream()
                .map(BasketItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal shippingCost = shippingService.calculateShippingCost(basket, total);
        BigDecimal finalTotal = total.add(shippingCost);

        Order order = orderService.placeOrder(user, basket);

        model.addAttribute("order", order);
        model.addAttribute("basketItems", basket);
        model.addAttribute("totalPrice", total);
        model.addAttribute("shippingCost", shippingCost);
        model.addAttribute("finalTotal", finalTotal);

        return "checkout";
    }

    @GetMapping("/complete")
    public String completeOrder(@RequestParam Long orderId, Model model) {
        Order order = orderService.findByIdOrThrow(orderId);
        order.setStatus("PAID");
        orderService.save(order);

        model.addAttribute("order", order);
        return "confirmation";
    }
}