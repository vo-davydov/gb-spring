package com.davydov.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import com.davydov.dto.BasketDto;
import com.davydov.dto.ProductDto;
import com.davydov.entity.Customer;
import com.davydov.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {
  private ProductService productService;
  private CustomerService customerService;

  @Autowired
  public BasketService(CustomerService customerService, ProductService productService) {
    this.customerService = customerService;
    this.productService = productService;
  }

  public Optional<HashMap<Long, List<Product>>> getBaskets() {
    Optional<List<Customer>> optionalCustomer = customerService.getCustomers();
    HashMap<Long, List<Product>> basketResult = new HashMap<>();
    if (optionalCustomer.isPresent()) {
      optionalCustomer.get().forEach(c -> basketResult.put(c.getId(), c.getProducts()));
      return Optional.of(basketResult);
    }

    return Optional.empty();
  }

  public Optional<List<ProductDto>> getBasket(Long id) {
    Optional<Customer> customerOptional = customerService.getCustomer(id);
    if (customerOptional.isPresent()) {
      List<ProductDto> productDtos = new ArrayList<>();
      customerOptional.get().getProducts().forEach(p -> productDtos.add(new ProductDto(p.getTitle(), p.getPrice())));
      return Optional.of(productDtos);
    }
    return Optional.empty();
  }

  public void putInBasket(Long id, BasketDto basketDto) {
    List<Product> newProducts = new ArrayList<>();
    if (basketDto != null) {
      basketDto.getIdProducts().forEach(p -> {
        Optional<Product> productOptional = productService.getProduct(p);
        if (productOptional.isPresent()) {
          newProducts.add(productOptional.get());
        }
      });
    }

    Optional<Customer> optionalCustomer = customerService.getCustomer(id);
    if (optionalCustomer.isPresent()) {
      Customer customer = optionalCustomer.get();
      customer.getProducts().addAll(newProducts);
      customerService.saveCustomer(customer);
    }
  }

  public void deleteBasket(Long customerId) {
    customerService.deleteProducts(customerId);
  }

}
