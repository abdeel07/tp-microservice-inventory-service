package com.ms.inventoryservice.controllers;

import com.ms.inventoryservice.configurations.ApplicationPropertiesConfiguration;
import com.ms.inventoryservice.entities.Customer;
import com.ms.inventoryservice.entities.CustomerProduct;
import com.ms.inventoryservice.entities.Product;
import com.ms.inventoryservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private final ApplicationPropertiesConfiguration applicationPropertiesConfiguration;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestTemplate restTemplate;

    public CustomerController(ApplicationPropertiesConfiguration applicationPropertiesConfiguration){
        this.applicationPropertiesConfiguration = applicationPropertiesConfiguration;
    }

    @RequestMapping("/customers")
    public List<Customer> getCustomers(){
        List<Customer> customers = customerRepository.findAll();

        List<Customer> limitCustomer = customers.subList(
                0, applicationPropertiesConfiguration.getLimitCustomers()
        );

        return limitCustomer;
    }

    @RequestMapping("/getCustomerProducts/{id}")
    public CustomerProduct getCustomerProducts(@PathVariable("id") Long id){
        Optional<Customer> customer = customerRepository.findById(id);

        Product product = restTemplate.getForObject("http://localhost:8888/INVENTORY-SERVICE/products/" + customer.get().getProductId(), Product.class);

        return new CustomerProduct(customer.get().getId(), customer.get().getName(),
                customer.get().getEmail(), product.getName(), product.getPrice());
    }

    @RequestMapping("/customers/{id}")
    public Optional<Customer> getCustomer(@PathVariable("id") Long id){
        return customerRepository.findById(id);
    }
}
