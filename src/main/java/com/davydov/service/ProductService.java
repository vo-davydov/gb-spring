package com.davydov.service;

import java.util.List;
import java.util.Optional;
import com.davydov.dto.ProductDto;
import com.davydov.entity.Product;
import com.davydov.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Optional<List<Product>> getProducts() {
    return Optional.of(productRepository.findAll());
  }

  public Optional<Product> getProduct(Long id) {
    return productRepository.findById(id);
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

  public Optional<Product> updateProduct(ProductDto productDto) {
    if (productDto == null) {
      throw new RuntimeException("Product cannot be null");
    }
    if (productDto.getId() != null) {
      Product product = productRepository.findById(productDto.getId()).get();
      product.setPrice(productDto.getPrice());
      product.setTitle(productDto.getTitle());
      productRepository.save(product);
      return Optional.of(product);
    }
    return Optional.of(productRepository.save(new Product(productDto.getTitle(), productDto.getPrice())));
  }
}
