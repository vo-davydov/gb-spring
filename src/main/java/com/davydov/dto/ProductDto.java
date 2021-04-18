package com.davydov.dto;

import java.util.List;

public class ProductDto {
  private Long id;
  private String title;
  private Double price;
  private List<Long> idCustomers;

  public ProductDto() {

  }

  public ProductDto(String title, Double price) {
    this.title = title;
    this.price = price;
  }

  public ProductDto(Long id, String title, Double price) {
    this.id = id;
    this.title = title;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
