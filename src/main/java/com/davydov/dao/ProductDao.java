package com.davydov.dao;

import java.util.List;
import java.util.Optional;
import com.davydov.entity.Customer;
import com.davydov.entity.Product;

public interface ProductDao {

  Optional<Product> findById(Long id);

  Optional<List<Product>> findAll();

  void deleteById(Long id);

  Optional<Product> saveOrUpdate(Product product);

  Optional<List<Customer>> findAllCustomers(Long id);
}
