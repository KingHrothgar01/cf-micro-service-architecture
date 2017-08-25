package com.hopper.training.uiservice.service;

import com.hopper.training.uiservice.model.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class ProductService {

    private RestTemplate restTemplate;

    @Value("${product.service.host}")
    private String product_service_host;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "fallBack", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") })
    public List<Product> getProducts() {
        ParameterizedTypeReference<List<Product>> parameterizedTypeReference = new ParameterizedTypeReference<List<Product>>() {
        };

        List<Product> products = restTemplate
                .exchange(product_service_host + "v1/product", HttpMethod.GET, null, parameterizedTypeReference)
                .getBody();
        return products;
    }


    public List<Product> fallBack(Throwable exception) throws Exception {
        log.info("product service is down "+ exception.getMessage());

        List<Product> product = new ArrayList<>();
        Product product1 = new Product();
        product1.setName("Rocket Ship");
        product.add(product1);
        return product;
    }
}