package com.hopper.training.productservice;

import com.hopper.training.productservice.model.Product;
import com.hopper.training.productservice.repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepo repo) {
		return (args) -> {
			repo.save(new Product(Long.valueOf(1), "DB Car"));
			repo.save(new Product(Long.valueOf(2), "DB Live Dinosaur"));
		};
	}

}
