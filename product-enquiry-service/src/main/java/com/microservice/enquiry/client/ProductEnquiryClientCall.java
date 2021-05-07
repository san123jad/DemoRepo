package com.microservice.enquiry.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "product-stock-service", url = "localhost:8800")
@FeignClient(name = "product-stock-service")
public interface ProductEnquiryClientCall {

	@GetMapping("/stock/check-product-stock/productName/{productName}/productAvailability/{productAvailability}")
	public ResponseEntity<?> checkProductStock(@PathVariable String productName,
			@PathVariable String productAvailability);

}
