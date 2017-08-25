package com.hopper.training.productservice.repository;

import com.hopper.training.productservice.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {

}