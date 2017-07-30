/**
 * 
 */
package com.cloudnativecoffee.market.services;

import com.cloudnativecoffee.market.model.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lshannon
 *
 */
@Service
public class ProductService {
	private final Logger LOG = Logger.getLogger(ProductService.class);
	private static final String FALLBACK_METHOD = "genericErrorMessage";
	private final String productServiceHost;
	private final String productBaseurl;
	private OAuth2RestTemplate restTemplate;

	public ProductService(@Value("${marketplace.services.product.id}") String productServiceHost,
						  @Value("${marketplace.services.product.api.url}") String productBaseurl,
						  OAuth2RestTemplate restTemplate) {
		this.productServiceHost = productServiceHost;
		this.productBaseurl = productBaseurl;
		this.restTemplate = restTemplate;
	}

//	@HystrixCommand(
//			fallbackMethod = FALLBACK_METHOD,
//			commandProperties={
//				@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
//				@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
//				
//			}
//	)
	public ResponseEntity<List<Product>> getAllProducts() {
		ParameterizedTypeReference<List<Product>> parameterizedTypeReference = new ParameterizedTypeReference<List<Product>>() {};
		String apiUrl = new StringBuilder().append(productServiceHost).append(productBaseurl).toString();
		
		OAuth2AccessToken token = restTemplate.getAccessToken();
		LOG.debug("token: " + token);
		
		List<Product> products = restTemplate.exchange(apiUrl, HttpMethod.GET, null, parameterizedTypeReference).getBody();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
//		return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	public ResponseEntity<List<Product>> genericErrorMessage(Throwable exception) {
		LOG.error("An error has occurred. Callback method called", exception);
		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
	}
}
