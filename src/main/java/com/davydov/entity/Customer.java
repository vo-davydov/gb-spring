package com.davydov.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "customer")
@NamedQueries({
  @NamedQuery(name = "Customer.findById", query = "SELECT p FROM Customer AS p WHERE p.id = :id"),
  @NamedQuery(name = "Customer.findAll", query = "SELECT p FROM Customer AS p")
})
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;
  @Column(name = "name", length = 128)
  private String name;


  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "customer_product",
    joinColumns = { @JoinColumn(name = "customer_id") },
    inverseJoinColumns = { @JoinColumn(name = "product_id") }
  )
  List<Product> products;

  public Customer() {

  }

  public Customer(String name) {
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
