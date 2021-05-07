package com.microservice.enquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.enquiry.beans.ProductEnquiryBean;
import com.microservice.enquiry.client.ProductEnquiryClientCall;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/enquiry")
public class ProductEnquiryController {
	@Autowired
	ProductEnquiryClientCall productEnquiryClientCall;
	private static final String PRODUCT_ENQUIRY_SERVICE="product-enquiry-service";
	
	
	@GetMapping("/check-product-enquiry/productName/{productName}/productAvailability/{productAvailability}/unit/{unit}")
	@CircuitBreaker(name=PRODUCT_ENQUIRY_SERVICE,fallbackMethod = "productFallback")
	public ResponseEntity<?> checkProductStock(@PathVariable String productName,
			@PathVariable String productAvailability, @PathVariable int unit) {
//		ProductEnquiryBean productEnquiryBean = productEnquiryClientCall.checkProductStock(productName,
//				productAvailability).getBody();
		ProductEnquiryBean productEnquiryBean =(ProductEnquiryBean) productEnquiryClientCall.checkProductStock(productName,
					productAvailability).getBody();
		if (productEnquiryBean.getErrorMessage() != "")
			return ResponseEntity.ok(productEnquiryBean);
		double total_price = productEnquiryBean.getProductPrice().doubleValue() * unit;
		double totla_price_with_discount = total_price - productEnquiryBean.getDiscountOffer() / 100 * total_price;
		return ResponseEntity.ok(new ProductEnquiryBean(productEnquiryBean.getId(), productName, productEnquiryBean.getProductPrice(),
				productEnquiryBean.getProductAvailability(), productEnquiryBean.getDiscountOffer(),
				totla_price_with_discount, total_price, productEnquiryBean.getErrorMessage(),
				productEnquiryBean.getPort()));
	}
	public ResponseEntity<?> productFallback(Exception e){
		return ResponseEntity.ok("Product service is down."); 
	}
}
