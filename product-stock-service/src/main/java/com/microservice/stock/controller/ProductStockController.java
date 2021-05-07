package com.microservice.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.stock.beans.ProductStockBean;
import com.microservice.stock.entity.ProductStcokRepository;
import com.microservice.stock.entity.ProductStock;

@RestController
@RequestMapping("/stock")
public class ProductStockController {

	@Autowired
	Environment environment;
	@Autowired
	ProductStcokRepository productStcokRepository;
	@Autowired
	ProductStcokRepository repository;
	

	@PostMapping("/saveProduct")
	public ProductStock saveproduct(@RequestBody ProductStock productStock) {
		return productStcokRepository.save(productStock);
	}

	@RequestMapping(value="/check-product-stock/productName/{productName}/productAvailability/{productAvailability}",method=RequestMethod.GET)
	
	public ResponseEntity<?> checkProductStock(@PathVariable String productName,
			@PathVariable String productAvailability) {

		ProductStock productStock = repository.findByProductNameAndProductAvailability(productName,
				productAvailability);
		if(productStock==null) {
			ProductStockBean productStockBean = new ProductStockBean();
			productStockBean.setErrorMessage("Product not found.");
			return ResponseEntity.ok(productStockBean);
		}
		ProductStockBean productStockBean = new ProductStockBean(productStock.getId(), productStock.getProductName(),
				productStock.getProductPrice(), productStock.getProductAvailability(), productStock.getDiscountOffer(),
				"",
				Integer.parseInt(environment.getProperty("local.server.port")));

		return ResponseEntity.ok(productStockBean);
	}
	

}
