package com.davydov.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "customer")
public class Customer {
  @ApiModelProperty(
    value = "ID customer",
    name = "ID",
    dataType = "Long",
    example = "1")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @ApiModelProperty(
    value = "first name of the user",
    name = "name",
    dataType = "String",
    example = "Jon")
  @Column(name = "name", length = 128)
  private String name;

  @ApiModelProperty(
    value = "products Array id",
    name = "id",
    dataType = "Long[]",
    example = "[1,2]")
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "customer_product",
    joinColumns = {@JoinColumn(name = "customer_id")},
    inverseJoinColumns = {@JoinColumn(name = "product_id")}
  )
  private List<Product> products;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  public Customer() {

  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Customer(String name) {
    this.name = name;
  }

  public Customer(String name, List<Product> products) {
    this.name = name;
    this.products = products;
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

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Customer customer = (Customer) o;

    if (!id.equals(customer.id)) {
      return false;
    }
    if (name != null ? !name.equals(customer.name) : customer.name != null) {
      return false;
    }
    return products != null ? products.equals(customer.products) : customer.products == null;
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (products != null ? products.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Customer{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", products=" + products +
      '}';
  }
}
