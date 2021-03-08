package com.davydov.repo;

import java.util.List;
import java.util.function.Predicate;
import com.davydov.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductRepo {

  @Autowired
  private List<Product> products;

  public List<Product> getAll() {
    return this.products;
  }

  public Product getById(Long id) {
    return products.stream()
      .filter(product -> product.getId().equals(id))
      .findFirst()
      .orElse(null);
  }

  public void deleteById(Long id){
    Predicate<Product> isQualified = product -> product.getId().equals(id);
    products.removeIf(isQualified);
  }

  public void add(Long id){
    products.add(new Product(id));
  }
}
