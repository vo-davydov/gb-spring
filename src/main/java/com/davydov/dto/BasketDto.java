package com.davydov.dto;

import java.util.List;

public class BasketDto {
  private List<Long> idProducts;

  public BasketDto() {
  }

  public BasketDto(List<Long> idProducts) {
    this.idProducts = idProducts;
  }

  public List<Long> getIdProducts() {
    return idProducts;
  }

  public void setIdProducts(List<Long> idProducts) {
    this.idProducts = idProducts;
  }
}
