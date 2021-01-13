package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@RestController
@EnableSwagger2
@SpringBootApplication
public class DemoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoServiceApplication.class, args);
	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2);
	}
	@GetMapping("/getres")
	public String getRes() {
		return "Hello World...!";
	}
}
