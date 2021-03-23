package com.davydov.controller;

import com.davydov.dto.ProductDto;
import com.davydov.entity.Product;
import com.davydov.service.ShopService;
import com.davydov.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/products")
@RestController
public class ShopController {
  private final ShopService shopService;

  @Autowired
  public ShopController(ShopService shopService) {
    this.shopService = shopService;
  }

  @GetMapping("/{id}")
  ResponseEntity<Product> getOneProduct(@PathVariable Long id) {
    return shopService.getProduct(id)
      .map(p -> new ResponseEntity<>(p, HttpStatus.FOUND))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/add")
  ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
    return shopService.addProduct(productDto)
      .map(p -> new ResponseEntity<>(p, HttpStatus.CREATED))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
  }

  @DeleteMapping("/delete/{id}")
  ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
    return shopService.deleteProduct(id)
      ? new ResponseEntity<>(HttpStatus.OK)
      : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @GetMapping("")
  ResponseEntity<Page> getAllProduct(@RequestParam(defaultValue = "id") String sortBy,
                                     @RequestParam(defaultValue = "DESC") String orderBy,
                                     @RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer size
  ) {
    return new ResponseEntity<>(shopService.getAllProduct(new PageableUtil(sortBy, orderBy, page, size)), HttpStatus.OK);
  }
}
