package com.hopper.training.productservice.controller;

import com.hopper.training.productservice.model.Product;
import com.hopper.training.productservice.repository.ProductRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ProductController {

    private ProductRepo productRepo;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("/product")
    public ResponseEntity<Iterable<Product>> products() {

        return ResponseEntity.ok(productRepo.findAll());
    }

}
