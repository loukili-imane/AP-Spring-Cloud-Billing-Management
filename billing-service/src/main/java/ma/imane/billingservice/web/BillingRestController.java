package ma.imane.billingservice.web;

import ma.imane.billingservice.entities.Bill;
import ma.imane.billingservice.feign.CustomerRestClient;
import ma.imane.billingservice.feign.ProductItemRestClient;
import ma.imane.billingservice.repositories.BillRepository;
import ma.imane.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerID()));
        bill.getProductItems().forEach(pi -> {
            //pi.setProduct(productItemRestClient.getProductById(pi.getProductId()));
            pi.setProductName(productItemRestClient.getProductById(pi.getProductID()).getName());
        });
        return bill;
    }
}