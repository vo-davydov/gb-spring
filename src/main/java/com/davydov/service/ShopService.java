package com.davydov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.davydov.dao.CustomerDao;
import com.davydov.dao.ProductDao;
import com.davydov.dto.CustomerDto;
import com.davydov.entity.Customer;
import com.davydov.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

  private CustomerDao customerDao;
  private ProductDao productDao;

  @Autowired
  public ShopService(CustomerDao customerDao, ProductDao productDao) {
    this.customerDao = customerDao;
    this.productDao = productDao;
  }

  public Optional<Product> getProduct(Long id) {
    return productDao.findById(id);
  }

  public Optional<Customer> addCustomer(CustomerDto customerDto) {
    Customer customer = new Customer();
    customer.setName(customerDto.getName());
    List<Product> products = new ArrayList<>();
    for (Long p : customerDto.getProducts()) {
      Product product = productDao.findById(p).get();
      products.add(product);
    }
    customer.setProducts(products);
    return customerDao.saveOrUpdate(customer);
  }

  public Optional<List<Customer>> getProductCustomers(Long id) {
    return productDao.findAllCustomers(id);
  }

  public Optional<List<Product>> getCustomerProducts(Long id) {
    return customerDao.getProducts(id);
  }

  public boolean addProduct(Long idCustomer, Long idProduct) {

    return customerDao.addProduct(idCustomer, idProduct);
  }
}
