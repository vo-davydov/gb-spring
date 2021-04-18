package com.davydov.controller.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.davydov.dto.ProductDto;
import com.davydov.entity.Product;
import com.davydov.service.BasketService;
import com.davydov.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/basket")
public class BasketMvcController {

  private ProductService productService;
  private BasketService basketService;

  @Autowired
  public BasketMvcController(ProductService productService,
                             BasketService basketService
  ) {
    this.productService = productService;
    this.basketService = basketService;
  }

  @GetMapping
  public String getBasket(Model model,
                          @RequestParam String id
  ) {
    Optional<List<Product>> productList = productService.getProducts();
    productList.ifPresent(products -> model.addAttribute("products", products));
    model.addAttribute("products");
    long customerId;
    if (id != null) {
      try {
        customerId = Long.parseLong(id);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      Optional<List<ProductDto>> basket = basketService.getBasket(customerId);
      basket.ifPresent(productDtos -> model.addAttribute("basket", productDtos));

    } else {
      model.addAttribute("basket", new ArrayList<ProductDto>());
    }

    return "basket";
  }

  @GetMapping("/delete")
  public String deleteProductBasket(@PathVariable Long id,
                                    @RequestParam String customerId
  ) {
    basketService.deleteBasket(Long.parseLong(customerId));
    return "redirect:/basket";
  }

  @PostMapping("/add")
  public String addToBasket(@RequestParam String productId,
                            @RequestParam String customerId
  ) {
    Optional<Product> optionalProduct = productService.getProduct(Long.parseLong(productId));

    return "redirect:/basket";
  }
}
