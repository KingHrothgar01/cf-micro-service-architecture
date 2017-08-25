package com.hopper.training.uiservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
public class UiServiceApplication {

	@Value("${username}")
	private String admin_username;

	@Value("${password}")
	private String admin_password;

	public static void main(String[] args) {
		SpringApplication.run(UiServiceApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplateBuilder().basicAuthorization(admin_username, admin_password).build();
	}
}
