package com.totaliteaShop.web;

import com.totaliteaShop.model.BasketItem;
import com.totaliteaShop.model.Order;
import com.totaliteaShop.model.User;
import com.totaliteaShop.repository.UserRepository;
import com.totaliteaShop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    public CheckoutController(OrderService orderService, UserRepository userRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    // --- GET to view checkout page (guest or logged-in)
    @GetMapping
    public String viewCheckout(@AuthenticationPrincipal UserDetails principal,
                               HttpSession session,
                               Model model) {
        List<BasketItem> basket = (List<BasketItem>) session.getAttribute("basket");
        if (basket == null || basket.isEmpty()) {
            model.addAttribute("basketItems", List.of());
            model.addAttribute("totalPrice", 0);
            return "checkout";
        }

        java.math.BigDecimal total = basket.stream()
                .map(BasketItem::getSubTotal)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);

        model.addAttribute("basketItems", basket);
        model.addAttribute("totalPrice", total);

        return "checkout";
    }

    // --- POST to create order and process payment (guest or logged-in)
    @PostMapping
    public String startCheckout(@AuthenticationPrincipal UserDetails principal,
                                HttpSession session,
                                @RequestParam String fullName,
                                @RequestParam String address,
                                @RequestParam String cardNumber,
                                @RequestParam String expiryDate,
                                @RequestParam String cvv,
                                Model model) {

        List<BasketItem> basket = (List<BasketItem>) session.getAttribute("basket");
        if (basket == null || basket.isEmpty()) {
            model.addAttribute("error", "Your basket is empty.");
            return "basket";
        }

        User user = null;
        if (principal != null) {
            user = userRepository.findByEmail(principal.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        Order order = orderService.placeOrder(user, basket);

        // clear session basket after placing order
        session.removeAttribute("basket");

        model.addAttribute("order", order);
        return "confirmation"; // show confirmation page
    }

    // optional: /checkout/complete for external payment gateway callbacks
    @GetMapping("/complete")
    public String completeOrder(@RequestParam Long orderId, Model model) {
        Order order = orderService.findByIdOrThrow(orderId);
        order.setStatus("PAID");
        orderService.save(order);

        model.addAttribute("order", order);
        return "confirmation";
    }
}
