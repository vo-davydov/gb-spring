package com.davydov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "product")
@NamedQueries({
  @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product AS p WHERE p.id = :id"),
  @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product AS p")
)
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;
  @Column(name = "title", length = 128)
  private String title;
  @Column(name = "price")
  private int price;

  public Product() {

  }

  public Product(String title, int price) {
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

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
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

    if (price != product.price) {
      return false;
    }
    if (!id.equals(product.id)) {
      return false;
    }
    return title != null ? title.equals(product.title) : product.title == null;
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + price;
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
