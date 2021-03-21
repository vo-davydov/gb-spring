package com.davydov.controller;

import java.util.List;
import java.util.Optional;
import com.davydov.dto.ShopForm;
import com.davydov.entity.Customer;
import com.davydov.entity.Product;
import com.davydov.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/shop")
@RestController
public class ShopController {
  private final ShopService shopService;

  @Autowired
  public ShopController(ShopService shopService) {
    this.shopService = shopService;
  }

  @GetMapping("/product/{id}")
  ResponseEntity<Product> getOneProduct(@PathVariable Long id) {
    Optional<Product> product = shopService.getProduct(id);
    return product.map(value -> new ResponseEntity<>(value, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/product/add")
  ResponseEntity<Boolean> addProduct(@RequestBody ShopForm shopForm) {

    if (shopService.addProduct(shopForm.getProduct().getId(), shopForm.getProduct().getId())) {
      return new ResponseEntity<>(true, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/customer/add")
  ResponseEntity<Customer> addCustomer(@RequestBody ShopForm shopForm) {
    Optional<Customer> customer = shopService.addCustomer(shopForm.getCustomer());
    return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(customer.get(), HttpStatus.NOT_FOUND));
  }

  @GetMapping("/customerByProduct/{id}")
  ResponseEntity<List<Customer>> getProductCustomers(@PathVariable Long id) {
    Optional<List<Customer>> customers = shopService.getProductCustomers(id);
    return customers.map(customerList -> new ResponseEntity<>(customerList, HttpStatus.FOUND))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/productByCustomer/{id}")
  ResponseEntity<List<Product>> getCustomerProducts(@PathVariable Long id) {
    Optional<List<Product>> products = shopService.getCustomerProducts(id);
    return products.map(customerList -> new ResponseEntity<>(customerList, HttpStatus.FOUND))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
