package com.microservice.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteLocatorClass {
	@Bean
	public RouteLocator setRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
                .route(r -> r.path("/stock/**")
                        .uri("lb://product-stock-service/")
                        .id("product-stock-service"))
                .route(r -> r.path("/enquiry/**")
                        .uri("lb://product-enquiry-service/")
                        .id("product-enquiry-service"))
                .build();
				
	}

}
