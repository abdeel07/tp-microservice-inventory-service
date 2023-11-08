package com.ms.inventoryservice;

import com.ms.inventoryservice.entities.Product;
import com.ms.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			productRepository.save(new Product(null, "aa", 100));
			productRepository.save(new Product(null, "bb", 99));
			productRepository.save(new Product(null, "cc", 150));
		};
	}
}
