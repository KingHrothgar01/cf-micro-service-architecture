/**
 * 
 */
package com.cloudnativecoffee.product.controller;

import com.cloudnativecoffee.model.Product;
import com.cloudnativecoffee.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

/**
 * @author lshannon
 *
 */
@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class ProductController {
	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
	private final ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> products() {
		List<Product> returnValue = this.productService.getAllProducts();
		if (returnValue != null) {
			LOG.info("All products returned");
			return ResponseEntity.ok(returnValue);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/products")
	public ResponseEntity<Product> createProducts(@RequestBody @Valid Product product) {
		try {
			return ResponseEntity.ok(this.productService.createUpdateProduct(product));
		} catch(RestClientException | DataIntegrityViolationException | ConstraintViolationException e) {
			LOG.error("error thrown during create/update of product", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
		if(this.productService.deleteProduct(productId))
			return ResponseEntity.ok("Product deleted successfully");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product does not exist");
	}

}
