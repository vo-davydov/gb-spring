package com.davydov.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import com.davydov.entity.Product;
import org.hibernate.cfg.Configuration;

public class ProductDaoImpl implements ProductDao {

  private EntityManager entityManager;

  public ProductDaoImpl() {
    entityManager = new Configuration()
      .configure("hibernate.xml")
      .buildSessionFactory()
      .createEntityManager();
  }

  @Override
  public Product findById(Long id) {
    return entityManager.createNamedQuery("Product.findById", Product.class)
      .setParameter("id", id)
      .getSingleResult();
  }

  @Override
  public List<Product> findAll() {
    return entityManager.createNamedQuery("Product.findAll", Product.class)
      .getResultList();
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
  public Product saveOrUpdate(Product product) {
    if (product.getId() == null) {
      entityManager.getTransaction().begin();
      entityManager.persist(product);
      entityManager.getTransaction().commit();
    } else {
      entityManager.getTransaction().begin();
      entityManager.merge(product);
      entityManager.getTransaction().commit();
    }
    return product;
  }

  public void close() {
    entityManager.close();
  }
}
