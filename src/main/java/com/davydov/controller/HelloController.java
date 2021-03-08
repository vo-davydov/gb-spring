package com.davydov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
  @GetMapping
  public String index(Model model) {
    model.addAttribute("message", "Hello World!");
    model.addAttribute("second_message", "Hello second WoRlD!1111");
    return "index";
  }
}
