package ma.imane.inventoryservice;

import ma.imane.inventoryservice.entities.Product;
import ma.imane.inventoryservice.repository.ProductRepository;
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
			productRepository.save(new Product(null,"Ordinateur",5000,10));
			productRepository.save(new Product(null,"souris",70,15));
			productRepository.save(new Product(null,"clavier",80,20));
			productRepository.findAll().forEach(product -> {
				System.out.println(product.toString());
			});


		};
	}
}
