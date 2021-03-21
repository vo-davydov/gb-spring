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
public class CustomerDaoImpl implements CustomerDao {

  private EntityManager entityManager;
  private ProductDaoImpl productDao;

  @Autowired
  public CustomerDaoImpl(EntityManager entityManager, ProductDaoImpl productDao) {
    this.entityManager = entityManager;
    this.productDao = productDao;
  }

  @Override
  public Optional<Customer> findById(Long id) {
    Customer customer = entityManager.createNamedQuery("Customer.findById", Customer.class)
      .setParameter("id", id)
      .getSingleResult();
    return customer != null ? Optional.of(customer) : Optional.empty();
  }

  @Override
  public Optional<List<Customer>> findAll() {
    List<Customer> customers = entityManager.createNamedQuery("Customer.findAll", Customer.class)
      .getResultList();
    return customers != null ? Optional.of(customers) : Optional.empty();
  }

  @Override
  public void deleteById(Long id) {
    entityManager.getTransaction().begin();
    Query query = entityManager.createQuery("DELETE FROM Customer c WHERE c.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
    entityManager.getTransaction().commit();
  }

  @Override
  public Optional<Customer> saveOrUpdate(Customer customer) {
    if (customer.getId() == null) {
      entityManager.getTransaction().begin();
      entityManager.persist(customer);
      entityManager.getTransaction().commit();
    } else {
      entityManager.getTransaction().begin();
      entityManager.merge(customer);
      entityManager.getTransaction().commit();
    }
    return Optional.of(customer);
  }

  @Override
  public Optional<List<Product>> getProducts(Long id) {
    entityManager.getTransaction().begin();
    Query query = entityManager.createQuery("SELECT c.products FROM Customer c  WHERE c.id = :id");
    query.setParameter("id", id);
    List<Product> products = query.getResultList();
    return products != null ? Optional.of(products) : Optional.empty();
  }

  @Override
  public boolean addProduct(Long idCustomer, Long idProduct) {

    Optional<Product> product = productDao.findById(idProduct);
    Optional<Customer> customer = findById(idCustomer);
    if (product.isPresent() && customer.isPresent()) {
      entityManager.getTransaction().begin();
      entityManager.persist(customer.get().getProducts().add(product.get()));
      entityManager.getTransaction().commit();
      return true;
    } else {
      return false;
    }
  }
}
