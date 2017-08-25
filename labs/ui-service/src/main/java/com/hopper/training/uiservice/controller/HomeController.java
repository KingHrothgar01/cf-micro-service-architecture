package com.hopper.training.uiservice.controller;

import com.hopper.training.uiservice.model.Product;
import com.hopper.training.uiservice.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by s5156275 on 2017-08-25.
 */
@Controller
public class HomeController {

    private ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/")
    public String home(Model model) {

        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "index";

    }

}