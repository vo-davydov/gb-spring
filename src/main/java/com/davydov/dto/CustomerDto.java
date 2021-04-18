package com.davydov.dto;

import java.util.List;

public class CustomerDto {
  private Long id;
  private String name;
  private List<Long> idProducts;

  public CustomerDto() {
  }

  public CustomerDto(String name) {
    this.name = name;
  }

  public CustomerDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Long> getIdProducts() {
    return idProducts;
  }

  public void setIdProducts(List<Long> idProducts) {
    this.idProducts = idProducts;
  }
}
