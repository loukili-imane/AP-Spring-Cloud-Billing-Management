package ma.imane.billingservice;

import ma.imane.billingservice.entities.Bill;
import ma.imane.billingservice.entities.ProductItem;
import ma.imane.billingservice.feign.CustomerRestClient;
import ma.imane.billingservice.feign.ProductItemRestClient;
import ma.imane.billingservice.model.Customer;
import ma.imane.billingservice.model.Product;
import ma.imane.billingservice.repositories.BillRepository;
import ma.imane.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClient customerRestClient,
							ProductItemRestClient productItemRestClient,
							RepositoryRestConfiguration repositoryRestConfiguration
	) {
		repositoryRestConfiguration.exposeIdsFor(Bill.class);
		return args -> {
			Customer customer = customerRestClient.getCustomerById(1L);
			Bill bill1 = billRepository.save(new Bill(null, new Date(), null, customer.getId(), null));
			PagedModel<Product> productPagedModel = productItemRestClient.pageProducts();
			productPagedModel.forEach(
					product -> {
						ProductItem productItem = new ProductItem();
						productItem.setPrice(product.getPrice());
						productItem.setQuantity(1 + new Random().nextInt(100));
						productItem.setProductID(product.getId());
						productItem.setBill(bill1);
						productItemRepository.save(productItem);
					}
			);
		};
	}
}
