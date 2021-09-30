package com.mercadolibre.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.mercadolibre.stock"})
@EntityScan("com.mercadolibre.stock.model.entity")
@SpringBootApplication
public class ControlStockApplication {
	public static void main(String[] args) {
		SpringApplication.run(ControlStockApplication.class, args);
	}
}