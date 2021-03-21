package com.davydov.dto;

public class ShopForm {
  private CustomerDto customer;
  private ProductDto product;

  public ShopForm() {

  }

  public CustomerDto getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerDto customer) {
    this.customer = customer;
  }

  public ProductDto getProduct() {
    return product;
  }

  public void setProduct(ProductDto product) {
    this.product = product;
  }
}
