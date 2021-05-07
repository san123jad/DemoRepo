package com.microservice.stock.beans;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductStockBean {

	private Long id;
	private String productName;
	private BigDecimal productPrice;
	private String productAvailability;
	private double discountOffer;
	private String errorMessage;
	private int port;
		
}
