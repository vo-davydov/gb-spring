package com.davydov.service;

import java.util.Optional;
import com.davydov.dto.ProductDto;
import com.davydov.entity.Product;
import com.davydov.repository.ProductRepository;
import com.davydov.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

  private ProductRepository productRepository;

  @Autowired
  public ShopService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Optional<Product> getProduct(Long id) {
    return productRepository.findById(id);
  }

  public Optional<Product> addProduct(ProductDto productDto) {
    Product product = new Product();
    product.setPrice(productDto.getPrice());
    product.setTitle(productDto.getTitle());
    return Optional.of(productRepository.save(product));
  }

  public Boolean deleteProduct(Long id) {
    try {
      productRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Page<Product> getAllProduct(PageableUtil filter) {
    if (filter != null) {
      if ("ASC".equals(filter.getOrderBy().toUpperCase())) {
        return productRepository.findAll(PageRequest.of(filter.getPage(), filter.getSize(), Sort.by(filter.getSortBy()).ascending()));
      } else {
        return productRepository.findAll(PageRequest.of(filter.getPage(), filter.getSize(), Sort.by(filter.getSortBy()).descending()));
      }
    }
    return Page.empty();
  }

}
