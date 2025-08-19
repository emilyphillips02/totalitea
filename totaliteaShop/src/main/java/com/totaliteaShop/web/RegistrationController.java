package com.totaliteaShop.web;

import com.totaliteaShop.model.User;
import com.totaliteaShop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    private final UserService userService;
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
 @GetMapping("/register")
public String showRegistrationForm(Model model)
{model.addAttribute("user", new User());
    return "registration.form";
}
@PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam("password") String password, Model model) {
        String result =userService.register(user,password);
        if ("Success".equals(result)) {
            return "redirect:/login";
        } else {
            model .addAttribute("errorMessage", result);
            return "registration.form";
        }
}

}

