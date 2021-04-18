package com.davydov.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import com.davydov.dto.BasketDto;
import com.davydov.dto.ProductDto;
import com.davydov.entity.Product;
import com.davydov.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/basket")
@RestController
public class BasketController {

  private BasketService basketService;

  @Autowired
  public BasketController(BasketService basketService) {
    this.basketService = basketService;
  }

  @GetMapping
  public ResponseEntity<HashMap<Long, List<Product>>> getBaskets() {
    Optional<HashMap<Long, List<Product>>> optionalBasket = basketService.getBaskets();
    return optionalBasket.map(basket -> new ResponseEntity<>(basket, HttpStatus.FOUND))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("{id}")
  public ResponseEntity<List<ProductDto>> getBasket(@PathVariable Long id) {
    if (basketService.getBasket(id).isPresent()) {
      return new ResponseEntity<>(basketService.getBasket(id).get(), HttpStatus.FOUND);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping("{id}")
  public ResponseEntity putInBasket(@PathVariable Long id,
                                    @RequestBody BasketDto basketDto) {
    basketService.putInBasket(id, basketDto);
    return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
  }

  @DeleteMapping("{customerId}")
  public void deleteBasket(@PathVariable Long customerId) {
    basketService.deleteBasket(customerId);
  }
}
