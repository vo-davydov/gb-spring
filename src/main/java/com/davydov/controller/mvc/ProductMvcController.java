package com.davydov.controller.mvc;

import java.util.List;
import java.util.Optional;
import com.davydov.dto.ProductDto;
import com.davydov.entity.Product;
import com.davydov.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductMvcController {

  private ProductService productService;

  @Autowired
  public ProductMvcController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public String getProducts(Model model) {
    model.addAttribute("product", new ProductDto());
    Optional<List<Product>> productList = productService.getProducts();
    productList.ifPresent(products -> model.addAttribute("products", products));
    return "product";
  }

  @GetMapping("/{id}")
  public String deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return "redirect:/product";
  }

  @PostMapping("/add")
  public String saveProduct(ProductDto product) {
    productService.updateProduct(product);
    return "redirect:/product";
  }
}



