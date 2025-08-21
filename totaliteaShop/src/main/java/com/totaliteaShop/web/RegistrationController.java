package com.totaliteaShop.web;

import com.totaliteaShop.model.User;
import com.totaliteaShop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        String result = userService.register(user, user.getPassword());
        if ("Success".equals(result)) {
            return "redirect:/login?registered";
        } else {
            model.addAttribute("errorMessage", result);
            return "register";
        }
    }
}