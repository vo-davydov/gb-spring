package com.davydov.controller.rest;

import java.util.List;
import java.util.Optional;
import com.davydov.dto.CustomerDto;
import com.davydov.entity.Customer;
import com.davydov.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

  private CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
    if (customerService.getCustomer(id).isPresent()) {
      return new ResponseEntity<>(customerService.getCustomer(id).get(), HttpStatus.FOUND);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping
  public ResponseEntity<List<Customer>> getCustomers() {
    if (customerService.getCustomers().isPresent()) {
      return new ResponseEntity<>(customerService.getCustomers().get(), HttpStatus.FOUND);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
    try {
      customerService.deleteCustomer(id);
      return new ResponseEntity(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping
  @ApiOperation("Update or create customer")
  public ResponseEntity<Customer> saveCustomer(@RequestBody CustomerDto customerDto) {
    Optional<Customer> optionalCustomer = customerService.updateCustomer(customerDto);
    return optionalCustomer.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity(HttpStatus.BAD_REQUEST));
  }

}
