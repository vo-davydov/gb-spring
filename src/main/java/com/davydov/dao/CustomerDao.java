package com.davydov.dao;

import java.util.List;
import java.util.Optional;
import com.davydov.entity.Customer;
import com.davydov.entity.Product;

public interface CustomerDao {

  Optional<Customer> findById(Long id);

  Optional<List<Customer>> findAll();

  void deleteById(Long id);

  Optional<Customer> saveOrUpdate(Customer customer);

  Optional<List<Product>> getProducts(Long id);

  boolean addProduct(Long idCustomer, Long idProduct);
}
