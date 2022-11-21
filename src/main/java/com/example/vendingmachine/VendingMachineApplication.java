package com.example.vendingmachine;

import com.example.vendingmachine.enumeration.Coin;
import com.example.vendingmachine.model.Product;
import com.example.vendingmachine.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.vendingmachine.enumeration.ProductType.*;

@SpringBootApplication
public class VendingMachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendingMachineApplication.class, args);
	}

	@Bean
	CommandLineRunner run(InventoryRepository inventoryRepository){
		return args ->{
			inventoryRepository.add(new Product(0L, "Coca", COCA_COLA, Coin.ONE_LV));
			inventoryRepository.add(new Product(1L, "Sprite", SPRITE, Coin.TWO_LV));
			inventoryRepository.add(new Product(2L, "Coffee", COFFEE, Coin.FIFTY_ST));
		};
	}
}
