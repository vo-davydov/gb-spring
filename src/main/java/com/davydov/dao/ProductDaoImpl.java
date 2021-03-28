package com.davydov.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import com.davydov.entity.Customer;
import com.davydov.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {

  private EntityManager entityManager;

  @Autowired
  public ProductDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Optional<Product> findById(Long id) {
    Product product = entityManager.createNamedQuery("Product.findById", Product.class)
      .setParameter("id", id)
      .getSingleResult();
    return product != null ? Optional.of(product) : Optional.empty();
  }

  @Override
  public Optional<List<Product>> findAll() {
    List<Product> products = entityManager.createNamedQuery("Product.findAll", Product.class)
      .getResultList();
    return products != null ? Optional.of(products) : Optional.empty();
  }

  @Override
  public void deleteById(Long id) {
    entityManager.getTransaction().begin();
    Query query = entityManager.createQuery("DELETE FROM Product p WHERE p.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
    entityManager.getTransaction().commit();
  }

  @Override
  public Optional<Product> saveOrUpdate(Product product) {
    if (product.getId() == null) {
      entityManager.getTransaction().begin();
      entityManager.persist(product);
      entityManager.getTransaction().commit();
    } else {
      entityManager.getTransaction().begin();
      entityManager.merge(product);
      entityManager.getTransaction().commit();
    }
    return Optional.of(product);
  }

  @Override
  public Optional<List<Customer>> findAllCustomers(Long id) {
    Query query = entityManager.createQuery("SELECT p.customers FROM Product AS p WHERE p.id = :id");
    query.setParameter("id", id);
    List<Customer> customers = (List<Customer>) query.getResultList();
    return customers != null ? Optional.of(customers) : Optional.empty();
  }

}
