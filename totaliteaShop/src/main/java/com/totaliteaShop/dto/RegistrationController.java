package com.totaliteaShop.dto;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    private final RegistrationService registrationService;
    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
@GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
}
@PostMapping("/register")
    public String processRegistration(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            Model model
){
        try{
            registrationService.registerUser(name, email, password);
            model .addAttribute ("successMessage", "Registration Successful! Please log in");
            return "redirect:/login";
        } catch (IllegalArgumentException e){
            model .addAttribute ("errorMessage", e.getMessage());
            return "register";
        }
}
}
