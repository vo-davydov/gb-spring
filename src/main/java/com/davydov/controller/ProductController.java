package com.davydov.controller;

import java.util.List;
import com.davydov.dto.ProductDto;
import com.davydov.entity.Product;
import com.davydov.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/product")
@RestController
public class ProductController {

  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("{id}")
  public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    if (productService.getProduct(id).isPresent()) {
      return new ResponseEntity<>(productService.getProduct(id).get(), HttpStatus.FOUND);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping
  public ResponseEntity<List<Product>> getProducts() {
    if (productService.getProducts().isPresent()) {
      return new ResponseEntity<>(productService.getProducts().get(), HttpStatus.FOUND);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
    try {
      productService.deleteProduct(id);
      return new ResponseEntity(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping
  public ResponseEntity<Product> saveProduct(@RequestBody ProductDto productDto) throws Exception {
    Product product = productService.updateProduct(productDto).get();
    if (product != null) {
      return new ResponseEntity<>(product, HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }

  @PutMapping
  @ApiOperation("Update product")
  public ResponseEntity<Product> updateProduct(@RequestBody ProductDto productDto) throws Exception {
    Product product = productService.updateProduct(productDto).get();
    if (product != null) {
      return new ResponseEntity<>(product, HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }

}
