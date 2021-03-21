package com.davydov.config;

import javax.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  public EntityManager getEntityManager() {
    return new org.hibernate.cfg.Configuration()
      .configure("hibernate.xml")
      .buildSessionFactory()
      .createEntityManager();
  }

}
