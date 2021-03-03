package com.davydov.repository;

import com.davydov.entity.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class ProductRepository{
    private List<Product> products;

    public ProductRepository(){
        products = new ArrayList<>();
        products.add(new Product(1L, "RTX 3080", new BigDecimal(100000)));
        products.add(new Product(2L, "RTX 3090", new BigDecimal(200000)));
        products.add(new Product(3L, "RTX 3070", new BigDecimal(80000)));
        products.add(new Product(4L, "RTX 3060ti", new BigDecimal(70000)));
        products.add(new Product(5L, "RTX 3080", new BigDecimal(50000)));
    }

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
