package com.davydov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;
  @Column(name = "title", length = 128)
  private String title;
  @Column(name = "price")
  private Double price;

  public Product() {

  }

  public Product(String title, Double price) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Product product = (Product) o;

    if (!id.equals(product.id)) {
      return false;
    }
    if (title != null ? !title.equals(product.title) : product.title != null) {
      return false;
    }
    return price != null ? price.equals(product.price) : product.price == null;
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (price != null ? price.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Product{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", price=" + price +
      '}';
  }
}
