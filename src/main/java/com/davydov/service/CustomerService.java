package com.davydov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.davydov.dto.CustomerDto;
import com.davydov.entity.Customer;
import com.davydov.entity.Product;
import com.davydov.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private CustomerRepository customerRepository;
  private ProductService productService;

  @Autowired
  public CustomerService(CustomerRepository customerRepository, ProductService productService) {
    this.customerRepository = customerRepository;
    this.productService = productService;
  }

  public Optional<List<Customer>> getCustomers() {
    return Optional.of(customerRepository.findAll());
  }

  public Optional<Customer> getCustomer(Long id) {
    return customerRepository.findById(id);
  }

  public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);
  }

  public Optional<Customer> updateCustomer(CustomerDto customerDto) {
    if (customerDto == null) {
      throw new RuntimeException("Customer cannot be null");
    }
    List<Product> products = new ArrayList<>();
    if (customerDto.getIdProducts() != null) {
      products = getProductsFromCustomerDto(customerDto);
    }
    if (customerDto.getId() != null) {
      Optional<Customer> optionalCustomer = customerRepository.findById(customerDto.getId());
      if (optionalCustomer.isPresent()) {
        Customer customer = optionalCustomer.get();
        customer.setName(customerDto.getName());
        customer.setProducts(products);
        customerRepository.save(customer);
        return Optional.of(customer);
      }
    }
    return Optional.of(customerRepository.save(new Customer(customerDto.getName(), products)));
  }

  private List<Product> getProductsFromCustomerDto(CustomerDto customerDto) {
    List<Product> products = new ArrayList<>();
    customerDto.getIdProducts().forEach(p -> {
      Optional<Product> productOptional = productService.getProduct(p);
      productOptional.ifPresent(products::add);
    });
    return products;
  }

  public void deleteProducts(Long id) {
    Optional<Customer> customer = getCustomer(id);
    if (customer.isPresent()) {
      Customer customer1 = customer.get();
      customer1.setProducts(null);
      customerRepository.save(customer1);
    }
  }

  public void saveCustomer(Customer customer) {
    customerRepository.save(customer);
  }
}
