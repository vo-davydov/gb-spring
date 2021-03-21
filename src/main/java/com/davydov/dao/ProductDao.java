package com.davydov.dao;

import java.util.List;
import com.davydov.entity.Product;

public interface ProductDao {

  Product findById(Long id);

  List<Product> findAll();

  void deleteById(Long id);

  Product saveOrUpdate(Product product);
}
