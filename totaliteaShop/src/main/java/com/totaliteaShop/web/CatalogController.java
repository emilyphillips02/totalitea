package com.totaliteaShop.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CatalogController {

    @GetMapping("/catalog")
    public String showCatalog(Model model) {
        model.addAttribute("products", List.of()); // Empty list for testing
        return "catalog";
    }
}




