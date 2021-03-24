package com.davydov.controller;

import java.util.List;
import com.davydov.entity.Product;
import com.davydov.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller()
public class ProductController {

  private ShopRepository repository;

  @Autowired
  public ProductController(ShopRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public String index(Model model) {

    return "index";
  }

  @GetMapping("/product")
  public String getProducts(Model model) {
    List<Product> productList = repository.findAll();
    model.addAttribute("products", productList);
    return "product";
  }

  @GetMapping("/{id}")
  public String deleteProduct(@PathVariable Long id) {
    repository.deleteById(id);
    return "redirect:product";
  }

}
