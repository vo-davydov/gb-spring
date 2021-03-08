package com.davydov.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.davydov.entity.Product;
import com.davydov.repo.ProductRepo;
import com.davydov.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan("com.davydov")
public class AppConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }

  @Bean
  public SpringResourceTemplateResolver templateResolver() {
    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setPrefix("/WEB-INF/templates/");
    templateResolver.setSuffix(".html");
    return templateResolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    return templateEngine;
  }

  @Bean
  public ThymeleafViewResolver thymeleafViewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setCharacterEncoding("UTF-8");
    viewResolver.setTemplateEngine(templateEngine());
    return viewResolver;
  }

  @Bean
  public List<Product> products() {
    List<Product> products = new ArrayList<>();
    products.add(new Product(1L, "RTX 3080", new BigDecimal(100000)));
    products.add(new Product(2L, "RTX 3090", new BigDecimal(200000)));
    products.add(new Product(3L, "RTX 3070", new BigDecimal(80000)));
    products.add(new Product(4L, "RTX 3060ti", new BigDecimal(70000)));
    products.add(new Product(5L, "RTX 3080", new BigDecimal(50000)));
    return products;
  }

  @Bean
  public ProductRepo productRepo() {
    return new ProductRepo();
  }

  @Bean
  public ProductService productService(){
    return new ProductService();
  }
}
