package com.davydov.controller;

import com.davydov.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PotatoController {

  @Autowired
  private ProductService productService;

  @RequestMapping(value = "/json/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String potatoJsonGet() throws JsonProcessingException {
    return productService.getAllProductJson();
  }

  @RequestMapping(value = "/xml/get", produces = MediaType.APPLICATION_XML_VALUE)
  public String potatoXmlGet() throws JsonProcessingException {
    return productService.getAllProductXml();
  }
}
